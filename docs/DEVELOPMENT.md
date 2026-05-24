# Development Guidelines

## Git Workflow

### Branch Naming Convention
- `main` - Production-ready code (protected)
- `develop` - Integration branch for features
- `feature/FEATURE-NAME` - New features
- `bugfix/BUG-NAME` - Bug fixes
- `hotfix/HOTFIX-NAME` - Production hotfixes

### Creating a Feature Branch
```bash
git checkout develop
git pull origin develop
git checkout -b feature/your-feature-name
```

### Committing Changes
```bash
git add .
git commit -m "feat: Add user authentication"
```

### Commit Message Format
- `feat:` New feature
- `fix:` Bug fix
- `docs:` Documentation changes
- `style:` Code style changes (formatting, missing semicolons, etc.)
- `refactor:` Code refactoring
- `test:` Adding or updating tests
- `chore:` Dependency updates, build config, etc.

### Pushing & Creating Pull Request
```bash
git push origin feature/your-feature-name
# Create PR on GitHub
```

## Backend Development (Spring Boot)

### Project Structure
```
backend/
├── src/main/java/com/tasksapp/
│   ├── entity/           # JPA Entities
│   ├── repository/        # Data repositories
│   ├── service/           # Business logic
│   ├── controller/        # REST controllers
│   ├── dto/               # Data Transfer Objects
│   ├── exception/         # Custom exceptions
│   └── config/            # Configuration classes
├── src/main/resources/
│   ├── application.properties
│   └── db/migration/      # Database migrations (Flyway)
└── pom.xml
```

### Code Style
- Use **camelCase** for method and variable names
- Use **PascalCase** for class names
- Maximum line length: 120 characters
- Use meaningful variable names
- Add Javadoc for public methods

### Creating an Entity
```java
@Entity
@Table(name = "tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String title;
    
    @ManyToOne
    @JoinColumn(name = "task_list_id", nullable = false)
    private TaskList taskList;
}
```

### Creating a Repository
```java
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByTaskListId(Long taskListId);
}
```

### Creating a Controller
```java
@RestController
@RequestMapping("/api/tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;
    
    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
}
```

## Frontend Development (React + Vite)

### Project Structure
```
frontend/
├── src/
│   ├── components/       # Reusable React components
│   ├── pages/            # Page components
│   ├── hooks/            # Custom React hooks
│   ├── store/            # Zustand store (state)
│   ├── services/         # API calls
│   ├── styles/           # Tailwind CSS
│   ├── App.jsx
│   └── main.jsx
├── public/
└── vite.config.js
```

### Code Style
- Use **camelCase** for variables and functions
- Use **PascalCase** for component names
- Use functional components with hooks
- Keep components focused and small (< 300 lines)
- Use Tailwind CSS for styling

### Creating a Component
```jsx
import React from 'react';

const TaskItem = ({ task, onDelete }) => {
  return (
    <div className="flex items-center justify-between p-4 bg-white rounded-lg shadow">
      <span className="text-lg font-semibold">{task.title}</span>
      <button 
        onClick={() => onDelete(task.id)}
        className="px-4 py-2 bg-red-500 text-white rounded hover:bg-red-600"
      >
        Delete
      </button>
    </div>
  );
};

export default TaskItem;
```

### Using Zustand for State
```javascript
import create from 'zustand';
import { getTasks, createTask } from '../services/taskService';

const useTaskStore = create((set) => ({
  tasks: [],
  setTasks: (tasks) => set({ tasks }),
  
  fetchTasks: async () => {
    const tasks = await getTasks();
    set({ tasks });
  },
  
  addTask: async (task) => {
    const newTask = await createTask(task);
    set((state) => ({ tasks: [...state.tasks, newTask] }));
  },
}));

export default useTaskStore;
```

### API Service
```javascript
import axios from 'axios';

const API_BASE = 'http://localhost:8080/api';

export const getTasks = async () => {
  const response = await axios.get(`${API_BASE}/tasks`);
  return response.data;
};

export const createTask = async (task) => {
  const response = await axios.post(`${API_BASE}/tasks`, task);
  return response.data;
};
```

## Testing

### Backend Testing
```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=TaskServiceTest
```

### Frontend Testing
```bash
# Install testing library
npm install --save-dev @testing-library/react @testing-library/jest-dom

# Run tests
npm test
```

## Code Review Checklist

- [ ] Code follows project style guidelines
- [ ] Tests added/updated for new functionality
- [ ] Documentation updated
- [ ] No hardcoded values (use constants/config)
- [ ] Error handling implemented
- [ ] No console.logs or debug code
- [ ] Performance optimizations considered
- [ ] Security best practices followed

## Common Commands

### Backend
```bash
cd backend
mvn clean install          # Build project
mvn spring-boot:run        # Run application
mvn test                   # Run tests
mvn clean                  # Clean build
```

### Frontend
```bash
cd frontend
npm install                # Install dependencies
npm run dev                # Run development server
npm run build              # Build for production
npm test                   # Run tests
```

## Resources

- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [React Documentation](https://react.dev)
- [Tailwind CSS Documentation](https://tailwindcss.com/docs)
- [Zustand Documentation](https://github.com/pmndrs/zustand)
