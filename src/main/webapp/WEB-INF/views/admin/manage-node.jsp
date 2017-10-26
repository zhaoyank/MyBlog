<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <title>节点列表</title>
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
          <form action="/admin/node/list" method="get" class="form-inline pull-left" >
            <input type="text" class="form-control" name="key" placeholder="关键字"/>
            <button class="btn btn-primary"><i class="fa fa-search"></i></button>
          </form>
         <!--  <h5 class="pull-left">文章列表</h5> -->
          <a href="#" class="btn btn-success pull-right"  data-toggle="modal" data-target="#addModal">新增节点</a>
        </div>

        <div class="box-body">
          
          <table class="table" id="cust_table">
            <thead>
              <tr >
                <th>节点名称</th>
                <th>创建时间</th>
                <th>操作</th>
              </tr>
            </thead>
            <tbody>
            
            <c:forEach items="${page.items}" var="node">
              <tr>
                <td>${node.nodeName}</td>
                <td><span class="date">${node.createTime}</span></td>
                <td>
                  <a href="javascript:;" class="del" rel="${node.id}">删除</a> 
                  <a href="javascript:;" class="update" rel="${node.nodeName}" nodeid="${node.id}">修改</a> 
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


  <div class="modal fade" id="addModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">新增节点</h4>
        </div>
        <div class="modal-body">
          <form action="/admin/node/add" method="post" class="form-horizontal" id="addForm">
              <div class="form-group">
                <label class="col-sm-2 control-label">节点名称:</label>
                <div class="col-sm-10">
                  <input type="text" class="form-control" name="nodename" placeholder="请输入节点名称">
                </div>
              </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="addBtn">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="updateModal">
    <div class="modal-dialog">
      <div class="modal-content">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span></button>
          <h4 class="modal-title">更新节点</h4>
        </div>
        <div class="modal-body">
          <form action="/admin/node/update" method="post" class="form-horizontal" id="editForm">
              <div class="form-group">
                  <label class="col-sm-2 control-label">节点名称:</label>
                  <div class="col-sm-10">
                    <input type="hidden" name="id" id="nodeid" value="${node.id}"/>
                    <input type="text" class="form-control" name="nodename" id="updateNodeName" placeholder="请输入节点名称">
                  </div>
                </div>

          </form>
        </div>
        <div class="modal-footer">
          <button class="btn btn-primary pull-left" id="editBtn">保存</button>
          <button class="btn btn-default pull-left" data-dismiss="modal">取消</button>
        </div>
      </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- ./wrapper -->

<!-- jQuery 2.2.3 -->
<script src="/static/plugins/jQuery/jquery-2.2.3.min.js"></script>
<script src="/static/dist/js/jquery.validate.js"></script>

<!-- Bootstrap 3.3.6 -->
<script src="/static/bootstrap/js/bootstrap.min.js"></script>
<!-- DataTables -->
<script src="/static/plugins/datatables/jquery.dataTables.min.js"></script>
<script src="/static/plugins/datatables/dataTables.bootstrap.min.js"></script>
<!-- SlimScroll -->
<script src="/static/plugins/slimScroll/jquery.slimscroll.min.js"></script>
<!-- FastClick -->
<script src="/static/plugins/fastclick/fastclick.js"></script>
<script src="/static/plugins/layer/layer.js"></script>
<!-- AdminLTE App -->
<script src="/static/dist/js/app.min.js"></script>
<!-- page -->
<script src="/static/dist/js/jquery.twbsPagination.min.js"></script>

<script src="/static/dist/js/notify.js"></script>
<script src="/static/dist/js/moment.js"></script>
<script src="/static/dist/js/date.js"></script>
<script src="/static/dist/js/logout.js"></script>

<script>
    $(function(){
    	
        $("#pagination").twbsPagination({
         totalPages:"${page.pageTotal}",
         visiblePages:3,
         href:"/admin/node/list?p={{number}}&key=${key}",
         first: "首页",
         prev: "上一页",
         next:"下一页",
         last:"末页"
       });  

       $(".update").click(function(){
         $("#updateNodeName").val("java");
          $('#updateModal').modal({
            keyboard: false
          });
       }); 
      
       $("#addBtn").click(function(){
    	   $("#addForm").submit();
       });
       
       $("#addForm").validate({
     	  errorClass : "text-danger",
     	  errorElement : "span",
     	  rules : {
     		  nodename : {
     			  required : true,
     			  remote : "/admin/node/validate"
     		  }
     	  },
     	  messages : {
     		  nodename : {
     			  required : "请填写节点名称",
     			  remote : "该节点已存在"
     		  }
     	  }
     	  
       });
       
       $(".del").click(function(){
     	 var id = $(this).attr("rel");
     	 layer.confirm("确定要删除么？",function(){
 	       		$.get("/admin/node/del",{"id":id}).done(function(json){
 	       			if(json.state == "success") {
 	       				layer.alert("删除成功",function(){
 	       					history.go(0);
 	       				})
 	       			} else {
 	       				layer.alert(json.message);
 	       			}
 	       		}).error(function(){
 	       			layer.alert("系统异常");
 	       		});
     	 
       		});
       });
       
        $(".update").click(function(){
     	     var nodename = $(this).attr("rel");
     	     var nodeid = $(this).attr("nodeid");
     	     $("#nodeid").val(nodeid);
 	         $("#updateNodeName").val(nodename);
 	          $('#updateModal').modal({
 	            keyboard: false
 	          });
        });
       
        
      $("#editBtn").click(function(){
    	 	$("#editForm").submit(); 
      });

      $("#editForm").validate({
 	   	  errorClass : "text-danger",
 	   	  errorElement : "span",
 	   	  rules : {
 	   			nodename : {
 	   			  	required : true,
 	   			  	remote : "/admin/node/validate"
 	   		  	}
 	   	  },
 	   	  messages : {
 	   			nodename : {
 	   			  	required : "请填写节点名称",
 	   			  	remote : "该节点已存在"
 	   		  	}
 	   	  }
    	  
      });
    });
    </script>
</body>
</html>
