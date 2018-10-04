# Elcus.Test project for Propellerhead

Back-end part of the Propellerhead test project called Elcus.

Undoubtedly it needs some corrections(for example returning 0 in pagination count) and improvements, but i just wanted to show you my approach to developing back-end in short time.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.


### Installing

to start it locally build project :

note: passing variable in command line it is only for local build.In future, we could take this variables directly from  CI secret variables fro example, so they will be hidden.
```
./gradlew clean build -Pdatasource_url="jdbc:postgresql://elcus.cvqn6lr0edx8.ap-southeast-2.rds.amazonaws.com:5432/elcus?characterEncoding=UTF-8" -Pdatasource_username="propellerhead_dev" -Pdatasource_password='GpwkN2FNZzfa4aTu' --stacktrace
```

And then start and watch it on localhost:8080

```
./gradlew bootRun -x flyWayMigrate -x generateElcusJooqSchemaSource
```

## Deployment

This application has been deployed on AWS infrastructure using ElasticBeanstalk(good for small projects);

Database has been created using AWS RDS.

Load balancer: http://elcuspropellerhead-env.nqjep9prut.ap-southeast-2.elasticbeanstalk.com

Try it :  http://elcuspropellerhead-env.nqjep9prut.ap-southeast-2.elasticbeanstalk.com/customers


## Built With

* [Java 8](https://www.oracle.com/technetwork/java/javase/overview/java8-2100321.html) - Yes, still 8.I could explain why not newer.
* [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/) - The web framework used for developing web-based applications
* [Gradle](https://docs.gradle.org/current/userguide/userguide.html) - Build automation system
* [jOOQ](https://www.jooq.org/learn/) - Tool for communication with database
* [FlyWay](https://flywaydb.org/documentation/) - Version control for your database
* [Lombok](https://projectlombok.org/features/all) - Used to generate some boilerplate code.
 

## Authors

* **Eldar Shykhmuradov**
