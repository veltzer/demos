var btnPressMe, btnScreenShot;
var video;
var canvas;
var context;

function init() {
	btnPressMe = document.getElementById("pressMe");
	btnPressMe.onclick = processSubmit;

	btnScreenShot = document.getElementById("btnScreenShot");
	btnScreenShot.onclick = takeScreenShot;

	video = document.getElementById("myVideo");
	canvas = document.getElementById("myCanvas");
	context = canvas.getContext("2d");
}

function processSubmit() {
	/* Enter Video Code Here */
	toggleSound();
	return false; // Prevent actual form submission
}

function toggleSound() {
	if (video.paused) { 
		video.play();
		btnPressMe.innerHTML = "Pause";
	} else {
		video.pause();
		btnPressMe.innerHTML ="Play";
	}
} 

function takeScreenShot() {
	//context.drawImage(video, 0, 0, video.videoWidth, video.videoHeight);
	//context.translate(-100,100);
	context.drawImage(video, 0, 0, 300, 200);
	//context.translate(+100,-100);
}

window.onload = init;

