function upload_login(event) {
		window.alert("clicked2");
		$.ajax({
			url : "/upload_login",
			type : "post",
			cache:false
	　　　　}).done(function(data, textStatus, jqXHR) {
			window.alert("s");
	　　　　}).fail(function(data, textStatus, jqXHR) {
			window.alert("f");
	});
}