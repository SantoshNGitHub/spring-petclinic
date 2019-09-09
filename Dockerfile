FROM openjdk:8

COPY ./target/spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar /user/src/app/

WORKDIR /user/src/app/

ENTRYPOINT ["java","-jar","spring-petclinic-2.1.0.BUILD-SNAPSHOT.jar"]

EXPOSE 8189     