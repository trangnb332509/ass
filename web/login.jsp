<%-- 
    Document   : login
    Created on : Mar 11, 2021, 11:33:59 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/logincss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="login">
            <h2>Welcome</h2>
            <form action="login" method="POST">
                <p>Username</p><br>
                <input type="text" name="username" placeholder="Enter username"/><br>
                <p>Password</p><br>
                <input type="password" name="password" placeholder="Enter password"/><br>
                <span>${mess}</span>
                <input type="submit" value="Login"/>
            </form>
        </div>
    </body>
</html>
