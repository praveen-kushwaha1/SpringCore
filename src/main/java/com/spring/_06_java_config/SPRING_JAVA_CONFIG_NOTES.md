
# Spring Core — Java-Based Configuration

## Learning objectives
- Configure Spring without XML.
- Understand @Configuration and @Bean.
- Discover components using @ComponentScan.
- Inject dependencies using constructors.
- Start Spring using AnnotationConfigApplicationContext.

## Important annotations

- @Configuration: defines a Java configuration class.
- @ComponentScan: discovers annotated components.
- @Component: registers a class through scanning.
- @Service: identifies a service-layer component.
- @Bean: registers the object returned by a method.
- @Value: injects literal values or evaluates expressions.

## Execution flow

App.java
-> AnnotationConfigApplicationContext
-> AppConfig.java
-> Component scanning
-> Register Address through @Bean
-> Create Employee and EmployeeService
-> Inject constructor dependencies
-> Display employee details

## XML replacement

XML:
<context:component-scan base-package="com.spring.java_config"/>

Java:
@ComponentScan(basePackages = "com.spring.java_config")

XML bean definitions can be replaced by @Bean methods.

## Interview revision

1. @Configuration marks a Java configuration class.
2. @Bean registers a method's returned object.
3. @Component enables component scanning.
4. Constructor injection supplies required dependencies.
5. AnnotationConfigApplicationContext loads Java configuration.
6. Singleton is the default Spring bean scope.

## Quick summary

Java configuration replaces XML bean definitions with Java
annotations and methods. It does not remove the Spring container;
it changes how bean definitions are supplied.

6. XML configuration versus Java configuration
   XML-based configuration	Java-based configuration
   config.xml	AppConfig.java
   <context:component-scan>	@ComponentScan
   <bean> definition	@Bean method
   ClassPathXmlApplicationContext	AnnotationConfigApplicationContext
   XML schema configuration	Java annotations and methods
   For example, this XML declaration:
   <bean id="address" class="com.spring.java_config.model.Address">
   <constructor-arg value="Noida"/>
   <constructor-arg value="Uttar Pradesh"/>
   </bean>


is replaced by this Java configuration:
@Bean
public Address address() {
return new Address("Noida", "Uttar Pradesh");
}


The Address class itself doesn't need @Component, because @Bean registers the object explicitly.
7. Important interview questions
   Q1. What is @Configuration?
   It marks a class as a source of bean definitions for Spring's Java-based configuration.
   Q2. What is the difference between @Component and @Bean?
- @Component marks a class for discovery through component scanning.
- @Bean marks a method whose returned object should be registered in the Spring container.
  Q3. What is AnnotationConfigApplicationContext?
  It creates a Spring application context from Java configuration classes or registered annotated components, without requiring XML.
  Q4. Is @ComponentScan mandatory?
  No. It is necessary for automatic discovery in this project, but you can register components explicitly or use other configuration mechanisms.
  Q5. What is the default bean scope?
  Singleton.
  Q6. Can XML and Java configuration be used together?
  Yes. Spring supports mixing configuration styles, but this project intentionally uses Java configuration only