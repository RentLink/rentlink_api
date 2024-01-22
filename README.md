![BUILD AND TEST](https://github.com/RentLink/rentlink_api/actions/workflows/gradle.yml/badge.svg)

# rentlink_api

SWAGGER UI: http://localhost:8080/swagger-ui/index.html#/
MAIL SLURPPER: http://localost:4436

To run project: 
```zsh
docker-compose up -d
```
Scripts:
To restart everything and clean databases:
```zsh
./restart-all.sh
```
To restart everything, rebuild API and clean databases:
```zsh
./rebuild-all.sh
```
To ONLY rebuild API and without cleaning databases:
```zsh
./rebuild-api.sh
```
