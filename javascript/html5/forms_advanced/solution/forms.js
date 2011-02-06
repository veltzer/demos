var form;
var formStatus = null;

function init() {
	var btnPressMe = document.getElementById("pressMe");
	form = document.forms[0];
	//form.onsubmit=processSubmit(form);
	form.onsubmit=processSubmit;
	formStatus = document.getElementById("formStatus");
	btnPressMe.onclick = processSubmit;
	prepFieldsForFeedback();
}

function prepFieldsForFeedback() {
	var inputs = form.querySelectorAll("input, textarea, select");
	for (var i = 0; i < inputs.length; i++) {
		inputs[i].addEventListener("invalid", handleInvalidData, false);
	}
}

function handleInvalidData(evt) {
	var field = evt.srcElement;
	updateStatus(field.name + ": " + field.validationMessage);
}
				
function processSubmit() {
	/* Enter Form Validation Code here */
	clearStatus();
	var formValid = true;
	var inputs = form.querySelectorAll("input, textarea, select");
	for (var i = 0; i < inputs.length; i++) {
		var fieldValid = inputs[i].validity.valid;
		if (!fieldValid) {
			updateStatus(inputs[i].name + ": " + inputs[i].validationMessage);
		}
		formValid = formValid && inputs[i].validity.valid;
	}
	updateStatus("Form Valid? " + formValid);
	var foo = form.checkValidity();
	updateStatus("CheckValidity: " + foo);
	return formValid;
}

function updateStatus(statusMessage) {
	if (formStatus != null) {
		formStatus.innerHTML += statusMessage + "<br/>";
	}
}

function clearStatus() {
	if (formStatus != null) {
		formStatus.innerHTML = "";
	}
}

// validMissing
// typeMismatch
// patternMismatch
// tooLong
// rangeUnderflow
// rangeOverflow
// stepMismatch
// customError
// setCustomValidity(message)
// willValidate attribute
// checkValidity()

window.onload = init;

