FROM openjdk:8
ADD target/payment-service order-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "payment-service.jar"]