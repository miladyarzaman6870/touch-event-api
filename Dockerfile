FROM art.behsacorp.com:5000/openjdk:11
#build working directory
ENV TZ=Asia/Tehran
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian-sec bullseye-security main" > /etc/apt/sources.list
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian bullseye main" >> /etc/apt/sources.list
RUN echo "deb https://cicd:AP25zrhr2xvFcwU8nd4tCL2PXxe@art.behsacorp.com/artifactory/debian bullseye-updates main" >> /etc/apt/sources.list
RUN apt-get update -y -o "Acquire::https::Verify-Peer=false"
RUN apt-get install bash  -y -o "Acquire::https::Verify-Peer=false"

 ENV     _JAVA_OPTIONS="-Xmx512m -Xms256m"
 ENV     SPRING_PROFILES_ACTIVE=prod,api-docs
 ENV     MANAGEMENT_METRICS_EXPORT_PROMETHEUS_ENABLED=true
 ENV     EUREKA_CLIENT_SERVICE_URL_DEFAULTZONE=http://admin:$${jhipster.registry.password}@jhipster-registry:8761/eureka
 ENV     SPRING_CLOUD_CONFIG_URI=http://admin:$${jhipster.registry.password}@jhipster-registry:8761/config
 ENV     SPRING_DATASOURCE_URL=jdbc:oracle:thin:@10.19.43.68:1521:omndbut
 ENV     SPRING_DATASOURCE_USERNAME=omni_channel
 ENV     SPRING_DATASOURCE_PASSWORD=
 ENV     SPRING_LIQUIBASE_URL=jdbc:oracle:thin:@10.19.43.68:1521:omndbut
 ENV     JHIPSTER_CACHE_REDIS_SERVER=redis://omni-redis:6379
 ENV     JHIPSTER_CACHE_REDIS_CLUSTER=false
 #ENV    JHIPSTER_CACHE_REDIS_SERVER=redis://omni-redis:6379
 #ENV    JHIPSTER_CACHE_REDIS_CLUSTER=true
 ENV     JHIPSTER_SLEEP=30
 ENV     SPRING_ELASTICSEARCH_REST_URIS=http://omni-elasticsearch:9200
 ENV     JHIPSTER_CLIENTAPP_CORS_ALLOWED_ORIGINS=http://localhost:4200,http://10.115.72.70:8080,http://10.115.72.70:8081
 #ENV    SERVER_PORT=80
 
 COPY . .
 CMD	java -jar target/touch-event-api-call-0.0.1-SNAPSHOT.jar
