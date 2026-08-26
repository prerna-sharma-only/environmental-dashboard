# Environmental Dashboard

A Java-based environmental monitoring dashboard that displays weather and air-quality data for multiple cities through a browser-based interface.

## Project Overview

| Category      | Details                              |
| ------------- | ------------------------------------ |
| Project       | Environmental Dashboard              |
| Backend       | Java                                 |
| Frontend      | HTML, CSS, JavaScript                |
| Visualization | Chart.js                             |
| Server        | Java `HttpServer`                    |
| Data          | Generated sample environmental data  |
| Cities        | Bareilly, Delhi, Varanasi, Pune      |
| Refresh       | Manual and automatic every 5 minutes |

## Features

| Feature         | Description                                 |
| --------------- | ------------------------------------------- |
| City Monitoring | Displays environmental data for four cities |
| Rainfall        | Shows hourly precipitation data             |
| UV Index        | Displays UV index values                    |
| AQI             | Shows Air Quality Index                     |
| PM2.5           | Displays particulate matter levels          |
| Temperature     | Hourly temperature visualization            |
| Humidity        | Hourly humidity visualization               |
| Wind Speed      | Hourly wind-speed visualization             |
| Charts          | Interactive line charts using Chart.js      |
| Auto Refresh    | Updates data automatically every 5 minutes  |
| Manual Refresh  | Allows users to refresh data instantly      |

## Technologies Used

| Technology        | Purpose                            |
| ----------------- | ---------------------------------- |
| Java              | Backend server and data generation |
| HTML5             | Dashboard structure                |
| CSS3              | Styling and responsive layout      |
| JavaScript        | Data fetching and dashboard logic  |
| Chart.js          | Data visualization                 |
| Java `HttpServer` | Serves frontend and API endpoints  |

## Project Structure

| File / Folder         | Purpose                       |
| --------------------- | ----------------------------- |
| `frontend/index.html` | Dashboard structure           |
| `frontend/script.js`  | Data fetching and chart logic |
| `frontend/style.css`  | Dashboard styling             |
| `EnvServer.java`      | Java HTTP server and API      |
| `README.md`           | Project documentation         |
| `.gitignore`          | Git ignored files             |

## Dashboard Metrics

| Metric      | Description              |
| ----------- | ------------------------ |
| Temperature | Hourly temperature       |
| Humidity    | Hourly relative humidity |
| Wind Speed  | Hourly wind speed        |
| Rainfall    | Hourly precipitation     |
| UV Index    | Hourly UV index          |
| AQI         | Air Quality Index        |
| PM2.5       | Fine particulate matter  |

## How to Run

### Requirements

| Requirement | Version            |
| ----------- | ------------------ |
| Java JDK    | 8 or later         |
| Web Browser | Any modern browser |

### Installation

Clone the repository:

```bash
git clone <your-repository-url>
cd java-environmental-dashboard
```

Compile the Java server:

```bash
javac EnvServer.java
```

Start the server:

```bash
java EnvServer
```

Open the dashboard in your browser:

```text
http://localhost:10000/
```

## API

The Java server provides the following endpoint:

| Method | Endpoint | Description                                         |
| ------ | -------- | --------------------------------------------------- |
| `GET`  | `/data`  | Returns environmental data for all supported cities |

Example:

```text
http://localhost:10000/data
```

## How It Works

| Step | Process                                       |
| ---- | --------------------------------------------- |
| 1    | Java starts an HTTP server on port `10000`    |
| 2    | The server serves the frontend files          |
| 3    | Java generates environmental sample data      |
| 4    | The `/data` endpoint returns the data as JSON |
| 5    | JavaScript fetches the JSON data              |
| 6    | Dashboard cards display the latest values     |
| 7    | Chart.js visualizes the hourly data           |
| 8    | Data refreshes automatically every 5 minutes  |

## Data Source

The current version uses randomly generated sample data through Java's `Random` class.

The project is designed so that a live weather and air-quality API can be integrated in the future.

## Future Improvements

| Planned Improvement  | Description                                      |
| -------------------- | ------------------------------------------------ |
| Live API Integration | Replace sample data with real environmental data |
| Historical Data      | Store and display previous measurements          |
| Location Selection   | Allow users to select cities dynamically         |
| Date Filtering       | Add hourly and daily date filters                |
| Deployment           | Deploy the application online                    |
| Database             | Store environmental data for analysis            |

## License

This project is intended for educational and personal use.

