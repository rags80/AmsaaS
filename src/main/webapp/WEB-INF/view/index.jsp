<!--
 * 
 *   Document   : Application Style
 *   Author     : Raghavendra Badiger
 *   Description: "HOME" page JSP which serves as template for website
 -->
 
<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>
<head>
<title>AmsaaS</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width-device-width,initial-scale=1.0" />

<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/lib/bootstrap/img/leaf.ico" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/lib/bootstrap/css/bootstrap-responsive.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/application/modules/common/views/glyph.css" />

<link rel="stylesheet"
	href="${pageContext.request.contextPath}/application/modules/common/views/application.css" />
<link
	href="${pageContext.request.contextPath}/lib/jquery/jquery-ui/custom-theme/jquery-ui-1.9.2.custom.min.css"
	rel="stylesheet" />

<script
	src="${pageContext.request.contextPath}/lib/jquery/jquery-1.8.3.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/jquery/jquery-ui-1.9.2.custom.min-1.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/moment/moment.min.js"></script>

<script
	src="${pageContext.request.contextPath}/lib/bootstrap/js/bootstrap.min.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout-latest.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout.validation.min.js"></script>
<script
	src="${pageContext.request.contextPath}/lib/knockout/knockout.mapping-latest.js"></script>


<script src="${pageContext.request.contextPath}/lib/amd/require.js"
	data-main="${pageContext.request.contextPath}/application/main"></script>
</head>

<body>
	<!-- AppContent starts which is main container of template -->
	<div id="appContent">

		<!-- Header starts -->
		<header id="Header">
			<div class="container">
				<div class="row">
					<div class="span4">
						<!-- Logo and site link -->
						<div class="logo">
							<h1>AmsaaS</h1>
						</div>
					</div>

				</div>
			</div>
		</header>
		<!-- Header ends -->

		<!-- Navigation Starts -->
		<div id="Main_Menu" class="navbar">
			<div class="navbar-inner">
				<div class="tabbable">
					<div class="container">
						<div class="row">
							<div class="span12">

								<ul class="nav nav-tabs">
									<li class="active"><a href="#people" data-toggle="tab"><i
											class="icon-user"></i> PEOPLE</a></li>
									<li><a href="#finances" data-toggle="tab"><i
											class="icon-briefcase"></i> FINANCES</a></li>

									<li><a href="#billpayments" data-toggle="tab"><i
											class="icon-list-alt"></i> BILLING &amp; PAYMENTS</a></li>
									<li><a href="#servicemanager" data-toggle="tab"><i
											class="icon-asterisk"></i> APARTMENT SERVICES</a></li>
									<li><a href="#calendar" data-toggle="tab"><i
											class="icon-calendar"></i> CALENDAR</a></li>
								</ul>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- Navigation Ends -->

		<!-- Content starts -->
		<div id="ContentBody" class="container well">
			<div class="row">
				<div class="span12">
					<div class="tab-content">
						<div class="tab-pane active" id="people"></div>
						<div class="tab-pane" id="finances">
							<div id="financeContainer" class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active"><a href="#FinanceManagerDiv"
										data-toggle="tab">Finance Manager</a></li>
									<li><a href="#xyz" data-toggle="tab">XYZ Manager</a></li>
								</ul>
								<div class="tab-content">
									<div id="FinanceManagerDiv" class="tab-pane active"></div>
									<div id="xyz" class="tab-pane"></div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="billpayments"></div>
						<div class="tab-pane" id="servicemanager">
							<div id="servicesContainer" class="tabbable">
								<ul class="nav nav-tabs">
									<li class="active">
									<a href="#serviceplans" data-toggle="tab">Service Plan Catalogue</a></li>
									<li><a href="#services" data-toggle="tab">Service Catalogue</a></li>
								</ul>
								<div class="tab-content">
									<div id="serviceplans" class="tab-pane active"></div>
									<div id="services" class="tab-pane"></div>
								</div>
							</div>
						</div>
						<div class="tab-pane" id="calendar"></div>
					</div>
				</div>
			</div>
		</div>
		<!-- Content Ends -->

		<!-- Footer Starts-->
		<footer id="Footer">
			<div class="container">
				<div class="row">
					<div class="span4">
						<div>
							<h4>About Us</h4>
							<p>The AmsaaS is designed and developed by Raghavendra
								Badiger,Santosh Mangsuli and Chandrashekar Kumbar.</p>
							<ul>
								<li><a href="#"><i class="sprite-glyphicons_001_leaf">
									</i> </a></li>
								<li><a href="#">Etiam at nulla ipsum, in rhoncus purus</a></li>
							</ul>
						</div>
					</div>
					<div class="span4">
						<div>
							<h4>Recent Posts</h4>
							<ul>
								<li><a href="#">Sed eu leo orci, in rhoncus puru
										condimentum gravida metus</a></li>
								<li><a href="#">Etiam at in rhoncus puru nulla ipsum,
										in rhoncus purus</a></li>
								<li><a href="#">Fusce vel magnain rhoncus puru faucibus
										felis dapibus facilisis</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</footer>
		<!-- Footer Ends-->

	</div>
	<!-- Appcontent Ends-->
</body>
</html>