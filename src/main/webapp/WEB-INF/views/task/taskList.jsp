<%-- 
    Document   : taskList
    Created on : 2016-1-12, 10:19:45
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>任务管理</title>
        </head>
        <body>
        <c:if test="${not empty message}">
            <div id="message" class="alert alert-success"><button data-dismiss="alert" class="close">x</button>${message}</div>
        </c:if>
        <c:if test="${page != null}">
            <table id="contentTable" class="table table-striped table-bordered table-condensed">
                <tr>
                    <td>编号</td>
                    <td>标题</td>
                    <td>描述</td>
                    <td>用户编号</td>
                    <td>操作</td>
                </tr>
                <c:forEach items="${pageInfo.list}" var="task">
                    <tr>
                        <td>${task.id}</td>
                        <td>${task.title}</td>
                        <td>${task.description}</td>
                        <td>${task.userId}</td>
                        <td>
                            <a href="${ctx}/task/update/${task.id}">修改</a>&nbsp;
                            <a href="${ctx}/task/delete/${task.id}">删除</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <table>
                <tr>
                    <td>
                        总记录数：${pageInfo.total}&nbsp;&nbsp;
                    </td>
                    <td>
                        总页数：${pageInfo.pages}&nbsp;&nbsp;&nbsp;&nbsp;
                    </td>
                    <c:if test="${!pageInfo.isFirstPage}">
                        <td>
                            <a href="${ctx}/task?page=${pageInfo.firstPage}&rows=${pageInfo.pageSize}">&nbsp;首页&nbsp;</a>
                        </td>
                    </c:if>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <td>
                            <a href="${ctx}/task?page=${pageInfo.prePage}&rows=${pageInfo.pageSize}">&nbsp;上一页&nbsp;</a>
                        </td>
                    </c:if>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="nav">
                        <c:if test="${nav == pageInfo.pageNum}">
                            <td style="font-weight: bold;">[&nbsp;${nav}&nbsp;]</td>
                        </c:if>
                        <c:if test="${nav != pageInfo.pageNum}">
                            <td>
                                <a href="${ctx}/task?page=${nav}&rows=${pageInfo.pageSize}">[&nbsp;${nav}&nbsp;]</a>
                            </td>
                        </c:if>
                    </c:forEach>
                    <c:if test="${pageInfo.hasNextPage}">
                        <td>
                            <a href="${ctx}/task?page=${pageInfo.nextPage}&rows=${pageInfo.pageSize}">&nbsp;下一页&nbsp;</a>
                        </td>
                    </c:if>
                    <c:if test="${!pageInfo.isLastPage}">
                        <td>
                            <a href="${ctx}/task?page=${pageInfo.lastPage}&rows=${pageInfo.pageSize}">&nbsp;尾页&nbsp;</a>
                        </td>
                    </c:if>
                </tr>
            </table>
        </c:if>
        <div><a class="btn" href="${ctx}/task/create">创建任务</a></div>
    </body>
</html>
