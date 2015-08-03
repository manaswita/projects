
//Variable Declaration
var vev;
var REQUIRED_3D_VIEWER = "8,0,100,27428";

function getSystemConfiguration(){
	var sysConfig=new Object();

	//Check OS
	sysConfig.os = navigator.platform;
	//Check browser
	//Note: 3D viewer is supported only for IE on Windows
	sysConfig.browserRaw=navigator.userAgent;
	var IE11Plus =false;
	if(parseInt($.browser.version)>=11){
		var trident = !!navigator.userAgent.match(/Trident\/7.0/);
		IE11Plus = trident;
	}
	if (($.browser.msie && parseInt($.browser.version)>=7) || IE11Plus){
		sysConfig.browser="Internet Explorer "+$.browser.version;
		if (sysConfig.os.indexOf("Win")>-1){
			//3D viewer supported
			sysConfig.isViewer3dSupported=true;
		}
		else{
			sysConfig.isViewer3dSupported=false;
		}
	}
	else {
		//Unsupported browsers for 3D viewer
		if ($.browser.webkit){
			var webkitBrowserName="";
			if (navigator.userAgent.match(/OPR/)){
				webkitBrowserName=" Opera";
			}
			else if (navigator.userAgent.match(/Chrome/)){
				webkitBrowserName=" Chrome";
			} 
			else if (navigator.userAgent.match(/Safari/)){
				webkitBrowserName=" Safari";
			} 
			
			sysConfig.browser="Webkit"+webkitBrowserName+" "+$.browser.version;
		}
		else if ($.browser.mozilla){
			sysConfig.browser="Mozilla "+$.browser.version;
		}
		else if ($.browser.msie){
			sysConfig.browser="Internet Explorer "+$.browser.version;
		}
		else if ($.browser.safari){
			sysConfig.browser="Safari "+$.browser.version;
		}
		else if ($.browser.opera){
			sysConfig.browser="Opera "+$.browser.version;
		}
		else{
			sysConfig.browser="Unknown "+$.browser.version;
		}
		sysConfig.isViewer3dSupported=false;
	}

	if (sysConfig.isViewer3dSupported==true){
		sysConfig.isViewer3dInstalled = is3dViewerInstalled();
	}
	else{
		sysConfig.isViewer3dInstalled=false;
	}
	return sysConfig;
}

/**
	This function checks the 3d viewer 
*/
function is3dViewerInstalled(){

	try{
		var sapViewer = new ActiveXObject("ExplorationX.mainSTA.1");
		viewer3dVersionNum = sapViewer.Version;
		alert(viewer3dVersionNum);
		if (viewer3dVersionNum == null){
			return false;
		}
		else if (viewer3dVersionNum!=REQUIRED_3D_VIEWER){
			alert(viewer3dVersionNum +"==="+REQUIRED_3D_VIEWER);
			return false;
		}
		else {
			return true;
		}
	}
	catch(err){
		return false;
	}
}

/**
	Load the Model file into the  Model Object
*/
function initialize(){
	var systemConfig = getSystemConfiguration();
	alert(systemConfig.isViewer3dInstalled);
	if (systemConfig==null || !systemConfig.isViewer3dInstalled){
		
	}
	
	document.getElementById("viewer3dDiv").innerHTML="<object id='ev3d' type='application/rh'></object>";
	vev = document.getElementById("ev3d");
	disableMenuItems();
	handleToolBarVisibility();
	vev.NodesSelected = getSelectedNodes;
}

/**
	This function Loads the file into the viewer
*/
function loadFile(filePath){
	vev.LoadFile(filePath);
}

/**
	This function is the callback function which returns the clicked part number
*/
function getSelectedNodes(nodes){
	
	if(nodes.count > 0){
		var nodeNameArray = new Array(nodes.count); 
		for(var i=0;i<nodes.count;i++){
			var node = nodes.item(i);
			nodeNameArray[i]=node.Name;
		}
		callbackClickedNodeNumber(nodeNameArray);
	}
	
}
/**
	This function executes on model to highlight the part in the model
*/
function highlight3dNode(nodeNumber){
	var str =  vev.Scene.ExecuteQuery('everything() unselect() exact_match($name,"'+nodeNumber+'",false) select()');
	if(str.indexOf("Skipped") > -1){
		alert("Part not shown in 3d model");
		return false;
	}
}

/**
	This function hides/shows the menu items by menuItemId
*/
function handleMenuItemVisibility(menuItemId,visibility){
	vev.ShowMenuItem(menuItemId,visibility);
}

/**
	This function hides/shows the 3d loader div
*/
function handle3dDivVisibility(visibility){
	if(visibility){
		document.getElementById("viewer3dDiv").style.display='block';
	}else{
		document.getElementById("viewer3dDiv").style.display='none';
	}
}

/**
	Disables Menu Items Not to be shown in the viewer
*/
function disableMenuItems(){
	handleMenuItemVisibility("M3000",false); 	//Page Layout - Toolbar Button
	handleMenuItemVisibility("M3002",false); 	//Cross Section - Toolbar Button
	handleMenuItemVisibility("M3018",false); 	//Visual Compare - Toolbar Button
	handleMenuItemVisibility("M3200",false); 	//Measurements - Toolbar Button
	//handleMenuItemVisibility("M1720",false);	//Turntable
	handleMenuItemVisibility("M1721",false); 	//Walk
	//handleMenuItemVisibility("M1702",false); 	//Pan
	handleMenuItemVisibility("M2902",false);  	//Projection
	handleMenuItemVisibility("M3011",false); 	//Fullscreen View - Toolbar Button
	handleMenuItemVisibility("M1704",false); 	//Mouse Mode\Fly
}

/**
	handles ToolBar Visibility
*/
function handleToolBarVisibility(){
	vev.ShowGuiElement("left_toolbar",false);
	vev.ShowGuiElement("right_toolbar",true);
	vev.ShowGuiElement("top_toolbar",true);
	vev.ShowGuiElement("bottom_toolbar",true);
	vev.ShowGuiElement("right_click_menu",false);
	vev.ShowGuiElement("HOTSPOT_TOOLTIP",true);
}