function init() {
	var btnPressMe = document.getElementById("pressMe");
	btnPressMe.onclick = processSubmit;
}

function processSubmit() {
	/* Enter CSS Code Here */
	var articleResults = document.querySelector("#results");
	articleResults.style.background='#ff0000';

	var lstInputs = document.querySelectorAll("input", "textarea");
	for (var counter = 0; counter <  lstInputs.length; counter++) {
		lstInputs[counter].style.background='#ff9942';
	}
	
	var taComments = document.querySelector(".comments");
	taComments.disabled = true;

	return false; // Prevent actual form submission
}

window.onload = init;
