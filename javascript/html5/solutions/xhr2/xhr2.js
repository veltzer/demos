var divResults;

function init() {
	var btnPresMe = document.getElementById("pressMe");
	divResults = document.getElementById("results");
	btnPresMe.onclick = processSubmit;
}


function processSubmit() {
	/* Enter XHR2 Code Here */
	var xhr = new XMLHttpRequest();
	xhr.open("GET", "Date.jsp", true);
	if (typeof xhr.onload != "undefined") {
		xhr.onload = function(e) {
			divResults.innerHTML = JSON.parse(xhr.responseText);
		};
		xhr.onerror = function(e) {
			divResults.innerHTML = '<span class="error">' + JSON.parse(xhr.responseText) + '</span>';
		};
	} else {
		xhr.onreadystatechange = function(e) {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					divResults.innerHTML = JSON.parse(xhr.responseText);
				} else if (xhr.status == 500) {
					divResults.innerHTML = '<span class="error">' + JSON.parse(xhr.responseText) + '</span>';
				}
			}
		};
	}
	xhr.send(null);
	
	return false; // Prevent actual form submission
}

window.onload = init;

