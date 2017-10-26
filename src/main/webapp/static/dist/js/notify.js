$(function(){
	
	var notifyCount = function(){
		$.post("/admin/notify",function(json){
			if(json.data > 0) {
				$("#notifySpan").text(json.data);
			}
		})
	}
	setInterval(notifyCount, 10000);
	notifyCount();
});