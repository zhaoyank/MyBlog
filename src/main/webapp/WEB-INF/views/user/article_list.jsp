<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>文章列表</title>
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
    <!-- Bootstrap 3.3.6 -->
    <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
    <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
    <link rel="stylesheet" href="/static/dist/css/style.css">
    <style>
        body {
            margin-top: 60px;
        }
        
    </style>
</head>
<body>
    <%@ include file="include/header.jsp" %>
              <!-- 导航栏结束 -->

              <!-- 文章列表开始 -->

        <div class="container">
            <div class="row">
                <div class="col-md-9">
                        <c:forEach items="${page.items}" var="article">
                        
                        	<div class="article-span">
                                <div class="media article">
                                        
                                      <div class="media-body">
                                          <a href="/user/article/detail?id=${article.id}"><span class="media-heading"> ${article.title}</span></a> <span class="time date">${article.postTime}</span>

                                          <p class="">${article.simpleContent}</p>
                                          <div class="meta">
                                          	<c:forEach items="${article.labelList}" var="label"><a href="/user/article/list?labelId=${label.id}"><span class="label label-info">${label.labelName}</span></a></c:forEach>
                                      	  </div> 
                                      </div>
                                  
                                      <div class="media-right">
                                      	<c:if test="${not empty article.picture}">
                                      		${article.picture}
                                      	</c:if>
                                      </div>
                                      
                               </div>
    
                        </div>
                        
                        </c:forEach>
    
                        <div class="text-center">
                            <ul id="pagination" class="pagination pagination-lg"></ul>
                        </div>
                </div>    
                <div class="col-md-3">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <h3 class="panel-title">浏览排行</h3>
                        </div>

                            <!-- List group -->
                            <ul class="list-group text-primary">
                            <c:forEach items="${scanSort}" var="article" varStatus="vs">
                                <li class="list-group-item">${vs.count}. <a href="/user/article/detail?id=${article.id}">${article.title}&nbsp;&nbsp;&nbsp;</a> <label class="label label-danger">${article.scanNum}</label></li>
                            
                            </c:forEach>
                            </ul>
                        
                    </div>
                </div>
            </div>
        </div>

             

              
    <!-- jQuery 2.2.3 -->
    <script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
    <!-- <script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script> -->  
    <script src="/static/bootstrap/js/bootstrap.min.js"></script>   
    <!-- page -->
 	<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>
 	
 	<script src="/static/dist/js/moment.js"></script>
	<script src="/static/dist/js/date.js"></script>
	<script>
	    $(function(){
	        $("#pagination").twbsPagination({
	         totalPages:"${page.pageTotal}",
	         visiblePages:3,
	         href:"/user/article/list?p={{number}}&nodeId=${param.nodeid}&labelId=${param.labelId}&keys=${keys}",
	         first: "首页",
	         prev: "上一页",
	         next:"下一页",
	         last:"末页"
	       });  
    	});
	</script>   
</body>
</html>
