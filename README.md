# AI Buster Spring Boot Backend

The Spring Boot service accepts mediaDto files, stores them locally, and forwards them to a Python microservice for AI manipulation detection.

## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Architecture](#architecture)
- [Requirements](#requirements)
- [Setup](#setup)
- [Configuration](#configuration)
- [Usage](#usage)
    - [File Upload](#file-upload)
    - [Manipulation Check](#manipulation-check)
    - [List Media](#list-mediaDto)
- [Project Structure](#project-structure)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project provides a Spring Boot REST API that receives mediaDto files, stores them in a local `uploads/` directory, and returns analysis results in JSON format.

## Features

- **File Upload**: Upload images or videos via HTTP multipart/form-data
- **Local Storage**: Save files under `uploads/` with a UUID-prefixed filename
- **Manipulation Check**: Synchronously call an external Python service for AI-based detection
- **List Media**: Retrieve stored mediaDto metadata as DTOs from the database

## Architecture

1. **Controllers**: Endpoints at `/api/files/upload`, `/api/check/image`, and `/api/mediaDto`
2. **Services**:
    - `FileStorageService`: Handles saving files to disk
    - `AnalyzerService`: Sends files to the Python checker
    - `MediaListService`: Fetches mediaDto records from the database
3. **Repository**: `MediaRepository` using Spring Data JPA
4. **Entity & DTO**: `Media` entity and `MediaDto` record
5. **Mapper**: Manual `MediaMapper` implementation
6. **Configuration**:
    - `RestTemplate` bean for HTTP calls
    - Static resource handler for serving `/uploads/**`

## Requirements

- Java 17 or newer
- Maven 3.6 or newer
- Running Python checker service (e.g., at `http://localhost:8000`)
- Database (H2, PostgreSQL, etc.)

## Setup

```bash
git clone https://github.com/erenertemden/ai-buster-jend.git
cd ai-buster-jend
mvn clean package
mvn spring-boot:run
```

By default, the application runs on port 8080.

## Configuration

Edit `src/main/resources/application.properties`:

```properties
# Base URL for the Python checker service
python.checker.base-url=http://localhost:8000

# Directory for storing uploaded files
file.upload-dir=uploads

# Maximum allowed upload size
spring.servlet.multipart.max-file-size=50MB
spring.servlet.multipart.max-request-size=50MB
```

## Usage

### File Upload

Send a POST request with multipart data:

```bash
curl -X POST http://localhost:8080/api/files/upload \
  -F "file=@/path/to/your/mediaDto.jpg"
```

**Response**:

```json
{ "url": "/uploads/{uuid}-mediaDto.jpg" }
```

### Manipulation Check

Submit an uploaded file for AI analysis:

```bash
curl -X POST http://localhost:8080/api/check/image \
  -F "file=@uploads/{uuid}-mediaDto.jpg"
```

**Response**:

```json
{ "manipulatedProbability": 0.75 }
```

### List Media

Retrieve all stored mediaDto records:

```bash
curl http://localhost:8080/api/mediaDto
```

**Response**:

```json
[
  {
    "id": "uuid",
    "type": "image/jpeg",
    "description": null,
    "status": "UNDETERMINED",
    "createdAt": "2025-04-23T10:00:00",
    "updatedAt": "2025-04-23T10:00:00"
  }
]
```

## Project Structure

```
ai-buster-jend/
├── src/main/java/ertem/eren/aibuster/
│   ├── config/           # RestTemplate and WebMvc configurations
│   ├── controller/       # REST API controllers
│   ├── dto/              # MediaDto record
│   ├── entities/         # Media JPA entity
│   ├── mapper/           # MediaMapper interface & implementation
│   ├── repositories/     # MediaRepository
│   ├── services/         # Service interfaces & implementations
│   └── AibusterApplication.java
├── src/main/resources/
│   └── application.properties
├── uploads/              # Local file storage directory
├── pom.xml
└── README.md
```

## Contributing

Contributions welcome! Feel free to open issues or submit pull requests for improvements.

## License

MIT © Eren Ertemden

