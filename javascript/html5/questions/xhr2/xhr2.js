var divResults;

function init() {
	var btnPresMe = document.getElementById("pressMe");
	divResults = document.getElementById("results");
	btnPresMe.onclick = processSubmit;
}


function processSubmit() {
	/* Enter XHR2 Code Here */
	
	return false; // Prevent actual form submission
}

window.onload = init;

