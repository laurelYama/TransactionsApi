FROM openjdk:17
WORKDIR /app
COPY target/secure-payment-0.0.1-SNAPSHOT.jar secure-payment.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "secure-payment.jar"]
