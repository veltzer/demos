// this is a store with an inlined model...
var states=Ext.create('Ext.data.Store', {
	fields: ['abbr', 'name'],
	data : [
		{"abbr":"AL", "name":"Alabama"},
		{"abbr":"AK", "name":"Alaska"},
		{"abbr":"AZ", "name":"Arizona"}
	]
});
// this is a model (defined seperately...)
Ext.define('MovieModel', {
	extend: 'Ext.data.Model',
		fields: [
			'id',
			'name',
		],
	idProperty: 'id',
});
// and a store using the model
var w_store=Ext.create('Ext.data.Store',{
	autoLoad: false,
	pageSize: 20,
	model: 'MovieModel',
	proxy: {
		type: 'ajax',
		url: 'server_url.jsp',
		reader: {
			type: 'json',
			root: 'views',
			totalProperty: 'total'
		},
	},
});
