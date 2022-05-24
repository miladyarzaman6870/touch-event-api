FROM art.behsacorp.com:5000/openjdk:11
#build working directory
ENV TZ=Asia/Tehran
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian-sec bullseye-security main" > /etc/apt/sources.list
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian bullseye main" >> /etc/apt/sources.list
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian bullseye-updates main" >> /etc/apt/sources.list
RUN apt-get update -y -o "Acquire::https::Verify-Peer=false"
RUN apt-get install bash  -y -o "Acquire::https::Verify-Peer=false"

ENV _JAVA_OPTIONS="-Xmx512m -Xms256m"
ENV SPRING_PROFILES_ACTIVE=prod
ENV queueDurable=true
 
 EXPOSE 8082
 COPY target/touch-event-api-call-0.0.1-SNAPSHOT.jar .
 CMD	java -jar touch-event-api-call-0.0.1-SNAPSHOT.jar
