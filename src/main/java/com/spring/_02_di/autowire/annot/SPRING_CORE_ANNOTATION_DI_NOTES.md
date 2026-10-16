# Spring Core — Annotation-Based Dependency Injection

## Learning objectives

Understand field, setter, and constructor injection with `@Autowired` and `@Qualifier`.

## Important annotations

- `@Autowired`: asks Spring to resolve and inject a dependency.
- `@Qualifier("ad2")`: narrows the dependency candidate to the bean named `ad2`.
- `<context:annotation-config/>`: enables processing of annotation-based injection in XML configuration.

## Injection types

### Field injection

```java
@Autowired
@Qualifier("ad2")
private Address address;
```

Spring injects the dependency into the field after construction.

### Setter injection

```java
@Autowired
public void setAddress(@Qualifier("ad2") Address address) {
    this.address = address;
}
```

Spring invokes the setter to supply the dependency.

### Constructor injection

```java
public Student(@Qualifier("ad2") Address address) {
    this.address = address;
}
```

Spring provides the dependency when creating the object. A single constructor in Spring 4.3+ does not require `@Autowired`.

## Comparison

| Type | Injection point | Recommended use |
|---|---|---|
| Field | Field | Learning and legacy code |
| Setter | Setter method | Optional dependencies |
| Constructor | Constructor | Required dependencies |

## Interview questions

**1. Which injection type is generally preferred?**  
Constructor injection for required dependencies.

**2. Why use `@Qualifier`?**  
To select a particular bean when multiple candidates match.

**3. Is `@Autowired` required on a single constructor?**  
Not in Spring 4.3 and later.

**4. Why is field injection less convenient for unit tests?**  
The dependency is not supplied directly through the constructor, so tests often need reflection or a Spring context.

**5. What does `@Autowired` do if multiple beans match?**  
Spring attempts to resolve a unique candidate; otherwise, an ambiguity error may occur.

## Quick revision

- Field: inject directly into a field.
- Setter: inject by calling a method.
- Constructor: inject during object creation.
- `@Qualifier`: choose a specific bean.
- Constructor injection: preferred for required dependencies.
