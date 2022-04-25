# spring-boot-kotlin

## Get Started
### Configure postgresql database
```
brew install postgresql
brew services start postgresql

createuser -s postgres
createdb -U postgres -w postgres

brew install flyway
```
Make sure you have postgresql installed with a user named `postgres` and a database named `postgres`, you can check how they're used in `app/src/main/resources/migrations/application-dev.yaml` and `app/script/migrate-db.sh` 

`app/script/migrate-db.sh` will use flyway to migrate, migration files are located in `app/src/main/resources/migrations`

```
cd app
./script/migrate-db.sh
./gradlew bootRun
```

### Sign up
```
curl -X POST -v localhost:8080/auth/signup -H 'Content-type:application/json' -d '{"username": "user01", "password": "password", "enabled": 1}' | jq
```

### Sign in
```
curl -u client:secret -X POST localhost:8080/oauth/token\?grant_type=password\&username=user01\&password=password | jq .
```
