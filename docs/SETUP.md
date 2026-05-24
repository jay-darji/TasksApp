# Environment Setup Guide

## Prerequisites

Before starting, ensure you have the following installed:

### 1. Java Development Kit (JDK)
- **Version:** Java 17 or 21
- **Download:** [https://www.oracle.com/java/technologies/downloads/](https://www.oracle.com/java/technologies/downloads/)
- **Verify:** `java -version`

### 2. Node.js & npm
- **Version:** Node.js 18+ (includes npm)
- **Download:** [https://nodejs.org/](https://nodejs.org/)
- **Verify:** `node -v` and `npm -v`

### 3. MySQL
- **Version:** MySQL 8.x
- **Download:** [https://dev.mysql.com/downloads/mysql/](https://dev.mysql.com/downloads/mysql/)
- **Verify:** `mysql --version`

### 4. Git
- **Download:** [https://git-scm.com/](https://git-scm.com/)
- **Verify:** `git --version`

## Backend Setup (Spring Boot)

### Step 1: Create Spring Boot Project
Visit [Spring Initializr](https://start.spring.io/) and configure:
- **Project:** Maven
- **Language:** Java
- **Spring Boot:** 3.x
- **Java:** 21
- **Dependencies:**
  - Spring Web
  - Spring Data JPA
  - MySQL Driver
  - Spring Security
  - Lombok

Download and extract the project to the `backend/` folder.

### Step 2: Configure Database
Create `backend/src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tasksapp
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
server.port=8080
```

### Step 3: Run Backend
```bash
cd backend
mvn clean install
mvn spring-boot:run
```

Backend will run on: `http://localhost:8080`

## Frontend Setup (React + Vite)

### Step 1: Create React Project
```bash
npm create vite@latest frontend -- --template react
cd frontend
npm install
```

### Step 2: Install Dependencies
```bash
npm install tailwindcss postcss autoprefixer zustand axios
npx tailwindcss init -p
```

### Step 3: Configure Tailwind
Update `frontend/tailwind.config.js`:
```javascript
export default {
  content: [
    "./index.html",
    "./src/**/*.{js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {},
  },
  plugins: [],
}
```

Update `frontend/src/index.css`:
```css
@tailwind base;
@tailwind components;
@tailwind utilities;
```

### Step 4: Run Frontend
```bash
npm run dev
```

Frontend will run on: `http://localhost:5173`

## Database Setup (MySQL)

### Step 1: Create Database
```sql
CREATE DATABASE tasksapp;
USE tasksapp;
```

### Step 2: Tables (Auto-created by Spring Boot JPA)
See `ARCHITECTURE.md` for the full schema.

## Verification

✅ Backend running on: `http://localhost:8080`
✅ Frontend running on: `http://localhost:5173`
✅ MySQL listening on: `localhost:3306`

Test API:
```bash
curl http://localhost:8080/api/tasks
```

## Troubleshooting

| Issue | Solution |
|-------|----------|
| Port already in use | Change `server.port` in `application.properties` |
| MySQL connection failed | Verify MySQL is running and credentials are correct |
| npm install fails | Delete `node_modules` and `package-lock.json`, then reinstall |
| Java not found | Add JDK to PATH or set JAVA_HOME environment variable |
