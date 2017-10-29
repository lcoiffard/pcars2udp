function loaddata(){
	var url = window.location;
    $('#data').load(url+'/refresh', function() {

    });
    
    
    
}


setInterval(function(){
    loaddata(); // this will run after every 5 seconds
    
}, 5000)