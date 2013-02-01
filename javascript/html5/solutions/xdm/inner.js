var btnPressMe;
var serviceMessages;
var divStatus;

window.onload = init;

function init() {
	btnPressMe = document.getElementById("pressMe");
	divStatus = document.getElementById("status");
	btnPressMe.onclick = sendMessage;
	window.onmessage = receiveMessage;
}

function sendMessage() {
	if (typeof window.postMessage === "undefined") {
		alert("XDM is not supported on this browser!");
	} else {
		var msg = new Object();
		msg.messageId = 1234;
		msg.messageType = "Ack";
		msg.message = "Acknowledged Order";
		window.top.postMessage(JSON.stringify(msg), "http://dev1.nextgened.com");
	}
}

function receiveMessage(e) {
	//alert("in the receiver"+e.origin);
	if (e.origin == "http://dev1.nextgened.com") {
		// The data can probably be trusted
		// It came from the mainPage
		serviceMessages = JSON.parse(e.data);
		addStatus("dev1: " + serviceMessages.message);
	}
}

function setStatus(status) {
	divStatus.innerHTML = status;
}

function addStatus(status) {
	divStatus.innerHTML += status + "<br/>";
}




