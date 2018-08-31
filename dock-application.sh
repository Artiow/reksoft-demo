#!/usr/bin/env bash

APP_PORT=80

docker build --network app-net -f 'dockerfile' --no-cache --rm -t application .
docker run --network app-net --name application -p ${APP_PORT}:8080 application