<%-- 
    Document   : insertBillDetail
    Created on : Mar 23, 2021, 5:35:36 PM
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
        <form action="insertBillDetail" method="POST">
            <h3>DĐ: 0974.046 839</h3>
            <span>Ngày:</span>p> <input type="date" name="date"value="${requestScope.bill.date}" readonly><br>
            <p>Tên khách hàng:</p> <input type="text" name="name" value="${requestScope.customer.name}" readonly><br>
            <c:forEach items="${requestScope.bds}" var="bd">
                <span>${bd.products.name}: ${bd.quantity}x${bd.products.price} </span><br>
            </c:forEach>
            <p>Loại:</p> 
            <select name="pid">
                <c:forEach items="${requestScope.products}" var="p">
                    <option value="${p.id}">${p.name}</option>
                </c:forEach>               
            </select>
            <br>
            <p>Số lượng:</p><input type="number" name="quantity"><br>
            <input type="hidden" name="cid" value="${requestScope.customer.id}"><br>
            <input type="submit" value="Thêm sản phẩm mới">
            <a href="listBill?cid=${requestScope.customer.id}">Xong</a>
        </form>
        </div>
    </body>
</html>
