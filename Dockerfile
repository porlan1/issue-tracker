FROM openjdk:8-jdk-alpine
# ----
# Install Maven
RUN apk add --no-cache curl tar bash
ARG MAVEN_VERSION=3.3.9
ARG USER_HOME_DIR="/root"
RUN mkdir -p /usr/share/maven && \
curl -fsSL http://apache.osuosl.org/maven/maven-3/$MAVEN_VERSION/binaries/apache-maven-$MAVEN_VERSION-bin.tar.gz | tar -xzC /usr/share/maven --strip-components=1 && \
ln -s /usr/share/maven/bin/mvn /usr/bin/mvn
ENV MAVEN_HOME /usr/share/maven
ENV MAVEN_CONFIG "$USER_HOME_DIR/.m2"
# speed up Maven JVM a bit
ENV MAVEN_OPTS="-XX:+TieredCompilation -XX:TieredStopAtLevel=1"
ENTRYPOINT ["/usr/bin/mvn"]
COPY pom.xml pom.xml
COPY src src
RUN mvn install

FROM openjdk:8-alpine

COPY --from=0 target/issue-tracker-0.0.1-SNAPSHOT.jar issue-tracker-0.0.1-SNAPSHOT.jar

COPY issue-tracker.yml issue-tracker.yml

COPY src/main/java/app/resources/assets/index.html src/main/java/app/resources/assets/index.html 

CMD java $JAVA_OPTS -Ddw.server.connector.port=$PORT -jar issue-tracker-0.0.1-SNAPSHOT.jar server issue-tracker.yml

EXPOSE 8080
