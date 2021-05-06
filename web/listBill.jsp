<%-- 
    Document   : listBill
    Created on : Mar 22, 2021, 6:18:27 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/listcss.css" rel="stylesheet" type="text/css"/>
        <script src="js/pagingBill.js" type="text/javascript"></script>
        <link href="css/paging.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h2>${requestScope.c.name}</h2>
            <nav>
                <c:if test="${requestScope.group == 1}">
                    <a href="listCustomer">Khách hàng</a>
                    <a href="#" onclick="mess('${requestScope.cid}')">Xóa sổ</a>
                    <a href="insertBill?cid=${requestScope.c.id}">Ghi sổ</a>
                </c:if>
                <a href="statisticalCustomer?cid=${requestScope.c.id}">Thống kê</a>
            </nav>
        </header>
        <div>
            <form action="listBill" method="GET">
                <input type="date" name="date" value="${requestScope.date}">
                <input type="submit" value="Tìm">
                <input type="hidden" name="cid" value="${requestScope.c.id}">
            </form>
            <table>
                <tr>
                    <th>Ngày</th>
                    <th>Tổng tiền</th>
                    <th>Đã trả</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.bills}" var="b">
                    <tr>
                        <td>${b.date}</td>
                        <td>${b.total}</td>
                        <td>${b.paid}</td>
                        <td><a href="listBillDetail?bid=${b.bid}&cid=${requestScope.c.id}&date=${b.date}">Chi tiết</a></td>
                        <c:if test="${requestScope.group==1}">
                            <td><a href="updateBill?bid=${b.bid}&cid=${requestScope.c.id}">Sửa</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <c:if test="${!requestScope.search}">
        <div id="botpager" class="pager"></div>
        </c:if>
    </body>
    <script>
        rederPager("botpager",${requestScope.pageindex},${requestScope.totalpage},1,"${requestScope.c.id}");
        function mess(id) {
            var option = confirm('Bạn có chắc chắn muốn xóa không?');
            if (option === true) {
                window.location.href = 'deleteBill?id=' + id;
            }
        }
    </script>
</html>
