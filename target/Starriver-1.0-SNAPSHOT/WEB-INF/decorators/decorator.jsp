<%-- 
    Document   : decorator
    Created on : 2016-1-5, 19:58:18
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="${ctx}/static/jquery/jquery-2.1.4.min.js"></script>
        <script type="text/javascript" src="${ctx}/static/jquery-validation/jquery.validate.min.js"></script>
        <script type="text/javascript" src="${ctx}/static/jquery-validation/localization/messages_zh.min.js"></script>
        <script type="text/javascript" src="${ctx}/static/bootstrap/js/bootstrap.min.js"></script>
        <link type="text/css" rel="stylesheet" href="${ctx}/static/styles/default.css"/>
        <link type="text/css" rel="stylesheet" href="${ctx}/static/bootstrap/css/bootstrap.min.css"/>

        <title>MDemo<sitemesh:write property='title'/></title>
        <sitemesh:write property="head"/>
    </head>
<body>
    <div class="container">
        <%@include file="/WEB-INF/decorators/header.jsp" %>
        <div id="content">
            <sitemesh:write property="body"/>
        </div>
        <%@include file="/WEB-INF/decorators/footer.jsp" %>
    </div>

</body>
</html>
