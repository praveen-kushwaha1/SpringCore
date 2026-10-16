# Spring Core — XML Autowiring

## 1. Learning objectives

- Understand dependency injection (DI) and Spring beans.
- Learn XML autowiring modes.
- Compare `no`, `byName`, `byType`, and `constructor`.
- Understand bean-name matching, type matching, and ambiguity.

## 2. Important definitions

- **Bean:** An object managed by the Spring IoC container.
- **IoC:** Inversion of Control; Spring manages object creation and dependencies.
- **DI:** Dependency Injection; dependencies are supplied to an object.
- **Autowiring:** Spring automatically resolves and injects dependencies.

## 3. XML autowiring modes

| Mode | Behavior |
|---|---|
| `no` | Default; no automatic wiring |
| `byName` | Matches property name with bean name |
| `byType` | Matches property type with bean type |
| `constructor` | Resolves constructor arguments |

## 4. Examples

### byName

```xml
<bean id="address"
      class="com.spring._02_di.autowire.xml.Address">
    <property name="city" value="Pune"/>
    <property name="state" value="Maharashtra"/>
</bean>

<bean id="st"
      class="com.spring._02_di.autowire.xml.Student"
      autowire="byName"/>
```

Spring finds the bean named `address` and injects it through `setAddress()`.

### byType

```xml
<bean id="ad"
      class="com.spring._02_di.autowire.xml.Address">
    <property name="city" value="Lucknow"/>
    <property name="state" value="Uttar Pradesh"/>
</bean>

<bean id="st"
      class="com.spring._02_di.autowire.xml.Student"
      autowire="byType"/>
```

Spring finds the bean by Java type. Keep one matching bean to avoid ambiguity.

### constructor

```xml
<bean id="st"
      class="com.spring._02_di.autowire.xml.Student"
      autowire="constructor"/>
```

Spring resolves a compatible constructor argument using the available beans.

### no — explicit injection

```xml
<bean id="st"
      class="com.spring._02_di.autowire.xml.Student"
      autowire="no">
    <property name="address" ref="ad"/>
</bean>
```

Automatic wiring is disabled, but explicit injection still works.

## 5. Execution flow

```text
Application
    |
    v
ApplicationContext loads XML
    |
    v
Spring creates and configures beans
    |
    v
Spring resolves the Student dependency
    |
    v
Student receives Address
    |
    v
Application prints Student
```

## 6. Common mistakes

- Bean name does not match the property name in `byName`.
- Multiple beans match the property type in `byType`.
- XML resource path is incorrect.
- `ref` is confused with `value`.
- `autowire="no"` is incorrectly understood to prohibit explicit injection.

## 7. Interview questions

**Q: What is the default autowiring mode?**  
A: `no`.

**Q: How does `byName` work?**  
A: It matches a bean name with a writable property name.

**Q: How does `byType` work?**  
A: It matches the dependency's Java type.

**Q: What happens if multiple beans match?**  
A: Legacy XML autowiring may fail because the dependency is ambiguous.

**Q: Can explicit injection work with `no`?**  
A: Yes, using `<property>` or `<constructor-arg>`.

## 8. Quick revision

- `no` = no automatic wiring.
- `byName` = match names.
- `byType` = match types.
- `constructor` = resolve constructor arguments.
- `ref` = reference a bean.
- `value` = supply a literal value.
- `primary="true"` should not be relied upon to resolve ambiguity in legacy XML autowiring modes.
