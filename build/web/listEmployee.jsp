<%-- 
    Document   : listEmployee
    Created on : Mar 16, 2021, 5:46:50 PM
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
            <h2>Danh sách thợ gia công</h2>
            <table>
                <tr>
                    <th>Mã thợ</th>
                    <th>Họ tên</th>
                    <th>Số điện thoại</th>
                    <th>Địa chỉ</th>
                    <th>Loại thợ</th>
                    <th></th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.emps}" var="e">
                    <tr>
                        <td><a href="#" onclick="option('${e.id}',${e.eType.id})">${e.id}</a></td>
                        <td>${e.name}</td>
                        <td>${e.phone}</td>
                        <td>${e.address}</td>
                        <td>${e.eType.name}</td>
                        <td><a href="updateEmployee?id=${e.id}">Sửa</a></td>
                        <td><a href="#" onclick="mess('${e.id}')">Xóa</a></td>
                    </tr>
                </c:forEach>
            </table>
        </div>
        <form action="insertEmployee" method="GET">
            <input type="submit" value="Thêm thợ">
        </form>
    </body>
    <script>
        function option(eid,etid){
            if(etid === 1){
                window.location.href = 'listTailor?id='+eid;
            }else if(etid === 2){
                window.location.href = 'listPrinted?id='+eid;
            }else{
                window.location.href = 'listButtonHole?id='+eid;
            }
        }
        function mess(id){
            var option = confirm('Bạn có chắc chắn muốn xóa không?');
            if(option===true){
                window.location.href = 'deleteEmployee?id='+id;
            }
        }
    </script>
</html>
