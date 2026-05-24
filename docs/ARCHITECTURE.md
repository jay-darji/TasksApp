# System Architecture

## Three-Tier Architecture Overview

```
┌─────────────────────────────────────────────────────────────┐
│                     Frontend Layer                           │
│              React (Vite) + Tailwind CSS                    │
│                 Zustand / React Context                     │
└─────────────────────────────────────────────────────────────┘
                            ↓ (REST API)
┌─────────────────────────────────────────────────────────────┐
│                     Backend Layer                            │
│           Java 21 + Spring Boot 3 + Spring Security         │
│              Spring Data JPA + Repositories                 │
└─────────────────────────────────────────────────────────────┘
                            ↓ (SQL)
┌─────────────────────────────────────────────────────────────┐
│                    Database Layer                            │
│                      MySQL 8.x                              │
│              Relational Data Persistence                    │
└─────────────────────────────────────────────────────────────┘
```

## Technology Stack

| Layer | Technology | Purpose |
|-------|-----------|----------|
| **Frontend** | React (Vite) + Tailwind CSS | Fast rendering, responsive UI, clean styling |
| **Frontend State** | Zustand / React Context | Fast, lightweight state management |
| **Backend** | Java 21 + Spring Boot 3 | RESTful APIs, business logic |
| **Persistence** | Spring Data JPA | Object-relational mapping |
| **Security** | Spring Security + JWT | Authentication & authorization |
| **Database** | MySQL 8.x | Relational data storage |

## Core Database Schema

### Users Table
```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(255) UNIQUE NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

### TaskLists Table
```sql
CREATE TABLE task_lists (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    user_id BIGINT NOT NULL,
    title VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);
```

### Tasks Table
```sql
CREATE TABLE tasks (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    task_list_id BIGINT NOT NULL,
    parent_task_id BIGINT,
    title VARCHAR(255) NOT NULL,
    description TEXT,
    is_completed BOOLEAN DEFAULT FALSE,
    due_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (task_list_id) REFERENCES task_lists(id) ON DELETE CASCADE,
    FOREIGN KEY (parent_task_id) REFERENCES tasks(id) ON DELETE CASCADE
);
```

## Entity Relationships

```
User (1) ──────→ (N) TaskList
                    │
                    ↓
                  Task (1) ──→ (N) Task (subtasks)
```

## REST API Endpoints (Phase 1)

### Tasks
- `GET /api/tasks` - Fetch all tasks for user
- `GET /api/tasks/{id}` - Fetch single task
- `POST /api/tasks` - Create new task
- `PUT /api/tasks/{id}` - Update task
- `DELETE /api/tasks/{id}` - Delete task

### Task Lists
- `GET /api/lists` - Fetch all lists for user
- `POST /api/lists` - Create new list
- `PUT /api/lists/{id}` - Update list
- `DELETE /api/lists/{id}` - Delete list

## Data Flow

1. **User interacts with React UI** → Zustand state updates
2. **Component triggers API call** → Axios sends request to Spring Boot
3. **Spring Boot Controller receives request** → Validates & processes
4. **Service layer applies business logic** → Calls repository
5. **Repository queries MySQL** → Data retrieved/modified
6. **Response returned to Frontend** → UI updates with new data

## Security Considerations (Phase 2)

- JWT tokens for stateless authentication
- Spring Security filters for request validation
- Password hashing with BCrypt
- CORS configuration for cross-origin requests
- Data isolation per user

## Scalability Notes

- Database indexes on frequently queried columns (user_id, task_list_id)
- Pagination for large task lists
- Lazy loading in frontend for performance
- Caching strategies for read-heavy operations
