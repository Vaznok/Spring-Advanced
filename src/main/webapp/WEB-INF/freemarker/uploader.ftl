<!DOCTYPE HTML>
<html>
<head>
    <meta charset="utf-8">
    <title>File uploader</title>
</head>
<body>

<form method="post" enctype="multipart/form-data" action="/${rc.getContextPath()}/upload">
    <p>Add users data in json   <input type="file" name="fileUsers"></p>
    <p>Add events data in json   <input type="file" name="fileEvents"></p>
    <p><input type="submit"></p>
</form>

</body>
</html>