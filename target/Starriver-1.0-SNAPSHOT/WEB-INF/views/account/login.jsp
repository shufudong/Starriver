<%-- 
    Document   : login
    Created on : 2016-1-13, 16:56:53
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ page import="org.apache.shiro.authc.ExcessiveAttemptsException"%>
<%@ page import="org.apache.shiro.authc.IncorrectCredentialsException"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登录</title>
    </head>
    <body>
        <form id="loginForm" action="${ctx}/login" method="post" class="form-horizontal">
            <%
                String error = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
                if (error != null) {
            %>
            <div class="alert alert-info input-sm control-label">
                <button class="close" data-dismiss="alert">x</button>登录失败，请重试
            </div>
            <%
                }
            %>
            <div class="control-group">
                <label for="username" class="control-label">名称：</label>
                <div class="controls">
                    <input type="text" id="username" name="username" value="${username}" class="input-sm required"/>
                </div>
            </div>
            <div class="control-group">
                <label for="password" class="control-label">密码:</label>
                <div class="controls">
                    <input type="password" id="password" name="password" class="input-sm required"/>
                </div>
            </div>

            <div class="control-group">
                <div class="controls">
                    <label class="checkbox" for="rememberMe"><input type="checkbox" id="rememberMe" name="rememberMe"/> 记住我</label>
                    <input id="submit_btn" class="btn btn-primary" type="submit" value="登录"/> <a class="btn" href="${ctx}/register">注册</a>
                    <span>(管理员: <b>admin/admin</b>, 普通用户: <b>user/user</b>)</span>
                </div>
            </div>
        </form>

        <script>
            $(document).ready(function () {
                $("#loginForm").validate();
            });
        </script>

    </body>
</html>
