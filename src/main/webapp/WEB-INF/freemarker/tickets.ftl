<html>
<head>
    <title>Tickets</title>
</head>
<body>
<table border="1">
    <caption><h1>Ticket search result</h1></caption>
    <tr>
        <th>Event</th>
        <th>Seats</th>
        <th>Customer</th>
        <th>Date</th>
    </tr>
    <#list tickets as ticket>
        <tr><td>${ticket.event.name}</td><td>${ticket.seats}</td><td>${ticket.user.name}</td><td>${ticket.dateTime}</td></tr>
    </#list>
</table>
</body>
</html>