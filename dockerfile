FROM maven:latest AS build
WORKDIR /reksoft-demo
ADD . /reksoft-demo
RUN mvn clean package -DskipTests

FROM tomcat:9.0-jre8
EXPOSE 8080
COPY --from=build /reksoft-demo/target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=build /reksoft-demo/target/demo-0.0.1-SNAPSHOT /usr/local/tomcat/webapps/ROOT
CMD ["catalina.sh", "run"]