var statusMessages = ["Uncached","Idle","Checking","Downloading","Update Ready","Obsolete"]; 
var needsUploading = false;

var tdEmail;
var tdOnline;
var tdCacheStatus;
var tdNeedsUploading;
var tdTimeStamp;
var imgStatusLogo;
var divStatus;

function initStatus(document) {
	tdEmail = document.getElementById("tdEmail");
	tdOnline = document.getElementById("tdOnline");
	tdCacheStatus = document.getElementById("tdCache");
	tdNeedsUploading = document.getElementById("tdNeedsUploading");
	tdTimeStamp = document.getElementById("tdTimeStamp");
	imgStatusLogo = document.getElementById("imgStatusLogo");
}

function setEmailAddress(email) {
	tdEmail.innerHTML = email;
}

function setOnline(online) {
	imgStatusLogo.src = null;
	if (online) {
		tdOnline.innerHTML = "Connected to Server";
//		tdOnline.style = "#00ff00";
		imgStatusLogo.src = "statusOnline.jpg"; 
	} else {
		tdOnline.innerHTML = "Running in Disconnect Mode";
//		tdOnline.style = "#ff0000";
		imgStatusLogo.src = "statusOffline.jpg"; 
	}
	// This will be either statusOnline or fallback to statusOffline depending on status
}

function getCacheStatusValue(number) { 
	return statusMessages[number];
}

function setCacheStatus(status) {
	if (typeof status=="number") {
		tdCacheStatus.innerHTML = getCacheStatusValue(status);
	} else {
		tdCacheStatus.innerHTML = status;
	}
}

function getCacheStatus() {
	return window.applicationCache.status;
}

function setNeedsUploading(needs) {
	if (needs) {
		tdNeedsUploading.innerHTML = "Data needs to be uploaded";
		tdNeedsUploading.style = "#ff0000";
	} else {
		tdNeedsUploading.innerHTML = "Data is flushed";
		tdNeedsUploading.style = "#00ff00";
	}
}

function getNeedsUploading() {
	return needsUploading;
}

function getXhr() {
	var xhr;
	if (xhr != null) {
		xhr.abort();
	}
	xhr = new XMLHttpRequest();
	return xhr;
}

function importScript(file) {
	var scr = document.createElement("script");
	scr.type="text/javascript";
	document.body.appendChild(scr);
	scr.src = file;
}

this.onload = function(event) {
	addEventListener("online", cameOnline, true);
	addEventListener("offline", wentOffline, true);
	addEventListener("obsolete", cacheObsolete, true);
	addEventListener("progress", cacheProgress, true);
	addEventListener("error", cacheError, true);
	addEventListener("noupdate", cacheNoUpdate, true);
	addEventListener("downloading", cacheDownloading, true);
	addEventListener("checking", cacheChecking, true);
	addEventListener("updateready", cacheUpdateReady, true);
	this.checking = function(e) {
		alert("F")
	};
	
	importScript("date.js");
	
	divStatus = document.getElementById("status");
	

	
	var xhrStatus = getXhr();
	xhrStatus.onreadystatechange = function(e) {
		if (xhrStatus.readyState == 4) {
			divStatus.innerHTML += xhrStatus.responseText;
			initStatus(document);
			setEmailAddress("foo@bar.com");
			cameOnline(null);
		}
	};
	xhrStatus.open("GET", "status.html", true);
	xhrStatus.send(null);
	
	var xhr = getXhr();
	xhr.onreadystatechange = function(e) {
		if (xhr.readyState == 4) {
			tdTimeStamp.innerHTML += xhr.responseText;
		}
	};
	xhr.open("GET", "date.jsp", true);
	xhr.send(null);
}

function cacheUpdateReady(event) {
	divStatus.innerHTML += "Update Ready...";
	setCacheStatus(window.applicationCache.status);
}
function cacheChecking(event) {
	divStatus.innerHTML += "Checking...";
	setCacheStatus(window.applicationCache.status);
}
function cacheObsolete(event) {
	divStatus.innerHTML += "Obsolete";
	setCacheStatus(window.applicationCache.status);
}
function cacheProgress(event) {
	divStatus.innerHTML += "Progress";
	setCacheStatus(window.applicationCache.status);
}
function cacheError(event) {
	divStatus.innerHTML += "Error: " + event + "<br/>";
}
function cacheNoUpdate(event) {
	divStatus.innerHTML += "NoUpdate";
	setCacheStatus(window.applicationCache.status);
}
function cacheDownloading(event) {
	divStatus.innerHTML += "Downloading";
	setCacheStatus(window.applicationCache.status);
}


function wentOffline(event) {
//	var cds = new ClientDataSource(document.getElementById("current").innerText);
	setOnline(false);
}

function cameOnline(event) {
	setOnline(true);
	setCacheStatus(window.applicationCache.status);
}

