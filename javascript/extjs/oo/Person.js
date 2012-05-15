Ext.define('Person', {
	// A property
	name: 'Unknown',
	// A constructor
	constructor: function(name) {
		console.log("in the Person constructor for "+name);
		if(name) {
			this.name=name;
		}
	},
	// A method
	eat: function(foodType) {
		console.log(this.name+" is eating a " + foodType);
	}
});
