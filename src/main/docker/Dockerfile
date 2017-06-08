FROM openjdk:8u102-jre
EXPOSE 8080
VOLUME /tmp
ADD pintabar-server-0.0.1-SNAPSHOT.war pintabar-server.war
ADD wrapper.sh wrapper.sh
RUN bash -c 'chmod +x /wrapper.sh'
RUN sh -c 'touch /pintabar-server.war'
ENTRYPOINT ["/bin/bash", "/wrapper.sh"]