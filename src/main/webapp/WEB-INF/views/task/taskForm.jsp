<%-- 
    Document   : taskForm
    Created on : 2016-1-12, 15:11:12
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
            <form id="inputForm" action="${ctx}/task/${action}" method="post" class="form-horizontal">
            <input type="hidden" name="id" value="${task.id}"/>
            <fieldset>
                <legend><small>管理任务</small></legend>
                <div>
                    <label for="task_title" class="control-label">任务名称:</label>
                    <div class="controls">
                        <input type="text" id="task_title" name="title"  value="${task.title}" class="input-lg required" minlength="3"/>
                    </div>
                </div>	
                    <div class="control-group">
                        <label for="description" class="control-label">任务描述:</label>
                        <div class="controls">
                        <textarea id="description" name="description" class="input-lg">${task.description}</textarea>
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
                $("#task_title").focus();
                //为inputForm注册validate函数
                $("#inputForm").validate();
            });
        </script>
    </body>
</html>
