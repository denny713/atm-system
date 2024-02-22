ATM System Using Springboot

Requirement :

Java JDK 17
Maven
PostgreSQL Database
Spring Boot
JPA
Step :

Clean Project Terlebih dahulu menggunakan command 'mvn clean install' tanpa tanda kutip.
Import Data untuk Database yang ada di folder 'db' menggunakan liquibase.
Run Project menggunakan command 'mvn spring-boot:run' tanpa tanda kutip.
Testing bisa dilakukan via Swagger via link 'localhost:7979/swagger-ui/index.html'.
Terdapat juga Unit Testing pada class 'src/test/java/com/multi/MainTest.java'.
Testing bisa juga dilakukan menggunakan Postman Tools curl berikut:

curl --location 'localhost:7979/v1/transfer' \
--header 'Content-Type: application/json' \
--data '{
"fromAccountNo": "112233",
"toAccountNo": "445566",
"amount": 1000000
}'

Happy Coding.

Created By: Denny Afrizal