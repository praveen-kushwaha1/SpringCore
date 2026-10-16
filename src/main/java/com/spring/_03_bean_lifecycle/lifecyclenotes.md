# Spring Core Notes — Bean Lifecycle

## 1. Learning objectives

Understand how Spring creates, configures, initializes, uses, and destroys beans. This exercise compares three lifecycle callback approaches:

1. Annotation-based: `@PostConstruct` and `@PreDestroy`
2. Spring interfaces: `InitializingBean` and `DisposableBean`
3. XML configuration: `init-method` and `destroy-method`

Assumptions for the annotation example: Java 17+ and Spring Framework 6+. Spring 6 uses `jakarta.annotation` imports. Older Spring 5 projects may use `javax.annotation` with a compatible dependency.

## 2. What is the Spring bean lifecycle?

The bean lifecycle is the sequence of stages a Spring-managed object passes through while the IoC container manages it.

```text
Spring container starts
        |
        v
Instantiate bean
        |
        v
Populate dependencies / properties
        |
        v
Aware callbacks and BeanPostProcessor steps (where applicable)
        |
        v
Initialization callbacks
  - @PostConstruct
  - InitializingBean.afterPropertiesSet()
  - configured init-method
        |
        v
Bean is ready for use
        |
        v
Spring context closes
        |
        v
Destruction callbacks
  - @PreDestroy
  - DisposableBean.destroy()
  - configured destroy-method
```

The diagram is a simplified overview. Spring's complete lifecycle includes additional container extension points, and the exact ordering of post-processors matters.

## 3. Three lifecycle approaches

| Approach | Initialization | Destruction |
|---|---|---|
| Annotation | `@PostConstruct` | `@PreDestroy` |
| Spring interface | `InitializingBean.afterPropertiesSet()` | `DisposableBean.destroy()` |
| XML | `init-method="init"` | `destroy-method="destroy"` |

Choose the approach that fits the project. Avoid implementing multiple callbacks for the same task without a reason.

### Annotation callbacks

```java
@PostConstruct
public void init() {
    System.out.println("Bean initialized");
}

@PreDestroy
public void destroy() {
    System.out.println("Bean destroyed");
}
```

- `@PostConstruct` marks a no-argument initialization callback.
- `@PreDestroy` marks a no-argument cleanup callback.
- In Spring 6, import both from `jakarta.annotation`.
- Spring's annotation-processing infrastructure must be enabled; standard Spring context configurations normally provide the required processing.

### Spring lifecycle interfaces

```java
public class Student implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() {
        System.out.println("Bean initialized");
    }

    @Override
    public void destroy() {
        System.out.println("Bean destroyed");
    }
}
```

- `InitializingBean` declares `afterPropertiesSet()`.
- `DisposableBean` declares `destroy()`.
- These methods are called by Spring for the managed bean at the relevant lifecycle stages.
- The interfaces couple the class to Spring, so annotations or XML can be preferable when keeping domain classes framework-independent is important.

### XML callbacks

```xml
<bean id="student"
      class="com.example.Student"
      init-method="init"
      destroy-method="destroy">
    <property name="id" value="1"/>
    <property name="name" value="Praveen"/>
</bean>
```

The class must declare compatible `init()` and `destroy()` methods. These are ordinary Java methods; the XML bean definition tells Spring when to invoke them.

## 4. Important distinctions

- **Constructor:** creates the Java object.
- **Dependency/property injection:** supplies required values or collaborators.
- **Initialization callback:** performs setup after dependency injection.
- **Destruction callback:** releases resources before managed-bean cleanup completes.
- **`BeanPostProcessor`:** allows Spring to process bean instances before and after initialization callbacks.
- **`ApplicationContext`:** Spring container that manages bean creation and lifecycle.
- **`registerShutdownHook()`:** registers context closure during normal JVM shutdown. Alternatively, explicitly close a configurable context when finished.

A simplified lifecycle order for these callback mechanisms is:

```text
@PostConstruct
    |
    v
InitializingBean.afterPropertiesSet()
    |
    v
XML/custom init-method
```

If one bean defines all three callback styles, the callbacks can all run. In normal applications, use one initialization and one destruction strategy unless there is a clear need to combine them. Spring's lifecycle extension points and post-processors can affect the broader sequence.

## 5. Resource cleanup and scope

- Singleton beans managed by a closing Spring context receive supported destruction callbacks.
- Prototype beans are created and supplied by Spring, but Spring does not automatically manage their full destruction lifecycle.
- A JVM shutdown hook is not a guarantee of cleanup after forced termination, power loss, or abrupt process killing.
- Avoid putting essential business transactions only in a shutdown callback.

## 6. Common errors

1. **Resource not found:** verify the XML file is in `src/main/resources` and use a classpath-relative path, e.g. `spring/lifecycle/lifecycle-xml.xml`.
2. **Bean class not found:** match the XML `class` attribute exactly to the Java package and class.
3. **Wrong annotation namespace:** Spring 6 uses `jakarta.annotation.PostConstruct` and `jakarta.annotation.PreDestroy`; do not mix them with `javax.annotation`.
4. **Missing dependency:** include `spring-context` and, for the Spring 6 annotation example, `jakarta.annotation-api`.
5. **No destroy message:** ensure the context closes, either by registering a shutdown hook or explicitly closing it.
6. **Expecting prototype cleanup:** prototype beans do not receive automatic destruction callbacks from the container.
7. **Multiple XML declarations:** each XML file must contain one XML document and one XML declaration.

## 7. Example execution flow

For an XML-defined singleton `Student`:

1. `ClassPathXmlApplicationContext` loads the XML resource.
2. Spring creates the `Student` instance.
3. Spring sets `id` and `name` through setter injection.
4. Spring invokes the configured initialization callback.
5. `getBean("st", Student.class)` returns the bean.
6. The application prints the student.
7. The context closes during normal shutdown.
8. Spring invokes the configured destruction callback.

Expected output (the final line appears when context shutdown occurs):

```text
XML init-method: Student initialized
Student [id=1, name=Praveen]
XML destroy-method: Student destroyed
```

## 8. Interview questions

**Q: What is the Spring bean lifecycle?**  
A: The stages Spring-managed objects pass through, from instantiation and dependency population to initialization, use, and destruction.

**Q: What does `@PostConstruct` do?**  
A: Marks an initialization callback that runs after dependency injection during bean initialization.

**Q: What is `InitializingBean`?**  
A: A Spring interface whose `afterPropertiesSet()` method is called after bean properties have been set.

**Q: What is the difference between `@PreDestroy` and `DisposableBean`?**  
A: `@PreDestroy` marks a cleanup callback, while `DisposableBean` provides the Spring `destroy()` callback through an interface.

**Q: Why use XML `init-method` and `destroy-method`?**  
A: They configure lifecycle callbacks without adding lifecycle annotations or interfaces to the Java class.

**Q: What does `registerShutdownHook()` do?**  
A: Registers a JVM shutdown hook that closes the Spring context during normal JVM shutdown.

**Q: Are lifecycle callbacks constructors?**  
A: No. A constructor creates the object; lifecycle callbacks execute later as Spring initializes or destroys the managed bean.

**Q: Does Spring destroy prototype beans automatically?**  
A: No. Spring does not automatically manage destruction callbacks for prototype-scoped beans.

## 9. Quick revision

- Constructor = object creation.
- Injection = populate dependencies and properties.
- `@PostConstruct` = annotation-based initialization.
- `afterPropertiesSet()` = interface-based initialization.
- `init-method` = XML-configured initialization.
- `@PreDestroy` = annotation-based cleanup.
- `DisposableBean.destroy()` = interface-based cleanup.
- `destroy-method` = XML-configured cleanup.
- Close the context to trigger destruction callbacks.
- For Spring 6, use `jakarta.annotation` imports.
