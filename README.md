# springmvc-with-boot-actuator
springmvc application with spring-boot-actuator. NOT a spring-boot-application

This is a simple SpringMVC REST application. I added spring-boot-actuator depedency to get metrics and health and 
expose a new endpoint that gives status information.

**steps**

1. clone the git project

2. Run `mvn clean install t7:run`

3. visit the following urls to ensure it works

`http://localhost:8090/MyController/data/person?id=15`

`http://localhost:8090/MyController/data/health`

`http://localhost:8090/MyController/data/metrics`

Now change the spring-boot-actuator dependency in pom.xml

`<version>1.2.5.RELEASE</version>`

run `mvn clean install t7:run`

The errors will thrown during container start-up.

**NOTE:** I do NOT have `@EnableAutoConfiguration` because I observed autoconfiguration is interferring with REAL springMVC application.

**UPDATE**

I want to use `spring-boot-actuator1.2.5` over `1.1.1` only because 1.2.* returns `DiskSpace` info over `/health endpoint`.

As proposed by Andy Wilkinson, the problem is due to incompatiable spring framework version. I should have used `Spring framework 4.1.1` or above

Since I don't have much chance to upgrade our production spring framework to 4.1.1, I thought of using `spring-boot-actuator.1.1.1` with `DiskSpaceHealthIndicator` (please look at com.disney.guestcontroller.app.controller.DiskSpaceHealthIndicator - borrowed from `https://github.com/matsev/spring-boot/blob/721dc900105e30fa6c6df2c8b90c8d71091da0a6/spring-boot-actuator/src/main/java/org/springframework/boot/actuate/health/DiskSpaceHealthIndicator.java`).

However, after adding it and restarting using mvn clean install t7:run, I get the following error.

```
SEVERE: StandardWrapper.Throwable
org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with name 'diskSpaceHealthIndicator' defined in file [/Users/rajas019/Google Drive/Spring-example&webapps/springmvc-with-boot-actuator/target/tomcat/webapps/MyController/WEB-INF/classes/com/sample/springApp/component/DiskSpaceHealthIndicator.class]: Unsatisfied dependency expressed through constructor argument with index 1 of type [long]: : Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string: "${health.threshold.bytes:10485760}"; nested exception is org.springframework.beans.TypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string: "${health.threshold.bytes:10485760}"
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:741)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:185)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1114)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1017)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:504)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:304)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:300)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:700)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:760)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:482)
	at org.springframework.web.servlet.FrameworkServlet.configureAndRefreshWebApplicationContext(FrameworkServlet.java:658)
	at org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext(FrameworkServlet.java:530)
	at org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:484)
	at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:136)
	at javax.servlet.GenericServlet.init(GenericServlet.java:160)
	at org.apache.catalina.core.StandardWrapper.initServlet(StandardWrapper.java:1280)
	at org.apache.catalina.core.StandardWrapper.load(StandardWrapper.java:1091)
	at org.apache.catalina.core.StandardContext.loadOnStartup(StandardContext.java:5176)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5460)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase.addChildInternal(ContainerBase.java:901)
	at org.apache.catalina.core.ContainerBase.addChild(ContainerBase.java:877)
	at org.apache.catalina.core.StandardHost.addChild(StandardHost.java:633)
	at org.apache.catalina.startup.HostConfig.deployDirectory(HostConfig.java:1113)
	at org.apache.catalina.startup.HostConfig$DeployDirectory.run(HostConfig.java:1671)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)
Caused by: org.springframework.beans.TypeMismatchException: Failed to convert value of type 'java.lang.String' to required type 'long'; nested exception is java.lang.NumberFormatException: For input string: "${health.threshold.bytes:10485760}"
	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:77)
	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:47)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:875)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:855)
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:805)
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:733)
	... 32 more
Caused by: java.lang.NumberFormatException: For input string: "${health.threshold.bytes:10485760}"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Long.parseLong(Long.java:578)
	at java.lang.Long.valueOf(Long.java:803)
	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:158)
	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:113)
	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:430)
	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:403)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:181)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:93)
	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:64)
	... 37 more

Sep 06, 2015 10:55:54 AM org.apache.catalina.core.StandardContext loadOnStartup
SEVERE: Servlet /MyController threw load() exception
java.lang.NumberFormatException: For input string: "${health.threshold.bytes:10485760}"
	at java.lang.NumberFormatException.forInputString(NumberFormatException.java:65)
	at java.lang.Long.parseLong(Long.java:578)
	at java.lang.Long.valueOf(Long.java:803)
	at org.springframework.util.NumberUtils.parseNumber(NumberUtils.java:158)
	at org.springframework.beans.propertyeditors.CustomNumberEditor.setAsText(CustomNumberEditor.java:113)
	at org.springframework.beans.TypeConverterDelegate.doConvertTextValue(TypeConverterDelegate.java:430)
	at org.springframework.beans.TypeConverterDelegate.doConvertValue(TypeConverterDelegate.java:403)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:181)
	at org.springframework.beans.TypeConverterDelegate.convertIfNecessary(TypeConverterDelegate.java:93)
	at org.springframework.beans.TypeConverterSupport.doConvert(TypeConverterSupport.java:64)
	at org.springframework.beans.TypeConverterSupport.convertIfNecessary(TypeConverterSupport.java:47)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.doResolveDependency(DefaultListableBeanFactory.java:875)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.resolveDependency(DefaultListableBeanFactory.java:855)
	at org.springframework.beans.factory.support.ConstructorResolver.resolveAutowiredArgument(ConstructorResolver.java:805)
	at org.springframework.beans.factory.support.ConstructorResolver.createArgumentArray(ConstructorResolver.java:733)
	at org.springframework.beans.factory.support.ConstructorResolver.autowireConstructor(ConstructorResolver.java:185)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.autowireConstructor(AbstractAutowireCapableBeanFactory.java:1114)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBeanInstance(AbstractAutowireCapableBeanFactory.java:1017)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.doCreateBean(AbstractAutowireCapableBeanFactory.java:504)
	at org.springframework.beans.factory.support.AbstractAutowireCapableBeanFactory.createBean(AbstractAutowireCapableBeanFactory.java:475)
	at org.springframework.beans.factory.support.AbstractBeanFactory$1.getObject(AbstractBeanFactory.java:304)
	at org.springframework.beans.factory.support.DefaultSingletonBeanRegistry.getSingleton(DefaultSingletonBeanRegistry.java:228)
	at org.springframework.beans.factory.support.AbstractBeanFactory.doGetBean(AbstractBeanFactory.java:300)
	at org.springframework.beans.factory.support.AbstractBeanFactory.getBean(AbstractBeanFactory.java:195)
	at org.springframework.beans.factory.support.DefaultListableBeanFactory.preInstantiateSingletons(DefaultListableBeanFactory.java:700)
	at org.springframework.context.support.AbstractApplicationContext.finishBeanFactoryInitialization(AbstractApplicationContext.java:760)
	at org.springframework.context.support.AbstractApplicationContext.refresh(AbstractApplicationContext.java:482)
	at org.springframework.web.servlet.FrameworkServlet.configureAndRefreshWebApplicationContext(FrameworkServlet.java:658)
	at org.springframework.web.servlet.FrameworkServlet.initWebApplicationContext(FrameworkServlet.java:530)
	at org.springframework.web.servlet.FrameworkServlet.initServletBean(FrameworkServlet.java:484)
	at org.springframework.web.servlet.HttpServletBean.init(HttpServletBean.java:136)
	at javax.servlet.GenericServlet.init(GenericServlet.java:160)
	at org.apache.catalina.core.StandardWrapper.initServlet(StandardWrapper.java:1280)
	at org.apache.catalina.core.StandardWrapper.load(StandardWrapper.java:1091)
	at org.apache.catalina.core.StandardContext.loadOnStartup(StandardContext.java:5176)
	at org.apache.catalina.core.StandardContext.startInternal(StandardContext.java:5460)
	at org.apache.catalina.util.LifecycleBase.start(LifecycleBase.java:150)
	at org.apache.catalina.core.ContainerBase.addChildInternal(ContainerBase.java:901)
	at org.apache.catalina.core.ContainerBase.addChild(ContainerBase.java:877)
	at org.apache.catalina.core.StandardHost.addChild(StandardHost.java:633)
	at org.apache.catalina.startup.HostConfig.deployDirectory(HostConfig.java:1113)
	at org.apache.catalina.startup.HostConfig$DeployDirectory.run(HostConfig.java:1671)
	at java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:511)
	at java.util.concurrent.FutureTask.run(FutureTask.java:266)
	at java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1142)
	at java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:617)
	at java.lang.Thread.run(Thread.java:745)

Sep 06, 2015 10:55:54 AM org.apache.coyote.AbstractProtocol start
INFO: Starting ProtocolHandler ["http-bio-8090"]
Sep 06, 2015 10:55:54 AM org.apache.catalina.startup.Catalina start
INFO: Server startup in 1277 ms
```
