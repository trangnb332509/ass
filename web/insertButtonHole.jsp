<%-- 
    Document   : insertButtonHole
    Created on : Mar 21, 2021, 11:35:13 AM
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
        <form action="insertButtonHole" method="POST">
            <p>Ngày:</p><br>
            <input type="date" name="date"><br>
            <p>Loại:</p> 
            <select name="bhid">
                <c:forEach items="${requestScope.buttonHoles}" var="bh">
                    <option value="${bh.id}">${bh.name}</option>
                </c:forEach>               
            </select>
            <br>
            <p>Số lượng hàng:</p><br>
            <input type="number" name="quantity"><br>
            <p>Số lượng cúc:</p><br>
            <input type="number" name="bhquantity"><br>
            <input type="hidden" name="eid" value="${requestScope.id}"><br>
            <input type="submit" value="Vào sổ">
        </form>
    </body>
</html>
