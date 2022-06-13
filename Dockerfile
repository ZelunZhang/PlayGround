FROM java:8-jdk-alpine

COPY ./target/pokeApiApp-0.0.1-SNAPSHOT.jar /usr/app/

WORKDIR /usr/app

RUN sh -c 'touch pokeApiApp-0.0.1-SNAPSHOT.jar'

ENTRYPOINT ["java", "-jar", "pokeApiApp-0.0.1-SNAPSHOT.jar"]