# expatrio
This is the challenge project of Ali Hlayel, created by the requirements defined in the challenge. It contains 1 microservice which is implemnted by me. I used IntelliJ IDE for development. Infrastructure is created over Spring Boot, Hibernate, postgress database and RESTFul services.

# user Service
The Microservice is built as RESTFul API. it contains methods about user details:

One is is for letting user to Login and obtain a bearer token, one to list all users who have roles "CUSTOMER". updat, create and delete user. All methodes excepts the login are Preauthorized for ADMIN.
on the start of the project the db are created and also few columns of DB are added using flyWay scripts. The project is documented using Postgress. 

<img width="1416" alt="Screenshot 2023-05-30 at 07 55 50" src="https://github.com/ali-hlayel/expatrio/assets/68303228/065d799e-8061-4ae8-9adb-0405c39e5abc">
