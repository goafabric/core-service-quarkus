# docker compose
go to /src/deploy/docker and do "./stack up" or "./stack up -native"


# run native image
docker run --pull always --name core-service-quarkus --rm -p50800:50800 goafabric/core-service-quarkus:1.0.3-SNAPSHOT -Xmx64m

# run native image arm
docker run --pull always --name core-service-native --rm -p50800:50800 goafabric/core-service-quarkus-arm64v8:1.0.3-SNAPSHOT -Xmx64m
                             
# auth-server 
docker run --name auth-server --rm -p30200:30200 goafabric/spring-auth-server-native:1.0.2 -Xmx32m

docker run --name auth-server --rm -p30200:30200 goafabric/spring-auth-server-native-arm64v8:1.0.2 -Xmx32m
