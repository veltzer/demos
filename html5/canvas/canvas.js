function init() {
	var btnPresMe = document.getElementById("pressMe");
	btnPresMe.onclick = processSubmit;
}

function processSubmit() {
	/* Enter Canvas Code Here */

	return false; // Prevent actual form submission
}

window.onload = init;

