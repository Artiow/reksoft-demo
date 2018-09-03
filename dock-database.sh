#!/usr/bin/env bash

docker network create --driver bridge app-net || true
docker build -f 'database/dockerfile' --no-cache --rm -t database .
docker run --network app-net --name database database