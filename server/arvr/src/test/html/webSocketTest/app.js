var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
	
    var socket = new SockJS('ws://localhost/ws-map-update');
	console.log("new oscket"); 
    stompClient = Stomp.over(socket);
	console.log("new stompClient"); 
    stompClient.connect({}, function (frame) {
		console.log("connecting..."); 
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/map/update', function (update) {
            showNewMapSettings(JSON.parse(update.body).content);
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

function showNewMapSettings(update) {
	console.log("update from server: ", update); 
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});