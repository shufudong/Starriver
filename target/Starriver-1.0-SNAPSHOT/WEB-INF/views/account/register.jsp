<%-- 
    Document   : register
    Created on : 2016-1-13, 16:23:56
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>注册</title>
        <script>
            $(document).ready(function () {
                //聚焦第一个输入框
                $("#loginName").focus();
                //为inputForm注册validate函数
                $("#inputForm").validate({
                    rules: {
                        loginName: {
                            remote: "${ctx}/register/checkLoginName"
                        }
                    },
                    messages: {
                        loginName: {
                            remote: "用户登录名已存在"
                        }
                    }
                });
            });
        </script>
    </head>
    <body>
        <form id="inputForm" action="${ctx}/register" method="post" class="form-horizontal">
            <fieldset>
                <legend><small>用户注册</small></legend>
                <div class="control-group">
                    <label for="loginName">登录名:</label>
                    <div class="controls">
                        <input type="text" id="loginName" name="loginName" class="input-lg required" minlength="3"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="name" class="control-label">用户名:</label>
                    <div class="controls">
                        <input type="text" id="name" name="name" class="input-lg required"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="plainPassword" class="control-label">密码:</label>
                    <div>
                        <input type="password" id="plainPassword" name="plainPassword" class="input-lg required"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="confirmPassword" class="control-label">确认密码:</label>
                    <div class="controls">
                        <input type="password" id="confirmPassword" name="confirmPassword" equalTo="#plainPassword" class="input-lg required"/>
                    </div>
                </div>
                <div class="form-actions">
                    <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
                    <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
                </div>
            </fieldset>
        </form>
    </body>
</html>
