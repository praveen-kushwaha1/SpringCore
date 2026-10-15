# Spring Core Basics — XML Configuration Revision Notes

This project demonstrates Java interfaces, Spring IoC, XML bean configuration, `BeanFactory`, `ApplicationContext`, `getBean()`, and singleton scope.

## 1. Project structure

```text
spring-core-learning/
├── pom.xml
├── SPRING_CORE_NOTES.md
└── src/main/
    ├── java/com/spring/_01_basics/
    │   ├── Action.java
    │   ├── Ram.java
    │   ├── Shyam.java
    │   └── App.java
    └── resources/spring/config.xml
```

For Maven, XML files loaded with `ClassPathXmlApplicationContext("spring/config.xml")` should be placed at `src/main/resources/spring/config.xml`.

## 2. `Action.java`

```java
package com.spring._01_basics;

/**
 * An interface defines a contract: it says what an implementation must do.
 * Any non-abstract class implementing Action must implement both methods.
 */
public interface Action {

    // Interface methods are public and abstract implicitly.
    void eat();

    void sleep();
}
```

**Remember:** interface = contract; implementation class = actual behavior.

## 3. `Ram.java`

```java
package com.spring._01_basics;

/** Ram is one concrete implementation of the Action interface. */
public class Ram implements Action {

    public Ram() {
        // Demonstration only: this message lets us see when an object is created.
        System.out.println("Ram object is created");
    }

    @Override
    public void eat() {
        System.out.println("Ram is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Ram is sleeping");
    }
}
```

## 4. `Shyam.java`

```java
package com.spring._01_basics;

/** Shyam provides a different implementation of the same Action contract. */
public class Shyam implements Action {

    public Shyam() {
        System.out.println("Shyam object is created");
    }

    @Override
    public void eat() {
        System.out.println("Shyam is eating");
    }

    @Override
    public void sleep() {
        System.out.println("Shyam is sleeping");
    }
}
```

**Remember:** `implements Action` requires `Ram` and `Shyam` to implement `eat()` and `sleep()`.

## 5. `src/main/resources/spring/config.xml`

```xml
<?xml version="1.0" encoding="UTF-8"?>

<!-- Spring XML configuration: declares objects Spring should manage. -->
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="
           http://www.springframework.org/schema/beans
           https://www.springframework.org/schema/beans/spring-beans.xsd">

    <!--
        id: name used to retrieve this bean with getBean("ram", ...).
        class: fully qualified Java class name (package + class).
        Spring creates and manages an instance of this class.
    -->
    <bean id="ram" class="com.spring._01_basics.Ram" />

    <!-- Register a second bean, managed separately from the Ram bean. -->
    <bean id="shyam" class="com.spring._01_basics.Shyam" />

</beans>
```

**Remember:** `id` is the bean name; `class` is the fully qualified class name. The default bean scope is `singleton`—one instance per bean definition per container.

## 6. Recommended `App.java`

Use one application context for this lesson. Loading the same XML twice creates two separate containers and therefore two separate singleton instances of each bean.

```java
package com.spring._01_basics;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/** Demonstrates retrieving and using Spring-managed beans. */
public class App {

    public static void main(String[] args) {

        // Traditional Java: our code directly creates and owns the object.
        // Action directObject = new Ram();
        // directObject.eat();
        // directObject.sleep();

        // Load Spring bean definitions from src/main/resources/spring/config.xml.
        // try-with-resources closes the context automatically at the end.
        try (ClassPathXmlApplicationContext context =
                     new ClassPathXmlApplicationContext("spring/config.xml")) {

            // Find bean named "ram" and verify that it is an Action implementation.
            Action ram = context.getBean("ram", Action.class);

            // Find bean named "shyam" using the same interface contract.
            Action shyam = context.getBean("shyam", Action.class);

            ram.eat();
            ram.sleep();

            shyam.eat();
            shyam.sleep();
        }
    }
}
```

### Why use `Action` as the variable type?

Both `Ram` and `Shyam` implement `Action`. The calling code can work with the shared interface instead of depending on concrete classes. The XML bean id selects which implementation is returned.

## 7. What happens at runtime?

1. `ClassPathXmlApplicationContext` loads `spring/config.xml` from the classpath.
2. Spring reads the `ram` and `shyam` bean definitions.
3. These beans use the default singleton scope and are normally created during context startup because they are non-lazy singletons.
4. Their constructors print the object-created messages.
5. `getBean("ram", Action.class)` retrieves the `ram` bean.
6. Calling `eat()` and `sleep()` runs the actual object's implementation.
7. The context closes when the `try` block finishes.

Constructor output appears during context initialization; method output appears when the methods are called.

## 8. Core concepts

### IoC — Inversion of Control

Without Spring, application code creates the object:

```java
Action ram = new Ram();
```

With Spring, the container creates and manages the bean, and application code asks for it:

```java
Action ram = context.getBean("ram", Action.class);
```

The responsibility for object creation and lifecycle management moves to the Spring container.

### DI — Dependency Injection

Dependency Injection means giving an object the dependencies it needs from outside rather than having it create those dependencies itself. This example demonstrates IoC and bean lookup, but **does not yet demonstrate one bean being injected into another**, because `Ram` and `Shyam` have no object dependencies.

### Spring bean

An object instantiated, configured, and managed by the Spring container.

### Spring container

Reads configuration, creates beans, injects dependencies when configured, and manages bean lifecycle.

### `BeanFactory` vs `ApplicationContext`

| Feature | `BeanFactory` | `ApplicationContext` |
|---|---|---|
| Core bean management | Yes | Yes |
| Dependency injection | Yes | Yes |
| Application event API | No full context event API | Yes |
| Message resolution | Basic container API | Yes |
| Typical use | Basic container concepts / specialized cases | Most standard Spring applications |
| Relationship | Base container interface | Extends `BeanFactory` |

**Practical rule:** understand both, but usually use `ApplicationContext` for normal Spring applications.

### `ClassPathXmlApplicationContext`

An `ApplicationContext` implementation that loads XML bean definitions from the classpath:

```java
new ClassPathXmlApplicationContext("spring/config.xml");
```

### `getBean()`

```java
context.getBean("ram", Action.class);
```

- `"ram"` is the bean id/name from XML.
- `Action.class` is the expected type.
- The typed overload avoids an explicit cast.

### Singleton scope

By default, Spring maintains one instance for each singleton bean definition within a particular container. If two different contexts load the same XML, each context has its own singleton instances.

## 9. Common mistakes

1. **Wrong XML path:** use `src/main/resources/spring/config.xml` for the path shown in the Java code.
2. **Wrong class name:** the package and class in XML must match exactly, for example `com.spring._01_basics.Ram`.
3. **Wrong bean id:** `getBean("ram", ...)` must match `id="ram"`.
4. **Loading the same XML twice:** this creates two containers and two sets of singleton beans. Usually use one context.
5. **Confusing IoC and DI:** IoC is the broader principle of control being handled by the container; DI is one way dependencies are supplied.
6. **Trying to instantiate the interface:** Spring creates `Ram` and `Shyam`, not `Action`, because XML names the concrete classes.
7. **Forgetting the interface contract:** each concrete class must implement the required methods.

## 10. Interview questions

**What is Spring IoC?** The Spring container takes responsibility for creating and managing application objects.

**What is a Spring bean?** An object managed by the Spring container.

**What is `ApplicationContext`?** A Spring container interface that manages beans and offers additional features such as events and message resolution.

**Does `ApplicationContext` extend `BeanFactory`?** Yes.

**What does `ClassPathXmlApplicationContext` do?** Loads bean definitions from an XML resource on the classpath.

**Why use `getBean()`?** To retrieve a container-managed bean by name and optionally by type.

**What is the default scope?** Singleton—one instance per bean definition per container.

**Does this example show DI?** Not yet. It shows IoC and retrieving beans. Add a class with a constructor dependency to practise DI.

**Why use an interface?** It allows code to depend on a common contract while implementations can vary.

## 11. One-minute revision

- `Action` = contract.
- `Ram`, `Shyam` = implementations.
- `config.xml` = bean definitions.
- `<bean id="ram" class="...Ram" />` = registers the bean definition.
- `ApplicationContext` = commonly used Spring container interface.
- `ClassPathXmlApplicationContext` = loads XML from the classpath.
- `getBean("ram", Action.class)` = retrieves the bean by id and type.
- IoC = Spring controls object creation and lifecycle.
- DI = dependencies are supplied from outside the dependent object.
- Singleton = one instance per bean definition per container by default.
- Use one context for this simple example.
