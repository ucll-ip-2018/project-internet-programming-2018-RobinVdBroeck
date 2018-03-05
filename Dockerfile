FROM tomcat:9-jre9
COPY target/runetracker.war /usr/local/tomcat/webapps/runetracker.war

