
##  Настройка параметров подключения Flyway

### В файле `build.gradle`
Замените `YOUR_POSTGRES_PASSWORD` на ваш реальный пароль:
```groovy
     flyway {
            url = 'jdbc:postgresql://localhost:5432/postgres'
            user = 'postgres'
            password = 'YOUR_POSTGRES_PASSWORD'
     }
```
### В  файле `application.properties`
Замените `YOUR_POSTGRES_PASSWORD` на ваш реальный пароль:
```properties
spring.datasource.password=YOUR_POSTGRES_PASSWORD
```