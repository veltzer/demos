var serviceMessages;
var divChatMessages;
var divStatus;
var btnPressMe;
var ifFeedback;

window.onload = init;

function init() {
	btnPressMe = document.getElementById("pressMe");
	btnPressMe.onclick = sendMessage;
	divChatMessages = document.getElementById("chatMessages");
	ifFeedback = document.getElementById("feedback");
	divStatus = document.getElementById("status");
	window.onmessage = receiveMessage;
}

function sendMessage() {
	if (typeof window.postMessage === "undefined") { 
		alert("XDM is not supported on this browser!");
	} else {
		alert('sending message');
		var msg = new Object();
		msg.messageId = 1234;
		msg.messageType = "OrderSent";
		msg.message = "Your order has been sent";
		ifFeedback.contentWindow.postMessage(JSON.stringify(msg), "http://dev2.nextgened.com");
	}
}


function receiveMessage(e) {
	if (e.origin == "http://dev2.nextgened.com") { 
		// The data can probably be trusted 
		// It came from the inner frame as an acknowledgement
//		A JSON.parse with a type reviver:
//		serviceMessages = JSON.parse(e.data, function (key, value) {
//		    var type;
//		    if (value && typeof value === 'object') {
//		        type = value.type;
//		        if (typeof type === 'string' && typeof window[type] === 'function') {
//		            return new (window[type])(value);
//		        }
//		    }
//		    return value;
//		});
		serviceMessages = JSON.parse(e.data);
		addMessage("dev2: " + serviceMessages.message);
	} 
}

function setStatus(status) {
	divStatus.innerHTML = status;
}

function addStatus(status) {
	divStatus.innerHTML += status + "<br/>";
}

function addMessage(message) {
	divChatMessages.innerHTML += "Inner Frame says: " + message + "<br/>";
}




