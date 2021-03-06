var GEOAPIS_V1 = GEOAPIS_V1 || {};
var ALL_SELLERS= null;
var isTWuseCase = false;
var setLocationEvent=null;
var setLocationPosition=null;
var sellerlocation = null;
var insideSeller = [];
var	outsideSeller = [];
var	sellers = [];
var globalCount = 0;
GEOAPIS_V1.apiDemo = function(){
	this.apiAddress = window.location.origin;
	this.map;
	this.pinInfobox;
	this.marker;

	//The following key is restricted for Pitney Bowes internal use only. Any other use is strictly prohibited.
	this.app = 'Ahn7E-sCulxJjaJgNrcAimfdLxrz-r8UcWZU8c7z2QF6NkKiyyx6iFlFGYoU6R-6';
	this.isMarkersPositionReset = false;
	this.tmp = {
				"branchId":'',
				"addressId":'',
				"routeId":'',
				"activeControlId":'',
				"disabledControlIds":[]
				}
	this.branches = {
						"Branch1" : {}, 
						"Branch2" : {}	
					};
	this.addresses = {
						"Address1" : [], 
						"Address2" : []	
					};
	this.routes = { 
					"Route1":
								{ 
									"points" : [], 
									"polylineOptions" :	{
															fillColor: new Microsoft.Maps.Color(255, 0, 0, 255),
															strokeColor: new Microsoft.Maps.Color(255, 0, 0, 255),
															strokeThickness: 3
														}
								},
					"Route2":
								{ 
									"points" : [], 
									"polylineOptions" :	{
															fillColor: new Microsoft.Maps.Color(255, 0, 255, 0),
															strokeColor: new Microsoft.Maps.Color(255, 0, 255, 0),
															strokeThickness: 3
														}
								}
				};
				
		this.routesTW = [{
			"id"			:	'',
			"points"		:	[
									{
										"id"	:	0,
										"lat"	:	0,
										"lng"	:	0,
										"type"	:	'',
										"markerIcon"	:	'',
										"info"	:	{
														"name"	:	'',
														"product"	:	'',
														"address"	:	'',
														"preferredTime" : '',
														"visitOrder":	0,
														"eta"	:	'',
														"etd"	:	'',
														"waitTime"	:	''
													}
									}
								],
			"instruction"	:	''
			
		}];
				
};

GEOAPIS_V1.apiDemo.prototype.renderMap = function(){
	var that = this;
	var mapOptions = { 
		credentials: that.app, 
		mapTypeId: Microsoft.Maps.MapTypeId.road,
		zoom: 12,
		enableSearchLogo: false,
		showMapTypeSelector: true,
		showScalebar: false,
		disableZooming: false
	} 
	that.map = new Microsoft.Maps.Map(document.getElementById("apiDemo-sd-map"), mapOptions);
	Microsoft.Maps.loadModule('Microsoft.Maps.AdvancedShapes');
	var loc = new Microsoft.Maps.Location(28.65019619154841, 77.23213411669923);
	that.map.setView({center: loc});
};

GEOAPIS_V1.apiDemo.prototype.clearMap = function(param){
	for(var i=this.map.entities.getLength()-1;i>=0;i--) {
		var entity= this.map.entities.get(i); 
		if(param == 'branches'){
			if ((entity instanceof Microsoft.Maps.Pushpin && ((entity._htmlContent != null && entity._htmlContent.indexOf("pb.png") >= 0)))  || entity instanceof Microsoft.Maps.Infobox || entity instanceof Microsoft.Maps.Polyline) { 
				this.map.entities.removeAt(i); 
			}
		}
		
		if(param == 'customers'){
			if(((entity instanceof Microsoft.Maps.Pushpin && (!(entity._htmlContent != null && entity._htmlContent.indexOf("pb.png") >= 0))) || entity instanceof Microsoft.Maps.Infobox || entity instanceof Microsoft.Maps.Polyline)){
				this.map.entities.removeAt(i); 
			}
			
		}
		
		if(param == 'routeMarkers'){
			if(entity instanceof Microsoft.Maps.Pushpin && (!(entity._htmlContent != null && entity._htmlContent.indexOf("pb.png") >= 0))){
				this.map.entities.removeAt(i); 
			}
			
		}
		
		if(param == 'routes'){
			if(entity instanceof Microsoft.Maps.Polyline){
				this.map.entities.removeAt(i); 
			}
		}
	}
};

GEOAPIS_V1.apiDemo.prototype.showCustomers = function(){
	var _branch;
	var _dataBranch;
	var _address;
	var that=this;
	
	var branchId = that.tmp.branchId;
	var addressId = that.tmp.addressId;
	var routeId = that.tmp.routeId;
	var activeControlId = that.tmp.activeControlId;
	var disabledControlIds = that.tmp.disabledControlIds;
	
	if(that.isMarkersPositionReset){
		_address = that.routes[routeId].points.slice(1);
	}
	else{
		that.routes[routeId].points=[];
		_branch = that.branches[branchId];
		//_dataBranch = geocodeAddress(_branch.column2);
		
		_dataBranch = _branch;
		that.routes[routeId].points[0] = {latitude: _dataBranch.Latitude, longitude: _dataBranch.Longitude, position: 0, order:0, markerIcon: _branch.markerIcon, column2:_dataBranch.column2, skip:false};
		
		_address = that.addresses[addressId];
	}
	
	for(var i=0; i< _address.length; i++){
		var _data;
		var _markerText;
		var _loc;
		if(that.isMarkersPositionReset){
			_data = _address[i];
			_markerText = _data.position.toString();
			_loc = new Microsoft.Maps.Location(_data.latitude, _data.longitude);
		}
		else
		{
			//_data = geocodeAddress(_address[i].column2);
			_data = _address[i];
			that.routes[routeId].points[i+1] = {latitude: _data.Latitude, longitude: _data.Longitude, position: (i+1), order: (i+1), markerIcon: _address[i].markerIcon, column2:_data.column2, skip:false, name:_data.name, product:_data.product};
			_markerText = that.routes[routeId].points[i+1].position.toString();
			_loc = new Microsoft.Maps.Location(_data.Latitude, _data.Longitude);
		}
		
		var _markerIndex = _markerText;
		var _htmlContent= '<img src="'+_address[i].markerIcon+'"><div class="'+ routeId + '">' + _markerText + '</div>';
		if(_markerText.indexOf("99999")>-1){
			_markerText="";
			_htmlContent= '<img src="'+_address[i].markerIcon+'"><div>' + _markerText + '</div>';
		}
		//var _htmlContent= '<img src="'+_address[i].markerIcon+'"><div class="'+ routeId + '">' + _markerText + '</div>';
		var _marker = new Microsoft.Maps.Pushpin(_loc, {
							//icon: _address[i].markerIcon ,
							zIndex: 99,
							//width: 64,
							//text: _markerText,
							//textOffset: new Microsoft.Maps.Point(27, 0),
							//height: 64
							textType:'MapPushpinBase',
							htmlContent: _htmlContent
						});
		//_marker.Description = _data.column2;
		
		var _name;
		var _custAddress;
		var _product;
		if(typeof _data.name == 'undefined'){
			_name = 'New Customer/Lead'
		}
		else{
			_name= _data.name;
		}
		if(typeof _data.column2 == 'undefined'){
			_custAddress = 'New Address'
		}
		else{
			_custAddress= _data.column2;
		}
		if(typeof _data.product == 'undefined'){
			_product = 'New Product'
		}
		else{
			_product= _data.product;
		}
		
		var _custLead;
		
		if(_data.markerIcon.indexOf('cust')>-1){
			_custLead = 'Customer Name';
		}
		else if(_data.markerIcon.indexOf('lead')>-1){
			_custLead = 'Lead Name';
		}
		else{
			_custLead = 'Name';
		}
		_marker.infoHeader = "Customer Information"
		_marker.Description = "<b>" + _custLead + ": </b>" + _name
									+ "<br/><b>Address: </b>" + _custAddress;
									
								
		_marker.index = _markerIndex;
		
		Microsoft.Maps.Events.addHandler(_marker, 'click', displayInfobox);
		Microsoft.Maps.Events.addHandler(_marker, 'rightclick', function(e) {
			if (e.target != null)
			{
				var index = parseInt(e.target.index);
				var _idx;
				for(var i=0;i<that.routes[routeId].points.length;i++){
						if(that.routes[routeId].points[i].position == index){
							_idx = i;
							break;
						}
					}
				if (index >= 9999900000) {
					if(($(activeControlId).is(':checked')) && (that.routes[routeId].points[_idx].skip)){
						bootbox.confirm("Do you want to add this customer ?", function(result) {
							//if(confirm("Do you want to add this customer ?")){
							if(result){
								that.routes[routeId].points[_idx].skip = false;
								//that.map.entities.remove(e.target);
								$('#directionDetails').html('');
								$('#directionDetails').hide();
								GSDS.clearMap('routes');
								GSDS.getRoutes(routeId,$(activeControlId+'Type').val());
								$('.labelPin div').show();
								/*
								for(var k=0;k<disabledControlIds.length;i++){
									$(disabledControlIds).attr('checked', false);
								}
								*/
							}
						});				
						
					}
				}
				else if (index >= 0) {
					if(($(activeControlId).is(':checked')) && !(that.routes[routeId].points[_idx].skip)){
						bootbox.confirm("Do you want to skip this customer ?", function(result) {
							//if(confirm("Do you want to skip this customer ?")){
							if(result){
								//that.routes[routeId].points.splice( _idx, 1 );
								that.routes[routeId].points[_idx].skip = true;
								//that.map.entities.remove(e.target);
								$('#directionDetails').html('');
								$('#directionDetails').hide();
								GSDS.clearMap('routes');
								GSDS.getRoutes(routeId,$(activeControlId+'Type').val());
								$('.labelPin div').show();
								/*
								for(var k=0;k<disabledControlIds.length;i++){
									$(disabledControlIds).attr('checked', false);
								}
								*/
							}
						});				
						
					}
				}
				
				
			}
		});
		Microsoft.Maps.Events.addHandler(that.pinInfobox, 'click', function(){
			that.pinInfobox.setOptions({ visible:false });
		});
		that.map.entities.push(_marker);
	}

};
function setUserLocation()
{
	setLocationEvent= Microsoft.Maps.Events.addHandler(GSDS.map, 'click',getLatlng ); 
}
function getLatlng(e)
{
	if (e.targetType == "map") {
           var point = new Microsoft.Maps.Point(e.getX(), e.getY());
           var locTemp = e.target.tryPixelToLocation(point);
           var location = new Microsoft.Maps.Location(locTemp.latitude, locTemp.longitude);
        setLocationPosition = location;

		var pin = new Microsoft.Maps.Pushpin(location, {'draggable': false});
             
             GSDS.map.entities.push(pin);
           
             Microsoft.Maps.Events.removeHandler(setLocationEvent );

        }              
}
function displayUserLocation()
{
	var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});
             
             GSDS.map.entities.push(pin);
}
function searchProduct()
{
	
	$('#loading').show();
	GSDS.clearMap('customers');
	var dataToSearch =null;
	new Object();
	dataToSearch = {name: $('#product').val(),lng:setLocationPosition.latitude,lat:setLocationPosition.longitude} ;
	//alert(dataToSearch);
	/* $.ajax({
		url: 'getSellerWhoSellsTheProduct.html',
		data: dataToSearch,
		type:'POST',
		cache: false,
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data){
			ALL_SELLERS = data;
			for(var _key in ALL_SELLERS)
			{
				ALL_SELLERS[_key].locationJson = $.parseJSON(ALL_SELLERS[_key].locationJson);
				ALL_SELLERS[_key].servingAreaJson = $.parseJSON(ALL_SELLERS[_key].servingAreaJson);
			}
			var loc = new Microsoft.Maps.Location(parseFloat(ALL_SELLERS[0].locationJson.coordinates[1],10),parseFloat( ALL_SELLERS[0].locationJson.coordinates[0],10));
			lngArray=[];
			latArray=[];
			
			GSDS.map.entities.clear();
			for(var _key in ALL_SELLERS)
			{
				latArray.push(parseFloat(ALL_SELLERS[_key].locationJson.coordinates[1],10));
				lngArray.push(parseFloat( ALL_SELLERS[_key].locationJson.coordinates[0],10));
				// 
				var loc = new Microsoft.Maps.Location(parseFloat(ALL_SELLERS[_key].locationJson.coordinates[1],10), parseFloat( ALL_SELLERS[_key].locationJson.coordinates[0],10));
				var pushpinOptions = {icon: 'images/location_icon.png', width: 50, height: 50}; 
				if(ALL_SELLERS[_key].status == "Not Available")
				{
					pushpinOptions = {icon: 'images/location_icon1.png', width: 50, height: 50}; 
				}
				var pushpin= new Microsoft.Maps.Pushpin(loc, pushpinOptions);
				GSDS.map.entities.push(pushpin);
				
			}
			latArray.sort(function(a,b){
				return parseFloat(a,10) - parseFloat(b,10);
			});
			
			var locationArr = [
					{latitude: latArray[0], longitude:lngArray[0]},
					{latitude: latArray[latArray.length - 1], longitude: lngArray[lngArray.length - 1]}
				];
				
				var initialViewBounds = Microsoft.Maps.LocationRect.fromCorners(new Microsoft.Maps.Location(locationArr[0].latitude,locationArr[0].longitude), new Microsoft.Maps.Location(locationArr[1].latitude,locationArr[1].longitude));
			 var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});             
             GSDS.map.entities.push(pin);
			GSDS.map.setView({bounds: initialViewBounds}); 
			
				searchPart2(dataToSearch);
			
			GSDS.map.setView({center: setLocationPosition});
		}
	});*/
	searchPart2(dataToSearch);
	//searchData3();
	
}
function showSellerGeofence()
{
	
	GSDS.clearMap('customers');
	$('#loading').show();
	var boundlocation = [];
			for(var key in toRender){
					var lineVertices = [];
					var locationArr = [];
					
					var newPos = 0;
					for(var i=0; i<toRender[key].COVERAGE.coordinates[0].length; i++){
						locationArr[i] = {};						
						locationArr[i].latitude = toRender[key].COVERAGE.coordinates[0][i][1];
						locationArr[i].longitude = toRender[key].COVERAGE.coordinates[0][i][0];
						
						lineVertices[i] = new Microsoft.Maps.Location(locationArr[i].latitude, locationArr[i].longitude);
						boundlocation.push(locationArr[i]);
					}
					/* var val=Math.floor((Math.random() * 250) + 1);
					var val1=Math.floor((Math.random() * 250) + 1); */
					var optionsInside = {
									fillColor: new Microsoft.Maps.Color(255, 0, 255, 0),
									//fillColor: colorsArr[i],
									strokeColor: new Microsoft.Maps.Color(255, 0, 255, 0),
									strokeThickness: 3
								};
					var optionsOutside = {
									fillColor: new Microsoft.Maps.Color(255, 255, 0, 0),
									//fillColor: colorsArr[i],
									strokeColor: new Microsoft.Maps.Color(255, 255, 0, 0),
									strokeThickness: 3
								};
								
					var line = null;
					if(toRender[key].type == "INSIDE"){
						line =	new Microsoft.Maps.Polyline(lineVertices, optionsInside);
					}else{
						line =	new Microsoft.Maps.Polyline(lineVertices, optionsOutside);
					}
					GSDS.map.entities.push(line);
					
					
			}	
			var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});             
			GSDS.map.entities.push(pin);
			boundlocation.push(setLocationPosition);
			var  params = {
						locations: boundlocation, // An array of locations, or can use bounds: boundsObject
						mapWidth: window.innerWidth, // Width of your map div in pixels
						mapHeight: window.innerHeight, // Height of your map div in pixels
						buffer: 0 // How many pixels to add as a buffer
					};
					
			var newZoom = getZoom(GSDS.map, params) - 1;
					
			GSDS.map.setView( {zoom:newZoom});	
			if(typeof insideSeller != 'undefined' && insideSeller != null && insideSeller.length > 0)
			{
				for(var __key in insideSeller){
					showSingleGeofence(insideSeller[__key], "INSIDE");
				}
			}	
			if(typeof outsideSeller != 'undefined' && outsideSeller != null && outsideSeller.length > 0)
			{
				for(var __key in outsideSeller){
					showSingleGeofence(outsideSeller[__key], "OUTSIDE");
				}
			}	
			
			
			$('#loading').hide();
}
function searchData3()
{
	
			toRender = new Array();
			insideSeller = [];
			outsideSeller = [];
			sellerlocation = new Array();
			for(var key in sampleData.Output)
			{
				if(typeof sampleData.Output[key] != 'undefined' && sampleData.Output[key] != null )
				{
					//isJsonConverted = true;
					sampleData.Output[key].COVERAGE = $.parseJSON(sampleData.Output[key].COVERAGE);
					toRender.push(sampleData.Output[key].COVERAGE);
					sampleData.Output[key].Latitude = parseFloat(sampleData.Output[key].Latitude);
					sampleData.Output[key].Longitude=parseFloat(sampleData.Output[key].Longitude)
					sellerlocation.push({latitude:sampleData.Output[key].Latitude,longitude:sampleData.Output[key].Longitude});
					if(sampleData.Output[key].TAG == "INSIDE"){
						insideSeller.push(sampleData.Output[key]);
					
					}else{
						
						outsideSeller.push(sampleData.Output[key]);
					}
				}
				
				
				
				
			}
				//var pushpinOptions = {'icon': 'images/location_icon.png', 'width': 50, 'height': 50,'draggable': false};
				var pushpinOptions = {'icon': 'images/location_icon.png', width: 50, height: 50};
				var pushpinOptions1 = {'icon': 'images/location_icon1.png', width: 50, height: 50};
				//var pushpinOptions1 = {'icon': 'images/location_icon.png', width: 50, height: 50};
				//var pushpin2= new Microsoft.Maps.Pushpin(sellerlocation[0], pushpinOptions);
				//var pushpin1= new Microsoft.Maps.Pushpin(setLocationPosition, pushpinOptions);
				//GSDS.map.entities.push(pushpin1);									
				//GSDS.map.entities.push(pushpin2);
								
				//displayUserLocation();
				var boundlocation =[];
				for(var i=0; i< insideSeller.length; i++)
				{ 
					//boundlocation.push(insideSeller[i]);
					boundlocation.push({longitude:insideSeller[i].Longitude,latitude:insideSeller[i].Latitude});
					GSDS.map.entities.push(new Microsoft.Maps.Pushpin({longitude:insideSeller[i].Longitude,latitude:insideSeller[i].Latitude}, pushpinOptions));
				}
				for(var i=0; outsideSeller != null &&outsideSeller.length > 0 && i< outsideSeller.length; i++)
				{ 
					boundlocation.push({longitude:outsideSeller[i].Longitude,latitude:outsideSeller[i].Latitude});
					GSDS.map.entities.push(new Microsoft.Maps.Pushpin({longitude:outsideSeller[i].Longitude,latitude:outsideSeller[i].Latitude}, pushpinOptions1));
				}
				//GSDS.map.entities.push({latitude:setLocationPosition.latitude,longitude:setLocationPosition.longitude}, {'draggable': false});
				//boundlocation.push(setLocationPosition);
				
				
				//boundlocation.push({longitude:76.9780752788086,
				//		latitude:29.02612735020869});
				//boundlocation.push({longitude:77.6592276225586,
				//	latitude:29.710691226198932});
			var  params = {
						locations: boundlocation, // An array of locations, or can use bounds: boundsObject
						mapWidth: window.innerWidth, // Width of your map div in pixels
						mapHeight: window.innerHeight, // Height of your map div in pixels
						buffer: 0 // How many pixels to add as a buffer
					};
					
			var newZoom = getZoom(GSDS.map, params) - 1;
					
			GSDS.map.setView( {zoom:newZoom});	
}
function searchPart2(dataToSearch)
{
	dataToSearch = {name: $('#product').val(),lattitude:setLocationPosition.latitude , longitude:setLocationPosition.longitude} ;
	dataToSearch = $.param(dataToSearch);
	
	$.ajax({
		url: 'getSellerWhoSellsTheProductPart2.html',
		//url: 'js/sample.js',
		data: dataToSearch,
		type:'POST',
		cache: false,
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data){
			toRender = new Array();
			insideSeller = [];
			outsideSeller = [];
			sellerlocation = new Array();
			GSDS.clearMap();
			var pushpinOptions = {'icon': 'images/location_icon.png', width: 50, height: 50,'draggable': false};
			var pushpinOptions1 = {'icon': 'images/location_icon1.png', width: 50, height: 50,'draggable': false};
			for(var key in data.Output)
			{
				if(typeof data.Output[key].Status != 'undefined' && data.Output[key].Status == 'F')
				{
					continue;
				}
				if(typeof data.Output[key] != 'undefined' && data.Output[key] != null  )
				{
					//var _htmlContent= '<img src="pb.png">';
					
					data.Output[key].COVERAGE = $.parseJSON(data.Output[key].COVERAGE);
					
					data.Output[key].Latitude = parseFloat(data.Output[key].Latitude);
					data.Output[key].Longitude=parseFloat(data.Output[key].Longitude)
					
					
					if(data.Output[key].TAG == "INSIDE"){						
						insideSeller.push(data.Output[key]);
						sellerlocation.push({latitude:data.Output[key].Latitude,longitude:data.Output[key].Longitude, pin : pushpinOptions});
						toRender.push({COVERAGE:data.Output[key].COVERAGE, type:"INSIDE",SELLER_NAME:data.Output[key].SELLER_NAME,Address:data.Output[key].Address});
					
					}else{
						sellerlocation.push({latitude:data.Output[key].Latitude,longitude:data.Output[key].Longitude, pin : pushpinOptions1});
						outsideSeller.push(data.Output[key]);
						toRender.push({COVERAGE:data.Output[key].COVERAGE, type:"OUTSIDE",SELLER_NAME:data.Output[key].SELLER_NAME,Address:data.Output[key].Address});
					}
				}
				
				
				
				
			}
				//var pushpinOptions = {'icon': 'images/location_icon.png', 'width': 50, 'height': 50,'draggable': false};
				
				//var pushpin2= new Microsoft.Maps.Pushpin(sellerlocation[0], pushpinOptions);
				//var pushpin1= new Microsoft.Maps.Pushpin(setLocationPosition, pushpinOptions);
				//GSDS.map.entities.push(pushpin1);									
				//GSDS.map.entities.push(pushpin2);
								
				//displayUserLocation();
				var boundlocation =[];
				_str = '<option value="-1" selected="selected"> Please select Product Sellers </option>';
				
				for(var i=0; i< sellerlocation.length; i++)
				{ 
					boundlocation.push(sellerlocation[i]);
					GSDS.map.entities.push(new Microsoft.Maps.Pushpin(sellerlocation[i], sellerlocation[i].pin));
				}
				if(insideSeller != null && insideSeller.length > 0){
					_str=_str+ "<option value='-1' type='INSIDE'>Inside Seller's within the zone</option>";
					for(var i=0; insideSeller != null && i< insideSeller.length; i++)
					{ 
						_str =_str + "<option value='"+i+"' type='INSIDE' >"+insideSeller[i].SELLER_NAME +" "+ insideSeller[i].Locality+"</option>";
					}
					
				}
				if(outsideSeller != null && outsideSeller.length > 0){
					_str=_str+ "<option value='-1' type='OUTSIDE'>Outside Seller's Outside the Zone</option>";
					for(var i=0; outsideSeller != null && i< outsideSeller.length; i++)
					{ 
						_str =_str + "<option value='"+i+"' type='OUTSIDE' >"+outsideSeller[i].SELLER_NAME +" "+ outsideSeller[i].Locality+"</option>";
					}
				}
				$('#showConfidence').html(_str );
				
				//GSDS.map.entities.push({latitude:setLocationPosition.latitude,longitude:setLocationPosition.longitude}, {'draggable': false});
				//boundlocation.push(setLocationPosition);
				var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});             
					GSDS.map.entities.push(pin);
				
				//boundlocation.push({longitude:76.9780752788086,
				//		latitude:29.02612735020869});
				//boundlocation.push({longitude:77.6592276225586,
				//	latitude:29.710691226198932});
			var  params = {
						locations: boundlocation, // An array of locations, or can use bounds: boundsObject
						mapWidth: window.innerWidth, // Width of your map div in pixels
						mapHeight: window.innerHeight, // Height of your map div in pixels
						buffer: 0 // How many pixels to add as a buffer
					};
					
			var newZoom = getZoom(GSDS.map, params) - 1;
					
			GSDS.map.setView( {zoom:newZoom});	
			$('#loading').hide();
			
        },error:function(){
			$('#loading').hide();
		}
			
			 //var polygon = new Microsoft.Maps.Polygon([innerring]);
			
			// GSDS.map.entities.push(innerring);
			//GSDS.map.setView({center: loc});
		
	});
}
function showSingleGeofence(masterData, type)
{
					GSDS.clearMap();
					var lineVertices = [];
					var locationArr = [];
					
					var newPos = 0;
					for(var i=0; i<masterData.COVERAGE.coordinates[0].length; i++){
						locationArr[i] = {};						
						locationArr[i].latitude = masterData.COVERAGE.coordinates[0][i][1];
						locationArr[i].longitude = masterData.COVERAGE.coordinates[0][i][0];
						
						lineVertices[i] = new Microsoft.Maps.Location(locationArr[i].latitude, locationArr[i].longitude);
						//boundlocation.push(locationArr[i]);
					}
					var val=Math.floor((Math.random() * 250) + 1);
					var val1=Math.floor((Math.random() * 250) + 1);
					var optionsInside = {
									fillColor: new Microsoft.Maps.Color(255, 0, 255, 0),
									//fillColor: colorsArr[i],
									strokeColor: new Microsoft.Maps.Color(255, 0, 255, 0),
									strokeThickness: 3
								};
					var optionsOutside = {
									fillColor: new Microsoft.Maps.Color(255, 255, 0, 0),
									//fillColor: colorsArr[i],
									strokeColor: new Microsoft.Maps.Color(255, 255, 0, 0),
									strokeThickness: 3
								};
								
					var line =null;
					if(type == "OUTSIDE"){	
					line = new Microsoft.Maps.Polyline(lineVertices, optionsOutside);
					}else{
						line = new Microsoft.Maps.Polyline(lineVertices, optionsInside);
					}
					
					GSDS.map.entities.push(line);
					var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});  
					GSDS.map.entities.push(pin);	
					var pushpinOptions1 = {'icon': 'images/location_icon.png', width: 50, height: 50};	
					if(type == "OUTSIDE"){					
					var pushpinOptions1 = {'icon': 'images/location_icon1.png', width: 50, height: 50};
					}					
					var pin = new Microsoft.Maps.Pushpin(new Microsoft.Maps.Location(masterData.Latitude, masterData.Longitude), pushpinOptions1);             
					GSDS.map.entities.push(pin);
}

function showProductCategory()
{
	GSDS.clearMap('customers');
	if($('#showConfidence').val() != "-1"){
		type = $('#showConfidence option:selected').attr('type');
		_val = parseInt($('#showConfidence  option:selected').val(),10);
		 _str = '<option value="-1" selected="selected"> Please select Product Category </option>';
		if(type == "INSIDE"){
			if(insideSeller != null && insideSeller.length > 0){
					for( var _key in insideSeller[_val].products)	
					{
						_str = _str + '<option type="INSIDE" value="'+insideSeller[_val].products[_key].Product_category+'" lattitude="'+insideSeller[_val].Latitude+'" longitude="'+insideSeller[_val].Longitude+'">'+insideSeller[_val].products[_key].Name+'</option>';
					}
					showSingleGeofence(insideSeller[_val], type);
			}
			
		}else if(type == "OUTSIDE")
		{
			if(outsideSeller != null && outsideSeller.length > 0){
				for( var _key in outsideSeller[_val].products)	
					{
						_str = _str + '<option type="OUTSIDE" value="'+outsideSeller[_val].products[_key].Product_category+'" lattitude="'+outsideSeller[_val].Latitude+'" longitude="'+outsideSeller[_val].Longitude+'">'+outsideSeller[_val].products[_key].Name+'</option>';
					}	
						showSingleGeofence(outsideSeller[_val], type);
			
			}
					
			
		}
		$('#showConfidenceWithProduct').html(_str);
	}
}
function topThreeSellers()
{
	$('#loading').show();
	productVal = $('#showConfidenceWithProduct option:selected').val();
	var count =1;
	sellers = [];
	str = "<thead><tr><th>Seller Name</th><th>First Day</th><th>Second Day</th><th>Third Day</th><th>Fourth Day</th><th>Fifth Day</th></tr></thead><tbody></tbody>";
	if(typeof outsideSeller != 'undefined' && outsideSeller != null && outsideSeller.length > 0)
	{
		for(var key in outsideSeller )
		{
			for(var _key in outsideSeller[key].products)
			{
				if(productVal == outsideSeller[key].products[_key].Product_category && count < 4)
				{
					sellers.push({
						name:productVal,
						lattitude:outsideSeller[key].Latitude,
						longitude:outsideSeller[key].Longitude,
						SELLER_NAME:outsideSeller[key].SELLER_NAME
					});
					count=count +1;
					break;
					
				}
					if(count >= 4){
						break;
					}
			}
			if(count >= 4){
				break;
			}
		}
	}
	if(typeof insideSeller != 'undefined' && insideSeller != null && insideSeller.length > 0 && count < 4)
	{
		for(var key in insideSeller )
		{
			for(var _key in insideSeller[key].products)
			{
				if(productVal == insideSeller[key].products[_key].Product_category && count < 4)
				{
					sellers.push({
						name:productVal,
						lattitude:insideSeller[key].Latitude,
						longitude:insideSeller[key].Longitude,
						SELLER_NAME:insideSeller[key].SELLER_NAME
					});
					count=count +1;
					break;
					
				}
					if(count >= 4){
						break;
					}
			}
			if(count >= 4){
				break;
			}
		}
	}
	
	if(sellers != null && sellers.length > 1)
	{
		$('#rdet').html(str);
		globalCount = 0;
		for(var key in sellers)
		{
			dataToSearch = {name:sellers[key].name ,
			lattitude:sellers[key].lattitude , 
			longitude:sellers[key].longitude} ;
		dataToSearch = $.param(dataToSearch);
		str = str + "<td>" + sellers[key].SELLER_NAME + "</td>";
		$.ajax({
		url: 'getConfidenceMetrics.html',
		data: dataToSearch,
		type:'POST',
		cache: false,
		async:false,
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data){
			if(data != null && typeof data.Output != 'undefined' && data.Output.length > 0)
			{
				//str = str + "<tr>"; 
				var count = 0;
				var setVal = false;
				
					/*if(count > 0)
					{
						str = str + " <td>" + data.Output[0][key] + "</td>";
					}*/
					
					
						if(typeof sellers[globalCount].First_Day == 'undefined')
						{
							
							str = "";
							sellers[globalCount].First_Day = new Object();
							sellers[globalCount].First_Day =data.Output[0].First_Day;
							sellers[globalCount].Second_Day = new Object();
							sellers[globalCount].Second_Day =data.Output[0].Second_Day;
							sellers[globalCount].Third_Day = new Object();
							sellers[globalCount].Third_Day =data.Output[0].Third_Day;
							sellers[globalCount].Fourth_Day = new Object();
							sellers[globalCount].Fourth_Day =data.Output[0].Fourth_Day;
							sellers[globalCount].Fifth_Day = new Object();
							sellers[globalCount].Fifth_Day =data.Output[0].Fifth_Day;
							
							setVal = true;							
							str = str + "<tr><td>" + sellers[globalCount].SELLER_NAME + "</td>";
							str = str + "<td>" + ((typeof data.Output[0].First_Day == 'undefined')?"Not Available":data.Output[0].First_Day) + "</td>";
							str = str + "<td>" + ((typeof data.Output[0].Second_Day == 'undefined')?"Not Available":data.Output[0].Second_Day)+ "</td>";
							str = str + "<td>" + ((typeof data.Output[0].Third_Day == 'undefined')?"Not Available":data.Output[0].Third_Day)+ "</td>";
							str = str + "<td>" + ((typeof data.Output[0].Fourth_Day == 'undefined')?"Not Available":data.Output[0].Fourth_Day) + "</td>";
							str = str + "<td>" + ((typeof data.Output[0].Fifth_Day == 'undefined')?"Not Available":data.Output[0].Fifth_Day) + "</td></tr>";
							globalCount=globalCount + 1;
							$('#rdet').append(str);
														
						}
					
				
				/*str  = str +  '</tr>';*/
				
			}
		},error:function(){$('#loading').hide();}
			
		
	}); 
		}
		$('#loading').hide();
		$('#btmgridpanel').show();
		
	}
}
function showConfidence()
{
	dataToSearch = {name: $('#showConfidenceWithProduct option:selected').val(),lattitude:$('#showConfidenceWithProduct option:selected').attr('lattitude') , longitude:$('#showConfidenceWithProduct option:selected').attr('longitude')} ;
	$('#loading').show();
	str = "<thead><tr><th>Pin Code</th><th>First Day</th><th>Second Day</th><th>Third Day</th><th>Fourth Day</th><th>Fifth Day</th><th>Product Category</th>			</tr></thead><tbody></tbody>";
	_dataVal = $('#rdet').html();
	if(_dataVal.indexOf("Product Category") > -1)
	{
		
	}else{
		$('#rdet').html(str);
		
	}
	dataToSearch = $.param(dataToSearch);
	$.ajax({
		url: 'getConfidenceMetrics.html',
		data: dataToSearch,
		type:'POST',
		cache: false,
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data){
			$('#loading').hide();
			$('#btmgridpanel').show();
			
			if(data != null && typeof data.Output != 'undefined' && data.Output.length > 0)
			{
				str =  "<tr>"
				var count = 0;
				for(var key in data.Output[0])
				{
					str = str + " <td>" + data.Output[0][key] + "</td>";
					if(count++ > 6)
					{
						break;
					}
				}
				str  = str +  '</tr>';
				$('#rdet').append(str);
			}
		},error:function(){$('#loading').hide();}
			
		
	}); 
}


function pushOffers()
{
	if($('#showConfidenceWithProduct option:selected').attr('type') == "INSIDE")
	{
		alert("Please the Seller from Outside region");
		return;
	}
	dataToSearch = {name: $('#showConfidenceWithProduct option:selected').val()} ;
	dataToSearch = $.param(dataToSearch);
	$('#loading').show();
	$.ajax({
		url: 'getProductCampaign.html',
		//url: 'js/sample.js',
		data: dataToSearch,
		type:'POST',
		cache: false,
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		success: function(data){
			
			$('#rdet').html("");
				str = "<thead><tr><th>Seller Name</th><th>Push Offer</th></tr></thead><tbody></tbody>";
				str = str + '<tr><td>'+ $('#showConfidence option:selected').html() +'</td><td>';
			if(data != null && typeof data.Output != 'undefined' && data.Output.length > 0)
			{
					str = str+ data.Output[0].Offer;
				
			}
			str = str + '</td></tr>';
			$('#rdet').append(str);
			$('#loading').hide();
			$('#btmgridpanel').show();
			
		},error:function(){$('#loading').hide();}
			
		
	}); 
}

GEOAPIS_V1.apiDemo.prototype.showBranches = function(){
 	var that = this;
	
	$.ajax({
		//url: "http://52.2.27.193/rest/Spatial/FeatureService/tables/features.json?q=select * from \"/icici/namedtables/poi/icici_branch\" where SOL_ID in ('1640', '0716')" ,                
		url: "js/ncr2.json",
		type: 'GET',
		//data:_data,
		headers: {
		   // 'Content-type': 'application/json',
			'Accept': 'application/json'
		},
		beforeSend: function(){
			$('#loading').show();
		},
		complete: function(){
			$('#loading').hide();
		},
		success: function(data){
			that.pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0), { visible: false });
			that.map.entities.push(that.pinInfobox);
			for(var i=0; i<data.features.length; i++){
			
				that.branches[("Branch"+(i+1))] =    {
												  "address":data.features[i].properties.ADDRESS,
												  "startTime":"08:00 AM",
												  "endTime":"06:00 PM",
												  "fieldAgentCount":5,
												  "meetingTime":30,
												  "id":"B"+(i+1),
												  "markerIcon":"pb.png",
												  "markerHeight":48,
												  "Latitude":data.features[i].geometry.coordinates[1],
												  "Longitude":data.features[i].geometry.coordinates[0],
												  "column2": data.features[i].properties.ADDRESS
											   };
				
				var loc = new Microsoft.Maps.Location(data.features[i].geometry.coordinates[1], data.features[i].geometry.coordinates[0]);
				var _htmlContent= '<img src="pb.png">';
				that.marker = new Microsoft.Maps.Pushpin(loc, {
					//icon: "pb.png" ,
					zIndex: 99,
					//width: 64,
					//text: 0,
					//textOffset: new Microsoft.Maps.Point(27, 0),
					//height: 64
					textType:'MapPushpinBase',
					htmlContent: _htmlContent
				});
				that.marker.infoHeader = "Branch Information";
				that.marker.Description = "<b>Branch Name: </b>" + data.features[i].properties.BRANCH_NAME+"<br/><b>Address: </b>"+ data.features[i].properties.ADDRESS;
				
				Microsoft.Maps.Events.addHandler(that.marker, 'click', displayInfobox);
				Microsoft.Maps.Events.addHandler(that.pinInfobox, 'click', function(){
					that.pinInfobox.setOptions({ visible:false });
				});
				that.map.entities.push(that.marker);
			}
			var locationArr = [
				{latitude: data.bbox[1], longitude: data.bbox[0]},
				{latitude: data.bbox[3], longitude: data.bbox[2]}
			];
			var  params = {
				locations: locationArr, // An array of locations, or can use bounds: boundsObject
				mapWidth: window.innerWidth, // Width of your map div in pixels
				mapHeight: window.innerHeight, // Height of your map div in pixels
				buffer: 0 // How many pixels to add as a buffer
			};
			var loc = new Microsoft.Maps.Location((data.bbox[1]+data.bbox[3])/2, (data.bbox[0]+data.bbox[2])/2);
			var newZoom = getZoom(that.map, params);
			that.map.setView( {zoom:newZoom});				
			that.map.setView({center: loc});
		}
	});  
};

GEOAPIS_V1.apiDemo.prototype.showTWCustomerLocations = function(){
	var that = this;
	that.pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0), { visible: false });
	that.map.entities.push(that.pinInfobox);
	var points = that.addresses['Address4'];
	var locationArr = [];
	for(var i=0; i<points.length; i++){
		var loc = new Microsoft.Maps.Location(points[i].Latitude, points[i].Longitude);
		locationArr[i] = loc;
		//locationArr[i].latitude = points[i].Latitude;
		//locationArr[i].longitude = points[i].Longitude;
		var _marker = new Microsoft.Maps.Pushpin(loc, {
			zIndex: 99,
			textType:'MapPushpinBase',
			htmlContent: '<img src="'+points[i].markerIcon+'">'
		});
		
		
		_marker.infoHeader = "Customer Information"
		_marker.Description = "<b>Name: </b>"+ points[i].name + "<br/>"
								+ "<b>Address: </b>"+ points[i].column2 + "<br/>"
								+ "<b>Preferred Time: </b>"+ points[i].startTime + "-" + points[i].endTime  + "<br/>";
									
		

		Microsoft.Maps.Events.addHandler(_marker, 'click', displayInfobox);
		Microsoft.Maps.Events.addHandler(that.pinInfobox, 'click', function(){
			that.pinInfobox.setOptions({ visible:false });
		});
		that.map.entities.push(_marker);
		
	}
	
	var initialViewBounds = Microsoft.Maps.LocationRect.fromLocations(locationArr);
	that.map.setView({bounds: initialViewBounds});
	
};
GEOAPIS_V1.apiDemo.prototype.showCustomerLocations = function(){
	var that = this;
	that.pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0), { visible: false });
	that.map.entities.push(that.pinInfobox);	
	
	that.tmp.branchId = 'Branch1';
	that.tmp.addressId = 'Address1';
	that.tmp.routeId = 'Route1';
	that.tmp.activeControlId = '#deliveryRoutes'
	//that.tmp.disabledControlIds = ['#deliveryRoutes2']
	that.showCustomers();
	//$('#deliveryRoutes2').attr('checked', false);
	
	that.tmp.branchId = 'Branch2';
	that.tmp.addressId = 'Address2';
	that.tmp.routeId = 'Route2';
	that.tmp.activeControlId = '#deliveryRoutes2'
	//that.tmp.disabledControlIds = ['#deliveryRoutes']
	that.showCustomers();
	//$('#deliveryRoutes').attr('checked', false);
	
		
	that.map.setView( {zoom:12});	
	//var loc = new Microsoft.Maps.Location(meanLat, meanLon);
	that.map.setView({center: new Microsoft.Maps.Location(28.53079, 77.28208)});
};

GEOAPIS_V1.apiDemo.prototype.getRouteTW = function(key, type){
	var that = this;
	var points;
	for(var i=0; i<this.routesTW.length; i++){
		if(this.routesTW[i].id == key){
			points = this.routesTW[i].points;
			break;
		}
	}
	
	var webServiceURL = '/soap/GetRouteNonOptimize';
	var soapMessage = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rout="http://www.pb.com/spectrum/services/GetRouteNonOptimize">';
	soapMessage += '<soapenv:Header/>';
	soapMessage += '<soapenv:Body>';
	soapMessage += '<rout:GetRouteNonOptimizeRequest>';
	soapMessage += '<rout:options/>';
	soapMessage += '<rout:Input>';
	soapMessage += '<rout:Row>';
	soapMessage += '<rout:RoutePoints>';	
	for(var i=0; i<points.length; i++){
		soapMessage += '<rout:RoutePoint>';
		soapMessage += '<rout:Latitude>'+points[i].lat+'</rout:Latitude>';	
		soapMessage += '<rout:Longitude>'+points[i].lng+'</rout:Longitude>';
		soapMessage += '</rout:RoutePoint>';
	}
	soapMessage += '</rout:RoutePoints>';
	soapMessage += '<rout:type>' + type +'</rout:type>';
	soapMessage += '</rout:Row>';
	soapMessage += '</rout:Input>';
	soapMessage += '</rout:GetRouteNonOptimizeRequest>';
	soapMessage += '</soapenv:Body>';
	soapMessage += '</soapenv:Envelope>';
	
	$.ajax({
		url: webServiceURL, 
		type: "POST",
		dataType: "xml",
		data: soapMessage,
		processData: false,
		contentType: "text/xml; charset=\"utf-8\"",
		beforeSend: function(){
			$('#loading').show();
		},
		complete: function(){
			$('#loading').hide();
		},
		success: function(data, status, req, xml, xmlHttpRequest, responseXML){
		var tmpHtml = '';
			$(req.responseXML).find("ns4\\:RouteDirection, RouteDirection").each(function(){
				if($(this)[0].children[0].innerHTML != ""){
					var position = '';
					for(var i=0; i<$(this)[0].children[5].children.length; i++){
						position += $(this)[0].children[5].children[i].children[1].innerHTML;
						position += ','+$(this)[0].children[5].children[i].children[0].innerHTML+'|';
					}
					position = position.substring(0, position.length - 1);
					tmpHtml += '<div class="routeDirection" pos="'+position+'">';
					tmpHtml += '<div class="instruction">'+$(this)[0].children[0].innerHTML+'</div>';
					tmpHtml += '<div class="routeDetails"><div>'+$(this)[0].children[1].innerHTML+' '+$(this)[0].children[2].innerHTML+' ('+$(this)[0].children[3].innerHTML+' '+$(this)[0].children[4].innerHTML+')</div></div>';
					tmpHtml += '</div>';
				}
			});
			
			var _time = $(req.responseXML).find("ns4\\:Time, Time")[0].innerHTML;
			var _timeUnits = $(req.responseXML).find("ns4\\:TimeUnits, TimeUnits")[0].innerHTML;
			var _distance = $(req.responseXML).find("ns4\\:Distance, Distance")[0].innerHTML;
			var _distanceUnits = $(req.responseXML).find("ns4\\:DistanceUnits, DistanceUnits")[0].innerHTML;
			
			$('#directionDetails').empty();
			$('#directionDetails').html(tmpHtml);
			$('#directionDetails').show();
		
			var meanLat = 0;
			var meanLon = 0;
			
			meanLat = meanLat/points.length;
			meanLon = meanLon/points.length;
			var loc = new Microsoft.Maps.Location(meanLat, meanLon);
			that.map.setView({center: loc});

			var _geoJSON = $(req.responseXML).find("ns4\\:GeoJSONString, GeoJSONString").text();
			var jsonCoordinates = JSON.parse(_geoJSON);
			var lineVertices = [];
			var newPos = 0;
			for(var i=0; i<jsonCoordinates.coordinates.length; i++){
				lineVertices[i] = new Microsoft.Maps.Location(jsonCoordinates.coordinates[i][1], jsonCoordinates.coordinates[i][0]);
			}
			
			var polylineOptions = {
								fillColor: new Microsoft.Maps.Color(255, 52, 152, 219),
								strokeColor: new Microsoft.Maps.Color(255, 52, 152, 219),
								strokeThickness: 5
							};
			
			var line = new Microsoft.Maps.Polyline(lineVertices, polylineOptions);
			
			GSDS.clearMap('customers');
			that.map.entities.push(line);
			
			
			var _bbox = $(req.responseXML).find("ns4\\:bbox, bbox");
			
			var locationArr = [
					{latitude: _bbox[0].children[0].children[1].innerHTML, longitude: _bbox[0].children[0].children[0].innerHTML},
					{latitude: _bbox[0].children[1].children[1].innerHTML, longitude: _bbox[0].children[1].children[0].innerHTML}
				];
				
			var initialViewBounds = Microsoft.Maps.LocationRect.fromCorners(new Microsoft.Maps.Location(locationArr[0].latitude,locationArr[0].longitude), new Microsoft.Maps.Location(locationArr[1].latitude,locationArr[1].longitude));
			
			that.map.setView({bounds: initialViewBounds});
			
			var  params = {
				locations: locationArr, // An array of locations, or can use bounds: boundsObject
				mapWidth: window.innerWidth, // Width of your map div in pixels
				mapHeight: window.innerHeight, // Height of your map div in pixels
				buffer: 0 // How many pixels to add as a buffer
			};
			var newZoom = getZoom(that.map, params) - 1;
			that.map.setView( {zoom:newZoom});

			
			that.map.entities.push(that.pinInfobox);
			$('.labelPin div').show();
			var _tbody = '';
			for(var i=0; i<points.length; i++){
				var loc = new Microsoft.Maps.Location(points[i].lat, points[i].lng);
				var _icon;
				var _w;
				var _h;
				var _htmlContent;
				if(i==0){
					_icon = points[i].markerIcon ;
					_w = 64;
					_h = 64;
					_htmlContent= '<img src="'+_icon+'">';
				}
				else{
					
						_icon = points[i].markerIcon ;
					
					_w = 64;
					_h = 64;
					_htmlContent= '<img src="'+_icon+'"><div>' + points[i].info.visitOrder + '</div';
					
					_tbody = _tbody + '<tr style="cursor:pointer">'
					_tbody = _tbody + '<td>' + points[i].info.visitOrder + '</td>'
					_tbody = _tbody + '<td><span>' + points[i].info.address + '</span></td>'
					_tbody = _tbody + '<td>' + points[i].info.preferredTime + '</td>'
					//_tbody = _tbody + '<td>' + points[i].info.distance + ' KM' + '</td>'
					//_tbody = _tbody + '<td>' + points[i].info.time + ' min.' + '</td>'
					_tbody = _tbody + '<td>' + points[i].info.eta + '</td>'
					_tbody = _tbody + '<td>' + points[i].info.etd + '</td>'
					_tbody = _tbody + '</tr>'
				}
				var _marker = new Microsoft.Maps.Pushpin(loc, {
					//icon: _icon,
					zIndex: 99,
					//width: _w,
					//text: 88,
					//textOffset: new Microsoft.Maps.Point(27, 0),
					//height: _h,
					textType:'MapPushpinBase',
					htmlContent: _htmlContent
				});
				
				if(i==0){
					_marker.infoHeader = "Branch Information"
					_marker.Description = "<b>Address: </b>"+ points[i].info.address;
				}
				else{
					_marker.infoHeader = "Customer Information"
					_marker.Description = "<b>Name: </b>"+ points[i].info.name + "<br/>"
											+ "<b>Address: </b>"+ points[i].info.address + "<br/>"
											+ "<b>Preferred Time: </b>"+ points[i].info.preferredTime + "<br/>"
											/* + "<b>Distance: </b>"+ points[i].info.distance + "<br/>"
											+ "<b>Time: </b>"+ points[i].info.time + "<br/>"  */
											+ "<b>Expected Arrival Time: </b>"+ points[i].info.eta + "<br/>"
											+ "<b>Expected Departure Time: </b>"+ points[i].info.etd + "<br/>";
											
				}

				Microsoft.Maps.Events.addHandler(_marker, 'click', displayInfobox);
				Microsoft.Maps.Events.addHandler(that.pinInfobox, 'click', function(){
					that.pinInfobox.setOptions({ visible:false });
				});
				that.map.entities.push(_marker);
				
			}
			//alert(_tbody);
			//$("#rdet tbody").empty();
			//$(_tbody).appendTo($("#rdet tbody"));
			$('.MapPushpinBase div').hide();
			$('.MapPushpinBase div').show();
			
		}
	});
};
function setHeader(xhr) {

  xhr.setRequestHeader('Authorization', token);
}

GEOAPIS_V1.apiDemo.prototype.getRoutes = function(key, type){
	var that = this;
	var points = this.routes[key].points;
	var polylineOptions = this.routes[key].polylineOptions;
	var webServiceURL = 'http://152.144.227.73:8080/soap/GetRoute';
	var soapMessage = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:rout="http://www.pb.com/spectrum/services/GetRoute">';
	soapMessage += '<soapenv:Header/>';
	soapMessage += '<soapenv:Body>';
	soapMessage += '<rout:GetRouteRequest>';
	soapMessage += '<rout:options/>';
	soapMessage += '<rout:Input>';
	soapMessage += '<rout:Row>';
	soapMessage += '<rout:RoutePoints>';	
	for(var i=0; i<points.length; i++){
		if(!points[i].skip){
			soapMessage += '<rout:RoutePoint>';
			soapMessage += '<rout:Latitude>'+points[i].latitude+'</rout:Latitude>';	
			soapMessage += '<rout:Longitude>'+points[i].longitude+'</rout:Longitude>';
			soapMessage += '</rout:RoutePoint>';
		}
	}
	soapMessage += '</rout:RoutePoints>';
	soapMessage += '<rout:type>' + type +'</rout:type>';
	soapMessage += '</rout:Row>';
	soapMessage += '</rout:Input>';
	soapMessage += '</rout:GetRouteRequest>';
	soapMessage += '</soapenv:Body>';
	soapMessage += '</soapenv:Envelope>';
	
	/*$.ajax({
		url: 'data/route.xml',
		dataType: 'xml',
		cache: false,
		contentType: 'text/json',*/
	$.ajax({
		url: webServiceURL, 
		type: "POST",
		beforeSend: setHeader,
		dataType: "xml",
		data: soapMessage,
		processData: false,
		contentType: "text/xml; charset=\"utf-8\"",
		beforeSend: function(){
			$('#loading').show();
		},
		complete: function(){
			$('#loading').hide();
		},
		success: function(data, status, req, xml, xmlHttpRequest, responseXML){
			var meanLat = 0;
			var meanLon = 0;
			
			var _geoJSON = $(req.responseXML).find("ns4\\:GeoJSONString, GeoJSONString").text();
			
			var _maxPos = 9999900000;
			for(var x=0; x<points.length; x++){
				if(!points[x].skip){
					meanLat += parseFloat(points[x].latitude);
					meanLon += parseFloat(points[x].longitude);
					
					var _idx = _geoJSON.indexOf(points[x].longitude+","+points[x].latitude);
					
					points[x].order = _idx;
				}
				else{
					points[x].order = points[0].order+1;
				}
				
			}
			points.sort(function(a, b) {
				return parseFloat(a.order) - parseFloat(b.order);
			});
			
			var _skipCount = 0;
			for(var x=0; x<points.length; x++){
				if(!points[x].skip){
					points[x].position = x - _skipCount;
				}
				else{
					points[x].position = _maxPos++;
					_skipCount++;
				}
			}
			
			
			meanLat = meanLat/(points.length - _skipCount);
			meanLon = meanLon/(points.length - _skipCount);
			var loc = new Microsoft.Maps.Location(meanLat, meanLon);
			//that.map.setView({center: loc});

			var jsonCoordinates = JSON.parse(_geoJSON);
			
			var lineVertices = [];
			//var locationArr = [];
			var newPos = 0;
			for(var i=0; i<jsonCoordinates.coordinates.length; i++){
				//locationArr[i] = {};
				//locationArr[i].latitude = jsonCoordinates.coordinates[i][1];
				//locationArr[i].longitude = jsonCoordinates.coordinates[i][0];
				lineVertices[i] = new Microsoft.Maps.Location(jsonCoordinates.coordinates[i][1], jsonCoordinates.coordinates[i][0]);
			
				
				
			}
			
			var line = new Microsoft.Maps.Polyline(lineVertices, polylineOptions);
			that.map.entities.push(line);
			GSDS.clearMap('routeMarkers');
			GSDS.isMarkersPositionReset = true;
			GSDS.showCustomerLocations();
			
			
			var _bbox = $(req.responseXML).find("ns4\\:bbox, bbox");
			
			var locationArr = [
					{latitude: _bbox[0].children[0].children[1].innerHTML, longitude: _bbox[0].children[0].children[0].innerHTML},
					{latitude: _bbox[0].children[1].children[1].innerHTML, longitude: _bbox[0].children[1].children[0].innerHTML}
				];
				
				var initialViewBounds = Microsoft.Maps.LocationRect.fromCorners(new Microsoft.Maps.Location(locationArr[0].latitude,locationArr[0].longitude), new Microsoft.Maps.Location(locationArr[1].latitude,locationArr[1].longitude));
			
			that.map.setView({bounds: initialViewBounds});
			
			var  params = {
				locations: locationArr, // An array of locations, or can use bounds: boundsObject
				mapWidth: window.innerWidth, // Width of your map div in pixels
				mapHeight: window.innerHeight, // Height of your map div in pixels
				buffer: 0 // How many pixels to add as a buffer
			};
			var newZoom = getZoom(that.map, params) - 1;
			that.map.setView( {zoom:newZoom});

			var tmpHtml = '';
			$(req.responseXML).find("ns4\\:RouteDirection, RouteDirection").each(function(){
				if($(this)[0].children[0].innerHTML != ""){
					var position = '';
					for(var i=0; i<$(this)[0].children[5].children.length; i++){
						position += $(this)[0].children[5].children[i].children[1].innerHTML;
						position += ','+$(this)[0].children[5].children[i].children[0].innerHTML+'|';
					}
					position = position.substring(0, position.length - 1);
					tmpHtml += '<div class="routeDirection" pos="'+position+'">';
					tmpHtml += '<div class="instruction">'+$(this)[0].children[0].innerHTML+'</div>';
					tmpHtml += '<div class="routeDetails"><div>'+$(this)[0].children[1].innerHTML+' '+$(this)[0].children[2].innerHTML+' ('+$(this)[0].children[3].innerHTML+' '+$(this)[0].children[4].innerHTML+')</div></div>';
					tmpHtml += '</div>';
				}
			});
			
			var _time = $(req.responseXML).find("ns4\\:Time, Time")[0].innerHTML;
			var _timeUnits = $(req.responseXML).find("ns4\\:TimeUnits, TimeUnits")[0].innerHTML;
			var _distance = $(req.responseXML).find("ns4\\:Distance, Distance")[0].innerHTML;
			var _distanceUnits = $(req.responseXML).find("ns4\\:DistanceUnits, DistanceUnits")[0].innerHTML;
			var _routeLabel;
			if(type == 'time'){
				_routeLabel = _time + ' ' + _timeUnits;
			}
			else if(type == 'distance'){
				_routeLabel = _distance + ' ' + _distanceUnits;
			}
			
			if(key == 'Route1'){
				$('#lblRoute2').hide();
				$('#lblRoute1').show();
				$('#lblRoute1').html(_routeLabel);
			}
			else if(key == 'Route2'){
				$('#lblRoute1').hide();
				$('#lblRoute2').show();
				$('#lblRoute2').html(_routeLabel);
			}
			
			$('#directionDetails').html(tmpHtml);
			$('#directionDetails').show();
			$('.MapPushpinBase .'+key).show();
		
			
		}
	});
};

GEOAPIS_V1.apiDemo.prototype.generateRoutes = function(){
isTWuseCase = true;
var that = this;
	var _addressTW = that.addresses['Address4'];
	var _b =  that.branches['Branch1'];
		var soapMessage = '<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:get="http://www.pb.com/spectrum/services/GetRouteTW">';
		soapMessage += '<soapenv:Body>';
		soapMessage += '<get:GetRouteTWRequest>';
		soapMessage += '<get:Input>';
		soapMessage += '<get:Row>';
		soapMessage += '<get:branchID>' + _b.id + '</get:branchID>';
		soapMessage += '<get:branchLatitude>' + _b.Latitude + '</get:branchLatitude>';
		soapMessage += '<get:branchLongitude>' + _b.Longitude + '</get:branchLongitude>';
		soapMessage += '<get:branchFieldAgentCount>' + _b.fieldAgentCount + '</get:branchFieldAgentCount>';
		soapMessage += '<get:meetingTime>' + _b.meetingTime + '</get:meetingTime>';
		soapMessage += '<get:customers>';

		for(var i=0; i< _addressTW.length; i++){
			soapMessage += '<get:customer>';
			soapMessage += '<get:id>' + _addressTW[i].id + '</get:id>';
			soapMessage += '<get:latitude>' + _addressTW[i].Latitude + '</get:latitude>';
			soapMessage += '<get:longitude>' + _addressTW[i].Longitude + '</get:longitude>';
			soapMessage += '<get:timeSlots>';
			soapMessage += '<get:timeSlot>';
			soapMessage += '<get:startTime>' + _addressTW[i].startTime + '</get:startTime>';
			soapMessage += '<get:endTime>' + _addressTW[i].endTime + '</get:endTime>';
			soapMessage += '</get:timeSlot>';
			soapMessage += '</get:timeSlots>';
			soapMessage += '</get:customer>';
		}
		
		soapMessage += '</get:customers>';
		soapMessage += '<get:fieldAgentStartTime>' + _b.startTime + '</get:fieldAgentStartTime>';
		soapMessage += '<get:fieldAgentEndTime>' + _b.endTime + '</get:fieldAgentEndTime>';
		soapMessage += '</get:Row>';
		soapMessage += '</get:Input>';
		soapMessage += '</get:GetRouteTWRequest>';
		soapMessage += '</soapenv:Body>';
		soapMessage += '</soapenv:Envelope>';
		
		//console.log(soapMessage);
		
		$.ajax({
		url: '/soap/GetRouteTW', 
		type: "POST",
		dataType: "xml",
		data: soapMessage,
		processData: false,
		contentType: "text/xml; charset=\"utf-8\"",
		beforeSend: function(){
			$('#loading').show();
		},
		complete: function(){
			$('#loading').hide();
		},
		success: function(data, status, req, xml, xmlHttpRequest, responseXML){
			
			var _routes = $(req.responseXML).find("ns3\\:route, route");
			var prevRouteID = '0';
			var cntR = -1;
			//var newCntR = 0;
			//var newCntC = 1;
			var cntC = 1;
			GSDS.routesTW = [];
			$('#agent').empty();
			$('#agent').append($('<option>', {
				value: '------',
				text: '------'
			}));
			for(var i=0; i<_routes.length;i++){
				//if(newCntR>1 && cntR != newCntR) cntR = newCntR;
				//if(cntC != newCntC) cntC = newCntC;
				for(var j=0; j<_routes[i].children.length;j++){
					if(_routes[i].children[j].localName == 'routeID'){
						if(!(prevRouteID == _routes[i].children[j].innerHTML)){
							cntC = 1;
							cntR = cntR + 1;
							prevRouteID = _routes[i].children[j].innerHTML
							$('#agent').append($('<option>', {
								value: prevRouteID,
								text: 'Agent ' + prevRouteID
							}));
							GSDS.routesTW[cntR] = {};
							GSDS.routesTW[cntR].id = prevRouteID;
							GSDS.routesTW[cntR].points = [];
							GSDS.routesTW[cntR].points[0] = {};
							GSDS.routesTW[cntR].points[0].info = {};
							
							GSDS.routesTW[cntR].points[0].id = _b.id;
							GSDS.routesTW[cntR].points[0].lat = _b.Latitude;
							GSDS.routesTW[cntR].points[0].lng = _b.Longitude;
							GSDS.routesTW[cntR].points[0].type = 'branch';
							GSDS.routesTW[cntR].points[0].markerIcon = "pb.png";
							GSDS.routesTW[cntR].points[0].info.address = _b.column2;
							GSDS.routesTW[cntR].points[0].info.visitOrder = 0;
							
							GSDS.routesTW[cntR].points[cntC] = {};
							GSDS.routesTW[cntR].points[cntC].info = {};
							//newCntR = cntR+1;
						}
						else{
							cntC = cntC + 1;
							GSDS.routesTW[cntR].points[cntC] = {};
							GSDS.routesTW[cntR].points[cntC].info = {};
						}
					}
					if(_routes[i].children[j].localName == 'customerID'){
						GSDS.routesTW[cntR].points[cntC].id = _routes[i].children[j].innerHTML;
						var cust = getCustomer(_routes[i].children[j].innerHTML);
						GSDS.routesTW[cntR].points[cntC].lat = cust.Latitude;
						GSDS.routesTW[cntR].points[cntC].lng = cust.Longitude;
						GSDS.routesTW[cntR].points[cntC].type = 'customer';
						GSDS.routesTW[cntR].points[cntC].markerIcon = cust.markerIcon;
						GSDS.routesTW[cntR].points[cntC].info.name = cust.name;
						GSDS.routesTW[cntR].points[cntC].info.address = cust.column2;
						GSDS.routesTW[cntR].points[cntC].info.product = cust.product;
					}
					if(_routes[i].children[j].localName == 'timeSlots'){
						GSDS.routesTW[cntR].points[cntC].info.preferredTime = _routes[i].children[j].innerHTML;
					}
					if(_routes[i].children[j].localName == 'visitOrder'){
						GSDS.routesTW[cntR].points[cntC].info.visitOrder = _routes[i].children[j].innerHTML;
					}
					if(_routes[i].children[j].localName == 'eta'){
						GSDS.routesTW[cntR].points[cntC].info.eta = _routes[i].children[j].innerHTML;
					}
					if(_routes[i].children[j].localName == 'etd'){
						GSDS.routesTW[cntR].points[cntC].info.etd = _routes[i].children[j].innerHTML;
					}
					if(_routes[i].children[j].localName == 'waitTime'){
						GSDS.routesTW[cntR].points[cntC].info.waitTime = _routes[i].children[j].innerHTML;
					}
				}
			}
		}
	});
};

var GSDS;
$(function(){
	$('input[type=checkbox]').each(function(){ 
		this.checked = false; 
	}); 
	
	GSDS = new GEOAPIS_V1.apiDemo();
	GSDS.renderMap();
	
	$.ajax({
		url: 'data/customerAddresses_1.json',
		dataType: 'json',
		cache: false,
		contentType: 'application/json',
		success: function(data){
			GSDS.addresses['Address1'] = data;
		}
	});
	
	$.ajax({
		url: 'data/customerAddresses_2.json',
		dataType: 'json',
		cache: false,
		contentType: 'application/json',
		success: function(data){
			GSDS.addresses['Address2'] = data;
		}
	});
	
	
	$.ajax({
		url: 'data/customerAddresses_tw.json',
		dataType: 'json',
		cache: false,
		contentType: 'application/json',
		success: function(data){
			GSDS.addresses['Address4'] = data;
		}
	});
	
	$('#myCatchments').change(function(){
		if($(this).is(':checked')){
			GSDS.showBranches();
			$('#myDeliveries').attr('disabled', false);
			$('#myDeliveries').attr('checked', false);
		}
		else{
			GSDS.clearMap('branches');
			GSDS.clearMap('customers');
			$('#myDeliveries').attr('disabled', true);
			$('#myDeliveries').attr('checked', false);
			$('#deliveryRoutes').attr('disabled', true);
			$('#deliveryRoutes').attr('checked', false);
			$('#deliveryRoutes2').attr('disabled', true);
			$('#deliveryRoutes2').attr('checked', false);
			$('#agent').attr('disabled', true);
			$('#generateRoute').attr('disabled', true);
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
		}
	});
	
	$('#myDeliveries').change(function(){
		if($(this).is(':checked')){
			$('#loading').show();
			//setTimeout(function(){
				GSDS.isMarkersPositionReset=false;
				GSDS.showCustomerLocations();
				$('#deliveryRoutes').attr('disabled', false);
				$('#deliveryRoutes2').attr('disabled', false);
				$('#agent').attr('disabled', false);
				$('#generateRoute').attr('disabled', false);
				$('#loading').hide();
			//}, 500);			
		}
		else{
			GSDS.clearMap('customers');
			GSDS.clearMap('routes');
			$('#deliveryRoutes').attr('disabled', true);
			$('#deliveryRoutes').attr('checked', false);
			$('#deliveryRoutes2').attr('disabled', true);
			$('#deliveryRoutes2').attr('checked', false);
			$('#agent').attr('disabled', true);
			$('#generateRoute').attr('disabled', true);
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
		}
	});
	
	$('#deliveryRoutes').change(function(){
		if($(this).is(':checked')){
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			//$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
			$('#deliveryRoutesType').show();
			GSDS.clearMap('routes');
			GSDS.getRoutes('Route1',$('#deliveryRoutesType').val());
			$('#deliveryRoutes2').attr('checked', false);
			$('#deliveryRoutes2Type').hide();
		}
		else{
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			//$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
			GSDS.clearMap('routes');
			$('#deliveryRoutesType').hide();
		}
	});
	$('#deliveryRoutes2').change(function(){
		if($(this).is(':checked')){
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			$('#deliveryRoutes2Type').show();
			//$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
			GSDS.clearMap('routes');
			GSDS.getRoutes('Route2',$('#deliveryRoutes2Type').val());
			$('#deliveryRoutes').attr('checked', false);
			$('#deliveryRoutesType').hide();
		}
		else{
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			//$('.MapPushpinBase div').hide();
			$('.labelPin div').show();
			GSDS.clearMap('routes');
			$('#deliveryRoutes2Type').hide();
		}
	});
	
	
	$('#deliveryRoutesType').change(function(){
			$('#directionDetails').html('');
			$('#directionDetails').hide();
			GSDS.clearMap('routes');
			GSDS.getRoutes('Route1',$('#deliveryRoutesType').val());
	});	
	$('#deliveryRoutes2Type').change(function(){
		$('#directionDetails').html('');
		$('#directionDetails').hide();
		GSDS.clearMap('routes');
		GSDS.getRoutes('Route2',$('#deliveryRoutes2Type').val());
	});
	
	$('.nav-section-header').click(function(){
		$('#navigation-wrapper > div').removeClass('active');
		$('.nav-section-header table tbody tr td:first-child').html('<img  src="img/left-arrow-grey.png">');
		$(this).parent().addClass('active');
		$(this).find('table tbody tr td:first-child').html('<img  src="img/left-arrow_blue.png">');
	});
	$('#directionDetails').on('click', '.routeDirection', function(){
		var position = $(this).attr('pos').split('|');
		var meanLat = 0;
		var meanLon = 0;
		var locationArr = [];
		for(var x=0; x<position.length; x++){
			var latLon = position[x].split(',');
			meanLat += parseFloat(latLon[0]);
			meanLon += parseFloat(latLon[1]);
			locationArr[x] = {};
			locationArr[x].latitude = parseFloat(latLon[0]);
			locationArr[x].longitude = parseFloat(latLon[1]);
		}
		meanLat = meanLat/position.length;
		meanLon = meanLon/position.length;
		var loc = new Microsoft.Maps.Location(meanLat, meanLon);
		GSDS.map.setView({center: loc});

		var  params = {
			locations: locationArr, // An array of locations, or can use bounds: boundsObject
			mapWidth: window.innerWidth, // Width of your map div in pixels
			mapHeight: window.innerHeight, // Height of your map div in pixels
			buffer: 0 // How many pixels to add as a buffer
		};
		var newZoom = getZoom(GSDS.map, params) - 1;
		GSDS.map.setView( {zoom:newZoom});
	});
	
	$('#generateRoute').click(function(){
		GSDS.generateRoutes();
	});
	
	$('#agent').change(function(){
		var _idx = $('#agent').find(":selected").index();
		if(_idx > 0){
			//alert($('#agent').val());
			GSDS.getRouteTW($('#agent').val(),'distance');
		}
	});
	
	$('#nav_0').on('show.bs.collapse', function () {
		isTWuseCase = false;
	});
	
	$('#nav_1').on('show.bs.collapse', function () {
		isTWuseCase = false;
	});
	$('#nav_2').on('show.bs.collapse', function () {
		/*GSDS.clearMap('customers');
		$('#directionDetails').html('');
		$('#directionDetails').hide();
		$("#lblRoute1").empty();
		$('#lblRoute1').hide();
		$("#lblRoute2").empty();
		$('#lblRoute2').hide();
		$('#deliveryRoutes').attr('checked', false);
		$('#deliveryRoutes2').attr('checked', false);
		$('#deliveryRoutesType').hide();
		$('#deliveryRoutes2Type').hide();
		$('#agent').empty();
		if($('#myDeliveries').is(':checked')){
			GSDS.showCustomerLocations();
		}*/
		isTWuseCase = false;
	});
	
$('#nav_3').on('show.bs.collapse', function () {
		/*GSDS.clearMap('customers');
		$('#directionDetails').html('');
		$('#directionDetails').hide();
		$("#lblRoute1").empty();
		$('#lblRoute1').hide();
		$("#lblRoute2").empty();
		$('#lblRoute2').hide();
		$('#deliveryRoutes').attr('checked', false);
		$('#deliveryRoutes2').attr('checked', false);
		$('#deliveryRoutesType').hide();
		$('#deliveryRoutes2Type').hide();
		$('#agent').empty();
		if($('#myDeliveries').is(':checked')){
			GSDS.showTWCustomerLocations();
		}*/
	});
	
	//Add handler for the map click event.
	//Microsoft.Maps.Events.addHandler(GSDS.map, 'rightclick', onMapClick);

});


function displayInfobox(e){
var pushpinFrameHTML = '<div class="infobox"><div class="modal-header"><button type="button" class="close infobox_close"><span aria-hidden="true">×</span></button><h4 class="modal-title">'+e.target.infoHeader+'</h4></div><div class="infobox_content">'+e.target.Description+'</div></div><div class="infobox_pointer"><img src="images/pointer_shadow.png"></div>';
	GSDS.pinInfobox.setOptions({
		/* title: '',
		description: e.target.Description,
		visible:true,
		offset: new Microsoft.Maps.Point(0, 35),
		height: 110,
		width: 300,
		zIndex: 100,
		showCloseButton: false */
		visible:true, 
		offset: new Microsoft.Maps.Point(-10, 20), 
		//htmlContent: pushpinFrameHTML.replace('{content}',e.target.Description)
		htmlContent: pushpinFrameHTML
	});
	GSDS.pinInfobox.setLocation(e.target.getLocation());
}

function geocodeAddress(address){
	var _data;
	dataToSearch = 'name=' + address;
	$.ajax({
			url: 'GeocodeService.html',
			dataType: 'json',
			cache: false,
			data: dataToSearch,
			async: false,
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success: function(data){
				_data = data.Output[0];
				setLocationPosition = new Microsoft.Maps.Location(parseFloat(_data.Latitude,10),parseFloat( _data.Longitude,10));
				var pin = new Microsoft.Maps.Pushpin(setLocationPosition, {'draggable': false});   
				GSDS.clearMap('customers');				
					GSDS.map.entities.push(pin);
				},
			beforeSend: function(){
				$('#loading').show();
			},
			complete: function(){
				$('#loading').hide();
			}
	});
	return _data;
}

function reverseGeocode(lat, lng){
	var _data;
	dataToSearch = 'lattitude='+ lat +'&longitude=' + lng;
	
	$.ajax({
			url: 'ReverseGeocodeService.html',
			dataType: 'json',
			data: dataToSearch,
			type: 'POST',
			cache: false,
			async: false,
			contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			success: function(data){
				_data = data.Output[0].Address;
			},
			beforeSend: function(){
				$('#loading').show();
			},
			complete: function(){
				$('#loading').hide();
			}
	});
	return _data;
}

function onMapClick(e) {
	if(e.targetType == 'map')	{
		
		if (e.target != null)
		{
			//if(($('#deliveryRoutes').is(':checked')) || ($('#deliveryRoutes2').is(':checked'))){
				var point = new Microsoft.Maps.Point(e.getX(), e.getY());
				var loc = e.target.tryPixelToLocation(point);
				var address = reverseGeocode(loc.latitude, loc.longitude)
			
				bootbox.confirm("Do you want to add another customer in this route ? <br/> <br/> <B>Address : </B><textarea id='newAddress'></textarea>", function(result) {
					//if(confirm("Do you want to add another customer in this route ?")){
					if(result){
						//alert($('#newAddress').val());
						address = $('#newAddress').val();
						var newLoc = geocodeAddress(address);
						//address = address.toLowerCase();
						loc.latitude = newLoc.Latitude;
						loc.longitude = newLoc.Longitude;
						if($('#deliveryRoutes').is(':checked')){
							addMarkerToRoute('Route1','#deliveryRoutes',loc, address)
							$('#deliveryRoutes2').attr('checked', false);
						}
					
						if($('#deliveryRoutes2').is(':checked')){
							addMarkerToRoute('Route2','#deliveryRoutes2',loc, address)
							$('#deliveryRoutes').attr('checked', false);
						}
					}
				});				
				
			
		}
	}
}

function addMarkerToRouteTW(e)
{
	var point = new Microsoft.Maps.Point(e.getX(), e.getY());
	var loc = e.target.tryPixelToLocation(point);
	var address = reverseGeocode(loc.latitude, loc.longitude);
	var _fields = "<form><div class='form-group'><label for='custName'>Name : </label><input type='text' class='form-control' id='custName' /></div>"
					+ "<div class='form-group'><label for='custAddress'>Address : </label><textarea class='form-control' id='custAddress'>"+address+"</textarea></div>"
					+ "<div class='form-group'><label>Preferred Time : </label>"
						+"<br/><label>Start Time: &nbsp;</label><select class='form-control' style='display: inline-block;width: auto;' id='custSTH'>"
							+ "<option value='01'>01</option>"
							+ "<option value='02'>02</option>"
							+ "<option value='03'>03</option>"
							+ "<option value='04'>04</option>"
							+ "<option value='05'>05</option>"
							+ "<option value='06'>06</option>"
							+ "<option value='07'>07</option>"
							+ "<option value='08' selected>08</option>"
							+ "<option value='09'>09</option>"
							+ "<option value='10'>10</option>"
							+ "<option value='11'>11</option>"
							+ "<option value='12'>12</option>"
						+"</select>&nbsp;"
						+"<select class='form-control' style='display: inline-block;width: auto;' id='custSTM'>"
							+ "<option value='00' selected>00</option>"
							+ "<option value='05'>05</option>"
							+ "<option value='10'>10</option>"
							+ "<option value='15'>15</option>"
							+ "<option value='20'>20</option>"
							+ "<option value='25'>25</option>"
							+ "<option value='30'>30</option>"
							+ "<option value='35'>35</option>"
							+ "<option value='40'>40</option>"
							+ "<option value='45'>45</option>"
							+ "<option value='50'>50</option>"
							+ "<option value='55'>55</option>"
							+ "<option value='60'>60</option>"
						+"</select>&nbsp;"
						+"<select class='form-control' style='display: inline-block;width: auto;' id='custSTAMPM'>"
							+ "<option value='AM' selected>AM</option>"
							+ "<option value='PM'>PM</option>"
						+"</select>"
						+"<br/><br/><label>End Time: &nbsp;&nbsp;&nbsp;</label>"
						+"<select class='form-control' style='display: inline-block;width: auto;' id='custETH'>"
							+ "<option value='01'>01</option>"
							+ "<option value='02'>02</option>"
							+ "<option value='03'>03</option>"
							+ "<option value='04'>04</option>"
							+ "<option value='05'>05</option>"
							+ "<option value='06'>06</option>"
							+ "<option value='07'>07</option>"
							+ "<option value='08'>08</option>"
							+ "<option value='09' selected>09</option>"
							+ "<option value='10'>10</option>"
							+ "<option value='11'>11</option>"
							+ "<option value='12'>12</option>"
						+"</select>&nbsp;"
						+"<select class='form-control' style='display: inline-block;width: auto;' id='custETM'>"
							+ "<option value='00' selected>00</option>"
							+ "<option value='05'>05</option>"
							+ "<option value='10'>10</option>"
							+ "<option value='15'>15</option>"
							+ "<option value='20'>20</option>"
							+ "<option value='25'>25</option>"
							+ "<option value='30'>30</option>"
							+ "<option value='35'>35</option>"
							+ "<option value='40'>40</option>"
							+ "<option value='45'>45</option>"
							+ "<option value='50'>50</option>"
							+ "<option value='55'>55</option>"
							+ "<option value='60'>60</option>"
						+"</select>&nbsp;"
						+"<select class='form-control' style='display: inline-block;width: auto;' id='custETAMPM'>"
							+ "<option value='AM' selected>AM</option>"
							+ "<option value='PM'>PM</option>"
						+"</select></div></form>";
	
	bootbox.confirm({
						title: 'Do you want to add customer in this route ?',
						message: _fields,
						buttons: {
							'cancel': {
								label: 'Cancel'
							},
							'confirm': {
								label: 'Regenerate Route'
							}
						},
						callback: function(result) {
							if (result) {
											
								//alert($('#newAddress').val());
								address = $('#custAddress').val();
								var newLoc = geocodeAddress(address);
								//address = address.toLowerCase();
								loc.latitude = newLoc.Latitude;
								loc.longitude = newLoc.Longitude;
								var _idx = GSDS.addresses['Address4'].length;
									
								var points  = GSDS.addresses['Address4'].length
								//var point = new Microsoft.Maps.Point(e.getX(), e.getY());
								//var loc = e.target.tryPixelToLocation(point);
								GSDS.addresses['Address4'][_idx]={
									id:"C"+(_idx+1), 
									name:$('#custName').val(),
									Latitude:loc.latitude,
									Longitude:loc.longitude,
									address:address,
									column2:address,
									startTime: $('#custSTH').val() + ":" + $('#custSTM').val() + " " + $('#custSTAMPM').val(),
									endTime:$('#custETH').val() + ":" + $('#custETM').val() + " " + $('#custETAMPM').val(),
									markerIcon: "new.png"
								}

								var location = new Microsoft.Maps.Location(loc.latitude, loc.longitude);
								var newPin = new Microsoft.Maps.Pushpin(location, {
									zIndex: 99,
									textType:'MapPushpinBase',
									htmlContent: '<img src="'+GSDS.addresses['Address4'][_idx].markerIcon+'">'
								});
								GSDS.map.entities.push(newPin);

								newPin.infoHeader = "Customer Information"
								newPin.Description = "<b>Name: </b>"+ GSDS.addresses['Address4'][_idx].name + "<br/>"
														+ "<b>Address: </b>"+ GSDS.addresses['Address4'][_idx].column2 + "<br/>"
														+ "<b>Preferred Time: </b>"+ GSDS.addresses['Address4'][_idx].startTime + "-" + GSDS.addresses['Address4'][_idx].endTime  + "<br/>";
											
				
								GSDS.pinInfobox = new Microsoft.Maps.Infobox(new Microsoft.Maps.Location(0, 0), { visible: false });
								GSDS.map.entities.push(GSDS.pinInfobox);
								Microsoft.Maps.Events.addHandler(newPin, 'click', displayInfobox);
								Microsoft.Maps.Events.addHandler(GSDS.pinInfobox, 'click', function(){
									GSDS.pinInfobox.setOptions({ visible:false });
								});
				
								GSDS.generateRoutes();
							}
						}
					});
}

function addMarkerToRoute(routeId,activeControlId,loc, address){
	var _idx = GSDS.routes[routeId].points.length;
	//var point = new Microsoft.Maps.Point(e.getX(), e.getY());
	//var loc = e.target.tryPixelToLocation(point);
	GSDS.routes[routeId].points[_idx]=GSDS.routes[routeId].points[_idx-1];
	GSDS.routes[routeId].points[_idx-1]= {latitude: loc.latitude, longitude: loc.longitude, position: _idx, order:_idx, markerIcon: "new.png", column2:address, skip:false};

	var location = new Microsoft.Maps.Location(loc.latitude, loc.longitude);
	var newPin = new Microsoft.Maps.Pushpin(location, {
		icon: "new.png" ,
		zIndex: 99,
		width: 64,
		height: 64
	});
	GSDS.map.entities.push(newPin);


	$('#directionDetails').html('');
	$('#directionDetails').hide();
	GSDS.clearMap('routes');
	GSDS.getRoutes(routeId,$(activeControlId+'Type').val());
	$('.labelPin div').show();
}

function getCustomer(id){
	var _addressTW = GSDS.addresses['Address4'];
	
	for(var i=0; i< _addressTW.length; i++){
		if(_addressTW[i].id == id) return _addressTW[i];
	}
}

$( document ).ready(function(){
	Microsoft.Maps.Events.addHandler(GSDS.map, 'rightclick', onMapClick);
	$('.btnclose').on('click',function(){
		$("#rdet").html("<thead><tr><th>Pin Code</th><th>First Day</th><th>Second Day</th><th>Third Day</th><th>Fourth Day</th><th>Fifth Day</th><th>Product Category</th>			</tr></thead><tbody></tbody>");
		$('#btmgridpanel').hide();
		//$('#btmgridpanel').css('display','none');
	});
});

function getZoom(map, params) {  // params are: locations, bounds, mapWidth, mapHeight, buffer

	var maxLat = -85,
		minLat = 85,
		maxLon = -180,
		minLon = 180,
		zoom1 = 0,
		zoom2 = 0,
		i;

	// If there are no params, then just get the zoom level of the passed map.
	if (!params) {
		return map.getZoom();
	}

	// If we have a locations array, the we ned to get the MBR
	if (params.locations) {     // A list of location objects, we need to get MBR
		for (i = 0; i < params.locations.length; i++) {
			if (params.locations[i].latitude > maxLat) {
				maxLat = params.locations[i].latitude;
			}
			if (params.locations[i].latitude < minLat) {
				minLat = params.locations[i].latitude;
			}
			if (params.locations[i].longitude > maxLon) {
				maxLon = params.locations[i].longitude;
			}
			if (params.locations[i].longitude < minLon) {
				minLon = params.locations[i].longitude;
			}
		}
	} else if (params.bounds) {     // if we have a bounds object, then it is an MBR already
		minLat = bounds.getSouth();
		maxLat = bounds.getNorth();
		minLng = bounds.getEast();
		maxLng = counds.getWest();
	} else {
		return map.getZoom();
	}

	//Determine the best zoom level based on the map scale and bounding coordinate information
	if (maxLon != minLon && maxLat != minLat)
	{
		//best zoom level based on map width
		zoom1 = Math.log(360.0 / 256.0 * (params.mapWidth - 2 * params.buffer) / (maxLon - minLon)) / Math.log(2);
		//best zoom level based on map height
		zoom2 = Math.log(180.0 / 256.0 * (params.mapHeight - 2 * params.buffer) / (maxLat - minLat)) / Math.log(2);
	}else{
		return map.getZoom();
	}

	//use the most zoomed out of the two zoom levels
	return (zoom1 < zoom2) ? zoom1 : zoom2;
}