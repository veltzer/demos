var current;
var clientDataSource;

function ClientDateSource(timestamp) {
	this.date = new Date(timestamp);	
	this.timer = tick();
	this.constructor = init;
	init();
	function tick() {
		this.date.setMilliseconds(this.date.getMilliseconds() + 1000);
		current.innerHTML = this.date.getMilliseconds();
		return setTimeout(tick, 1000);
	}
	function setTime(timestamp) {
		this.date.setMilliseconds(timestamp);
	}
}

window.onload = function() {
	current = document.getElementById("current");
}



