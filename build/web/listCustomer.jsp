<%-- 
    Document   : listCustomer
    Created on : Mar 21, 2021, 3:46:50 PM
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
            <h2>Danh sách khách hàng</h2>
            <table>
                <tr>
                    <th>Mã khách hàng</th>
                    <th>Họ tên</th>
                    <th>Số điện thoại</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.customers}" var="c">
                    <tr>
                        <td><a href="listBill?cid=${c.id}">${c.id}</a></td>
                        <td>${c.name}</td>
                        <td>${c.phone}</td>
                        <td><a href="updateCustomer?id=${c.id}">Sửa</a></td>
                        <td><a href="#" onclick="mess('${c.id}')">Xóa</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <form action="insertCustomer" method="GET">
            <input type="submit" value="Thêm khách hàng">
        </form>
    </body>
    <script>
        function mess(id){
            var option = confirm('Bạn có chắc chắn muốn xóa không?');
            if(option===true){
                window.location.href = 'deleteCustomer?id='+id;
            }
        }
    </script>
</html>
