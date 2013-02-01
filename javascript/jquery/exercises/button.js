function Button(text,id_for_append) {
	this.text=text;
	this.realButton=$('<button/>').text(this.text);
	$(id_for_append).append(this.realButton);
}

// this method is called when the button is clicked.
Button.prototype.click=function() {
	// in this parent class we do nothing...
	// (let the children inherit...)
};
