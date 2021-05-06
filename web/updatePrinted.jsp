<%-- 
    Document   : updatePrinted
    Created on : Mar 21, 2021, 11:17:26 AM
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
        <form action="updatePrinted" method="POST">
            <p>Ngày:</p><br> 
            <input type="date" name="date" value="${requestScope.pg.date}" readonly><br>
            <p>Loại:</p> 
            <select name="pid">
                <c:forEach items="${requestScope.patterns}" var="p">
                    <option 
                        <c:if test="${p.id eq requestScope.pg.pattern.id}">
                            selected="selected"
                        </c:if>
                        value="${p.id}">${p.name}</option>
                </c:forEach>               
            </select>
            <br>
            <p>Số lượng:</p><br>
            <input type="text" name="quantity" value="${requestScope.pg.quantity}"><br>
            <input type="hidden" name="eid" value="${requestScope.id}"><br>
            <input type="submit" value="Vào sổ">
        </form>
    </body>
</html>
