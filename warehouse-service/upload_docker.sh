#!/usr/bin/env bash

echo "=============== mvn Clean && install  =============="
mvn clean install -DskipTests=true

echo "=============== Create dockerpath $dockerpath =============="
dockerpath=mahsakr/springboot-cloud

echo "=============== Build image and add a descriptive tag =============="
docker build -t $dockerpath .
docker tag $dockerpath:latest 716651577445.dkr.ecr.eu-west-1.amazonaws.com/springboot-cloud:latest

# echo "=============== Authenticate & tag ============="
aws ecr get-login-password --region eu-west-1 | docker login --username AWS --password-stdin 716651577445.dkr.ecr.eu-west-1.amazonaws.com

echo "=============== Push image to a docker repository ============="
docker push 716651577445.dkr.ecr.eu-west-1.amazonaws.com/springboot-cloud:latest

