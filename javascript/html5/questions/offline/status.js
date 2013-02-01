var statusMessages = ['Uncached','Idle','Checking','Downloading','Update Ready','Obsolete'];
var needsUploading = false;

function init() {
	tdEmail = document.getElementById('tdEmail');
	tdOnline = document.getElementById('tdOnline');
	tdCache = document.getElementById('tdCache');
	tdNeedsUploading = document.getElementById('tdNeedsUploading');
}

function setEmailAddress(email) {
	tdEmail.innerHTML = email;
}

function setOnline(online) {
	if (online) {
		tdOnline.innerHTML = 'Connected to Server';
		tdOnline.style = '#00ff00';
	} else {
		tdOnline.innerHTML = 'Running in Disconnect Mode';
		tdOnline.style = '#ff0000';
	}
}

function getCacheStatusValue(number) {
	return statusMessages[number];
}

function setCacheStatus(status) {
	if (status typeof(Number)) {
		tdCacheStatus.innerHTML = getCacheStatusValue(number);
	} else {
		tdCacheStatus.innerHTML = status;
	}
}

function getCacheStatus() {
	return window.applicationCache.status;
}

function setNeedsUploading(needs) {
	if (needs) {
		tdNeedsUploading.innerHTML = 'Data needs to be uploaded';
		tdNeedsUploading.style = '#ff0000';
	} else {
		tdNeedsUploading.innerHTML = 'Data is flushed';
		tdNeedsUploading.style = '#00ff00';
	}
}

function getNeedsUploading() {
	return needsUploading;
}



