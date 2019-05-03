#!/usr/bin/env bash

DOCKER_TAG=$1

if [[ -z "${DOCKER_TAG}" ]]; then
    echo "Docker tag argument is missing"
    exit
else
    echo "Building assembly and Docker image with tag: ${DOCKER_TAG}"
    mvn clean package
    docker build -t ${DOCKER_TAG} .
    docker push ${DOCKER_TAG}
fi