#! /bin/bash

printf "Start"
docker compose up -d --no-deps --build rentlink
printf "DONE"