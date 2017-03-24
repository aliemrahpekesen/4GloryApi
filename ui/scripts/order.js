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
	
	$('#sub_price').text("$"+curSubTotal);
	$('#calculated_total').text((curSubTotal+2+4));
}

function getProductDetailsById(id){
	return products.filter(function(n,i){
		return n.id==id;
	})[0];
}




/*
<div class="product_image">
                           <img src="img/products/1.jpg"/>
                        </div>
                        <div class="product_details">
                           <span class="product_name">Forma</span>
                           <span class="quantity">1</span>
                           <span class="price">$45.00</span>
                        </div>*/