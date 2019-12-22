mvn clean package
docker build -t lmikoto/bot .
docker push lmikoto/bot
docker image prune -f
docker rmi -f  `docker images | grep '<none>' | awk '{print $3}'`