<div align="center">

# 🌍 Environmental Dashboard

### A Java-Powered Environmental Monitoring & Visualization System

**Weather • Air Quality • Data Visualization • Java Backend**

<br>

`JAVA`   `JAVASCRIPT`   `HTML5`   `CSS3`   `CHART.JS`

</div>

---

## About the Project

**Environmental Dashboard** is a lightweight web-based monitoring system built using **Java and modern frontend technologies**.

The application provides environmental information for **Bareilly, Delhi, Varanasi, and Pune**, with both summary cards and interactive hourly charts.

The backend is implemented using Java's built-in `HttpServer`, while the frontend uses JavaScript and Chart.js to fetch, process, and visualize the data.

---

## Project Information

| Category              | Details                         |
| --------------------- | ------------------------------- |
| Project Name          | Environmental Dashboard         |
| Project Type          | Full-Stack Web Application      |
| Backend               | Java                            |
| Frontend              | HTML5, CSS3, JavaScript         |
| Visualization         | Chart.js                        |
| Server                | Java `HttpServer`               |
| Data Format           | JSON                            |
| Cities                | Bareilly, Delhi, Varanasi, Pune |
| Data Refresh          | Manual + Automatic              |
| Auto Refresh Interval | 5 Minutes                       |
| Status                | Completed / Functional          |

---

## Key Features

| Feature               | Description                                     |
| --------------------- | ----------------------------------------------- |
| 🌦 Weather Monitoring | Temperature, humidity, wind speed and rainfall  |
| ☀️ UV Monitoring      | Hourly UV index visualization                   |
| 🌫 Air Quality        | AQI and PM2.5 monitoring                        |
| 📊 Interactive Charts | Hourly data visualization using Chart.js        |
| 🏙 Multi-City Support | Monitors four different cities                  |
| 🔄 Manual Refresh     | Refresh data instantly using the refresh button |
| ⟳ Auto Refresh        | Automatically updates data every 5 minutes      |
| ⚡ Java Backend        | Lightweight HTTP server using Java              |
| 📡 JSON API           | Environmental data exposed through `/data`      |

---

## Environmental Metrics

| Metric      | Description               | Visualization |
| ----------- | ------------------------- | ------------- |
| Temperature | Hourly temperature values | Line Chart    |
| Humidity    | Hourly humidity values    | Line Chart    |
| Wind Speed  | Hourly wind speed         | Line Chart    |
| Rainfall    | Hourly precipitation      | Line Chart    |
| UV Index    | Hourly UV index           | Line Chart    |
| AQI         | Air Quality Index         | Line Chart    |
| PM2.5       | Fine particulate matter   | Line Chart    |

---

## Supported Cities

| City     | Monitoring            |
| -------- | --------------------- |
| Bareilly | Weather + Air Quality |
| Delhi    | Weather + Air Quality |
| Varanasi | Weather + Air Quality |
| Pune     | Weather + Air Quality |

---

## Technology Stack

| Technology        | Role                                  |
| ----------------- | ------------------------------------- |
| Java              | Backend server and data generation    |
| Java `HttpServer` | HTTP server implementation            |
| HTML5             | Webpage structure                     |
| CSS3              | User interface and responsive layout  |
| JavaScript        | API communication and dashboard logic |
| Chart.js          | Interactive data visualization        |
| JSON              | Data exchange format                  |

---

## Project Structure

```text
java-environmental-dashboard/
│
├── frontend/
│   ├── index.html
│   ├── script.js
│   └── style.css
│
├── EnvServer.java
├── README.md
└── .gitignore
```

---

## System Architecture

```text
                 ┌──────────────────────┐
                 │      Web Browser     │
                 │  Environmental UI    │
                 └──────────┬───────────┘
                            │
                            │ HTTP
                            ▼
                 ┌──────────────────────┐
                 │    Java HttpServer   │
                 │     EnvServer.java   │
                 └──────────┬───────────┘
                            │
                 ┌──────────┴───────────┐
                 │                      │
                 ▼                      ▼
        ┌─────────────────┐    ┌─────────────────┐
        │ Frontend Files  │    │   /data API     │
        │ HTML/CSS/JS     │    │  JSON Response  │
        └─────────────────┘    └────────┬────────┘
                                        │
                                        ▼
                              ┌──────────────────┐
                              │ Environmental    │
                              │ Sample Data      │
                              └──────────────────┘
```

---

## How the Application Works

| Step | Process                                                 |
| ---: | ------------------------------------------------------- |
|   01 | Java starts the HTTP server                             |
|   02 | Server listens on port `10000`                          |
|   03 | Frontend files are served from the `frontend` directory |
|   04 | Java generates environmental sample data                |
|   05 | `/data` returns the data as JSON                        |
|   06 | JavaScript fetches the JSON response                    |
|   07 | Dashboard cards display the latest values               |
|   08 | Chart.js renders hourly graphs                          |
|   09 | Data automatically refreshes every 5 minutes            |

---

## API

### Environmental Data Endpoint

| Method | Endpoint | Purpose                                             |
| ------ | -------- | --------------------------------------------------- |
| `GET`  | `/data`  | Returns environmental data for all supported cities |

Example:

```text
http://localhost:10000/data
```

The response contains hourly weather and air-quality information for each city.

---

## Installation & Setup

### Requirements

| Requirement | Version        |
| ----------- | -------------- |
| Java JDK    | 8+             |
| Web Browser | Modern Browser |
| Git         | Recommended    |

### Clone the Repository

```bash
git clone <your-repository-url>
cd java-environmental-dashboard
```

### Compile

```bash
javac EnvServer.java
```

### Run

```bash
java EnvServer
```

### Open the Dashboard

```text
http://localhost:10000/
```

---

## Data Source

The current implementation uses Java's `Random` class to generate environmental sample data.

This approach is used for demonstration and development purposes.

The architecture can be extended to integrate real-time weather and air-quality APIs.

---

## Future Improvements

| Improvement    | Description                                 |
| -------------- | ------------------------------------------- |
| Live Data      | Integrate real weather and air-quality APIs |
| Database       | Store historical environmental data         |
| Authentication | Add user authentication                     |
| City Selection | Allow users to dynamically select cities    |
| Date Filters   | Add hourly, daily and historical filters    |
| Alerts         | Add AQI and weather threshold alerts        |
| Deployment     | Deploy the application to a cloud platform  |
| Analytics      | Add environmental trend analysis            |

---

## Author

| Field        | Details                                              |
| ------------ | ---------------------------------------------------- |
| Name         | **PRERNA SHARMA**                                    |
| Role         | Full-Stack Developer                                 |
| Project      | Environmental Dashboard                              |
| Technologies | Java, JavaScript, HTML, CSS, Chart.js                |
| GitHub       | https://github.com/prerna-sharma-only                |
| LinkedIn     | https://www.linkedin.com/in/prerna-sharma-10425a360/ |

---

## Project Highlights

| Area                 | Implementation             |
| -------------------- | -------------------------- |
| Backend Development  | Java HTTP Server           |
| API Development      | Custom `/data` endpoint    |
| Frontend Development | HTML, CSS and JavaScript   |
| Data Handling        | JSON                       |
| Visualization        | Chart.js                   |
| Automation           | 5-minute automatic refresh |
| Architecture         | Client–Server Architecture |

---

## License

This project is developed for **educational, learning, and portfolio purposes**.

---

<div align="center">

### Environmental Dashboard

**Built with Java and Web Technologies**

`Java` • `JavaScript` • `HTML5` • `CSS3` • `Chart.js`

</div>



