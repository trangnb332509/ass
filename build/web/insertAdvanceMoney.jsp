<%-- 
    Document   : insertAdvanceMoney
    Created on : Mar 21, 2021, 2:55:53 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insertAdvanceMoney" method="POST">
            Ngày: <input type="date" name="date"><br>
            Số tiền: <input type="text" name="money"><br>
            <input type="hidden" name="eid" value="${requestScope.id}"><br>
            <input type="submit" value="Vào sổ">
        </form>
    </body>
</html>
