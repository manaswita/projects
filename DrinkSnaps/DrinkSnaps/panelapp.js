 Ext.Loader.setConfig({
    enabled : true
});
Ext.application({
    
 launch:function () {

	var data = [
				 {
				 status: '3',
				 orderNo:'48',				 
				 orderDetail:'Cantidad :2 #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				},{
				 status: '2',
    			 orderNo:'50',				 
				 orderDetail:'Cantidad :2 #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				},{
				 status: '3',
    			 orderNo:'52',				 
				 orderDetail:'Cantidad :2\n #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				},{
				 status: '1',
    			 orderNo:'78',				 
				 orderDetail:'Cantidad :2 #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				},{
				 status: '3',
    			 orderNo:'97',				 
				 orderDetail:'Cantidad :2 #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				},{
				 status: '1',
    			 orderNo:'97',				 
				 orderDetail:'Cantidad :2 #1 Vodka Smrinoff Gin and tonic #2 PISCO MISTRAL 35 Coca Cola'
				}
 ];

 Ext.define('Order', {
     extend: 'Ext.data.Model',
     config: {
         fields: [{
             name: 'status',
             type: 'string'
         },{
             name: 'orderNo',
             type: 'string'
         },{
             name: 'orderDetail',
             type: 'string'
         }]
     }
 });

 var store = Ext.create('Ext.data.Store', {
     model: 'Order',
	 data:data,
	 autoLoad: true
 });

//panel containing  order detail For First Tab
var firstPanel=Ext.create('Ext.Container', {
		layout:'hbox',
		height:'85%',
		width:'90%'
		
	 });

//panel containing  order detail For Second Tab
var secondPanel=Ext.create('Ext.Container', {
		layout:'hbox',
		height:'90%',
		width:'90%'
		
	 });
	


//panel containing the order number,image,order detail and order status

    var firstRootPanel=Ext.create('Ext.Container', {
			layout:'vbox',
			height:'80%',
			width:'100%'
         });

	  var secondRootPanel=Ext.create('Ext.Container', {
		layout:'hbox',
		height:'90%',
		width:'90%'
		
	 });



//Adding Panel item for First Tab
for(var i=0;i<store.data.length;i++){
	if(store.data.items[i].raw.status!="3"){
		var id="panel"+i;
		var panelId="panelId"+i;
		var subpanelId="subpanelId"+i;
		var orderId="orderId"+i;
		var orderDetailId="orderDetailId"+i;
		var imgId="imgId"+i;
		var txtId="txtId"+i;

				var id=Ext.create('Ext.Container',{
						layout:'vbox',						
						height:750,
						width:300,
						id:panelId,
						items:[	{
									xtype:'label',
                                    style:'margin-left:25%', 
									id:orderId,	
									html:"Orden: #"+store.data.items[i].raw.orderNo,
								},{	xtype:'panel',
									//style:'background-color:white',
								    id:subpanelId,
									//cls:'white',
									height:450,
									items:[{
											xtype:'image',
											src:'Desert.jpg',
											height:200,
											width:300,
											id:imgId	
											},{
											xtype:'container',
											id:txtId,
											html:store.data.items[i].raw.orderDetail.replace(" ", "\n")
										}],
									listeners:{
												painted:function(){
													for(var j=0;j<store.data.length;j++){
														if(store.data.items[j].raw.status=="1"){
															var colorId="subpanelId"+j;
															Ext.get(colorId).dom.style.cssText="background-color:white;height: 450px !important;";
															//console.log(this.getStyle());
															//this.setStyle('background-color:white');
														}else if(store.data.items[j].raw.status=="2"){
															//console.log(j);
															var secolorId="subpanelId"+j;
															Ext.get(secolorId).dom.style.cssText="background-color:lightgreen;height: 450px !important;";
														}
													}
												}
											}
								  },
									{
									xtype:'label',
                                    style:'margin-left:25%', 
									id:orderDetailId,
									html:"",
								},
							 ]
					 });
				var spacer=Ext.create('Ext.Spacer',{
						width:10
				});

				


				firstPanel.add(id);
				firstPanel.add(spacer);
				id.element.on('tap', function(){
					var testOdetailId="orderDetailId"+this.id.substr(7);
//						if(store.data.items[this.id.substr(7)].raw.status == "1"){
						if(Ext.get(testOdetailId).dom.textContent=="Estado: En Proceso!"){
							var newId="subpanelId"+this.id.substr(7);
									var changeOrderId="txtId"+this.id.substr(7);
									var changeOrderDetailId="orderDetailId"+this.id.substr(7);
									Ext.Msg.confirm('Hecho!', 'Esta Seguro que esta listo?', function (button) {
											if (button == 'yes') {									
												//Ext.get(changeOrderId).dom.textContent="2";
												Ext.get(changeOrderDetailId).dom.textContent="Estado: Listo para entrega!";
												Ext.get(newId).dom.style.cssText="background-color:lightgreen;height: 450px !important;";
											}
									});
						}else{
							var hideId="panelId"+this.id.substr(7);							
							var hideOrderId="orderId"+this.id.substr(7);
							var hideOrderDetailId="orderDetailId"+this.id.substr(7);
							var hideImgId="imgId"+this.id.substr(7);							
							var hidetxtId="txtId"+this.id.substr(7);

							Ext.Msg.confirm('Entregado!', 'Esta Seguro fue entregado?', function (button) {
											if (button == 'yes') {									
												Ext.get(hideId).dom.style.cssText="display:none";
												Ext.get(hideImgId).dom.style.cssText="display:none";
												Ext.get(hideOrderId).dom.textContent="";
												Ext.get(hidetxtId).dom.textContent="";
												Ext.get(hideOrderDetailId).dom.textContent="";
											}
									});
							
						}
				});
				
				if(store.data.items[i].raw.status=="1"){
					id.items.items[2].setHtml("Estado: En Proceso!");
				}else if(store.data.items[i].raw.status=="2"){
					id.items.items[2].setHtml("Estado: Listo para entrega!");
				}
			//firstRootPanel.add(OrderNo);
			firstRootPanel.add(firstPanel);
			//firstRootPanel.add(OrderStatus);
	}
}

//Adding Panel item for Second Tab
for(var i=0;i<store.data.length;i++){
	if(store.data.items[i].raw.status=="3"){
		var id="child"+i;
		var btnId="btn"+i;
		var oderNoId="oderNoId"+i;
				var secondchildPanel=Ext.create('Ext.Container',{
						id:id,
						layout:'vbox',
						//cls:'grey',
						height:450,
						width:300,
						items:[{
									xtype:'label',
                                    style:'margin-left:25%', 
									id:oderNoId,	
									html:"Orden: #"+store.data.items[i].raw.orderNo,
								},{
										xtype:'container',
										cls:'grey', 
										layout:'vbox',
										height:450,
									//	id:orderId,	
											items:[{
											xtype:'image',
											src:'Desert.jpg',
											height:200,
											width:300
											},
											{
											xtype:'container',
											height:180,
											html:store.data.items[i].raw.status,
											},{
												xtype:'button',									
												text:"Activar",
												cls:'redBtn',
												id:btnId,
													listeners: {												
																tap: function() {
																		var hideId="child"+this.id.substr(3); 
																	Ext.Msg.confirm('Activar!', 'Esta Seguro de activarlo de nuevo?', function (button) {
																			if (button == 'yes') {
																				Ext.get(hideId).hide();
																			}
																	});
																
																}
														}
										}]
									}
									
								],
								
					 });
				var spacer=Ext.create('Ext.Spacer',{
						width:10
				});
				secondPanel.add(secondchildPanel);
				secondPanel.add(spacer);
	}
}




 var tabs2 = Ext.create('Ext.tab.Panel', {
        activeTab: 0,
		// iconCls: 'bar',
        fullscreen:true,
        plain: true,

		 tabBar: {
                docked: 'bottom',
                defaults: {
                    iconMask: true,
                    ui: 'plain'
                },
                layout: {
                    pack: 'center'
                }
            },
        defaults :{
            autoScroll: true,
            bodyPadding: 10
        },
        items: [{
                title: 'Enla Cola',
				iconCls: 'action',
				id:'1sttab',
                items:[firstRootPanel]
            },{
                title: 'Entregados',
				iconCls: 'add',
				items:[secondPanel],

            },{
                xtype: 'titlebar',
                docked: 'top',
                title: 'DrinkSnaps'
            }
        ]
    });
	
					Ext.Viewport.setLayout('fit');
					Ext.Viewport.add(tabs2);
    // console.log(Ext.getCmp('mainPanel').getItems().items[0].getStore());
    }
	
});
