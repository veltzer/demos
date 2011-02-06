function init() {
	var btnPressMe = document.getElementById("pressMe");
	var btnStop = document.getElementById("stop");
	btnPressMe.onclick = processSubmit;
	btnStop.onclick = stopWatchingLocation;
}

/* Enter global variables here */

function processSubmit() {
	/* Enter GeoLocation Code Here */
}

function stopWatchingLocation() {
	/* Enter Stop Watching Code here */
}

function updatePosition(position) {
	/* Enter Position Update Code here */
}

function errorPosition(error) {
	/* Enter Position Error Code here */
}

function optionsPosition() {
	/* Enter Position Options Code here */
}

window.onload = init;

