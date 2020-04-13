# Sample Microservices application

This is very small app for developing microservices.

### Technologies used
This application is written in *Kotlin* language. It uses multiple *Spring Boot applications*, which act as microservices. Currently it consists of 4 microservices:

- **Discovery service** (port 8761), responsible for making one microservice visible to others (technology used: Spring Eureka and Feign clients)
- **Config service** (port 8888), holds configuration properties for other services
- **[Data service](data-server)** (port 8100), for now returning Java8 time dates and times
- **Employee service** (port 8300), for now returning mocked employees with date from Data service (Feign client)

### How can you start that
For running microservices there are lot of possibilities. In this project I use docker. For that we need these steps:

1. Create *Spring Boot* *JAR* archive for each microservice (usually with `mvn clean install`)
2. Create a *Docker* image from this *JAR*. For this purpose I prepared Dockerfile (which is same for each microservice) and e.g you just go into `config-server` folder and run `docker build -t sk.momosi.services/config-server:1.14 .`. Attribute after `-t` is name:version
3. If that was successfull, we can find our images in the list with `docker images` (we need to do that for all microservices)
4. Then we go to root directory of this project. There is [docker-compose](docker-compose.yml) file, which can run whole application with one command `docker-compose up`.

### What else is inside this project
- Jackson Kotlin module, which is configured to use JSON as a media type over HTTP and also support serialization of new Java 8 Time API classes
- [Kotlin Library](https://github.com/piskula/microservices-interface-shared) - Common interface. Interfaces and DTOs are shared among microservices. For this purpose I created a library (in other repository)
- [wait-for](employee-server/wait-for.sh) files, which are shell scripts common for all microservices. Their purpose is to make liveness probes during start of microservices inside Docker, so e.g. *Employee Service* will wait after *Data Service* is fully initialized.
- [Configuration service](config-server) - this service has the one and only task - hold configurations for others. It can be in future exchange with git repository or other technology. Thanks to that we do not need to rebuild new JARs, when we want to change some property inside microservice.
- [Kubernetes](kubernetes) folder with configuration YAML files, which I am using when I want to deploy this app on [GCP](https://cloud.google.com/gcp).
