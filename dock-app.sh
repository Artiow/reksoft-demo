#!/usr/bin/env bash

APP_PORT=80

docker build --network app-net -f 'dockerfile-app' --no-cache --rm -t app .
docker run --network app-net --name app -p ${APP_PORT}:8080 app