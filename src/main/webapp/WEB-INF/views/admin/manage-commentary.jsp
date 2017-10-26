<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>评论列表</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <%@ include file="include/css.jsp" %>
</head>
<body class="hold-transition skin-blue sidebar-mini">
<!-- Site wrapper -->
<div class="wrapper">

    <%@ include file="include/header.jsp" %>

  <!-- =============================================== -->

  <!-- Left side column. contains the sidebar -->
  <%@ include file="include/sider.jsp" %>

  <!-- =============================================== -->

  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
  
    <!-- Main content -->
    <section class="content">
      <!-- Default box -->
      <div class="box">
        <div class="box-header with-border">
          <form action="/admin/reply/list" method="get" class="form-inline pull-left" >
            <input type="text" class="form-control" name="keys" id="keys" placeholder="关键字"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
         <!--  <h5 class="pull-left">文章列表</h5> -->
         
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>文章标题</th>
                <th>评论内容</th>
                <th>评论IP</th>
                <th>评论时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
              <c:forEach items="${page.items}" var="reply">
              	<tr>
	                <td><a href="/user/article/detail?id=${reply.articleId}" target="_blank">${reply.articleTitle}</a></td>
	                <td><a href="/user/article/detail?id=${reply.articleId}#${reply.id}" target="_blank">${reply.simpleContent}</a></td>
	                <td>${reply.userIp}</td>
	                <td><span class="date">${reply.createTime}</span></td>
	                <td>
	                  <a href="javascript:;" class="del" rel="${reply.id}">删除</a> 
	                </td>
                </tr>
              </c:forEach>
            
            </tbody>
          </table>
          <br> 
          <ul id="pagination" class="pagination pull-right"></ul>
        </div>
        <!-- /.box-body -->
        
      </div>
      <!-- /.box -->

    </section>
    <!-- /.content -->
  </div>
  <!-- /.content-wrapper -->

  <footer class="main-footer">
    <div class="pull-right hidden-xs">
      <b>Version</b> 2.3.8
    </div>
    <strong>Copyright &copy; 2014-2016 <a href="">凯盛软件</a>.</strong> All rights
    reserved.
  </footer>


</div>
<!-- ./wrapper -->

<%@ include file="include/js.jsp" %>
<script>
    $(function(){
      
        $("#pagination").twbsPagination({
	         totalPages:"${page.pageTotal}",
	         visiblePages:3,
	         href:"/admin/reply/list?p={{number}}&keys=${keys}",
	         first: "首页",
	         prev: "上一页",
	         next:"下一页",
	         last:"末页"
        });  
      
        $(".del").click(function(){
        	var id = $(this).attr("rel");
        	layer.confirm("确定要删除该评论么?",function(){
        		$.get("/admin/reply/del",{"id":id}).done(function(json){
	       			if(json.state == "success") {
	       				layer.alert("删除成功",function(){
	       					history.go(0);
	       				})
	       			} else {
	       				layer.alert(json.message);
	       			}
	       		}).error(function(){
	       			layer.alert("系统异常")
	       		});
        	});
        	var id = $(this).attr("rel");
        });
        
    });
    </script>
</body>
</html>
