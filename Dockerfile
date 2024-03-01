FROM gradle:8.1.1-jdk17 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar --no-daemon

FROM gcr.io/distroless/java17-debian11
RUN mkdir -p /opt/rentlink/files/
WORKDIR boot
COPY --from=build /home/gradle/src/build/libs/rentlink-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/boot/app.jar"]