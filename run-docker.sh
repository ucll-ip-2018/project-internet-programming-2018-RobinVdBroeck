#!/usr/bin/env bash
mvn clean && mvn package && docker-compose down && docker-compose up --build
