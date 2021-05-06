<%-- 
    Document   : insertCustomer
    Created on : Mar 21, 2021, 4:25:17 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/insertE.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insertCustomer" method="POST">
            <h2>Thông tin khách hàng</h2>
            <p>Mã khách hàng:</p><br>
            <input type="text" name="id"><br>
            <p>Họ tên:</p><br>
            <input type="text" name="name"><br>
            <p>Số điện thoại:</p><br>
            <input type="text" name="phone"><br>
            <p>Mật khẩu:</p><br>
            <input type="password" name="password"><br>
            <input type="submit" value="Thêm">
        </form>
    </body>
</html>
