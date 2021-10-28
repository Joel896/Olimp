$(document).ready(function(){
	$("input[name$='bsc']").click(function(){
		var aux = $(this).val();
		
		$("div.container-fluid").hide();
		$("#Bsc" + aux).show();
	});
});