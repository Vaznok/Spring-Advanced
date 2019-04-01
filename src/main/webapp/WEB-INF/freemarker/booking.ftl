<!DOCTYPE html>
<#import "/spring.ftl" as spring />
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Booking</title>
</head>
<body>

<h1>Book ticket</h1>

<form name="user" action="/trainings/book" method="post">
    Event: <input type="text" name="eventName" /><br/>
    Auditorium: <input type="text" name="auditoriumName"/><br/>
    Date time: <input type="text" name="dateTime"/><br/>
    Seat: <input type="text" name="seat"/><br/>
    <input type="submit" value="Book" />
</form>

</body>
</html>