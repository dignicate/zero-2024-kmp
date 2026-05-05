# Architecture Guidelines

## 1. Layer Structure
- **Domain Layer**: Contains Business Logic, Models, and Repository Interfaces.
- **Data Layer**: Contains Repository Implementations, API Clients, and DTOs.

## 2. Use Case Pattern: Mandatory CQS
Every Use Case must follow a Reactive CQS (Command Query Separation) pattern to ensure architectural consistency and scalability.

- **Trigger (Command)**: Use `suspend` functions (e.g., `fetch()`, `execute()`) to initiate actions. These should only emit parameters into internal flows.
- **Stream (Query/State)**: Expose a `StateFlow` or `Flow` to provide the data, operation status, or completion results.
- **Strict Rule**: Never return operation results directly from the `suspend` trigger functions. Consumers must subscribe to the exposed state stream.
- **Benefit**: Decouples "action" from "state", handles race conditions via flow operators (e.g., `debounce`, `flatMapLatest`), and accommodates future complexity without changing the interface.
