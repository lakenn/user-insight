FROM openjdk:8-alpine
MAINTAINER Kenneth Lam <mklam4@gmail.com>

ADD target/insight-0.0.1-SNAPSHOT.jar /app/insight/insight.jar
ADD clicks.csv selections.csv /data/
EXPOSE 8080
CMD ["java", "-jar", "/app/insight/insight.jar"]
