$(function(){
	$("#logout").click(function(){
		layer.confirm("确定要退出么?",function(){
			window.location.href = "/admin/logout";
		});
	});
});