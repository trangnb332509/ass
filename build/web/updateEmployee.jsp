<%-- 
    Document   : updateEmployee
    Created on : Mar 18, 2021, 11:04:26 AM
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
        <form action="updateEmployee" method="POST">
            <h2>Thêm Thợ gia công</h2>
            <p>Mã thợ:</p><br>
            <input type="text" name="id" value="${requestScope.e.id}" readonly><br>
            <p>Họ tên:</p><br>
            <input type="text" name="name" value="${requestScope.e.name}"><br>
            <p>Số điện thoại:</p><br>
            <input type="text" name="phone" value="${requestScope.e.phone}"><br>
            <p>Địa chỉ:</p><br> <input type="text" name="address" value="${requestScope.e.address}"><br>
            <p>Loại thợ:</p> 
            <select name="etid">
                <c:forEach items="${requestScope.eTypes}" var="et">
                    <option 
                        <c:if test="${et.id eq requestScope.e.eType.id}">
                            selected="selected"
                        </c:if>
                        value="${et.id}">${et.name}</option>
                </c:forEach>               
            </select>
            <br>
            <input type="submit" value="Sửa">
        </form>
    </body>
</html>
