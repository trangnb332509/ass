<%-- 
    Document   : insertEmployee
    Created on : Mar 16, 2021, 6:34:44 PM
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
        <form action="insertEmployee" method="POST">
            <h2>Thêm Thợ gia công</h2>
            <p>Mã thợ:</p> <input type="text" name="id"><br>
            <p>Họ tên:</p><br> <input type="text" name="name"><br>
            <p>Số điện thoại:</p> <input type="text" name="phone"><br>
            <p>Địa chỉ:</p> <input type="text" name="address"><br>
            <p>Loại thợ:</p> 
            <select name="etid">
                <c:forEach items="${requestScope.eTypes}" var="et">
                    <option value="${et.id}">${et.name}</option>
                </c:forEach>               
            </select>
            <br>
            <p>Mật khẩu:</p><input type="text" name="password"><br>
            <input type="submit" value="Thêm">
        </form>
    </body>
</html>
