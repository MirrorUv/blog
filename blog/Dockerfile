FROM openjdk:17

WORKDIR /app

COPY target/blog-0.0.1-SNAPSHOT.jar /app/blog.jar

EXPOSE 9090

ENTRYPOINT ["java","-jar"]

CMD ["/app/blog.jar"]
