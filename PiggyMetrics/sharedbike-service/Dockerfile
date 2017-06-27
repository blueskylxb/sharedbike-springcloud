FROM java:8-jre
MAINTAINER Alexander Lukyanchikov <sqshq@sqshq.com>

ADD ./target/sharedbike-service.jar /app/
CMD ["java", "-Xmx200m", "-jar", "/app/sharedbike-service.jar"]

EXPOSE 6000