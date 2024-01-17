#! /bin/bash

printf "Start"
docker-compose down
sleep 2
rm -rf pgdata
rm -rf pgadmin-pgdata
docker-compose up -d --build rentlink
sleep 2
printf "DONE"