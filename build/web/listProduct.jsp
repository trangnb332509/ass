<%-- 
    Document   : listProduct
    Created on : Mar 16, 2021, 11:18:57 AM
    Author     : Dell Inc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/home.css" rel="stylesheet" type="text/css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <header>
            <h1>Quần Áo Trẻ Em</h1>
            <nav>
                <a href="listEmployee">Thợ</a>
                <a href="listProduct">Sản phẩm</a>
                <a href="listCustomer">Khách hàng</a>
            </nav>
        </header>
        <div>
            <h2>Tất cả các sản phẩm có trong kho</h2>
            <table>
                <tr>
                    <th>Tên sản phẩm</th>
                    <th>Giá</th>
                    <th>Số lượng</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.products}" var="p">
                    <tr>
                        <td>${p.name}</td>
                        <td>${p.price}</td>
                        <td>${p.quantity}</td>
                        <td><a href="updateProduct?id=${p.id}">Cập nhật</a></td>
                        <td><a href="#" onclick="showMess(${p.id})">Xóa sản phẩm</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <form action="insertProduct" method="GET">
            <input type="submit" value="Thêm sản phẩm">
        </form>
    </body>
    <script>
        function showMess(id){
            var option = confirm('Bạn có chắc chắn muốn xóa không?');
            if(option === true){
                window.location.href = 'deleteProduct?id='+id;
            }
        }
    </script>
</html>
