<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Log in</title>
</head>
<body>

<h1>Log in</h1>

<form role="form" action="http://localhost:8080${rc.getContextPath()}/login" method="post">
    <div>
        <label for="email">Email address</label>
        <input type="username" name="username" id="username" required autofocus/>
    </div>
    <div>
        <label for="password">Password</label>
        <input type="password" name="password" id="password" required/>
    </div>
    <div>
        <label for="remember-me">Remember me</label>
        <input type="checkbox" name="remember-me" id="remember-me"/>
    </div>
    <button type="submit">Sign in</button>
    <#if error ??>
        <h4>${error}</h4>
    <#elseif message ??>
        <h4>${message}</h4>
    </#if>
</form>
</body>
</html>