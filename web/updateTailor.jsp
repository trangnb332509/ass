<%-- 
    Document   : updateTailor
    Created on : Mar 20, 2021, 4:38:59 PM
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
        <form action="updateTailor" method="POST">
            <p>Ngày:</p><br> 
            <input type="date" name="date" value="${requestScope.tg.date}" readonly><br>
            <p>Loại:</p> 
            <select name="cid">
                <c:forEach items="${requestScope.categories}" var="c">
                    <option 
                        <c:if test="${c.id eq requestScope.tg.category.id}">
                            selected="selected"
                        </c:if>
                        value="${c.id}">${c.name}</option>
                </c:forEach>               
            </select>
            <br>
            <p>Số lượng:</p><br> <input type="number" name="quantity" value="${requestScope.tg.quantity}"><br>
            <input type="hidden" name="eid" value="${requestScope.id}"><br>
            <input type="submit" value="Vào sổ">
        </form>
    </body>
</html>
