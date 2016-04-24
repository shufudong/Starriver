<%-- 
    Document   : profile
    Created on : 2016-1-14, 20:55:43
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
    <!DOCTYPE html>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
            <title>资料修改</title>
        </head>
        <body>
            <form id="inputForm" action="${ctx}/profile" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${user.id}"/>
            <fieldset>
                <legend><small>资料修改</small></legend>
                <div class="control-group">
                    <label for="name" class="control-label">用户名:</label>
                    <div class="controls">
                        <input type="text" id="name" name="name" value="${user.name}" class="input-lg required"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="plainPassword" class="control-label">密码:</label>
                    <div class="controls">
                        <input type="password" id="plainPassword" name="plainPassword" class="input-lg" placeholder="...Leave it blank if no change"/>
                    </div>
                </div>
                <div class="control-group">
                    <label for="confirmPassword" class="control-label">确认密码:</label>
                    <div class="controls">
                        <input type="password" id="confirmPassword" class="input-lg" name="confirmPassword" equalTo="#plainPassword" />
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
