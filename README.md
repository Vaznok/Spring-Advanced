Request examples:
    - UserController
        1. http://localhost:8080/trainings/user?email=laory@yandex.ru
        2. http://localhost:8080/trainings/user?name=Dmytro%20Babichev
    - BookingController:
        1. http://localhost:8080/trainings/tickets?event=The%20revenant&auditorium=Yellow%20hall&dateTime=2016-02-05T21:18
        p.s. Returns PDF if header "accept=application/pdf" else render web page
        