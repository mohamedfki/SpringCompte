function deleteCompte(rib){
	$.ajax({
	url: "/comptes/delete-ajax",
	type: "POST",
	data: { rib: rib },
	success: function() {
		$('#'+rib).remove();
	alert("success");
	}
	});
}