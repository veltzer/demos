function init() {
	var btnPressMe = document.getElementById("pressMe");
	var btnStop = document.getElementById("stop");
	btnPressMe.onclick = processSubmit;
	btnStop.onclick = stopWatchingLocation;
	geostatus = document.getElementById("geostatus");
	map = document.getElementById("map");
	results = document.getElementById("results");
	if (window.localStorage) {
		try {
			mapData = JSON.parse(window.localStorage.getItem("mapData"));
		} catch (e) {
		}
		if (mapData == "undefined" || mapData == null) {
			mapData = new Array();
			window.localStorage.setItem("mapData", JSON.stringify(mapData));
		}
		displayResultsTable(results);
	}
	
	// Only works with Gears apparently, but doesn't hurt to try
	if (navigator.geolocation.lastPosition) {
		updatePosition(navigator.geolocation.lastPosition);
	}
}

/* Enter global variables here */
var geostatus;
var txtLatitude, txtLongitude, txtAccuracy;
var watchId;
var map;
var results;
var mapData;



function processSubmit() {
	/* Enter GeoLocation Code Here */
	if (!navigator.geolocation) {
		geostatus.innerHTML = "<span class='error'>Your browser does not support GeoLocation</span>";
		return false;
	}
	geostatus.innerHTML = "GeoLocation Supported... Attempting to find you.";
	txtLatitude = document.getElementById("latitude");
	txtLongitude = document.getElementById("longitude");
	txtAccuracy = document.getElementById("accuracy");
	
	/*
	navigator.geolocation.getCurrentPosition(updatePosition, errorPosition, optionsPosition);
	*/
	watchId = navigator.geolocation.watchPosition(updatePosition, errorPosition, optionsPosition);
	return false; // Prevent actual form submission
}

function stopWatchingLocation() {
	navigator.geolocation.clearWatch(watchId);
	geostatus.innerHTML = "Stoping the GeoLocation Listener";
}

function updatePosition(position) {
	/* Enter Position Update Code here */
	geostatus.innerHTML = "Position returned";
	txtLatitude.value  = position.coords.latitude;
	txtLongitude.value  = position.coords.longitude;
	txtAccuracy.value  = position.coords.accuracy;
	map.src = "http://maps.google.com/maps?sll=" + position.coords.latitude + "," + position.coords.longitude;
	var mapDataLocal = JSON.parse(window.localStorage.getItem("mapData"));
	var mapDataEntry = {
			timestamp: new Date(),
			latitude: position.coords.latitude,
			longitude: position.coords.longitude,
			accuracy: position.coords.accuracy
		};
	mapDataLocal.push(mapDataEntry);
	window.localStorage.setItem("mapData", JSON.stringify(mapDataLocal));
	populateResultRow(results.insertRow(1), mapDataEntry);
}

function displayResultsTable(table) {
	for (var i = 0; i < mapData.length; i++) {
		// Always insert the latest result in the top data row after the header
		var row = table.insertRow(1);
		populateResultRow(row, mapData[i]);
	}
}

function populateResultRow(tr, mapDataEntry) {
	var tdTimeStamp = tr.insertCell(0);
	var tdLat =	tr.insertCell(1);
	var tdLong = tr.insertCell(2);
	var tdAcc = tr.insertCell(3);
	tdTimeStamp.innerHTML = mapDataEntry.timestamp;
	tdLat.innerHTML = mapDataEntry.latitude;
	tdLong.innerHTML = mapDataEntry.longitude;
	tdAcc.innerHTML = mapDataEntry.accuracy;
	return tr;
}

function errorPosition(error) {
	/* Enter Position Error Code here */
	geostatus.innerHTML = "<span class='error'>";
	switch (error.code) {
		case 0: geostatus.innerHTML += "There was some error - We couldn't find you... "; break;
		case 1: geostatus.innerHTML += "You need to tell the browser to share your location if you want us to find you... "; break;
		case 2: geostatus.innerHTML += "We tried, but we couldn't find you... "; break;
		case 3: geostatus.innerHTML += "It's taking too long to find you... "; break;
	}
	geostatus.innerHTML += error.message;
	geostatus.innerHTML += "</span>";
}

function optionsPosition() {
	/* Enter Position Options Code here */
	return {timeout:15000, maximumAge:2000, enableHighAccuracy:false};
}


window.onload = init;

