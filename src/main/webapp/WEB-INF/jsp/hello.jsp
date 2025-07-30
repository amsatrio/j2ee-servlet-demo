<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Controllers</title>
    </head>
    <body>
        <p>Controllers</p>
        <p>Username: <%= request.getAttribute("userName") %></p>
        <p>response: <%= request.getAttribute("responseDto") %></p>
    </body>
</html>