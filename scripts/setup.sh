
# run postgresql
# https://hub.docker.com/r/bitnami/postgresql
docker run -v C:\dev\docker-data\postgresql:/bitnami/postgresql -p 5432:5432 -d --name postgresql -e POSTGRESQL_USERNAME=utrade -e POSTGRESQL_PASSWORD=utrade -e POSTGRESQL_DATABASE=trade bitnami/postgresql:latest

# run mongodb
# https://hub.docker.com/r/bitnami/mongodb
docker run -v C:\dev\docker-data\mongodb:/bitnami/mongodb -p 27017:27017 -d --name mongodb -e MONGODB_DATABASE=metallica-marketdata -e MONGODB_DATABASE=metallica bitnami/mongodb:latest

#run rabbitmq
# https://hub.docker.com/r/bitnami/rabbitmq
#docker run -v C:\dev\docker-data\rabbitmq:/bitnami -p 5672:5672 -d --name rabbitmq  -e RABBITMQ_USERNAME=guest -e RABBITMQ_PASSWORD=guest bitnami/rabbitmq:latest
#docker run -v C:\dev\docker-data\rabbitmq:/bitnami -p 5672:5672 -p 15672:15672 -d --name rabbitmq  bitnami/rabbitmq:latest
docker run -p 5672:5672 -p 15672:15672 -d --name rabbitmq  -e RABBITMQ_USERNAME=guest -e RABBITMQ_PASSWORD=guest rabbitmq:3-management