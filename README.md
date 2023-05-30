# expatrio
This is the challenge project of Ali Hlayel, created by the requirements defined in the challenge. It contains 1 microservice which is implemnted by me. I used IntelliJ IDE for development. Infrastructure is created over Spring Boot, Hibernate, postgress database and RESTFul services.

# user Service
The Microservice is built as RESTFul API. it contains methods about user details:

One is is for letting user to Login and obtain a bearer token, one to list all users who have roles "CUSTOMER". updat, create and delete user. All methodes excepts the login are Preauthorized for ADMIN.
on the start of the project the db are created and also few columns of DB are added using flyWay scripts. The project is documented using Postgress. 

Here are some pictures for the documentations and also postman test:-

<img width="1416" alt="Screenshot 2023-05-30 at 07 55 50" src="https://github.com/ali-hlayel/expatrio/assets/68303228/065d799e-8061-4ae8-9adb-0405c39e5abc">

{
  "username": "admin",
  "password": "admin"
}

<img width="857" alt="Screenshot 2023-05-30 at 08 03 54" src="https://github.com/ali-hlayel/expatrio/assets/68303228/79e49031-0485-457a-ade8-dbe84db302fa">

When user is authorized
<img width="860" alt="Screenshot 2023-05-30 at 08 05 19" src="https://github.com/ali-hlayel/expatrio/assets/68303228/6ede1398-a762-44c9-9d05-5b4dc7922bf4">

When user has no Authorization
<img width="858" alt="Screenshot 2023-05-30 at 08 06 56" src="https://github.com/ali-hlayel/expatrio/assets/68303228/797a41e6-d332-45fa-acf9-eb6d4b3cdab1">
