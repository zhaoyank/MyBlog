$(function(){
	var times = $(".date");
	for(var i = 0; i < times.length; i++) {
		$(times[i]).text(moment($(times[i]).text()).format("YYYY-MM-DD HH:mm:ss"));
	}
});