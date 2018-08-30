#!/usr/bin/env bash

docker network create app-net
docker build -f 'dockerfile-appdb' --no-cache --rm -t appdb .
docker run --network app-net --name appdb appdb