FROM openjdk:8
ADD target/payment-service.jar payment-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "payment-service.jar"]