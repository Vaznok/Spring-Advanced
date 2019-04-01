<html>
<head>
    <title>Account</title>
</head>
<body>
<table border="1">
    <caption><h1>Account details</h1></caption>
    <tr>
        <th>Full name</th>
        <th>Balance</th>
    </tr>
    <#list accounts as account>
        <tr><td>${account.user.name}</td><td>${account.cash}</td></tr>
    </#list>
</table>
</body>
</html>