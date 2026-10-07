# AutoMech: Smart Nearby Automobile Workshop Locator & Service Discovery

[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Android](https://img.shields.io/badge/Android-Java%20SDK-green.svg)](https://developer.android.com/)
[![Mapbox](https://img.shields.io/badge/Mapbox-Maps%20SDK-blue.svg)](https://www.mapbox.com/)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

An end-to-end full-stack automotive repair locator platform combining a native **Android Java client** with a scalable **Spring Boot REST API backend**. Enables stranded drivers and vehicle owners to discover nearby certified car mechanics, check ratings, inspect offered services, and navigate directly using Mapbox location routing.

---

## Architecture Overview

```
┌─────────────────────────────────┐
│     Android Java Mobile App     │
│   (Mapbox SDK + Location GPS)   │
└────────────────┬────────────────┘
                 │ HTTP REST / JSON
                 ▼
┌─────────────────────────────────┐
│    Spring Boot 3 REST Server    │
│  - Haversine Geo-Distance Filter │
│  - Web Scraping Discovery Sync   │
│  - JPA / Relational Database     │
└─────────────────────────────────┘
```

---

## Key Features

1. **Native Android Mapbox Client**:
   - Real-time GPS user position tracking.
   - Dynamic custom workshop map markers with interactive callouts.
   - Turn-by-turn route previews.

2. **Spring Boot RESTful Backend**:
   - **Haversine Geo-Filtering**: Calculates spherical distance from user GPS coordinates (`lat`, `lng`) and filters workshops within a dynamic radial threshold (e.g. 5km, 10km, 25km).
   - **Garage Web Scraper Service**: Automatically crawls public garage directories and synchronizes metadata into the database.
   - **JPA & Relational Persistence**: Stores workshop attributes, contact details, operational hours, and ratings.

---

## API Endpoints

| Method | Endpoint | Description |
|---|---|---|
| `GET` | `/api/v1/workshops` | Fetch all indexed workshops |
| `GET` | `/api/v1/workshops/nearby?lat=12.93&lng=77.62&radiusKm=10` | Filter nearby workshops within radius |
| `GET` | `/api/v1/workshops/{id}` | Get workshop details by ID |
| `GET` | `/api/v1/workshops/search?q=oil+change` | Search workshops by keyword |
| `POST` | `/api/v1/workshops/sync-scrape?location=Bengaluru` | Trigger web scraping sync |

---

## How to Run Locally

### 1. Spring Boot Backend
```bash
cd backend
./mvnw clean spring-boot:run
```
Backend starts on `http://localhost:8080`.  
H2 Web Console available at `http://localhost:8080/h2-console`.

### 2. Android App
1. Open the project root in **Android Studio**.
2. Add your Mapbox Public Token in `res/values/strings.xml`.
3. Point the API base URL in the app to your backend IP: `http://10.0.2.2:8080/api/v1/workshops/nearby`.
4. Run on an Android Emulator or physical device.

---

## Author & License
Developed by [Mezba Uddin Ahmed](https://github.com/mezba23). Distributed under the MIT License.
