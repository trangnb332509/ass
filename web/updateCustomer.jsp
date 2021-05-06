<%-- 
    Document   : updateCustomer
    Created on : Mar 21, 2021, 4:36:41 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="css/formcss.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="updateCustomer" method="POST">
            <h2>Thông tin khách hàng</h2>
            <p>Mã khách hàng:</p>
            <br><input type="text" name="id" value="${requestScope.c.id}" readonly><br>
            <p>Họ tên:</p><br>
            <input type="text" name="name" value="${requestScope.c.name}"><br>
            <p>Số điện thoại:</p><br>
            <input type="text" name="phone" value="${requestScope.c.phone}"><br>
            <input type="submit" value="Sửa">
        </form>
    </body>
</html>
