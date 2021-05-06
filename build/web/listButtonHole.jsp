<%-- 
    Document   : listButtonHole
    Created on : Mar 20, 2021, 12:06:41 PM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/listcss.css" rel="stylesheet" type="text/css"/>
        <script src="js/pagingButtonHole.js" type="text/javascript"></script>
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
                <a href="insertButtonHole?id=${requestScope.id}">Ghi sổ</a>
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
                    <th>Số cúc</th>
                    <th>Số lượng</th>
                    <th>Giá</th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.bgs}" var="bg">
                    <tr>
                        <td>${bg.date}</td>
                        <td>${bg.buttonhole.name}</td>
                        <td>${bg.bhquantity}</td>
                        <td>${bg.quantity}</td>
                        <td>${bg.buttonhole.price}</td>
                        <c:if test="${requestScope.group == 1}">
                        <td><a href="updateButtonHole?id=${requestScope.id}&date=${bg.date}">Sửa</a></td>
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
                window.location.href = 'deleteButtonHole?id='+id;
            }
        }
    </script>
</html>
