<%-- 
    Document   : adminUserForm
    Created on : 2016-1-14, 21:01:49
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
            <title> 用户管理</title>
        </head>
        <body>
            <form id="inputForm" action="${ctx}/admin/user/update" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${user.id}"/>
            <fieldset>
                <legend><small>用户管理</small></legend>
                <div class="control-group">
                    <label class="control-label">登录名:</label>
                    <div class="controls">
                        <input type="text" value="${user.loginName}" disabled="" class="input-lg" />
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">用户名:</label>
                    <div class="controls">
                        <input type="text" id="name" name="name" value="${user.name}" class="input-lg required"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="plainPassword" class="control-label">密码:</label>
                    <div class="controls">
                        <input type="password" id="plainPassword" name="plainPassword" placeholder="...Leave it blank if no change"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="confirmPassword">确认密码:</label>
                    <div class="controls">
                        <input type="password" id="confirmPassword" name="confirmPassword" equalTo="#plainPassword" class="input-lg"/>
                    </div>
                </div>
                <div class="control-group">
                    <label class="control-label">注册日期:</label>
                    <div class="controls">
                        <span class="help-inline" style="padding:5px 0px"><fmt:formatDate value="${user.registerDate}" pattern="yyyy年MM月dd日  HH时mm分ss秒" /></span>
                    </div>
                </div>
                    <div class="form-actions">
                        <input id="submit_btn" class="btn btn-primary" type="submit" value="提交"/>&nbsp;	
                        <input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()"/>
                </div>
            </fieldset>
        </form>
        <script>
            $(document).ready(function () {
                //聚焦第一个输入框
                $("#name").focus();
                //为inputForm注册validate函数
                $("#inputForm").validate();
            });
        </script>
    </body>
</html>
