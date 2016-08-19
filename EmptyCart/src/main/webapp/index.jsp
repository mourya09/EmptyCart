	<!DOCTYPE html>
<html lang="en">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Empty Cart</title>
    <meta name="description" content="">

	<link rel="shortcut icon" href="img/favicon_16.png" type="image/png"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<link href="css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css">	
	<link rel="stylesheet" href="//code.jquery.com/ui/1.11.0/themes/smoothness/jquery-ui.css">
    <link href="css/style.css" rel="stylesheet">
	<link href="css/infoboxStyles.css" rel="stylesheet">
	<script src="js/jquery.min.js"></script>
	<script src="js/jquery-ui.js"></script>
    <script src="js/bootstrap.min.js"></script>
	<script charset="UTF-8" type="text/javascript" src="http://ecn.dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=7.0"></script>
	<script type="text/javascript" src="js/ulDemo.js"></script>
	<script type="text/javascript" src="js/bootbox.js"></script>
	<script type="text/javascript" src="js/sample.js"> </script>

	<body oncontextmenu="return false">
		<div class="page-header">
			<nav class="navbar navbar-fixed-top" role="navigation">
				<!-- Brand and toggle get grouped for better mobile display -->
				<div class="navbar-header">
					<a class="navbar-brand" href="#"></a>
				</div>
			</nav>
		</div>
		<div class="container">
			<div class="row">
				<!--Left Navigation Pane-->
				<div id="navigation-wrapper" class="col-xs-3 left-nav">
					<!-- ngRepeat: navSection in navigationTree -->
					<div class="panel nav-section ng-scope active">
						<!--Navigation Pane Header-->
						<div class="nav-section-header" data-toggle="collapse" data-parent="#navigation-wrapper" data-target="#nav_0">
							<table>
								<tbody>
									<tr>
										<td><img src="img/left-arrow_blue.png"></td>
										<td>
											<span  class="step-number ng-binding">1</span>
										</td>
										<td>
											<div class="step-description-wrapper"  onclick="setUserLocation()">
												<span class="step-description ng-binding">Customer Location</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<!--Navigation Pane Body-->
						<div id="nav_0" class="nav-section-body-wrapper in">
							<div class="nav-section-body">
								<p>
									Click on the map to set customer's location.
								</p>
								<div class="">
									<!-- <input type="button" name="setLocation" value="Set Location" onclick="setUserLocation()" ><br> 
									<input type="text" name="text" id="product" value=""  >
									<input type="button" name="productSearch" value="Search" onclick="searchProduct()" ><br>
									 <input type="checkbox" name="showSellerServingArea" id="showSellerServingArea" >&nbsp; Show Seller's Coverage Area -->
								</div>
							</div>
						</div>
					</div>
					
					<div class="panel nav-section ng-scope">
						<div class="nav-section-header" data-toggle="collapse" data-parent="#navigation-wrapper" data-target="#nav_2" >
							<table>
								<tbody>
									<tr>
										<td><img  src="img/left-arrow-grey.png"></td>
										<td>
											<span class="step-number ng-binding">2</span>
										</td>
										<td>
											<div class="step-description-wrapper">
												<span class="step-description ng-binding">Search Product/Category</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="nav_2" class="collapse nav-section-body-wrapper">
							<div class="nav-section-body">
								<p>
									Click on the map to set customer's location.
								</p>
								<div class="">
									<!-- <input type="button" name="setLocation" value="Set Location" onclick="setUserLocation()" ><br> -->
									<input type="text" name="text" id="product" value="" placeholder="Category" >
									<input type="text" name="text" id="product2" value=""  placeholder="Product" >
									<input type="button" name="productSearch" value="Search" onclick="searchProduct()" ><br>
									
									<!-- <input type="checkbox" name="showSellerServingArea" id="showSellerServingArea" >&nbsp; Show Seller's Coverage Area -->
								</div>
								<!-- <p>
									Display visit route on the map.
								</p>
                           		<div class="check_box_inpt">
                                   	<input type="radio" name="checkbox" id="deliveryRoutes" value="value" disabled>
									<label for="deliveryRoutes">Show route for today - Agent 1</label>
									<select id = 'deliveryRoutesType' style="display:none;">
										<option value='distance' selected>Shortest Distance</option>
										<option value='time'>Fastest Time</option>
									</select>
									<label id = 'lblRoute1' style="display:none;font-weight: 500;color: #007ac2;"></label>
								</div>
								 <div class="check_box_inpt">
                                   	<input type="radio" name="checkbox" id="deliveryRoutes2" value="value" disabled>
									<label for="deliveryRoutes2">Show route for today - Agent 2</label>
									<select id = 'deliveryRoutes2Type' style="display:none;">
										<option value='distance' selected>Shortest Distance</option>
										<option value='time'>Fastest Time</option>
									</select>
									<label id = 'lblRoute2' style="display:none;font-weight: 500;color: #007ac2;"></label>
								</div>
								-->
								<!-- <div id="directionDetails" style="display:none;"></div> -->
							</div>
						</div>
					</div>
					<div class="panel nav-section ng-scope">
						<div class="nav-section-header" data-toggle="collapse" data-parent="#navigation-wrapper" data-target="#nav_1" >
							<table>
								<tbody>
									<tr>
										<td><img  src="img/left-arrow-grey.png"></td>
										<td>
											<span  class="step-number ng-binding">3</span>
										</td>
										<td>
											<div class="step-description-wrapper" onclick="showSellerGeofence()">
												<span class="step-description ng-binding">Sellers & Coverage</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="nav_1" class="collapse nav-section-body-wrapper">
							<div class="nav-section-body">
								<!-- <p>
									Display addresses on map where I need to visit customer.
								</p>
								<div class="check_box_inpt">
									<input type="checkbox" name="checkbox" id="myDeliveries" value="value" unchecked disabled>
									<label for="myDeliveries">Show my targeted customer locations for today</label>
								</div> -->
							</div>
						</div>
					</div>
					<div class="panel nav-section ng-scope">
						<div class="nav-section-header" data-toggle="collapse" data-parent="#navigation-wrapper" data-target="#nav_3" >
							<table>
								<tbody>
									<tr>
										<td><img  src="img/left-arrow-grey.png"></td>
										<td>
											<span class="step-number ng-binding">4</span>
										</td>
										<td>
											<div class="step-description-wrapper">
												<span class="step-description ng-binding">Tag Customer & Push Offer</span>
											</div>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div id="nav_3" class="collapse nav-section-body-wrapper">
							<div class="nav-section-body">
								<p>
									Display confidence metrics and push offers.
								</p>
								<div class="check_box_inpt">
                                   	
									<select id="showConfidence" name="v11" onchange="showProductCategory()" >
									<option value="-1"> Please select Product Sellers </option>
									
									</select><br><br>
									<select id="showConfidenceWithProduct" name="v11" onchange="showConfidence()" >
									<option value="-1"> Please select Product Category </option>
									
									</select><br>
									<br>
									<label for="agent">Show Confidence Metrics </label><br>
									<input type="radio" id="pushOffers" name="v11" onclick="pushOffers()" />
									<label for="agent">Show Push Offers </label><br>
									
									
									
								</div>
								<!--  <div id="directionDetailsTW" style="display:none;"></div> -->
							</div>
						</div>
					</div>
					<div class="nav-section-body">
					<div id="directionDetails" style="display:none;"></div>
					</div>
				</div>
				<!--Map Window-->
				<div class="map-wrapper">
					<div id="loading" class="loading" style="display:none">
							</div>
					<div class="apiDemo-sd-error"></div>			
					<div class="apiDemo-sd-resultContainer">
						<div class="apiDemo-sd-resultMap">
							<div id="apiDemo-sd-map"></div>
							<div id="apiDemo-sd-mapLoader"></div>
						</div>
					</div>
				</div>
			</div> 
		</div>
	
		<div id="footer" class="footer-thin">
			<div  class="">
				<div class="row">
					<div class="col-lg-6">
						<a href="http://www.pb.com"><i class="pb-footer-logo gradient"></i></a>© 2015 Pitney Bowes Inc. All rights reserved.
					</div>
					<div class="col-lg-6 text-right xs-text-left">
						<ul class="list-unstyled list-inline social">
							<li>Follow us:</li>
							<li>
								<a target="_blank" class="fa-stack twitter" href="https://twitter.com/pitneybowes">
									<i class="fa fa-circle-thin fa-stack-2x"></i>
									<i class="fa fa-twitter fa-stack-1x"></i>
								</a>
							</li>
							<li>
								<a target="_blank" class="fa-stack facebook" href="http://www.facebook.com/pitneybowes">
									<i class="fa fa-circle-thin fa-stack-2x"></i>
									<i class="fa fa-facebook fa-stack-1x"></i>
								</a>
							</li>
							<li>
								<a target="_blank" class="fa-stack linkedin" href="http://www.linkedin.com/company/pitney-bowes">
									<i class="fa fa-circle-thin fa-stack-2x"></i>
									<i class="fa fa-linkedin fa-stack-1x"></i>
								</a>
							</li>
							<li>
								<a target="_blank" class="fa-stack google-plus" href="https://plus.google.com/+pitneybowes">
									<i class="fa fa-circle-thin fa-stack-2x"></i>
									<i class="fa fa-google-plus fa-stack-1x"></i>
								</a>
							</li>
							<li>
								<a target="_blank" class="fa-stack youtube" href="http://www.youtube.com/user/PitneyBowesInc">
									<i class="fa fa-circle-thin fa-stack-2x"></i>
									<i class="fa fa-youtube-play fa-stack-1x"></i>
								</a>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>	
	
		<div id="btmgridpanel" class="dn" style="right: 0px; left: auto; display: none;">
			<div class="headerbar">
				<div class="togglebar_dn" id="btmarrow">&nbsp;</div>
				<h1 class="srch" id="btmgridpanelheader" style="float:left;width:300px;">Results Grid</h1>                
				<div class="action" style="clear:none;margin-top:8px;">
					<a href="javascript:void(0);" id="minimize" class="btnmin" title="Minimize">&nbsp;</a>
					<a href="javascript:void(0);" id="maxrest" class="btnmax" title="Maximize">&nbsp;</a>
					<a href="javascript:void(0);" id="Close" class="btnclose" title="Close Panel">&nbsp;</a>
				</div>
			</div>
			<div class="innertube">
				<div id="resultsGrid">
					<div id="apiDemo-sd-resultTbl"></div>
				</div>
			</div>
		</div>
	</body>
</html>