
# Spring Core — Bean Scope

## Definition
Bean scope determines how Spring creates and shares instances of a bean.

## Types of bean scope

1. Singleton: one shared instance per bean definition per container.
2. Prototype: a new instance for every bean lookup.
3. Request: one instance per HTTP request.
4. Session: one instance per HTTP session.
5. Application: one instance per ServletContext.
6. WebSocket: one instance per WebSocket session.

## Execution flow

ApplicationContext
|
+--> singletonEmployee
|       +--> getBean() -> Object A
|       +--> getBean() -> Object A
|
+--> prototypeEmployee
+--> getBean() -> Object B
+--> getBean() -> Object C

## Key differences

| Scope | Instance behavior |
|---|---|
| singleton | Shared instance |
| prototype | New instance per lookup |
| request | One per HTTP request |
| session | One per HTTP session |
| application | One per ServletContext |
| websocket | One per WebSocket session |

## Common mistakes
- Forgetting component scanning.
- Using a web scope without a web-aware context.
- Assuming prototype destruction callbacks run automatically.
- Assuming a prototype injected into a singleton is recreated on every method call.
- Treating Spring singleton scope as a Java static variable.

## Interview revision
- Default scope: singleton.
- `@Scope("prototype")`: new object per bean lookup.
- Web scopes require a suitable web application context.
- A singleton containing mutable state may need thread-safety measures.
