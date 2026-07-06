# 🔗 URLForge

> A production-ready URL Shortener built using **Spring Boot**, featuring URL validation, analytics, Swagger documentation, Redis caching, and a responsive frontend.

![Java](https://img.shields.io/badge/Java-21-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![Render](https://img.shields.io/badge/Backend-Render-blue)
![Vercel](https://img.shields.io/badge/Frontend-Vercel-black)
![License](https://img.shields.io/badge/License-MIT-green)

---

# 🌐 Live Demo

| Service | Link |
|---------|------|
| 🚀 Frontend | https://url-forge-nu.vercel.app |
| ⚙️ Backend API | https://urlforge-p2g3.onrender.com |
| 📚 Swagger UI | https://urlforge-p2g3.onrender.com/swagger-ui/index.html |
| 🐳 Docker | https://hub.docker.com/r/aazizam/urlforge |

---

# 📖 Overview

URLForge is a modern URL shortening platform that allows users to:

- 🔗 Shorten long URLs
- 🔄 Redirect using generated short links
- ✅ Validate URLs before shortening
- 📊 View click analytics
- ⚡ Improve performance with Redis caching
- 📚 Explore APIs using Swagger UI

---

# ✨ Features

- URL Shortening
- Automatic Redirection
- URL Validation
- Click Analytics
- Redis Cache
- RESTful APIs
- Swagger Documentation
- Responsive UI
- CORS Enabled
- Global Exception Handling

---

# 🛠 Tech Stack

## Backend

- Java 21
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Hibernate
- PostgreSQL
- Redis
- Maven
- Swagger (OpenAPI)

## Frontend

- HTML5
- CSS3
- JavaScript

## Deployment

- Render (Backend)
- Vercel (Frontend)

---

# 📂 Project Structure

```
URLForge
│
├── backend
│   ├── controller
│   ├── service
│   ├── repository
│   ├── entity
│   ├── dto
│   ├── config
│   ├── exception
│   └── util
│
├── frontend
│   ├── index.html
│   ├── styles.css
│   ├── script.js
│   └── assets
│
└── README.md
```

---

# 🚀 API Endpoints

## URL APIs

| Method | Endpoint | Description |
|---------|----------|-------------|
| POST | `/api/urls` | Create Short URL |
| GET | `/{shortCode}` | Redirect to Original URL |
| GET | `/api/urls/analytics/{shortCode}` | Get Analytics |
| POST | `/api/urls/validate` | Validate URL |

---

# 📊 Analytics

The analytics endpoint provides:

- Total Clicks
- Original URL
- Short URL
- Creation Time
- Last Access Time

---

# ⚙️ Running Locally

## Clone Repository

```bash
git clone https://github.com/yourusername/urlforge.git
```

```bash
cd urlforge
```

---

## Backend

```bash
cd backend
```

```bash
./mvnw spring-boot:run
```

Backend runs on

```
http://localhost:8080
```

---

## Frontend

Open

```
frontend/index.html
```

or use

```bash
Live Server
```

---

# 🔧 Configuration

Update the following in

```
application.properties
```

```properties
spring.datasource.url=
spring.datasource.username=
spring.datasource.password=

spring.data.redis.host=
spring.data.redis.port=
```

---

# 📚 Swagger Documentation

After running the backend:

```
http://localhost:8080/swagger-ui/index.html
```

Production:

```
https://your-render-app.onrender.com/swagger-ui/index.html
```

---

# 🧪 Example Request

### Create Short URL

```http
POST /api/urls
```

Request

```json
{
    "longUrl":"https://github.com"
}
```

Response

```json
{
    "shortUrl":"http://localhost:8080/Ab12Cd"
}
```

---

# 🚀 Future Improvements

- JWT Authentication
- Rate Limiting
- QR Code Generation
- Custom Alias
- User Accounts
- Dashboard
- Link Expiration
- Password Protected URLs

---

# 🤝 Contributing

1. Fork the repository

2. Create a feature branch

```bash
git checkout -b feature-name
```

3. Commit changes

```bash
git commit -m "Added new feature"
```

4. Push

```bash
git push origin feature-name
```

5. Open a Pull Request

---

# 👨‍💻 Author

**Tarsem Gulab**

GitHub: https://github.com/gittarsem

---

# ⭐ Support

If you found this project helpful, consider giving it a ⭐ on GitHub.

It helps others discover the project!
