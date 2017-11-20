function loaddata(){
	var url = window.location;
    $('#data').load(url+'/refresh', function() {

    });
    
    
    
}


setInterval(function(){
    loaddata(); // this will run after every 5 seconds
    
}, 5000)




function loadrecords(){
	var url = window.location;
	
    $('#records').load(url+'/getRecords', {idCar:$('#idCar option:selected').val(), className:$('#className option:selected').val(), idTrack:$('#idTrack option:selected').val()}, function() {
    	
    });
      
}


$('#idCar').change(function() {
	$("#className").val("");
	loadrecords();
});

$('#className').change( function() {
		$("#idCar").val("");
		loadrecords();
})

$('#idTrack').change( function() {
		loadrecords();
})

$('#refreshRecords').click(function() {
	loadrecords();
})

