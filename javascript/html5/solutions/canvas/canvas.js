function init() {
	var btnPresMe = document.getElementById("pressMe");
	btnPresMe.onclick = processSubmit;
	var btnPresMeBack = document.getElementById("pressMeBack");
	btnPresMeBack.onclick = processSubmitBack;
}
function processSubmitBack() {
	/* Enter Canvas Code Here */
	var cnvMyCanvas = document.getElementById("myCanvas");
	var context = cnvMyCanvas.getContext("2d");
	drawBackGround(context, cnvMyCanvas);
	drawGrid(context, cnvMyCanvas);
}

function processSubmit() {
	/* Enter Canvas Code Here */
	var cnvMyCanvas = document.getElementById("myCanvas");
	var context = cnvMyCanvas.getContext("2d");

/*
	context.beginPath();
	context.moveTo(50, 50);
	context.lineTo(350, 50);
	context.stroke();
*/
	
	drawBackGround(context, cnvMyCanvas);
	drawGrid(context, cnvMyCanvas);
	drawBar(context, cnvMyCanvas, 30, 270, "#ff0000");
	drawBar(context, cnvMyCanvas, 60, 70, "#00ff00");
	drawBar(context, cnvMyCanvas, 90, 135, "#ffff00");
	drawBar(context, cnvMyCanvas, 120, 35, "#ff00ff");
	drawBar(context, cnvMyCanvas, 150, 235, "#00ffff");
	drawBar(context, cnvMyCanvas, 180, 185, "#0000ff");

	context.save();
	context.font = "42px Helvetica,Arial,sans-serif";
	context.fillStyle = "#000000";
	context.textAlign = "left";
	//context.strokeText('Sales By Region', 200, 35, cnvMyCanvas.width);
	context.fillText('Sales By Region', 200, 35, cnvMyCanvas.width);
	context.restore();
	context.stroke();

	var smiley = new Image();
	smiley.src = 'BigSmiley.jpg';
	smiley.onload = function() {
		context.drawImage(smiley, 400, 100, 50, 50);
	}

	return false; // Prevent actual form submission
}

var padding = 50;
var bottomLine;
var topLine;
var leftBorder;
var rightBorder;

function drawBar(context, canvas, relativeX, height, style) {
	context.beginPath();
	context.moveTo(padding + relativeX - 10, bottomLine);
	context.lineTo(padding + relativeX + 10, bottomLine);
	context.lineTo(padding + relativeX + 10, bottomLine - height);
	context.lineTo(padding + relativeX - 10, bottomLine - height);
	context.closePath();
	context.fillStyle=style;
	context.fill();
}

function drawGrid(context, canvas) {
	var stripeWidth = (canvas.height - (padding * 2)) / 13;
	for (var i = 50; i < canvas.height - padding; i = i+stripeWidth) {
		context.moveTo(padding, i);
		context.lineTo(canvas.width - padding, i);
	}
	context.stroke();
}

function drawBackGround(context, canvas) {
	bottomLine = canvas.height - padding;
	topLine = padding;
	leftBorder = padding;
	rightBorder = canvas.width - padding;

	context.beginPath();
	context.moveTo(leftBorder, topLine);
	context.lineTo(rightBorder, topLine);
	context.lineTo(rightBorder, bottomLine);
	context.lineTo(leftBorder, bottomLine);
	context.lineTo(leftBorder, topLine);
	context.closePath();
	context.fillStyle="#dadada";
	context.fill();
}

window.onload = init;
