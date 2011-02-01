var btnPressMe;
var video;

function init() {
	btnPressMe = document.getElementById("pressMe");
	btnPressMe.onclick = processSubmit;
	video = document.getElementById("myVideo");
}

function processSubmit() {
	/* Enter Video Code Here */

	return false; // Prevent actual form submission
}

window.onload = init;

