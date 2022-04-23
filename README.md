# spring-boot-kotlin

```
cd app
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

