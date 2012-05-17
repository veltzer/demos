// a store with inline data (hopefully the list of states will not change...)
var store_inline=Ext.create('Ext.data.Store', {
	fields: ['abbr', 'name'],
	data : [
		{"abbr":"AL", "name":"Alabama"},
		{"abbr":"AK", "name":"Alaska"},
		{"abbr":"AZ", "name":"Arizona"}
	]
});
// same store with a proxy to fetch the data from the server... 
var w_store=Ext.create('Ext.data.Store',{
	fields: ['abbr', 'name'],
	autoLoad: true,
	proxy: {
		type: 'ajax',
		url: 'states.php',
		reader: {
			type: 'json',
			root: 'states',
		},
	},
});
