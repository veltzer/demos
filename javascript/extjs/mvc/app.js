Ext.define('My.store.Contacts', {
    extend: 'Ext.data.Store',
    storeId: 'Contacts',
    fields: [
        'name',
        'phone'
        ],
    data: [
        {
        name: "neil",
        phone: "6045551212"},
    {
        name: "frank",
        phone: "6045551213"}
    ],
    autoLoad: true
});

Ext.define('My.store.Orders', {
    extend: 'Ext.data.Store',
    storeId: 'Orders',
    fields: [
        'product',
    {
        name: 'quantity',
        type: 'int'}
    ],
    data: [
        {
        product: "Deez Brand Nuts",
        quantity: 2},
    {
        product: "iPhone 5",
        quantity: 20}
    ],
    autoLoad: true
});

Ext.define('My.view.ContactsGrid', {
    extend: 'Ext.grid.Panel',
    alias:'widget.contacts-grid',

    height: 250,
    width: 400,
    title: 'Contacts',
    store: 'Contacts',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'name',
                    text: 'Name'
                },
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'phone',
                    text: 'Phone'
                }
            ],
            viewConfig: {

            }
        });

        me.callParent(arguments);
    }
});

Ext.define('My.view.OrdersGrid', {
    extend: 'Ext.grid.Panel',
    alias:'widget.orders-grid',

    height: 250,
    width: 400,
    title: 'Orders',
    store: 'Orders',

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            columns: [
                {
                    xtype: 'gridcolumn',
                    dataIndex: 'product',
                    text: 'Product'
                },
                {
                    xtype: 'numbercolumn',
                    dataIndex: 'quantity',
                    text: 'Quantity'
                }
            ],
            viewConfig: {

            }
        });

        me.callParent(arguments);
    }
});

Ext.define('My.controller.Contacts', {
    extend: 'Ext.app.Controller',

    stores: [
        'Contacts'
        ],

    views: [
        'ContactsGrid'
        ],

    refs: [
        {
        ref: 'grid',
        selector: '',
        xtype: 'contacts-grid',
        autoCreate: true}
    ],

    init: function(application) {
        if (this.inited) {
            return;
        }
        this.inited = true;

        this.control({
            'contacts-grid': {
                itemdblclick: function() {
                    alert('you double clicked an item in the contacts grid');
                }
            }
        });
    },

    actionIndex: function() {
        this.application.setMainView(this.getGrid());
    }
});

Ext.define('My.controller.Orders', {
    extend: 'Ext.app.Controller',

    stores: [
        'Orders'
        ],

    views: [
        'OrdersGrid'
        ],

    refs: [
        {
        ref: 'grid',
        selector: '',
        xtype: 'orders-grid',
        autoCreate: true}
    ],

    init: function(application) {
        if (this.inited) {
            return;
        }
        this.inited = true;

        this.control({
            'orders-grid': {
                itemdblclick: function() {
                    alert('you double clicked an item in the orders grid');
                }
            }
        });
    },

    actionIndex: function() {
        this.application.setMainView(this.getGrid());
    }
});

Ext.define('My.controller.Viewport', {
    extend: 'Ext.app.Controller',

    init: function(application) {
        if (this.inited) {
            return;
        }
        this.inited = true;

        this.control({
            'viewport #contacts': {
                click: function() {
                    this.application.runAction('Contacts', 'Index');
                }
            },
            'viewport #orders':{
                click:function(){
                    this.application.runAction('Orders', 'Index');
                }
            }
        });
    }
});

Ext.define('My.view.Viewport', {
    extend: 'Ext.container.Viewport',

    layout: {
        type: 'border'
    },

    initComponent: function() {
        var me = this;

        Ext.applyIf(me, {
            items: [
                {
                    xtype: 'tabpanel',
                    region: 'center',
		    id: 'center'
                },
                {
                    xtype: 'container',
                    style: 'background-color:cornflowerblue',
                    region: 'north',
                    items: [
                        {
                            xtype: 'label',
                            style: 'color:white;font-size:20px',
                            text: 'My Application'
                        },
                        {
                            xtype: 'toolbar',
                            items: [
                                {
                                    xtype: 'button',
                                    itemId: 'contacts',
                                    text: 'Contacts'
                                },
                                {
                                    xtype: 'button',
                                    itemId: 'orders',
                                    text: 'Orders'
                                }
                            ]
                        }
                    ]
                }
            ]
        });

        me.callParent(arguments);
    }
});

Ext.application({
    autoCreateViewport: true,
    name: 'My',

    launch: function() {
        this.viewport = Ext.ComponentQuery.query('viewport')[0];
        
        var c = this.getController('Viewport');
        c.init();
    },
    
    runAction:function(controllerName, actionName){
        var controller = this.getController(controllerName);
        controller.init(this);
        controller['action'+actionName]();
    },
    
    setMainView: function(view){
        //var center = this.viewport.layout.regions.center;
	var center=Ext.getCmp('center');
        
        if(center.items.indexOf(view)===-1){
            center.add(view);
        }
        
        center.setActiveTab(view);
    }
});
