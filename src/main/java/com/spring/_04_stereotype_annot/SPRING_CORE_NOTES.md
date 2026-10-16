
# Spring Core — Stereotype Annotations

## 1. Learning objectives
- Understand `@Component` and component scanning.
- Learn singleton and prototype bean scopes.
- Inject literal values using `@Value`.
- Resolve beans using Spring Expression Language (SpEL).
- Configure collection beans in XML.

## 2. Important annotations

| Annotation | Purpose |
|---|---|
| `@Component` | Registers a component for scanning |
| `@Scope("prototype")` | Requests a new instance per bean retrieval |
| `@Value("1")` | Injects a literal value |
| `@Value("#{ad}")` | Evaluates a SpEL expression |

## 3. Execution flow

App.main()
-> Load config.xml
-> Scan the component package
-> Register the employee component
-> Register the ad list
-> Inject field values and the address list
-> Retrieve employee twice
-> Receive two prototype instances

## 4. Common mistakes
- Incorrect XML resource path.
- Missing component scanning.
- Mismatched bean IDs in SpEL.
- Assuming hash codes uniquely identify objects.
- Expecting automatic destruction callbacks for prototype beans.

## 5. Interview revision
- Default scope: singleton.
- Prototype: new instance for each bean retrieval.
- SpEL: evaluates expressions such as `#{ad}`.
- Component scanning: discovers eligible annotated classes.
- `@Service` and `@Repository` are specialized stereotypes.

## 6. Quick summary
@Component registers the class.
@Scope controls bean scope.
@Value injects values.
SpEL resolves expressions.
XML util:list defines a collection bean.


5. Interview questions
   Q1. What is @Component?
   It marks a class as a candidate for Spring component scanning so Spring can register it as a bean.
   Q2. What are stereotype annotations?
   @Component, @Service, @Repository, and @Controller are stereotype annotations. They identify classes for Spring's component scanning, with specialized roles for some annotations.
   Q3. What is the default Spring bean scope?
   singleton, which means one shared instance per bean definition per Spring container.
   Q4. What does @Scope("prototype") do?
   It requests a new instance each time the bean is retrieved from the Spring container.
   Q5. What is the difference between @Value("ad") and @Value("#{ad}")?
- @Value("ad") injects the literal string "ad".
- @Value("#{ad}") evaluates a SpEL expression and resolves the bean or value named ad.
  Q6. Does Spring call destruction callbacks automatically for prototype beans?
  Not in the same way it does for singleton beans. The caller is responsible for cleanup of prototype instances when required.
  Q7. Can @Component be used without XML configuration?
  Yes. Java-based configuration using @Configuration and @ComponentScan can replace XML component scanning.