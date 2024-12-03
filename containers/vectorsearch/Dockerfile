FROM openjdk:21-slim
RUN apt-get update && apt-get -y install bash curl
COPY vector-search-app-1.0-SNAPSHOT.jar vector-search-app-1.0-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "vector-search-app-1.0-SNAPSHOT.jar"]