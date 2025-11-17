# Package Structure Overview

## `com.odintsov.wallpapers_project`

### `application`
Contains **business logic**, **use cases**, **services**, and **DTOs** (Data Transfer Objects).  
This is the core of the application — coordinates between presentation and domain layers.

- `dtos` → Request/response models for API
- `exceptions` → Application-specific exceptions
- `services` → Services (maybe abstract base crud services will be also located here) 
- `usecases` → Concrete implementations of business flows
- `validators` → Custom validation logic

---

### `domain`
Holds **core domain models** and **repository interfaces**.  
Pure business entities — no framework dependencies here.

- `entities` → JPA `@Entity` classes (e.g., `Wallpaper`, `Category`)
- `repositories` → Spring Data JPA repository interfaces

---

### `infrastructure`
Framework-specific implementations and configurations.

- `config` → Spring `@Configuration` classes (security, JPA, beans)
- `exceptions` → Global exception handlers, custom runtime exceptions
- `jpa` → JPA-specific configurations, entity listeners, converters

---

### `presentation`
Handles **HTTP layer** — incoming requests and outgoing responses.

- `controllers` → `@RestController` or `@Controller` classes
- `dtos` → API-specific request/response models (may overlap with application DTOs)
- `mappers` → Conversion between entities and DTOs (e.g., MapStruct)

---

## Summary Table

| Package | Purpose |
|--------|--------|
| `application` | Business rules & orchestration |
| `domain` | Pure data model & persistence contracts |
| `infrastructure` | Technical setup & integrations |
| `presentation` | API endpoints & HTTP handling |
