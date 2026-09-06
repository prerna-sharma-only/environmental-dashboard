import com.sun.net.httpserver.HttpServer;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.io.File;
import java.nio.file.Files;
import java.net.InetSocketAddress;
import java.util.Random;

public class EnvServer {
    public static void main(String[] args) throws IOException {
        // Render gives PORT as environment variable
        int port = Integer.parseInt(System.getenv().getOrDefault("PORT", "10000"));

        HttpServer server = HttpServer.create(new InetSocketAddress(port), 0);
        // Serve frontend files
        server.createContext("/", new FileHandler());

        // API endpoint with random data
        server.createContext("/data", new DataHandler());

        server.setExecutor(null);
        System.out.println("✅ Server started on http://localhost:3000");
        server.start();
    }

    // Serve static files (index.html, style.css, script.js)
    static class FileHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String path = exchange.getRequestURI().getPath();
            if (path.equals("/"))
                path = "/frontend/index.html";

            File file = new File("." + path);
            if (file.exists() && !file.isDirectory()) {
                byte[] bytes = Files.readAllBytes(file.toPath());
                String type = "text/html";
                if (path.endsWith(".css"))
                    type = "text/css";
                if (path.endsWith(".js"))
                    type = "application/javascript";

                exchange.getResponseHeaders().add("Content-Type", type);
                exchange.sendResponseHeaders(200, bytes.length);
                OutputStream os = exchange.getResponseBody();
                os.write(bytes);
                os.close();
            } else {
                String msg = "File Not Found";
                exchange.sendResponseHeaders(404, msg.length());
                OutputStream os = exchange.getResponseBody();
                os.write(msg.getBytes());
                os.close();
            }
        }
    }

    // API Handler with random sample data
    // step1)Class Declaration
    static class DataHandler implements HttpHandler {
        // step 2) City Names & Random Number Generator
        private final String[] cities = { "Bareilly", "Delhi", "Varanasi", "Pune" };
        private final Random random = new Random();

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            StringBuilder json = new StringBuilder("{");

            for (int i = 0; i < cities.length; i++) {

                String city = cities[i];
                json.append("\"").append(city).append("\": {");

                json.append("\"weather\": {\"hourly\": {");

                json.append("\"temperature_2m\": ").append(randomArray(24, 20, 40)).append(",");
                // "temperature_2m": [22, 25, 27, 30, 29, 26, ...]
                json.append("\"humidity_2m\": ").append(randomArray(24, 30, 90)).append(",");
                json.append("\"windspeed_10m\": ").append(randomArray(24, 0, 20)).append(",");
                json.append("\"precipitation\": ").append(randomArray(24, 0, 15)).append(",");
                json.append("\"uv_index\": ").append(randomArray(24, 0, 12));
                json.append("}},");

                json.append("\"air\": {\"hourly\": {");
                json.append("\"aqi\": ").append(randomArray(24, 50, 200)).append(",");
                json.append("\"pm2_5\": ").append(randomArray(24, 10, 150));
                json.append("}}}");

                if (i < cities.length - 1)
                    json.append(",");
            }
            json.append("}");// JSON object complete ho gaya — ab poora data ready hai.

            //// Response type aur permission
            exchange.getResponseHeaders().add("Content-Type", "application/json");

            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");

            exchange.sendResponseHeaders(200, json.toString().getBytes().length);
            OutputStream os = exchange.getResponseBody();
            os.write(json.toString().getBytes());
            os.close();

        }

        private String randomArray(int size, int min, int max) {
            StringBuilder sb = new StringBuilder("[");
            for (int i = 0; i < size; i++) {
                int val = random.nextInt(max - min + 1) + min;
                sb.append(val);
                if (i < size - 1)
                    sb.append(",");
            }
            sb.append("]");
            return sb.toString();
        }
    }
}

```java
// You are creating a list of cities whose data you want to generate.
// Random random = new Random() means:
// "I will generate random numbers later."
//
// Instead of getting real temperature data from an API,
// the program generates random values such as 28°C, 30°C, 33°C, etc.
// These values are only used for testing.

// Step 3: When the website requests data...
//
// The handle() method runs automatically whenever the frontend sends
// a request to http://localhost:3000/data.
//
// Think of it like:
// "When someone knocks on the /data door, this function opens the door
// and sends them some data."

// Starting the JSON response.
//
// JSON is a data format, similar to a dictionary or a collection
// of key-value pairs.
//
// This line starts an empty JSON object.
// It begins with { because the object will contain multiple sections.

// This loop runs 4 times — once for each city:
// Bareilly, Delhi, Varanasi, and Pune.
//
// For each city, separate random data is generated.

// The JSON structure contains a section for each city, for example:
// "Bareilly": { ...data... }
//
// In this way, every city gets its own section.

// This part generates weather data for each city:
//
// temperature_2m → 24 hourly temperature values,
// with random numbers between 20°C and 40°C.

// Air-quality data is also generated for each city.
//
// This part creates air-quality information.
//
// aqi and pm2_5 each contain 24 hourly values.
//
// Example:
// AQI → [75, 120, 150, ...]
// PM2.5 → [40, 60, 80, ...]

// A comma is added after each city in the JSON object.
// However, the last city should not have a trailing comma,
// because that would make the JSON format invalid.

// Example JSON structure:
//
// {
//   "Bareilly": {
//     "weather": {
//       "hourly": {
//         "temperature_2m": [22, 25, 27, ...],
//         "humidity_2m": [60, 65, 70, ...],
//         "windspeed_10m": [3, 5, 7, ...],
//         "precipitation": [0, 1, 2, ...],
//         "uv_index": [5, 6, 8, ...]
//       }
//     },
//     "air": {
//       "hourly": {
//         "aqi": [100, 150, 130, ...],
//         "pm2_5": [50, 70, 65, ...]
//       }
//     }
//   },
//   "Delhi": { ... },
//   "Varanasi": { ... },
//   "Pune": { ... }
// }

// The exchange object handles communication between the server and
// the browser.
//
// When the browser sends a request to /data, the server receives
// an exchange object containing:
// → information about the request
// → a channel through which the server can send the response.

// json.toString() converts the JSON object into a string.
//
// .getBytes() converts that string into bytes,
// because data is transmitted over the network as bytes.
//
// .length tells us how many bytes the data contains.
//
// For example:
// If the JSON is {"city":"Bareilly"},
// its byte length will be around 20 bytes.
// This length is then provided to the browser.

// This line tells the browser:
// "I am sending you a 200 (OK) response,
// and the response contains this many bytes."

// In Java, a stream represents a flow of data in one direction.
//
// An output stream means:
// server → browser.

// This is the final step where the data is actually sent
// from the server to the browser.
//
// 200 → HTTP success status code (OK).
//
// os.write() → sends the JSON data to the browser.
//
// os.close() → closes the connection after the data has been sent.
```
