#! /bin/bash

printf "Start"
docker-compose down
sleep 2
rm -rf pgdata
rm -rf pgadmin-pgdata
docker-compose up -d --build rentlink
docker-compose up -d
sleep 5
docker exec -it database /bin/bash -c "psql -U root -d kratos -a -f /tmp/kratos.sql"
docker restart kratos
printf "DONE"