<html>
    <head>
        <title>Users</title>
    </head>
    <body>
        <table border="1">
            <caption><h1>User search result</h1></caption>
              <tr>
                  <th>Full name</th>
                  <th>Email</th>
                  <th>Birthday</th>
              </tr>
            <#list users as user>
                <tr><td>${user.name}</td><td>${user.email}</td><td>${user.birthday}</td></tr>
            </#list>
        </table>
    </body>
</html>