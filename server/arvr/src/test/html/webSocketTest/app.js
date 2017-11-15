var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
}

//
// Connect to server websocket
//
function connect() {
	
    var socket = new SockJS('http://localhost:8080/ws-map-update');
	console.log("new oscket"); 
    stompClient = Stomp.over(socket);
	console.log("new stompClient"); 
    stompClient.connect({}, function (frame) {
		console.log("connecting..."); 
        setConnected(true);
        console.log('Connected: ' + frame);

		stompClient.subscribe('/map/test', function(data) {
			console.log("Received data from server: ", data); 
		}); 
		
		stompClient.subscribe('/map/route/update', function(route) {
			onRouteUpdate(JSON.parse(route.body)); 
		}); 
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

mapUpdateCon = null; 
// 
// Method to subscribe to map updates
//
function subscribeMapUpdates() {
	mapUpdateCon = stompClient.subscribe('/map/update', function (update) {
		onMapSettingUpdate(JSON.parse(update.body));
	});
}

//
// Method to unsubscribe map updates
//
function unsubscribeMapUpdates() {
	mapUpdateCon.unsubscribe(); 
	mapUpdateCon = null; 
}



function onRouteUpdate(routeList) {
	console.log(routeList); 
	
	// 
	// implement here what you wanna do with the new route updates
	// 
}

function onMapSettingUpdate(update) {
	console.log("update from server: ", update); 
	
	// 
	// implement here what you wanna do with map updates
	// 
}

function sendMapUpdate(lattitude, longtitude, zoom) {
	
	//
	// pass to this function the necessary map updates
	// updates will be send to server
	//
	stompClient.send("/app/setmap", {}, JSON.stringify({'coords': { 'lattitude' : 232.23, 'longtitude' : '4555.4323' }, 'zoom' : 1 }));
}


$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});