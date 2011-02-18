var btnPressMe;

window.onload = init;

function init() {
	btnPressMe = document.getElementById("pressMe");
}

function setStatus(status) {
	divStatus.innerHTML = status;
}

function addStatus(status) {
	divStatus.innerHTML += status + "<br/>";
}




