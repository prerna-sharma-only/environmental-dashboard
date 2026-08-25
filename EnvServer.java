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

// // You are creating a list of cities whose data you want to generate.
// Random random = new Random() means: “I will generate random numbers later.”
// Instead of getting real temperatures from an API,
// it will make up values like 28°C, 30°C, 33°C, etc. randomly — just for
// testing.

// step 3)When the website asks for data…
// The handle() method runs automatically whenever your frontend requests
// http://localhost:3000/data.
// Think of it like:
// “When someone knocks on /data, this function opens the door and gives them
// some data.”
// Starting the JSON message
// JSON ek data format hota hai (jaise dictionary).
// Ye line ek empty JSON object start kar rahi hai.
// It begins with a { — just like writing a sentence that will have many parts.
// Ye loop 4 baar chalega — Bareilly, Delhi, Varanasi, Pune ke liye.Har city ke
// liye alag random data banega.
// JSON me likh rahe hain: "Bareilly": { ...data... }Is tarah har city ke liye
// alag section banega.
// Ye part har city ke liye weather data banata hai:
// temperature_2m → 24 hourly temperatures (20°C se 40°C ke beech random
// numbers)
// air quality data-hr city ke liy
// Ye part air data ke liye hai.
// aqi aur pm2_5 dono ke 24 hourly values banata hai.
// Example:
// AQI → [75, 120, 150, ...]
// PM2.5 → [40, 60, 80, ...]
// Har city ke baad ek comma lagta hai JSON me —
// but last city ke baad nahi lagta, warna format galat ho jaata h.

// {
// "Bareilly": {
// "weather": {
// "hourly": {
// "temperature_2m": [22, 25, 27, ...],
// "humidity_2m": [60, 65, 70, ...],
// "windspeed_10m": [3, 5, 7, ...],
// "precipitation": [0, 1, 2, ...],
// "uv_index": [5, 6, 8, ...]
// }
// },
// "air": {
// "hourly": {
// "aqi": [100, 150, 130, ...],
// "pm2_5": [50, 70, 65, ...]
// }
// }
// },
// "Delhi": { ... },
// "Varanasi": { ... },
// "Pune": { ... }
// }
// line 3 lines
// exchange ek object hai jo server aur browser ke beech communication handle
// karta hai.
// Jab browser /data request bhejta hai, to server ko ye exchange object milta
// hai jisme
// → request info hoti hai
// → aur response bhejne ke liye channel milta hai.
// json.toString() → JSON ko string banata hai.
// .getBytes() → us string ko bytes me convert karta hai (kyunki data internet
// par bytes me jaata hai).
// .length → kitne bytes hai ye count karta hai.
// Agar JSON = {"city":"Bareilly"}
// to uske bytes = 20 bytes ke aaspaas honge, aur yahi length browser ko batayi
// jaati hai.
// Ye line browser ko batati hai:
// “Main tumhe 200 (OK) response bhej raha hoon, aur itne bytes ka data aayega"
// Java me, stream ka matlab hai ek direction me data flow —
// “output stream” → matlab server → browser.
//// Ye final step hai — data actually browser tak pahuchta hai.
// 200 → success code (HTTP OK).
// os.write() → ye line JSON data browser ko send karti hai.
// os.close() → connection close karta hai.