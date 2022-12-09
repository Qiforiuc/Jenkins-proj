FROM openjdk:17-oracle
WORKDIR /MavenProject
COPY src ./src
COPY pom.xml .
RUN mvn clean
CMD["mvn", "clean", "install"]
