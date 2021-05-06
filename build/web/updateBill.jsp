<%-- 
    Document   : updateBill
    Created on : Mar 23, 2021, 10:25:16 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/formcss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="updateBill" method="POST">
            <h2>${requestScope.c.name}</h2>
            <input type="date" name="date" value="${requestScope.b.date}"readonly><br>
            <input type="text" name="total" value="${requestScope.b.total}"readonly><br>
            <input type="text" name="paid" value="${requestScope.b.paid}">
            <input type="hidden" name="bid" value="${requestScope.b.bid}">
            <input type="hidden" name="cid" value="${requestScope.c.id}"><br>
            <input type="submit" value="Sửa">
        </form>
    </body>
</html>
