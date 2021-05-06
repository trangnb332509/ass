<%-- 
    Document   : listAdvanceMoney
    Created on : Mar 20, 2021, 12:23:10 PM
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
            <h2>Sổ Ứng Tiền</h2>
            <nav>
                <a href="#" onclick="option('${requestScope.id}',${requestScope.etid})">Sổ ghi</a>
                <c:if test="${requestScope.group==1}">
                <a href="#" onclick="mess('${requestScope.id}')">Xóa sổ</a>
                <a href="insertAdvanceMoney?id=${requestScope.id}">Ứng tiền</a>
                </c:if>
            </nav>
        </header>
        <div>
            <table>
                <tr>
                    <th>Ngày</th>
                    <th>Số tiền</th>
                    <th></th>
                </tr>
                <c:forEach items="${requestScope.ams}" var="am">
                    <tr>
                        <td>${am.date}</td>
                        <td>${am.money}</td>
                        <c:if test="${requestScope.group==1}">
                        <td><a href="updateAdvanceMoney?id=${am.id}">Sửa</a></td>
                        </c:if>
                    </tr>
                </c:forEach>
            </table>
        </div>
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
                window.location.href = 'deleteAdvanceMoney?id='+id;
            }
        }
    </script>
</html>
