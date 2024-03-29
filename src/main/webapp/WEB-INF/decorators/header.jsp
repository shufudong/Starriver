<%-- 
    Document   : header
    Created on : 2015-12-23, 19:22:37
    Author     : jesse
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<div id="header">
    <div id="title">
        <h1><a href="${ctx}">Spring-MyBatis</a><small>--应用演示</small>
            <shiro:user>
                <div class="btn-group pull-right">
                    <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="icon-user"></i> <shiro:principal property="name"/>
                        <span class="caret"></span>
                    </a>

                    <ul class="dropdown-menu">
                        <shiro:hasRole name="admin">
                            <li><a href="${ctx}/admin/user">Admin Users</a></li>
                            <li class="divider"></li>
                        </shiro:hasRole>
                        <li><a href="${ctx}/profile">Edit Profile</a></li>
                        <li><a href="${ctx}/logout">Logout</a></li>
                    </ul>
                </div>
            </shiro:user>
        </h1>
    </div>
</div>
