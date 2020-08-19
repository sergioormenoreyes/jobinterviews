FROM adoptopenjdk/openjdk11:latest

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# Default copy (Gradle)
COPY ./build/libs/*.jar /api/app.jar

# Default workspace dir
RUN ls /api
WORKDIR /api

# no root execution
USER www-data

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Doracle.jdbc.timezoneAsRegion=false", "-jar", "/api/app.jar"]
