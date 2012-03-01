Ext.onReady(function() {
	Ext.define('MyGrid', {
		extend: 'Ext.Component',
		BodyBorder: false,
		domElems:[],
		initComponent: function() {
			this.callParent(arguments);
		},
		afterRender: function() {
			this.callParent(arguments);
			var dom_table=this.el.createChild({
				tag:'table',
			});
			var dom_tbody=dom_table.createChild({
				tag:'tbody',
			});
			for(var i=0;i<this.rows;i++) {
				var el=dom_tbody.createChild({
					tag:'tr',
				});
				var a=[];
				for(var j=0;j<this.cols;j++) {
					var dom_td=el.createChild({
						tag:'td',
						style: {width: 120},
					});
					dom_td.dom.innerHTML='< '+i+'-'+j+' >';
					a.push(dom_td);
				}
				this.domElems.push(a);
			}
		},
		show_data: function(data) {
			for(var row=0;row<data.length;row++) {
				var curr=data[row];
				this.domElems[row][0].dom.innerHTML=curr.data.company;
				this.domElems[row][1].dom.innerHTML=curr.data.value;
			}
		}
	});
	Ext.define('MyPaginatedGrid', {
		extend: 'Ext.Panel',
		BodyBorder: false,
		height: 1000,
		rows:5,
		cols:5,
		currentRow:0,
		layout: {
			type: 'vbox',
		},
		initComponent: function() {
			this.callParent(arguments);
			var widget=this;
			//console.log(this.store.fields);
			var w_grid=Ext.create('MyGrid',{
				rows:this.rows,
				cols:this.cols,
			});
			this.add(w_grid);
			this.w_grid=w_grid;
			var w_button_panel=Ext.create('Ext.Panel',{
				//height:200,
				width:800,
				layout: {
					type: 'hbox',
				},
			});
			var w_prev=Ext.create('Ext.Button',{
				text: 'prev',
				flex: 1,
				listeners: {
					click:function() {
						widget.currentRow-=widget.rows;
						//console.log('prev '+widget.currentRow);
						widget.updateData();
					},
				},
			});
			w_button_panel.add(w_prev);
			var w_next=Ext.create('Ext.Button',{
				text: 'next',
				flex: 1,
				listeners: {
					click:function() {
						widget.currentRow+=widget.rows;
						//console.log('next '+widget.currentRow);
						widget.updateData();
					},
				},
			});
			w_button_panel.add(w_next);
			this.add(w_button_panel);
		},
		updateData:function() {
			var data=this.store.getRange(
				this.currentRow,
				this.currentRow+this.rows-1
			);
			console.dir(data);
			this.w_grid.show_data(data);
		},
	});
	// Here comes the client code:
	var st=Ext.create('Ext.data.Store',{
		fields:[
			{name: 'company',},
			{name: 'value',},
		],
		data:[
			{company:'3m Co',value:71.72,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'3m Co',value:71.72,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'3m Co',value:71.72,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'3m Co',value:71.72,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'3m Co',value:71.72,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
			{company:'3m Co',value:71.72,},
			{company:'Alcoa Inc',value:29.01,},
			{company:'Altria Group Inc',value:83.81,},
			{company:'American Express Company',value:52.55,},
			{company:'American International Group, Inc.',value:64.13,},
		],
	});
	console.dir(st);
	Ext.create('MyPaginatedGrid',{
		rows: 10,
		store: st,
		renderTo: Ext.getBody()
	});
});
