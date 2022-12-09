FROM maven:3.8.6
WORKDIR /MavenProject
COPY src ./src
COPY pom.xml .
RUN mvn clean
CMD["mvn", "clean", "install"]
