Boas vindas ao projeto de Reservas de quartos
-------
Resumo 
----
Projeto de uma API simulando reservas de quartos com usuários, quartos e as reservas em si. O projeto conta com arquitetura robusta, pensada para uma situação do mundo real, com classes e atributos que se realcionam entrea si. As regras de negócios foram bem estruturadas,
pensando em regras que seguem a lógica, como por exemplo : Um quarto não pode possuir duas reservas, quando seu status for OCUPADO ele se torna um quarto restrito.
Diversas regras de negócio foram criadas e implementadas seguindo a lógica, desperto a curiosidade do leitor para avalia-las. Um dos diferencias deste projeto foi a integração total de testes que percorrem todas as classes de serviço e validações.

Techs
----
Java 24

Spring Boot Web

Spring Data Jpa para persistência de dados

PostGreSql 18 como banco de dados

Jackson (Escrever arquivos .json)

Junit5 para testes automatizados

Postman para enviar requisições HTTP (GET, POST, PUT, DELETE)

Git - Versionamento de código

Arquitetura
----
O projeto foi construido separando as reponsabilidades por pacotes. (REPOSITORY, SERVICE, CONTROLLERS, MODEL, DTO, EXCEPTIONS, VALIDAÇÕES). Garantindo assim um código muito modularizado e de fácil manutenção.
Para as validações foi implementada uma interface no padrão STRATEGY, assim varias validações podem ser executadas de uma só vez, um exemplo disso é quando chamamos a requisição POST para criar uma reserva,
dentro deste metódo o validador precisa realizar todas as validações necessárias para que não acontençam erros graves, exemplo: um quarto tem que estar dispónivel para realizar uma reserva e etc...

Status do projeto
-----
O projeto está em sua primeira versão, mais a frente mais funcionalidades e novas techs serão implementadas, como o Spring Security

----------------------------------------------------
Welcome to the Room Reservation project
-----
Summary
------
An API project simulating room reservations featuring users, rooms, and the reservations themselves. The project has a robust architecture designed for a real-world scenario, with classes and attributes that relate to each other. The business rules were well-structured according to logic; for example: a room cannot have two reservations, and when its status is OCCUPIED, it becomes a restricted room. Several business rules were created and implemented following logic, and I invite the reader to evaluate them. One of the differentiators of this project was the full integration of tests that cover all service classes and validations.

Techs
-------
Java 24

Spring Boot Web

Spring Data JPA for data persistence

PostgreSQL 18 as the database

Jackson (To write .json files)

JUnit 5 for automated tests

Postman to send HTTP requests (GET, POST, PUT, DELETE)

Git - Code versioning

Architecture
--------
The project was built by separating responsibilities into packages (REPOSITORY, SERVICE, CONTROLLERS, MODEL, DTO, EXCEPTIONS, VALIDATIONS). This ensures highly modularized code that is easy to maintain. For validations, an interface was implemented using the STRATEGY pattern, allowing multiple validations to be executed at once. An example of this is when calling a POST request to create a reservation; within this method, the validator needs to perform all necessary validations to prevent serious errors—for example, a room must be available to make a reservation, and so on.

Project Status
-----
The project is in its first version. Later, more features and new technologies will be implemented, such as Spring Security.







