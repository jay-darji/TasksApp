# TasksApp

A full-stack task management application emulating Google Tasks with a clean, responsive UI and robust REST API.

## 🏗️ Architecture

- **Frontend:** React (Vite) + Tailwind CSS
- **Backend:** Java 21 + Spring Boot 3
- **Database:** MySQL 8.x

## 📋 Quick Start

See [docs/SETUP.md](./docs/SETUP.md) for detailed environment setup instructions.

## 🚀 Development Phases

### Phase 1: MVP (Core CRUD)
- Basic database schema
- REST API endpoints
- Simple React UI

### Phase 2: Authentication & Multi-List Support
- JWT authentication
- User data isolation
- Custom task lists

### Phase 3: Advanced Features
- Subtasks
- Due dates
- Sorting & filtering
- UI polish

## 📁 Project Structure

```
TasksApp/
├── frontend/          # React (Vite) application
├── backend/           # Spring Boot application
├── docs/              # Documentation
│   ├── SETUP.md
│   ├── ARCHITECTURE.md
│   └── DEVELOPMENT.md
└── README.md
```

## 🔄 Git Workflow

- `main` - Production-ready code
- `develop` - Integration branch
- `feature/*` - Feature branches

## 📝 License

MIT
