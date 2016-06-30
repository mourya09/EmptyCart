
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en"><head>
        <title>GreatMart </title>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<!-- <script type="text/javascript" src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
			<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script> -->
			
			<script type="text/javascript" src="script/jquery-1.8.3.min.js"></script>
			<script src="script/jquery-ui.js"></script>		
			
			<script src="script/mapbox.js"></script>
        <style type="text/css">
            html { height: 100%; }
            body { height: 100%;font-family:Verdana,sans-serif, Arial;color:#000;margin: 0; font-size:14px; padding: 0; }
            #map-container {
                position: absolute;
                left: 312px; top: 46px; 
                right: 2px; bottom: 2px; 
                border: 1px solid #cccccc; }
            #result {
                position: absolute;
                left: 2px; top: 46px;
                width: 306px;
                bottom: 2px; 
                border: 1px solid #cccccc;
                background-color: #FAFAFA;
                overflow: auto;
            }
             button{width: 170px;font-family:Verdana,sans-serif, Arial;font-size:12px;padding:2px 0;color:#333}     
        </style>
		<style type="text/css" src="css/main.css"></style>
        <script type="text/javascript"src="https://api.mapmyindia.com/v3?fun=load_api_cached&scope=nt-india&v=0.8&lic_key=7i6pivjtmxk6cms1p9vcyj5orhaia6wg"></script>
        <script type="text/javascript" src="script/main.js"></script>
    <style type="text/css"></style></head>
    <body>
        <div style="border-bottom: 1px solid #e9e9e9;padding:10px 12px;"><span style="font-size: 20px">GreatMart:  </span><span style="font-size:16px;color:#777">Route Management System</span></div>
 <div id="result">            
<div style="padding: 16px 12px 6px 38px">
<select id="routeId" onchange="displayRoute()">
	<option value="5">Route 5</option>
	<option value="6">Route 6</option>
	<option value="7">Route 7</option>
</select>

<button onclick="displayRoute()" style="width: 170px;">Get Route Information</button>

</div>



<div style="padding: 16px 12px 6px 38px">
<select id="Warehouse" onchange="displayWarehouse()">
	<option value="1">Warehouse 1</option>
	
</select>
<button onclick="displayWarehouse()" style="width: 170px;">Display Warehouse</button>

</div>
<div style="padding: 16px 12px 6px 38px">
<label for="createWareHouse">Display Moving Truck</label>
<button onclick="showTrucksMoving()" style="width: 170px;">Display</button><br>
<label for="createWareHouse">Find nearby Warehouse</label>
<select id="distanceId" >
	<option value="-1">Select Distance Location</option>
	<option value="5">5 Km</option>
	<option value="10">10 Km</option>
	<option value="15">15 Km</option>
	<option value="25">25 Km</option>
	<option value="50">50 Km</option>
	<option value="75">75 Km</option>
	<option value="100">100 Km</option>
</select>
<button onclick="findNearByWarehouse()" style="width: 170px;">Find</button>

</div>

<div style="padding: 16px 12px 6px 38px">
<label for="createWareHouse">Create Warehouse </label>
<button onclick="createWareHouse()" id="createWareHouse" style="width: 170px;">Create Warehouse</button>
<button onclick="createWareHouseBoundary()" id="createWareHouse" style="width: 170px;">Create Warehouse Boundary</button>
<button onclick="saveWarehouse()" id="saveWarehouse" style="width: 170px;">Save Warehouse</button>

</div>
           
            <div style="padding:14px 12px 6px 38px;color: #666;">
			
			<button onclick="clearAll()" id="clearAll" style="width: 170px;">Clear All</button>
			</div>
            <div style="padding:6px 12px 6px 38px;color: #777; font-size: 12px; line-height: 22px;" id="event-log1"></div>
        </div>
        <div id="map-container"></div>
    


</body></html>
