Ext.define('MovieModel', {
	extend: 'Ext.data.Model',
	fields: [
		{name: 'id', type: 'number'},
		{name: 'name', type: 'string'},
		{name: 'length', type: 'auto'},
		{name: 'size', type: 'auto'},
		{name: 'chapters', type: 'auto'},
		{name: 'typeName', type: 'string'},
		{name: 'startViewDate', type: 'date', dateFormat: 'timestamp'},
		{name: 'endViewDate', type: 'date', dateFormat: 'timestamp'},
		{name: 'personFirstname', type: 'string'},
		{name: 'personSurname', type: 'string'},
		{name: 'locationName', type: 'string'},
		{name: 'deviceName', type: 'string'},
		{name: 'languageName', type: 'string'},
		{name: 'ratingName', type: 'string'},
		{name: 'review', type: 'string'},
		{name: 'reviewDate', type: 'date', dateFormat: 'timestamp'},
		{name: 'fullname',
			convert: function(value,record) {
				return record.get('personFirstname')+' '+record.get('personSurname');
			}
		},
	],
});
