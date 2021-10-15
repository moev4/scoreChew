#Base image must support the archetecture you are on
# arm 64 in my case
FROM openjdk:17
#ENV JAVA_HOME=/usr/java/openjdk-17
WORKDIR /app

#install depenencies prior to app 
COPY .mvn/ .mvn
COPY mvnw pom.xml ./
RUN ./mvnw dependency:go-offline

#build and run application 
COPY src ./src

#CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.profiles=mysql"]
RUN ./mvnw package
RUN mv target/*.jar target/app.jar
CMD ["java", "-jar", "target/app.jar"]