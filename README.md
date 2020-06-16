# todo-backend
API server for todo web application


## how to run it

### 1. install openjdk11
- install [openjdk11](https://jdk.java.net/java-se-ri/11)
- [guide for window](https://loginfo.dev/OpenJDK-%EC%84%A4%EC%B9%98%EB%B0%A9%EB%B2%95)

### 2. install & run MySQL
#### with docker (recommend) 
- install [docker](https://docs.docker.com/compose/install)
- run `docker-compose up` command

#### without docker
- install [mysql 8.0.x](https://dev.mysql.com/downloads/mysql/)

### 3. run server
#### with intellij
- click `Run 'TodoApplication'` button

#### with command line
```bash
./gradlew bootRun
```