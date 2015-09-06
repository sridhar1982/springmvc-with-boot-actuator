# springmvc-with-boot-actuator
springmvc application with spring-boot-actuator. NOT a spring-boot-application

This is a simple SpringMVC REST application. I added spring-boot-actuator depedency to get metrics and health and 
expose a new endpoint that gives status information.

steps:

1. clone the git project

2. Run mvn clean install t7:run

3. visit the following urls to ensure it works

`http://localhost:8090/MyController/data/person?id=15`

`http://localhost:8090/MyController/data/health`

`http://localhost:8090/MyController/data/metrics`

Now change the spring-boot-actuator dependency in pom.xml
```<version>1.2.5.RELEASE</version>```

run mvn clean install t7:run

The errors will thrown during container start-up.

NOTE: I do NOT have `@EnableAutoConfiguration` because I observed autoconfiguration is interferring with REAL springMVC application.


