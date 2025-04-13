FROM debian
EXPOSE 8080
WORKDIR /app
COPY . /app/
RUN apt update \
    && apt install -y default-jre default-jdk maven postgresql-client
RUN mvn clean package -DskipTests
RUN mvn install -DskipTests

CMD ["mvn", "spring-boot:run"]