#!/usr/bin/env bash
mvn clean && mvn package -Dmaven.test.skip=true && docker-compose down && docker-compose up --build
