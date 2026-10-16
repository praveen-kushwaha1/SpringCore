# Spring Core Notes: XML Dependency Injection

## 1. Introduction

Spring XML configuration lets us declare Java objects as beans and tell the Spring IoC container how to create and connect them. This project uses `Student`, `Address`, and `Department`.

## 2. Learning objectives

- Declare beans with `id` and `class`.
- Understand IoC, dependency injection, and `ApplicationContext`.
- Inject dependencies through constructors and setters.
- Use `constructor-arg`, `property`, `value`, and `ref`.
- Configure an inner bean.
- Inject `List`, `Set`, and `Map` collections.
- Run and test XML configurations.

## 3. Important definitions

- **IoC (Inversion of Control):** object creation and lifecycle management are handled by the Spring container instead of being controlled entirely by application code.
- **Dependency Injection (DI):** an object receives its dependencies from an external source rather than constructing them itself.
- **Bean:** an object managed by Spring.
- **ApplicationContext:** a Spring container that loads bean definitions and manages beans.
- **Bean ID:** the name used to reference or retrieve a bean.
- **Fully qualified class name:** package plus class name, such as `com.ncr.xml.Student`.

## 4. XML bean definition

```xml
<bean id="department" class="com.ncr.xml.Department"/>
```

`id` identifies the bean. `class` tells Spring which Java class to instantiate.

## 5. Constructor injection

```xml
<bean id="student" class="com.ncr.xml.Student">
    <constructor-arg ref="address"/>
</bean>
```

Spring passes the referenced `Address` bean to `Student(Address)`. Constructor injection is useful for required dependencies.

Constructor arguments can also be literal values:

```xml
<constructor-arg index="0" value="H-101"/>
```

When overloaded constructors make argument resolution unclear, use `index`, `name`, or `type` to make the intended constructor argument explicit.

## 6. Setter injection

```xml
<property name="studentName" value="Praveen"/>
<property name="department" ref="department"/>
```

The first invokes `setStudentName("Praveen")`; the second passes the `Department` bean to `setDepartment(...)`.

## 7. `value` vs `ref`

| XML attribute | Purpose | Example |
|---|---|---|
| `value` | Literal/simple value converted to the target type where supported | `value="101"` |
| `ref` | Reference to another bean | `ref="department"` |

## 8. Inner bean

```xml
<constructor-arg>
    <bean class="com.ncr.xml.Address">
        <property name="city" value="Lucknow"/>
    </bean>
</constructor-arg>
```

The `Address` is declared inline and has no independent bean ID. Use a top-level bean plus `ref` when it must be shared or retrieved separately.

## 9. Collections

```xml
<property name="skills">
    <list>
        <value>Java</value>
        <value>Spring</value>
    </list>
</property>

<property name="certifications">
    <set>
        <value>Java</value>
        <value>SQL</value>
    </set>
</property>

<property name="marks">
    <map>
        <entry key="Java" value="90"/>
        <entry key="SQL" value="88"/>
    </map>
</property>
```

| XML element | Java type | Behavior |
|---|---|---|
| `list` | `List` | Preserves order; permits duplicates |
| `set` | `Set` | Stores unique elements; order depends on implementation |
| `map` | `Map` | Stores key-value pairs |
| `entry` | Map entry | Defines a key and value |

## 10. Execution flow

```text
App.main()
   |
   v
ClassPathXmlApplicationContext
   |
   v
Loads spring/06-complete-student.xml
   |
   v
Creates Address and Department beans
   |
   v
Creates Student and injects Address
   |
   v
Injects simple values, Department, and collections
   |
   v
getBean("student", Student.class)
   |
   v
student.displayStudent()
```

## 11. Project structure

```text
SpringCoreXML/
├── pom.xml
├── .gitignore
├── SPRING_CORE_NOTES.md
├── src/main/java/com/ncr/xml/
│   ├── App.java
│   ├── Student.java
│   ├── Address.java
│   └── Department.java
├── src/main/resources/spring/
│   ├── 01-beans.xml
│   ├── 02-constructor-injection.xml
│   ├── 03-setter-injection.xml
│   ├── 04-inner-bean.xml
│   ├── 05-collections.xml
│   └── 06-complete-student.xml
└── src/test/java/com/ncr/xml/
    └── XmlConfigurationTest.java
```

## 12. Common mistakes

- Putting XML under `src/main/java` instead of `src/main/resources`.
- Using a resource path that does not match the file location.
- Using a bean ID in `ref` that does not exist.
- Using a `property` name without a matching setter.
- Supplying constructor arguments that do not match an available constructor.
- Calling methods on a dependency that was never injected.
- Forgetting to close the context; use try-with-resources for `ClassPathXmlApplicationContext`.
- Expecting a `Set` to preserve insertion order.
- Forgetting that a bean definition and a Java object are not the same thing.

## 13. Interview questions and answers

**Q1. What is a Spring bean?**  
An object created, configured, and managed by the Spring container.

**Q2. What is IoC?**  
It means application code delegates object creation and management to a container or framework.

**Q3. What is dependency injection?**  
A technique where an object receives the dependencies it needs from outside itself.

**Q4. Constructor injection vs setter injection?**  
Constructor injection supplies dependencies during construction and suits required dependencies. Setter injection supplies dependencies after construction and can suit optional or changeable properties.

**Q5. What is the difference between `value` and `ref`?**  
`value` supplies a literal value; `ref` points to another Spring bean.

**Q6. What is an inner bean?**  
A bean declared inside another bean's constructor argument or property, without an independent bean ID.

**Q7. What is the difference between `List`, `Set`, and `Map`?**  
A `List` is ordered and permits duplicates, a `Set` stores unique elements, and a `Map` stores key-value pairs.

**Q8. What does `ApplicationContext` do?**  
It loads bean definitions and provides bean creation, dependency injection, lifecycle management, and other container services.

**Q9. Where should XML configuration be placed in a Maven project?**  
Usually under `src/main/resources`, so it is copied onto the runtime classpath.

**Q10. Why use constructor injection?**  
It makes required dependencies explicit and helps avoid partially initialized objects.

## 14. Commands

Run the test suite:

```bash
mvn test
```

Build the project:

```bash
mvn clean package
```

Run `App.main()` from your IDE. The default config is `spring/06-complete-student.xml`. To choose another config, add a program argument such as `spring/04-inner-bean.xml`.

## 15. Quick revision summary

- `bean` declares a bean.
- `id` names a bean.
- `class` identifies its Java class.
- `constructor-arg` supplies constructor arguments.
- `property` configures a setter-backed property.
- `value` supplies a literal value.
- `ref` references another bean.
- `list`, `set`, and `map` inject collections.
- An inner bean is declared inline within its containing bean.
- `ApplicationContext` loads configuration and manages beans.
