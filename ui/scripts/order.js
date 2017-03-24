var products = null;
var curSubTotal=0;
var curTotal=0;


function loadFile(){
  $.getJSON("scripts/products.json", function(json) {
    products=json;
}).done(function(){
		$.each(products,function(i,obj){
			$('#productsDDL').append('<option id="'+obj.id+'">'+obj.name+'</option>');
		});
  })
  .fail(function(jqXHR, textStatus, errorThrown){console.log(errorThrown)});
}

function addProduct(){
  var selectedProductId = $('#productsDDL option:selected').attr('id');
  var selectedProduct = getProductDetailsById(selectedProductId);
  
  var orderRowData = '<tr><td><table><tr >'
					+'<td rowspan="4"><img src="img/products/'+selectedProduct.image+'" style="width:100px;height:75px;"/></td>'
					+'</tr>'
					+'<tr >'
					+'<td>Name</td>'
					+'<td>'+selectedProduct.name+'</td>'
					+'</tr>'
					+'<tr >'
					+'<td>Price</td>'
					+'<td>'+selectedProduct.unitPrice+'</td>'
					+'</tr>'
					+'<tr >'
					+'<td>Quantity</td>'
					+'<td><input type="code" class="form-control"  value="1" onchange="reCalculateAmount();"></td>'
					+'</tr></table></td></tr>'
  $('#orderList').append(orderRowData);
  reCalculateAmount();
};

function reCalculateAmount(){
	curSubTotal=0;
	$('#orderList > tr').each(function() {
		var priceOfProduct= $(this).find('td > table > tbody > tr:nth-child(3) > td:nth-child(2)').text();
		var quantityOfProduct= $(this).find('td > table > tbody > tr:nth-child(4) > td:nth-child(2) > input')[0].value;
		var rowTotal = parseInt(priceOfProduct)*parseInt(quantityOfProduct);
		curSubTotal+=rowTotal;
	});
	curTotal= curSubTotal+2+4;
	
	$('#sub_price').text("$"+curSubTotal);
	$('#calculated_total').text("$"+curTotal);
}

function getProductDetailsById(id){
	return products.filter(function(n,i){
		return n.id==id;
	})[0];
};

function convertToMiles() {
	console.log('GELDİK BABA');
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
			"monetaryAmount" : curTotal,
			"partnerCompanyCode" : "MG",
			"ffpProgramCode" : "TK"		
		}),
		success : function (res) {
			$('#conversionOfMiles').text("Mile Amount : "+ res.milesAmount);
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			alert('fail');
			console.log(errorThrown);
		}
	});
};

function blockMiles() {
	var card1 = $("#card1").val();
	var card2 = $("#card2").val();
	var card3 = $("#card3").val();
	var card4 = $("#card4").val();
	var appended = card1 + card2 + card3 + card4;
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
			"ffpCode":"TK"},
			"companyCode":"MG",
			"amount":curTotal,
			"currency":"USD",
			"partnerTransactionCode":"1534313541531435124312434312321533"					
		}),
		success : function (res) {
			if(res.status=="BLK"){
				burnMiles(res);
			}
		},
		error : function (XMLHttpRequest, textStatus, errorThrown) {
			console.log(errorThrown);
		}
	});
};

function burnMiles(data){
console.log(data);
$.ajax({
	type : 'POST',
	url : 'http://localhost:8080/burnMiles',
	dataType : 'json',
	async : false,
	headers : {
		"Accept" : "application/json",
		"Content-Type" : "application/json"
	},
	data : JSON.stringify({
			"amadeusTransactionId": data.amadeusTransactionID,
			"partnerTransactionId": data.partnerTransactionCode
			}),
	success : function (res) {
		location.replace('success.html');
	},
	error : function (XMLHttpRequest, textStatus, errorThrown) {
		console.log(errorThrown);
	}
});
}