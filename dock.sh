#!/usr/bin/env bash

APP_PORT=8080

docker build -f 'dockerfile' --rm -t reksoft .
docker run --name reksoft -p ${APP_PORT}:8080 reksoft