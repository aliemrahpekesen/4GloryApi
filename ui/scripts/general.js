$(document).ready(function () {
	// Add scrollspy to
	$('body').scrollspy({
		target : ".navbar",
		offset : 50
	});

	// Add smooth scrolling on all links inside the navbar
	$("#myNavbar a").on('click', function (event) {
		// Make sure this.hash has a value before overriding default behavior
		if (this.hash !== "") {
			// Prevent default anchor click behavior
			event.preventDefault();

			// Store hash
			var hash = this.hash;

			// Using jQuery's animate() method to add smooth page scroll
			// The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
			$('html, body').animate({
				scrollTop : $(hash).offset().top
			}, 800, function () {

				// Add hash (#) to URL when done scrolling (default click behavior)
				window.location.hash = hash;
			});
		} // End if
	});
});

function blockMiles() {
	var card1 = $("#card1").val();
	var card2 = $("#card2").val();
	var card3 = $("#card3").val();
	var card4 = $("#card4").val();
	var appended = card1 + card2 + card3 + card4;
	alert(appended);
	$.ajax({
		type : 'POST',
		url : 'http://localhost:8080/checkMiles',
		dataType : 'json',
		async : false,
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		data : JSON.stringify({
			"cardInfo":{"number" : appended,
			"expireMonth" : $("#exp_month").val(),
			"expireYear" : $("#exp_year").val(),
			"cvv" : $("#cvv").val(),
			"nameOnCard" : $("#nameOnCard").val(),
			"ffpCode":$("#companyId").val()},
			"companyCode":"MG",
			"amount":$("#calculated_total").val(),
			"currency":"USD",
			"partnerTransactionCode":"1534313541531435124312434312321533"					
		}),
		success : function (res) {
			alert($("#appended").val());
			console.log('response:' + res);
			location.replace("success.html");
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert('fail');
			console.log(errorThrown);
			location.replace("success.html");
		}
	});
};

function convertToMiles(airPartnerCode) {
	$.ajax({
		type : 'POST',
		url : 'http://localhost:8080/convertToMiles',
		dataType : 'json',
		async : false,
		headers : {
			"Accept" : "application/json",
			"Content-Type" : "application/json"
		},
		data : JSON.stringify({
			"monetaryAmount" : $("#calculated_total").val(),
			"partnerCompanyCode" : "MG",
			"ffpProgramCode" : $("#companyId").val()		
		}),
		success : function (res) {
			document.getElementById("calculated-points").value = res.milesAmount;
			alert(res);
			console.log('response:' + res);
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert('fail');
			console.log(errorThrown);
		}
	});
};
function backToHome() {
		location.replace("orderAndCheckOut.html")
};

function maxLengthCheckCardNumber(object) {
	if (object.value.length > object.maxLength)
		object.value = object.value.slice(0, object.maxLength)
};
function maxLengthCheckCcv(object) {
	if (object.value.length > object.maxLength)
		object.value = object.value.slice(0, object.maxLength)
};
