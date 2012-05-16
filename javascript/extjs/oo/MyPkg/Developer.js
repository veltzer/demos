Ext.define('MyPkg.Developer', {
	extend: 'MyPkg.Person',
	requires: ['MyPkg.Person'],
	constructor: function(name, isGeek) {
		console.log("in the Developer constructor for "+name);
		this.isGeek = isGeek;
		// Apply a method from the parent class' prototype
		this.callParent([name]);
	},
	code: function(language) {
		console.log(this.name+" coding in: " + language);
		this.eat("Bug");
	}
});
