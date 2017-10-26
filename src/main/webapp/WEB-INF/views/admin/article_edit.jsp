<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>更新文章</title>
  <!-- Tell the browser to be responsive to screen width -->
  <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">
  <!-- Bootstrap 3.3.6 -->
  <link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
  <!-- Font Awesome -->
  <link rel="stylesheet" href="/static/dist/css/font-awesome.css">
  <!-- Theme style -->
  <link rel="stylesheet" href="/static/dist/css/AdminLTE.min.css">
  <link rel="stylesheet" href="/static/dist/css/skins/_all-skins.min.css">
  <link rel="stylesheet" href="/static/plugins/editer/styles/simditor.css">
  <link rel="stylesheet" href="/static/dist/css/style.css">
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
      <div class="box box-primary">
          <div class="container">
          
          
            <form action="/admin/article/edit" method="post" class="form-horizontal" id="postForm">
              <br>
              
               <div class="form-group">
               	  <input type="hidden" name="id" value="${article.id}" />
                  <input type="text" class="form-control" value="${article.title}" name="title" placeholder="请输入主题标题">
               </div>
               <div class="form-group">
                  <input type="text" class="form-control" name="label" value="<c:forEach items='${labelList}' var='label' varStatus='vs'>${label.labelName}<c:if test='${not vs.last}'>,</c:if></c:forEach>" placeholder="请为主题贴上标签，多个标签使用，号分开">
               </div>
               <div class="form-group"> 
                  
                  <textarea name="content" class="from-control" id="editor" placeholder="正文从这里开始...">${article.content}</textarea>
               </div>
              
               <div class="form-group">
                 <div class="col-sm-6" style="padding:0px">
                  <select name="nodeId" class="form-control" style="margin-top:15px">
                      <option value="">请选择节点</option>
                      
                      <c:forEach items="${nodeList}" var="node">
                      	<option value="${node.id}"
                      		<c:if test="${node.id == article.nodeId}">selected</c:if>
                      	>${node.nodeName}</option>
                      </c:forEach>
                      
                  </select>
                </div>
               </div>
               <br>
               <div class="form-group">
                  <button class="btn btn-primary" id="postBtn">更新文章</button>
               </div>
            </form>

          </div>
  
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

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>

<script src="/static/dist/js/jquery.validate.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>

<script src="/static/plugins/layer/layer.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>

<script src="/static/plugins/editer/scripts/module.min.js"></script>
<script src="/static/plugins/editer/scripts/hotkeys.min.js"></script>
<script src="/static/plugins/editer/scripts/uploader.min.js"></script>
<script src="/static/plugins/editer/scripts/simditor.min.js"></script>
<script src="/static/dist/js/logout.js"></script>
<script src="/static/dist/js/notify.js"></script>

</body>
<script>
  $(function(){
	  
	  var editor = new Simditor({
	      textarea: $('#editor'),
	      upload : {
	    	  url : "/file/upload",
	    	  fileKey : "file"
	      }
	  });
	  
	  $("#postBtn").click(function(){
		  $("#postForm").submit();
	  });
	  
	  $.validator.addMethod("labelValidete", function(value, element) {
	  	return this.optional(element) || /^[a-zA-Z0-9\u4e00-\u9fa5]+(,[a-zA-Z0-9\u4e00-\u9fa5]+)*$/.test(value);
	  }, "Please specify the correct domain for your documents");
	  
	  $("#postForm").validate({
		  errorClass : "text-danger",
		  errorElement : "span",
		  rules : {
			  title : {
				  required : true
			  },
			  label : {
				  required : true,
				  labelValidete : true
			  },
			  node : {
				  required : true
			  }
		  },
		  messages : {
			  title : {
				  required : "请输入标题"
			  },
			  label : {
				  required : "请输入关键字",
				  labelValidete : "格式错误"
			  },
			  node : {
				  required : "请选择分类信息"
			  }
		  },
		  	submitHandler : function(form){
			 	 $.ajax({
				  	url : "/admin/article/edit",
				  	type : "post",
				  	data : $(form).serialize(),
				    beforeSend : function(){
						$("#postBtn").attr("disabled","disabled");
						$("#postBtn").text("更新中...");
					},
					success : function(json) {
						if(json.state == "success") {
	    					window.location.href = "/user/article/detail?id="+json.data;
	    				} else {
	    					layer.msg(json.message)
	    				}
					},
					error : function(json) {
						layer.alert(json.message);
					},
					complete : function(){
						$("#postBtn").removeAttr("disabled");
						$("#postBtn").text("更新文章");
					}
			  });
		  }
	  });
	  
	 
  });


</script>
</html>
    