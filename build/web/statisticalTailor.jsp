<%-- 
    Document   : statisticalTailor
    Created on : Mar 20, 2021, 5:24:38 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<link href="css/listcss.css" rel="stylesheet" type="text/css"/>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h2>${requestScope.ee.name}</h2>
            <nav>
                <a href="#" onclick="option('${requestScope.id}',${requestScope.etid})">Sổ ghi</a>
            </nav>
        </header>
        <div>
            <table>
                <tr>
                    <th>Mẫu</th>
                    <th>Số lượng</th>
                        <c:if test="${requestScope.etid == 3}">
                        <th>Số lượng cúc</th>
                        </c:if>
                    <th>Giá</th>
                    <th>Tiền</th>
                </tr>
                <c:forEach items="${requestScope.common}" var="c">
                    <tr>
                        <td>${c.name}</td>
                        <td>${c.sum}</td>
                        <c:if test="${requestScope.etid == 3}">
                            <td>${c.bhquantity}</td>
                        </c:if>
                        <td>${c.price}</td>
                        <td>${c.money}</td>
                    </tr>
                </c:forEach>
            </table>
            <p>Tổng tiền: ${requestScope.sSumMoney}</p>
            <p>Tiền ứng: ${requestScope.formatMoney}</p>
            <p>Cần trả: ${requestScope.debt}</p>
            
        </div>
    </body>
    <script>
        function option(eid, etid) {
            if (etid === 1) {
                window.location.href = 'listTailor?id=' + eid;
            } else if (etid === 2) {
                window.location.href = 'listPrinted?id=' + eid;
            } else {
                window.location.href = 'listButtonHole?id=' + eid;
            }
        }
    </script>
</html>
