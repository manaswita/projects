Ext.application({
    name: 'DrinkSnaps',
    launch: function () {
        Ext.create('Ext.Container', {
           fullscreen: true,
            layout: 'hbox',
            id: 'mainPanel',
            items: [{
                flex: 1,
                id: 'menu',
                xtype: 'dataview',
                scrollable: false,
                autoLoad: true,
                cls: 'menu-bg',
                dockedItems: [{xtype: 'toolbar', dock: 'top', cls: 'menu-bg', border: 0}],
                store: {
                    fields: ['name'],
                    data: menu
                },
                listeners: {
                    /*painted: function () {
                        // this.select(0);
                    },*/
                    //select: function (self, record, eOpts) {
					'itemtap': function (self, index, target, record, e, eOpts) {
                        var orderStore = Ext.getCmp('order').getStore();
                        var _data = [];
						Ext.getCmp('products').getStore().removeAll();
                        for (var i in menu[index].items) {
                        	
                        	var _tmp = Ext.clone(menu[index].items[i]);
                        	if (typeof menu[index].mixers != 'undefined') {
                            	_tmp.mixers = _mixers[menu[index].mixers];
                        	}
                        	_tmp.category = menu[index].name;
							Ext.getCmp('products').getStore().add(_tmp);
                            _data.push(_tmp);
                            var menuItemId = menu[index].items[i].id;
                            if (orderStore.getById(menuItemId)) {
                                _data[_data.length - 1].quantity = orderStore.getById(menuItemId).get('quantity')
                            }
                        }
                        Ext.getCmp('mixers').getStore().removeAll();
                    }
                },
                itemTpl: '<div class="menu-item">{name}</div>'
            }, {
            	layout: 'hbox', flex: 3,
                cls: 'main test', 
                items: [{
                xtype: 'dataview',
                width: 410,
                dockedItems: [{xtype: 'toolbar', dock: 'top', cls: 'main', border: 0}],
                id: 'products',

                itemTpl: '<div class=" category-thumb category-list"><div class="thumbnail"><img src="drinkpics/{id}.png" height="213" width="370" /></div><h1 class="title">{name}</h1><h2 class="price">${price}</h2><h2 class="quantity" id="product-{id}-quantity"><tpl if="quantity">{quantity}</tpl></h2></div></div>',
                store: new Ext.data.Store({
                    model: 'Products',
					 autoLoad: false,
                    data: []
                }),
                // baseCls: 'test',
                itemSelector: '.category-thumb',
                listeners: {
                    'itemtap': function (dataView, index, target, event, eOpts) {						
                    	var orderColumn = Ext.getCmp('orderColumn');
                    	orderColumn.setActiveItem(1);
                        var record = dataView.getStore().getAt(index);
                        Ext.select('div.category-extended').toggleCls('category-extended');
						Ext.getCmp('mixers').getStore().removeAll();
                        if (record.raw.mixers) {
                        	Ext.getCmp('mixers').getStore().add(record.raw.mixers);
                        	target.addCls('category-extended')
                        	return;
                        } else {
                        	Ext.getCmp('mixers').getStore().removeAll();                        	
                        }
						
                        modifyOrder(record, true);                                                                      
                        target.select('h2.quantity').first().dom.innerHTML = record.get('quantity');
                    },
                    scope: this
                }
                }, {xtype: 'dataview',
                	width: 400,
					id:'chk',
                    listeners: {
                        'itemtap': function (dataView, index, target, event, eOpts) {
                            	var products = Ext.getCmp('products');
                            	var mixerRecord = dataView.getStore().getAt(index);
                            	var data = Ext.clone(products.selected.getAt(0).raw);
                            	data.id = data.id + '-' + mixerRecord.get('id');
                            	data.mixer = mixerRecord.get('name');
                            	modifyOrder({data: data});
                        }
                    },
                	dockedItems: [{xtype: 'toolbar', dock: 'top', cls: 'main', border: 0}],
                	id: 'mixers',
                    itemTpl: '<div class=" category-thumb category-list"><div class="thumbnail"><img src="drinkpics/{id}.png"  height="213" width="370" /></div><h1 class="title">{name}</h1></div>',
                    scrollable: false,
                    store: new Ext.data.Store({
                    	fields: ['name', 'id'],
                        data: []
                    }),
                    }]
            },{
            	layout: 'card',  flex: 1, id: 'orderColumn',
            	items: [{layout: 'fit', html: '', cls: 'cart-bg'},{
                xtype: 'dataview',
                cls: 'cart-bg',
                id: 'order',
                autoScroll:true,
				layout: 'fit',
                store: {
                    fields: ['id', 'name', 'price', 'quantity', 'total', 'mixer'],
                    data: [],
                    listeners: {
                        datachanged: function (store, eOpts) {
                            if (Ext.getCmp('total')) {
                                Ext.getCmp('total').setText('Total: $' + store.sum('total'));
                            }
                        },
                        clear: function (store, eOpts) {
                            store.fireEvent('datachanged', store, eOpts);
                        },
                    },
                },
                itemTpl: '<div class="cart-item"><div class="quantity">{quantity}X</div><div class="name">{name}<div class="mixer">{mixer}</div></div><div class="total">${total}</div><div class="change"><div class="plus"></div><div class="minus"></div></div></div>',
                listeners: {
                    'itemtap': function (dataView, index, target, event, eOpts) {
                        var store = dataView.getStore(),
                        	record = store.getAt(index);                    	
                        if (/minus/.test(eOpts.changedTouches[0].target.className)) {
                            record.set('quantity', record.get('quantity') - 1);
                            record.set('total', record.get('quantity') * record.get('price'));
                            try {
                                Ext.get('product-' + record.get('id') + '-quantity').dom.innerHTML = record.get('quantity') ? record.get('quantity') : '';
                            } catch (e) {}
                            record.commit(false);
                            if (!record.get('quantity')) {
                                store.remove(record);
                            }
                            store.fireEvent('datachanged', store);
                        }
                        if (/plus/.test(eOpts.changedTouches[0].target.className)) {
                        	var data = Ext.clone(record.data);
                        	modifyOrder({data: data});
                        }
                    }
                },
                items: [{xtype: 'toolbar',
                    docked: 'top', cls: 'cart-bg', border: 0},{xtype: 'toolbar', docked: 'bottom', cls: 'cart-bg', border: 0},{
                    xtype: 'toolbar',
                    docked: 'bottom',
					itemId:'myItem',
					//layout: 'fit',
                    cls: 'cart-bg', border: 0,
                    items: [{
                    	cls: 'cart-toolbar',
                        text: 'Borrar',
                        ui: 'trash', ui: 'action',
                        handler: function () {
                            Ext.getCmp('order').getStore().removeAll();
                            Ext.getCmp('products').getStore().queryBy(function (record, index) {
                                if (record.get('quantity')) record.set('quantity', null);
                                record.commit();
                            });
                            Ext.getCmp('products').getStore().removeAll();
                            Ext.getCmp('menu').deselect(Ext.getCmp('menu').selected.getAt(0));
                            Ext.getCmp('mixers').getStore().removeAll();
							console.log(Ext.getCmp('mixers').getStore());
                            Ext.getCmp('orderColumn').setActiveItem(0);
                        }
                    }, {cls: 'cart-toolbar', text: 'Pagar', ui: 'action', handler: function(){
                    	var finalView = Ext.create('Ext.Container', {
                            fullscreen: true,layout: 'fit', mask: {
                                message: 'Waiting for card'
                            }, items: [
{
    docked: 'top',
    xtype: 'toolbar',
	title: Ext.getCmp('total').getText()
}, {
    docked: 'bottom',
    xtype: 'toolbar',
    items: [{
        text: 'Cancelar',
        handler: function() {
        	finalView.hide();
        	Ext.getCmp('mainPanel').show();
        }
    }]}                                                                                                           
                                                                                                            
                                                                                                            ]});
                    	finalView.show();
                		Ext.getCmp('mainPanel').hide();
                    	
                    }}, {
                    	cls: 'cart-toolbar',
                        id: 'total',
                        text: 'Total: $0'
                    }]
                }],
            }]}]
        });
    }
});


function modifyOrder(record, increase){
	increase = increase || true;
    var store = Ext.getCmp('order').getStore();
    if (!store.getById(record.data.id)) {
        record.data.quantity = 0;
        store.add(record.data);
    }
    var amount = increase ? 1 : -1;
    record = store.getById(record.data.id);
    record.set('quantity', record.get('quantity') + amount);
    record.set('total', record.get('quantity') * record.get('price'));
	
    record.commit();
    store.fireEvent('datachanged', store);
    Ext.getCmp('order').select(store.getAt(store.getCount()-1), false);
}

Ext.define('Products', {
    extend: 'Ext.data.Model',
    fields: ['id', 'name', 'price', 'quantity', 'total', 'mixers']
});

var _mixers = {group1: [{id: 1000, name: 'Coca Cola'}, {id: 1002, name: 'Coca Cola Zero'}, {id: 1001, name: 'Coca Cola Light'}]};
