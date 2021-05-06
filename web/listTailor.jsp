<%-- 
    Document   : listTailor
    Created on : Mar 20, 2021, 11:05:30 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/listcss.css" rel="stylesheet" type="text/css"/>
        <script src="js/paging.js" type="text/javascript"></script>
        <link href="css/paging.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h2>${requestScope.ee.name}</h2>
            <nav>
                <c:if test="${requestScope.group == 1}">
                <a href="listEmployee">Thợ</a>
                <a href="#" onclick="mess('${requestScope.id}')">Xóa sổ</a>
                <a href="insertTailor?id=${requestScope.id}">Ghi sổ</a>
                </c:if>
                <a href="listAdvanceMoney?id=${requestScope.id}">Ứng Tiền</a>
                <a href="statisticalTailor?id=${requestScope.id}">Thống kê</a>
            </nav>
        </header>
        <div>
            <table>
                <tr>
                    <th>Ngày</th>
                    <th>Mẫu</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.tgs}" var="tg">
                    <tr>
                        <td>${tg.date}</td>
                        <td>${tg.category.name}</td>
                        <td>${tg.quantity}</td>
                        <td>${tg.category.price}</td>
                        <c:if test="${requestScope.group == 1}">
                        <td><a href="updateTailor?id=${requestScope.id}&date=${tg.date}">Sửa</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <div id="botpager" class="pager"></div>
    </body>
    <script>
        rederPager("botpager",${requestScope.pageindex},${requestScope.totalpage},1,"${requestScope.id}");
        function mess(id){
            var option = confirm('Bạn có chắc chắn muốn xóa không?');
            if(option===true){
                window.location.href = 'deleteTailor?id='+id;
            }
        }
    </script>
</html>
