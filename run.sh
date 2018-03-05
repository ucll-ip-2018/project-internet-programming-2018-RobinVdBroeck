#!/usr/bin/env bash
mvn package -Dmaven.test.skip=true
eval $(docker-machine env)
docker-compose up

