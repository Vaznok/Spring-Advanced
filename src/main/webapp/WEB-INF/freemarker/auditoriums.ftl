<html>
<head>
    <title>Auditoriums</title>
</head>
<body>
<table border="1">
    <caption><h1>Auditorium List</h1></caption>
    <tr>
        <th>Auditorium</th>
        <th>Seats Count</th>
        <th>Vip Seats</th>
    </tr>
    <#list auditoriums as auditorium>
        <tr><td>${auditorium.name}</td><td>${auditorium.seatsNumber}</td><td>${auditorium.vipSeats}</td></tr>
    </#list>
</table>
</body>
</html>