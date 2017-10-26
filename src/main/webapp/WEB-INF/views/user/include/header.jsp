<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default navbar-inverse navbar-fixed-top">
   <div class="container">
     <!-- Brand and toggle get grouped for better mobile display -->
     <div class="navbar-header">
       <a class="navbar-brand" href="#"><i class="fa fa-weibo"></i></a>
     </div>
 
     <!-- Collect the nav links, forms, and other content for toggling -->
     <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
       <ul class="nav navbar-nav">
         <li><a href="/user/article/list">全部</a></li>
         <c:forEach items="${nodeList}" var="node">
         	<li><a href="/user/article/list?nodeId=${node.id}">${node.nodeName}</a></li>
         </c:forEach>
       </ul>
       <ul class="nav navbar-nav navbar-right">
         <c:if test="${not empty sessionScope.curr_admin}">
         	<li><a href="/admin/article/list">后台管理</a></li>
         </c:if>
       </ul>
       <form action="/user/article/list" method="get" class="navbar-form navbar-right">
         <div class="form-group">
           <input type="text" name="keys" class="form-control" placeholder="Search" value="${keys}">
         </div>
         <button class="btn btn-default"><i class="fa fa-search"></i></button>
       </form>
      
     </div><!-- /.navbar-collapse -->
   </div><!-- /.container-fluid -->
 </nav>