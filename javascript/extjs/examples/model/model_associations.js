Ext.define('Post', {
	extend: 'Ext.data.Model',
	fields: ['id', 'user_id'],
	belongsTo: 'User',
	hasMany: {model: 'Comment', name: 'comments'}
});

Ext.define('Comment', {
	extend: 'Ext.data.Model',
	fields: ['id', 'user_id', 'post_id'],
	belongsTo: 'Post'
});

Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: ['id'],
	hasMany: [
		'Post',
		{model: 'Comment', name: 'comments'}
	]
});

// A different way to define associations would be using the 'associations' attribute:
Ext.define('User', {
	extend: 'Ext.data.Model',
	fields: ['id'],
	associations: [
		{type: 'hasMany', model: 'Post', name: 'posts'},
		{type: 'hasMany', model: 'Comment', name: 'comments'}
	]
});
