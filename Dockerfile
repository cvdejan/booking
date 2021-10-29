FROM bellsoft/liberica-openjdk-alpine-musl:11.0.13-8
EXPOSE 8080
ADD target/booking-rest-api.jar booking-rest-api.jar
CMD java -jar booking-rest-api.jar