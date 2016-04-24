<%-- 
    Document   : adminUserList
    Created on : 2016-1-14, 21:15:14
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>

    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>用户管理</title>
        </head>
        <body>
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">×</button>${message}</div>
        </c:if>
        <c:if test="${page != null}">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <thead<tr><th>登录名</th><th>用户名</th><th>注册时间</th><th>管理</th></tr></thead>
                <tbody>
                    <c:forEach items="${pageInfo.list}" var="user">
                        <tr>
                            <td><a href="${ctx}/admin/user/update/${user.id}">${user.loginName}</a></td>
                            <td>${user.name}</td>
                            <td>
                                <fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" />
                            </td>
                            <td><a href="${ctx}/admin/user/delete/${user.id}">删除</a></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </body>
</html>
