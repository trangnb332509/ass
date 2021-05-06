<%-- 
    Document   : updateAdvanceMoney
    Created on : Mar 21, 2021, 3:18:15 PM
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
        <form action="updateAdvanceMoney" method="POST">
            <p>Ngày:</p><br>
            <input type="date" name="date" value="${requestScope.adm.date}" readonly><br>
            <p>Số tiền:</p><br>
            <input type="text" name="money" value="${requestScope.adm.money}"><br>
            <input type="hidden" name="id" value="${requestScope.adm.id}">
            <input type="hidden" name="eid" value="${requestScope.adm.emp.id}"><br>
            <input type="submit" value="Vào sổ">
        </form>
    </body>
</html>
