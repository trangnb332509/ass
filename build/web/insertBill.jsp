<%-- 
    Document   : insertBill
    Created on : Mar 23, 2021, 4:41:56 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/insertBill.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="formm">
        <div>
            <h1>Quynh Hạnh</h1><br>
            <h4>CHUYÊN SX, BÁN BUÔN - BÁN LẺ QUẦN ÁO PHÔNG MAY SẴN</h4><br>
            <h3>QUẦY 91 A3 - TẦNG 3 CHỢ ĐỒNG XUÂN</h3><br>
        </div>
        <form action="insertBill" method="POST">
            <h3>DĐ: 0974.046 839</h3>
            <span>Ngày:</span> <input type="date" name="date"><br>
            <p>Tên khách hàng:</p> <input type="text" name="name" value="${requestScope.c.name}" readonly><br>
            <p>Đã trả:</p> <input type="number" name="paid"><br>
            <input type="hidden" name="cid" value="${requestScope.c.id}"><br>
            <input type="submit" value="Thêm sản phẩm">
        </form>
        </div>
    </body>
</html>
