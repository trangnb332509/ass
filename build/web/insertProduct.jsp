<%-- 
    Document   : insertProduct
    Created on : Mar 16, 2021, 11:46:04 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/formcss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <form action="insertProduct" method="POST">
            <h2>Thêm Sản Phẩm</h2>
            <p>Tên sản phẩm:</p><br>
            <input type="text" name="name"><br>
            <p>Giá:</p><br>
            <input type="number" name="price"><br>
            <p>Số lượng:</p><br>
            <input type="number" name="quantity"><br>
            <input type="submit" value="Thêm">
        </form>
    </body>
</html>
