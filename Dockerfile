FROM maven:3.8.3-openjdk-17
WORKDIR /MavenProject
COPY src ./src
COPY pom.xml .
RUN mvn clean install

