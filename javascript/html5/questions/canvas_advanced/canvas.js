function init() {
	var btnPresMe = document.getElementById("pressMe");
	btnPresMe.onclick = processSubmit;
}

function degreesToRadians(degrees) {
	return degrees / 360 * Math.PI * 2;
}

function processSubmit() {
	/* Enter Canvas Code Here */
	var myCanvas = document.getElementById("myCanvas");
	var context = myCanvas.getContext("2d");
	drawRainbow(context);
	drawStonePath(context);
	return false; // Prevent actual form submission
}

function drawRainbow(context) {
	context.save();
	context.beginPath();

	var gradient = context.createRadialGradient(300,300,220,300,300,280);
	gradient.addColorStop(0.0, "#0000ff");
	gradient.addColorStop(0.25, "#00ffff");
	gradient.addColorStop(0.5, "#00ff00");
	gradient.addColorStop(0.75, "#ffff00");
	gradient.addColorStop(1.0, "#ff0000");

	context.strokeStyle = gradient;

	var arc = context.arc(300, 300, 250, Math.PI, 0, false);
	context.lineWidth = 80;
	context.stroke();

	context.closePath();
	context.restore();
}

function drawStonePath(context) {
	context.save();
	context.beginPath();

	var stone = new Image();
        stone.src = 'stone.jpg';
        stone.onload = function() {
                context.strokeStyle = context.createPattern(stone, 'repeat');
                context.lineWidth = 40;
                context.moveTo(200, 350);
                context.lineTo(600, 350);
                context.stroke();
        }

	context.closePath();
	context.restore();
}

window.onload = init;

