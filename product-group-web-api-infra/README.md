## Development Environment Setup
Install Docker Desktop and your favorite database development tool such as DBeaver.

if you use Postgres as database server , use following steps to create your database.

1. Start your local Postgres Server `docker run --detach --name postgre-dev-server --env="POSTGRES_PASSWORD=postgres" --publish 5432:5432 postgres`
2. Connect to database server with username `postgres` / password `postgres` and create database using  following script. `CREATE DATABASE product_management`

## Generate controller API from Open API specification
Edit specification file located at `/src/main/resources/product-group.yaml` then run ` gradle openApiGenerate` command.

## Build Local Docker Image

1. `./gradlew buildLocalImage`

## Clean up local docker images

1. `docker images | grep product-group | awk '{print $3}' | xargs  docker rmi --force`
