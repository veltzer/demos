/*

This file is part of Ext JS 4

Copyright (c) 2011 Sencha Inc

Contact:  http://www.sencha.com/contact

GNU General Public License Usage
This file may be used under the terms of the GNU General Public License version 3.0 as published by the Free Software Foundation and appearing in the file LICENSE included in the packaging of this file.  Please review the following information to ensure the GNU General Public License version 3.0 requirements will be met: http://www.gnu.org/copyleft/gpl.html.

If you are unsure which license is appropriate for your use, please contact the sales department at http://www.sencha.com/contact.

*/
Ext.onReady(function(){
	Ext.define('MovieModel', {
		extend: 'Ext.data.Model',
			fields: [
				'id',
				'name',
			],
		idProperty: 'id'
	});
	var store=Ext.create('Ext.data.Store',{
		pageSize: 50,
		model: 'MovieModel',
		proxy: {
			type: 'ajax',
			url: 'paging.json',
			reader: {
				type: 'json',
				root: 'views',
				totalProperty: 'total'
			},
		},
	});
	var grid=Ext.create('Ext.grid.Panel',{
		height: 500,
		title: 'Movies that I have seen',
		store: store,
		columns:[
			{
				text: "Id",
				dataIndex: 'id',
				width: 20,
				sortable: true,
			},
	    		{
				text: "Name",
				dataIndex: 'name',
				flex: 1,
				sortable: true
			},
		],
		bbar: Ext.create('Ext.PagingToolbar', {
			store: store,
			displayInfo: true,
			displayMsg: 'Displaying movies {0} - {1} of {2}',
			emptyMsg: "No movies to display",
		}),
		renderTo: 'movie-grid'
	});
	// trigger the data store load, we must do it or no data is displayed
	store.loadPage(1);
});
