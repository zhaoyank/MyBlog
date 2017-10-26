<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>消息中心</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  
  <%@ include file="include/css.jsp"%>

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
            <div class="box-header">
				
				<button id="markBtn" style="margin-left: 8px;" disabled class="btn btn-success">标记为已读</button>
				<button id="delBtn" style="margin-left: 8px;" class="btn btn-success">清除已读消息</button>
			</div>
          
        </div>

        <div class="box-body">
          
          <table class="table">
            <thead>
                <tr>
                    <th width="30"><input type="checkbox" id="ckFather"></th>
                    <th width="200">发布日期</th>
                    <th width="400">内容</th>
                    <th>查看日期</th>
                </tr>
            </thead>
            <tbody>
               <c:forEach items="${page.items }" var="notify">
   				  <c:choose>
   				  	<c:when test="${notify.state == false}">
   				  	  <tr>
	                     <td><input value="${notify.id}" type="checkbox" class="ckSon"></td>
	                     <td><span class="date">${notify.createTime}</span></td>
	                     <td><span id="${notify.id}">${notify.content}</span></td>
	                     <td>未读 </td>
	                  </tr>	
   				    </c:when>
   				    <c:otherwise>
	   				  <tr>
	   				  	 <td></td>
	                     <td><span class="date">${notify.createTime}</span></td>
	                     <td><span id="${notify.id}">${notify.content}</span></td>
	                     <td><span class="date">${notify.readTime}</span></td>
	                  </tr>	
   				    </c:otherwise>
   				  </c:choose>
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
      
    	var sons = $(".ckSon");
    	if(sons.length == 0) {
    		$("#ckFather").attr("disabled","disabled");
    	}
    	
        $("#pagination").twbsPagination({
	        totalPages:"${page.pageTotal}",
	        visiblePages:3,
	        href:"/admin/notify?p={{number}}",
	        first: "首页",
	        prev: "上一页",
	        next:"下一页",
	        last:"末页"
        });  
      	
        $("#ckFather").click(function(){
  			var sons = $(".ckSon");
  			for(var i = 0; i < sons.length; i++) {
  				sons[i].checked = $(this)[0].checked;
  			}
  			
  			if($(this)[0].checked == true) {
  				$("#markBtn").removeAttr("disabled");
  			} else {
  				$("#markBtn").attr("disabled","disabled");
  			}
  			
      	});
        
        $(".ckSon").click(function(){
			var sons = $(".ckSon");
			var num = 0;
			for(var i = 0; i < sons.length; i++) {
				if(sons[i].checked == true) {
					num ++;
				}
			}
			
			if(num == sons.length) {
				$("#ckFather")[0].checked = true;
			} else {
				$("#ckFather")[0].checked = false;
			}
			
			if(num > 0) {
				$("#markBtn").removeAttr("disabled");
			} else {
				$("#markBtn").attr("disabled","disabled");
			}
       });
       
       $("#markBtn").click(function(){
    	   var ids = [];
    	   var sons = $(".ckSon");
    	   for(var i = 0; i < sons.length; i++) {
				if(sons[i].checked == true) {
					ids.push(sons[i].value);
				}
    	   }
    	   
    	   $.post("/admin/notify/read",{"ids":ids.join(",")},function(json){
				if(json == "success") {
					window.history.go(0);
				}
    	   });
    	   
       });
       
       $("#delBtn").click(function(){
    	   layer.confirm("确定删除所有已读信息?",function(){
	    	   $.get("/admin/notify/del",function(json){
	    		   if(json == "success") {
						window.history.go(0);
					}
	    	   });
    	   });
       });
       
       $(".toread").click(function(){
    	   var url = $(this).attr("rel");
    	   var id = $(this).parent().attr("id");
    	   $.get("/admin/notify/read",{"id":id},function(json){
    		   	if(json == "success") {
			   		window.location.href = url;
			   	}
    	   });
       });
       
    });
    </script>
</body>
</html>
    