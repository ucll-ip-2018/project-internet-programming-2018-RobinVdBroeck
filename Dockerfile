FROM tomcat:9-jre9
COPY web/target/runetracker.war /usr/local/tomcat/webapps/runetracker.war

