#!/bin/bash

function stop() {
	echo "Shutting down docker."
	docker-compose down
	echo "Stopped bash opeation."
	exit 2
}

trap "stop" 2

  docker-compose build &&
  docker-compose up
