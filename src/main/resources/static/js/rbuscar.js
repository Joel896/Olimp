$(document).ready(function(){
	$("input[name$='bsc']").click(function(){
		var aux = $(this).val();
		var mostrado = [[${mostrado}]];
		
		$("div.container-fluid").hide();
		$("#Bsc" + aux).show();
	});
});