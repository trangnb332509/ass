<%-- 
    Document   : listBillDetail
    Created on : Mar 22, 2021, 6:43:50 PM
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
            <h2>${requestScope.c.name}</h2>
            <nav>
                <a href="listBill?cid=${requestScope.c.id}">Hóa đơn</a>
            </nav>
        </header>
        <div>
            <p>${requestScope.c.bills[0].date}</p>
            <table>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                </tr>
                <c:forEach items="${requestScope.c.bills}" var="b">
                    <c:forEach items="${b.billDetails}" var="bd">
                        <tr>
                            <td>${bd.products.name}</td>
                            <td>${bd.products.price}</td>
                            <td>${bd.quantity}</td>
                        </tr>
                    </c:forEach>
                </c:forEach>
            </table>
            <p>Tổng tiền: ${requestScope.tMoney}</p>
            <p>Đã trả: ${requestScope.pMoney}</p>
        </div>
    </body>
</html>
