var baseUrl = 'http://localhost:8080';
var fullRuleListOnPage = null;
var companyList = null;
var airlineList = null;
var partnerList = null;

$(document).ready(function(){
	  fillDropdowns();
	  getAllRules();
	  
	  $('#ruleList').on('click', '.clickable-row', function(event) {
			$(this).addClass('active').siblings().removeClass('active');
	  });
});

function addRule(){
	$.ajax({
			type: 'POST',
			url: baseUrl+'/rules',
			dataType: 'json',
			async:false,
			crossDomain:true,
			headers: {
				"Accept" : "application/json",
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "*"
			},
			data: JSON.stringify({
			   "code":$("#code").val(),
			   "rate":$("#rate").val(),
			   "currency":$("#currency option:selected").val(),
			   "description":$("#description").val(),
			   "partnerId":$('#partnerCompany option:selected').attr('id'),
			   "airlineId":$('#airlineCompany option:selected').attr('id')
			}),
			success: function(res){
				getAllRules();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});
}


function getAllRules(){
var memberList=null;
$("#ruleList").find('tbody').empty();
$.ajax({
	type: 'GET',
	crossDomain:true,
	url: baseUrl+'/rules',
	dataType: 'json',
	async:false,
	headers: {
		"Accept" : "application/json",
		"Content-Type": "application/json",
		"Access-Control-Allow-Origin": "*"
	},
	success: function(data){
		fillRuleListTable(data);
	},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
		console.log(errorThrown);
	}
});
};

function fillRuleListTable(data){
fullRuleListOnPage=null;
var ruleList = data._embedded.rules;
fullRuleListOnPage = ruleList;
$.each(ruleList,function(i,obj){
	var objId = retrieveRuleIdOfObject(obj);
	var manipulationColumn   = '<button type="button" class="btn btn-blue btn-sm">'
					  +'<span class="glyphicon glyphicon-edit" aria-hidden="true" onClick="editRule('+objId+');"></span>'
					  +'</button>'
					  +'<button type="button" class="btn btn btn-danger btn-sm">'
					  +'<span class="glyphicon glyphicon-remove" aria-hidden="true" onClick="deleteRule('+objId+');"></span>'
					  +'</button>';				
	$("#ruleList").find('tbody')
		.append(
				$('<tr class="clickable-row" id ="'+objId+'">').append(
									$('<td>').append(obj.code)
								)
						 .append(
									$('<td>').append(obj.rate)
								)
						 .append(
									$('<td>').append(obj.currency)
								)
						 .append(
									$('<td>').append(getCompanyNameByCompanyId(obj.airlineId))
								)
						 .append(
									$('<td>').append(getCompanyNameByCompanyId(obj.partnerId))
								)
						 .append(
									$('<td align="right">').append(manipulationColumn)
								)
				)
});
};

function deleteRule(objId){
$('#confirmModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#delete').unbind("click").click(function(){
				  $.ajax({
					type:'DELETE',
					url: baseUrl+'/rule?id='+objId,
					contentType:'application/json',
					dataType: 'text', 
					success: function(data){
						getAllRules();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(errorThrown);
					}
				});
			});
};

function retrieveRuleIdOfObject(obj){
	return obj._links.self.href.substring((baseUrl+'/rules').length+1);
}

function editRule(objId){
$("#ruleDetails").empty();
var editObject=null;
for(i=0;i<fullRuleListOnPage.length;i++){
  var searchId = retrieveRuleIdOfObject(fullRuleListOnPage[i]);
  if(searchId==objId){
	editObject=fullRuleListOnPage[i];
	break;
  }
}
var editForm='<form class="form-horizontal">'
			+'	<div class="col-sm-6">'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="code">Code:</label>'
			+'			<div class="col-sm-8">'
			+'				<input type="code" class="form-control" id="newCode" value="'+editObject.code+'">'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="rate">Rate:</label>'
			+'			<div class="col-sm-8">'
			+'				<input type="rate" class="form-control" id="newRate" value="'+editObject.rate+'">'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="currency">Currency:</label>'
			+'			<div class="col-sm-8">'
			+'				<select class="form-control" id="newCurrency">'
			+'					<option value="TRY"'+(editObject.currency=="TRY"?' selected="true"':'')+'>TRY</option>'
			+'					<option value="EUR"'+(editObject.currency=="EUR"?' selected="true"':'')+'>EUR</option>'
			+'					<option value="USD"'+(editObject.currency=="USD"?' selected="true"':'')+'>USD</option>'
			+'				</select>'
			+'			</div>'
			+'		</div>'
			+'	</div>'
			+'	<div class="col-sm-6">'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="description">Description:</label>'
			+'			<div class="col-sm-8">'
			+'				<input type="description" class="form-control" id="newDescription" value="'+editObject.description+'">'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="type">Company:</label>'
			+'			<div class="col-sm-8">'
			+'				<select class="form-control" id="newAirlineCompany">';
		for(i=0;i<airlineList.length;i++){
			var airlineId = getCompanyIdOfObject(airlineList[i]);
			editForm+=' <option value="AIR" id="'+airlineId+'" '+(editObject.airlineId==airlineId?' selected="true"':'')+'>'+airlineList[i].name+'</option>';
		};
		editForm+='				</select>'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-4" for="type">Partner:</label>'
			+'			<div class="col-sm-8">'
			+'				<select class="form-control" id="newPartnerCompany">'
		for(i=0;i<partnerList.length;i++){
			var partnerId = getCompanyIdOfObject(partnerList[i]);;
			editForm+=' <option value="NONAIR" id="'+partnerId+'" '+(editObject.partnerId==partnerId?' selected="true"':'')+'>'+partnerList[i].name+'</option>';
		};
		editForm+='				</select>'
			+'			</div>'
			+'		</div>'
			+'	</div>'
			+'</form>';

$("#ruleDetails")
		.append($('<td colspan="2">').append(editForm));
				
$('#editModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#save').unbind("click").click(function(){
			    $.ajax({
						type: 'PUT',
						url: baseUrl+'/rule/'+objId,
						dataType: 'json',
						contentType:'application/json',
						data:JSON.stringify({
						   "code":$("#newCode").val(),
						   "rate":$("#newRate").val(),
						   "currency":$("#newCurrency option:selected").val(),
						   "description":$("#newDescription").val(),
						   "partnerId":$('#newPartnerCompany option:selected').attr('id'),
						   "airlineId":$('#newAirlineCompany option:selected').attr('id')
						}),
						success: function(res){
							getAllRules();
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							console.log(errorThrown);
						}
					});
			});
};

function fillDropdowns(){
  $.ajax({
	type: 'GET',
	crossDomain:true,
	url: baseUrl+'/companies',
	dataType: 'json',
	async:false,
	headers: {
		"Accept" : "application/json",
		"Content-Type": "application/json",
		"Access-Control-Allow-Origin": "*"
	},
	success: function(data){
		seperateLists(data);
		for(i=0;i<airlineList.length;i++){
			var airlineId = getCompanyIdOfObject(airlineList[i]);
			$('#airlineCompany').append(' <option value="AIR" id="'+airlineId+'">'+airlineList[i].name+'</option>');
		};
		for(i=0;i<partnerList.length;i++){
			var partnerId = getCompanyIdOfObject(partnerList[i]);;
			$('#partnerCompany').append(' <option value="NONAIR" id="'+partnerId+'">'+partnerList[i].name+'</option>');
		};
		
	},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
		console.log(errorThrown);
	}
});
};

function seperateLists(data){
	companyList = data._embedded.companies;
	airlineList = companyList.filter(function( n, i ) {
						  return n.type==='AIR';
						});
	partnerList = companyList.filter(function( n, i ) {
						  return n.type==='NONAIR';
						});
	
}
function getCompanyIdOfObject(obj){
	return obj._links.self.href.substring((baseUrl+'/companies').length+1);
}

function getCompanyNameByCompanyId(id){
  var company = $.grep(companyList, function(e){ return e._links.self.href.substring((baseUrl+'/companies').length+1) == id; })[0];
  return company.name;
}
