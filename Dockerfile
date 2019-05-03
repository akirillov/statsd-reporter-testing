FROM java:8

COPY target/scratch-statsd-reporter.jar /opt/scratch-statsd/scratch-statsd-reporter.jar

WORKDIR /opt/scratch-statsd

ENTRYPOINT ["java", "-jar", "scratch-statsd-reporter.jar"]