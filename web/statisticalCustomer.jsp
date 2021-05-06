<%-- 
    Document   : statisticalCustomer
    Created on : Mar 23, 2021, 11:29:40 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/listcss.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h2>Thống kê</h2>
            <nav>
                <a href="listBill?cid=${requestScope.c.id}">Sổ ghi</a>
            </nav>
        </header>
        <div>
            <table>
                <tr>
                    <th>Tên mẫu</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th>Tiền</th>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <td>${p.price*p.quantity}</td>
                    </tr>
                </c:forEach>
            </table>
            <p>Tổng tiền: ${requestScope.tMoney}</p>
            <p>Đã trả: ${requestScope.pMoney}</p>
            <p>Còn thiếu: ${requestScope.lMoney}</p>
        </div>
    </body>
</html>
