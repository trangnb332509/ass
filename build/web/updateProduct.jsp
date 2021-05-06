<%-- 
    Document   : updateProduct
    Created on : Mar 16, 2021, 4:23:26 PM
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
        
        <form action="updateProduct" method="POST">
            <h2>Cập Nhật Sản Phẩm</h2>
            <p>Tên sản phẩm:</p><br>
            <input type="text" name="name" value="${requestScope.p.name}"><br>
            <p>Giá:</p><br>
            <input type="text" name="price" value="${requestScope.p.price}"><br>
            <p>Số lượng hiện có:</p><br>
            <input type="text" name="quantity" value="${requestScope.p.quantity}"><br>
            <p>Số lượng thêm:</p><br>
            <input type="text" name="plusquantity">
            <input type="hidden" name="id" value="${requestScope.p.id}"><br>
            <input type="submit" value="Cập nhật">
        </form>
    </body>
</html>
