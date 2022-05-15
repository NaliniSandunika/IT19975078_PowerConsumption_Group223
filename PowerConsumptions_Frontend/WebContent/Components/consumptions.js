$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	var status = validateConsumptionForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}
	// If valid------------------------
	var type = ($("#hidConsumptionIDSave").val() == "") ? "POST" : "PUT";
	$.ajax({
		url : "ConsumptionAPI",
		type : type,
		data : $("#frmConsumption").serialize(),
		dataType : "text",
		complete : function(response, status) {
			onConsumptionSaveComplete(response.responseText, status);
		}
	});
});

function onConsumptionSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divConsumptionsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidConsumptionIDSave").val("");
	$("#frmConsumption")[0].reset();
}

$(document).on("click", ".btnUpdate", function(event) {
	$("#hidConsumptionIDSave").val($(this).data("consumptionid")); //change
	$("#accountNumber").val($(this).closest("tr").find('td:eq(0)').text()); 
	$("#premisesID").val($(this).closest("tr").find('td:eq(1)').text()); 
	$("#month").val($(this).closest("tr").find('td:eq(2)').text());
	$("#date").val($(this).closest("tr").find('td:eq(3)').text()); 
	$("#units").val($(this).closest("tr").find('td:eq(4)').text()); 
});

$(document).on("click", ".btnRemove", function(event) {
	$.ajax({
		url : "ConsumptionAPI",
		type : "DELETE",
		data : "consumptionID=" + $(this).data("consumptionid"),  //change
		dataType : "text",
		complete : function(response, status) {
			onConsumptionDeleteComplete(response.responseText, status);
		}
	});
});

function onConsumptionDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divConsumptionsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}

// CLIENT-MODEL================================================================
function validateConsumptionForm() {
	// Account Number
	if ($("#accountNumber").val().trim() == "") {
		return "Insert Account Number.";
	}
	// Premises ID
	if ($("#premisesID").val().trim() == "") {
		return "Insert Premises ID.";
	}
	// Month-------------------------------
	if ($("#month").val().trim() == "") {
		return "Insert Month.";
	}
	// Date-------------------------------
	if ($("#date").val().trim() == "") {
		return "Insert Date.";
	}
	// Units-------------------------------
	if ($("#units").val().trim() == "") {
		return "Insert Units.";
	}
	
	return true;
}
