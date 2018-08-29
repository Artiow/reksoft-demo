#!/usr/bin/env bash
docker build -f 'dockerfile' --rm -t reksoft .
docker run --name reksoft -p 8080:8080 reksoft