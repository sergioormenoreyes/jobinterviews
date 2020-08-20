FROM gradle:6.6-jdk11 AS gradle_build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

FROM adoptopenjdk/openjdk11:latest

# Default to UTF-8 file.encoding
ENV LANG C.UTF-8

# Default copy (Gradle)
COPY --from=gradle_build /home/gradle/src/build/libs/*.jar /app/app.jar


# Default workspace dir
RUN mkdir /api
RUN ls /api
WORKDIR /api

# no root execution
USER www-data

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-Doracle.jdbc.timezoneAsRegion=false", "-jar", "/app/app.jar"]
