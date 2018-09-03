FROM tomcat:9.0-jre8
EXPOSE 8080
RUN rm -rf /usr/local/tomcat/webapps/ROOT
ADD ./target/demo-0.0.1-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
ADD ./appfiles /appfiles
ENTRYPOINT ["catalina.sh", "run"]