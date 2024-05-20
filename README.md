## mysql 

```shell
docker run -d --name mysql-container \
 -e MYSQL_ROOT_PASSWORD=root \
 -e MYSQL_DATABASE=unit_test \
 -p 3306:3306 \
 mysql:8.0.37
```

