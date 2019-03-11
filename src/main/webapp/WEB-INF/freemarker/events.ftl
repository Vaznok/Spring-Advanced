<html>
    <head>
        <title>Events</title>
    </head>
    <body>
        <table border="1">
            <caption><h1>Event List</h1></caption>
            <tr>
                <th>Event</th>
                <th>Price</th>
                <th>Date</th>
                <th>Auditorium</th>
            </tr>
            <#list events as event>
                <tr><td>${event.name}</td><td>${event.basePrice}</td><td>${event.dateTime}</td><td>${event.auditorium.name}</td></tr>
            </#list>
        </table>
    </body>
</html>