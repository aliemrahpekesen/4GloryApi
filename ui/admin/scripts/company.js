var baseUrl = 'http://localhost:8080';
var fullCompanyListOnPage=null;

$(document).ready(function(){
	  getAllCompanies();
	  
	  $('#companyList').on('click', '.clickable-row', function(event) {
			$(this).addClass('active').siblings().removeClass('active');
	  });
});

function addCompany(){
	$.ajax({
			type: 'POST',
			url: baseUrl+'/companies',
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
			   "name":$("#name").val(),
			   "type":$('#type option:selected').val()
			}),
			success: function(res){
				getAllCompanies();
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});
}


function getAllCompanies(){
var memberList=null;
$("#companyList").find('tbody').empty();
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
		fillCompanyListTable(data);
	},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
		console.log(errorThrown);
	}
});
};

function fillCompanyListTable(data){
fullCompanyListOnPage=null;
var companyList = data._embedded.companies;
fullCompanyListOnPage = companyList;
$.each(companyList,function(i,obj){
	var objId = retrieveCompanyIdOfObject(obj);
	var manipulationColumn   = '<button type="button" class="btn btn-blue btn-sm">'
					  +'<span class="glyphicon glyphicon-edit" aria-hidden="true" onClick="editCompany('+objId+');"></span>'
					  +'</button>'
					  +'<button type="button" class="btn btn btn-danger btn-sm">'
					  +'<span class="glyphicon glyphicon-remove" aria-hidden="true" onClick="deleteCompany('+objId+');"></span>'
					  +'</button>';				
	$("#companyList").find('tbody')
		.append(
				$('<tr class="clickable-row" id ="'+objId+'">').append(
									$('<td>').append(obj.code)
								)
						 .append(
									$('<td>').append(obj.name)
								)
						 .append(
									$('<td>').append(obj.type=="AIR"?"Air Partner":"Non Air Partner")
								)
						 .append(
									$('<td align="right">').append(manipulationColumn)
								)
				)
});
};

function deleteCompany(objId){
$('#confirmModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#delete').unbind("click").click(function(){
				  $.ajax({
					type:'DELETE',
					url: baseUrl+'/company?id='+objId,
					contentType:'application/json',
					dataType: 'text', 
					success: function(data){
						getAllCompanies();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(errorThrown);
					}
				});
			});
};

function retrieveCompanyIdOfObject(obj){
	return obj._links.self.href.substring((baseUrl+'/companies').length+1);
}

function editCompany(objId){
$("#companyDetails").empty();
console.log('Edit Modal is opened');
var editObject=null;
for(i=0;i<fullCompanyListOnPage.length;i++){
  var searchId = retrieveCompanyIdOfObject(fullCompanyListOnPage[i]);
  if(searchId==objId){
	editObject=fullCompanyListOnPage[i];
	break;
  }
}
var curType=editObject.type==="AIR";
var editForm='<form class="form-horizontal">'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-2" for="code">Code:</label>'
			+'			<div class="col-sm-10">'
			+'				<input type="code" class="form-control" id="newCode" value="'+editObject.code+'">'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-2" for="name">Name:</label>'
			+'			<div class="col-sm-10">'
			+'				<input type="name" class="form-control" id="newName" value="'+editObject.name+'">'
			+'			</div>'
			+'		</div>'
			+'		<div class="form-group">'
			+'			<label class="control-label col-sm-2" for="type">Type:</label>'
			+'			<div class="col-sm-10">'
			+'				<select class="form-control" id="newType">';
			
if(curType){
editForm+=   '		  			<option value="AIR" selected="true">Air Partner</option>'
		    +'		  			<option value="NONAIR">Non Air Partner</option>';
}
else{
editForm+=   '		  			<option value="AIR">Air Partner</option>'
		    +'		  			<option value="NONAIR" selected="true">Non Air Partner</option>';
}

editForm+=+'				</select>'
			+'			</div>'
			+'		</div>'
			+'</form>';

$("#companyDetails")
		.append($('<td colspan="2">').append(editForm));
				
$('#editModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#save').unbind("click").click(function(){
			    $.ajax({
						type: 'PUT',
						url: baseUrl+'/company/'+objId,
						dataType: 'json',
						contentType:'application/json',
						data:JSON.stringify({
						   "code":$("#newCode").val(),
						   "name":$("#newName").val(),
						   "type":$("#newType option:selected").val()
						}),
						success: function(res){
							getAllCompanies();
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							console.log(errorThrown);
						}
					});
			});
};
