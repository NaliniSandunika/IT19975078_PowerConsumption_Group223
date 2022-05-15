<%@page import="com.Consumption"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Power Consumption Management System</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.2.1.min.js"></script>
<script src="Components/consumptions.js"></script> 
<link rel="stylesheet" href="//cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
</head>
<body>
	<!-- As a heading -->
	<nav class="navbar navbar-dark" style="background: #990000;">
		<h3 style="color: white">Power Consumption Management System</h3>
	</nav>


	<div class="container">

		<form id="frmConsumption" name="frmConsumption"
			style="margin-top: 20px; margin-left: 25%; width: 500px;">

			<div class="form-group">
				<label>Account Number</label> <input type="text" name="accountNumber" id="accountNumber"
					style="margin-bottom:15px;" class="form-control" placeholder="Account Number" size="30px" required>
			</div>

			<div class="form-group">
				<label>Premises ID</label> <input type="text" name="premisesID"
					id="premisesID" style="margin-bottom:15px;" class="form-control" placeholder="Premises ID"
					size="30px" required>
			</div>

			<div class="form-group">
				<label>Month</label> <input type="text" name="month"
					id="month" style="margin-bottom:15px;" class="form-control" placeholder="Month"
					size="30px" required>
			</div>

			<div class="form-group">
				<label>Date</label> <input type="text" name="date" id="date"
					style="margin-bottom:15px;" class="form-control" placeholder="Date" size="30px" required>
			</div>

			<div class="form-group">
				<label>Units</label> <input type="text" name="units" id="units"
					style="margin-bottom:15px;" class="form-control" placeholder="Units" size="30px" required>
			</div>
 
			<div class="form-group" align="right">
				<input id="btnSave" name="btnSave" type="button" value="Save" style="margin-bottom:15px;" class="btn btn-primary">
				<input type="hidden" id="hidConsumptionIDSave" name="hidConsumptionIDSave" value="">
			</div>

		</form>
		<div id="alertSuccess" class="alert alert-success"></div>
		<div id="alertError" class="alert alert-danger"></div>
		<br>

		<div id="divConsumptionsGrid">
		<%
			Consumption consumptionObj = new Consumption();
			out.print(consumptionObj.readConsumptions());
		%>
		</div>

	</div>

</body>
</html>




