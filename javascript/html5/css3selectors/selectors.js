function init() {
	var btnPressMe = document.getElementById("pressMe");
	btnPressMe.onclick = processSubmit;
}

function processSubmit() {
	/* Enter CSS Code Here */

	return false; // Prevent actual form submission
}

window.onload = init;
