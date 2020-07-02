#!/bin/bash

function stop() {
	echo "Shutting down docker."
	sudo docker-compose down
	echo "Stopped bash opeation."
	exit 2
}

trap "stop" 2

cd ./spring-boot-app &&
 sudo mvn clean package -DskipTests &&
 cd .. &&
 sudo docker-compose build &&
 sudo docker-compose up
