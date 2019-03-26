-------------
1. SPRING MVC
    Request examples:
        - UserController
            1. http://localhost:8080/trainings/user?email=laory@yandex.ru
            2. http://localhost:8080/trainings/user?name=Dmytro%20Babichev
        - BookingController:
            1. http://localhost:8080/trainings/tickets?event=The%20revenant&auditorium=Yellow%20hall&dateTime=2016-02-05T21:18
            p.s. Returns PDF if header "accept=application/pdf" else render web page
        - AuditoriumController
            1. http://localhost:8080/trainings/auditorium
            2. http://localhost:8080/trainings/auditorium/Blue%20hall
        - EventController
            1. http://localhost:8080/trainings/event
            2. http://localhost:8080/trainings/event/The%20revenant
        - UploadController
            1. http://localhost:8080/trainings/upload
            You can add prepared file in json format in corresponding input fields.
            Files location - /resources/files (events.txt, users.txt)
        - WelcomeController
            1. http://localhost:8080/trainings/
------------------------------------------------------------------------------------------            
2. SPRING SECURITY
    - To check that remember-me token is saved in datastore
        http://localhost:8080/trainings/h2-console/
        where jdbc url = jdbc:h2:~/data/spring-db
    Users:
        login: vetall@gmail.com 
        pass: 12345
        ROLE_BOOKING_MANAGER,ROLE_REGISTERED_USER
        
        login: laory@yandex.ru 
        pass: 12345
        ROLE_REGISTERED_USER
    - Request example only for users with ROLE_BOOKING_MANAGER
        http://localhost:8080/trainings/tickets?event=The%20revenant&auditorium=Yellow%20hall&dateTime=2016-02-05T21:18
------------------------------------------------------------------------------------------      
Deployment:
    1. mvn clean package 
    2. copy trainings.war from /target/ to /webapp/ tomcat folder
    3. launch tomcat server