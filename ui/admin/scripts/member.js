var baseUrl = 'http://localhost:8080';
var fullMemberListOnPage=null;

$(document).ready(function(){
	  getAllMembers();
	  
	  $('#memberList').on('click', '.clickable-row', function(event) {
			$(this).addClass('active').siblings().removeClass('active');
	  });
});

function addMember(){
	$.ajax({
			type: 'POST',
			url: baseUrl+'/members',
			dataType: 'json',
			async:false,
			crossDomain:true,
			headers: {
				"Accept" : "application/json",
				"Content-Type": "application/json",
				"Access-Control-Allow-Origin": "*"
			},
			data: JSON.stringify({
			   "name":$("#name").val(),
			   "surname":$("#surname").val(),
			   "email":$("#email").val(),
			   "number":$("#phoneNumber").val()
			}),
			success: function(res){
				console.log('response:' + res);
			},
			error: function(XMLHttpRequest, textStatus, errorThrown) {
				console.log(errorThrown);
			}
		});
}


function getAllMembers(){
var memberList=null;
$("#memberList").find('tbody').empty();
$.ajax({
	type: 'GET',
	crossDomain:true,
	url: baseUrl+'/members',
	dataType: 'json',
	async:true,
	headers: {
		"Accept" : "application/json",
		"Content-Type": "application/json",
		"Access-Control-Allow-Origin": "*"
	},
	success: function(data){
		fillMemberListTable(data);
	},
	error: function(XMLHttpRequest, textStatus, errorThrown) {
		console.log(errorThrown);
	}
});
};

function fillMemberListTable(data){
fullMemberListOnPage=null;
var memberList = data._embedded.members;
fullMemberListOnPage = memberList;
$.each(memberList,function(i,obj){
	var objId = retrieveMemberIdOfObject(obj);
	var manipulationColumn   = '<button type="button" class="btn btn-blue btn-sm">'
					  +'<span class="glyphicon glyphicon-edit" aria-hidden="true" onClick="editMember('+objId+');"></span>'
					  +'</button>'
					  +'<button type="button" class="btn btn btn-danger btn-sm">'
					  +'<span class="glyphicon glyphicon-remove" aria-hidden="true" onClick="deleteMember('+objId+');"></span>'
					  +'</button>';				
	$("#memberList").find('tbody')
		.append(
				$('<tr class="clickable-row" id ="'+objId+'">').append(
									$('<td>').append(obj.name)
								)
						 .append(
									$('<td>').append(obj.surname)
								)
						 .append(
									$('<td>').append(obj.email)
								)
						 .append(
									$('<td>').append(obj.number)
								)
						 .append(
									$('<td>').append(obj.activePoints)
								)
						 .append(
									$('<td>').append(obj.burntPoints)
								)
						 .append(
									$('<td align="right">').append(manipulationColumn)
								)
				)
});
};

function deleteMember(objId){
$('#confirmModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#delete').unbind("click").click(function(){
				  $.ajax({
					type:'DELETE',
					url: baseUrl+'/member?id='+objId,
					contentType:'application/json',
					dataType: 'text', 
					success: function(data){
						getAllMembers();
					},
					error: function(XMLHttpRequest, textStatus, errorThrown) {
						console.log(errorThrown);
					}
				});
			});
};

function retrieveMemberIdOfObject(obj){
	return obj._links.self.href.substring((baseUrl+'/members').length+1);
}

function editMember(objId){
$("#memberDetails").empty();
console.log('Edit Modal is opened');
var editObject=null;
for(i=0;i<fullMemberListOnPage.length;i++){
  var searchId = retrieveMemberIdOfObject(fullMemberListOnPage[i]);
  if(searchId==objId){
	editObject=fullMemberListOnPage[i];
	break;
  }
}
$("#memberDetails")
		.append($('<td colspan="2">').append('<form class="form-horizontal">'
								+'		<div class="form-group">'
								+'			<label class="control-label col-sm-2" for="name">Name:</label>'
								+'			<div class="col-sm-10">'
								+'				<input type="name" class="form-control" id="newName" value="'+editObject.name+'">'
								+'			</div>'
								+'		</div>'
								+'		<div class="form-group">'
								+'			<label class="control-label col-sm-2" for="surname">Surname:</label>'
								+'			<div class="col-sm-10">'
								+'				<input type="surname" class="form-control" id="newSurname" value="'+editObject.surname+'">'
								+'			</div>'
								+'		</div>'
								+'		<div class="form-group">'
								+'			<label class="control-label col-sm-2" for="email">Email:</label>'
								+'			<div class="col-sm-10">'
								+'				<input type="email" class="form-control" id="newEmail" value="'+editObject.email+'">'
								+'			</div>'
								+'		</div>'
								+'		<div class="form-group">'
								+'			<label class="control-label col-sm-2" for="phoneNumber">Phone Number:</label>'
								+'			<div class="col-sm-10">'
								+'				<input type="phoneNumber" class="form-control" id="newPhoneNumber" value="'+editObject.number+'">'
								+'			</div>'
								+'		</div>'
								+'</form>'));
				
$('#editModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#save').unbind("click").click(function(){
			    $.ajax({
						type: 'PUT',
						url: baseUrl+'/member/'+objId,
						dataType: 'json',
						contentType:'application/json',
						data:JSON.stringify({
						   "name":$("#newName").val(),
						   "surname":$("#newSurname").val(),
						   "email":$("#newEmail").val(),
						   "number":$("#newPhoneNumber").val(),
						   "activePoints":editObject.activePoints,
						   "blockedPoints":editObject.blockedPoints,
						   "burntPoints":editObject.burntPoints,
						   "cards":editObject.cards
						}),
						success: function(res){
							getAllMembers();
						},
						error: function(XMLHttpRequest, textStatus, errorThrown) {
							console.log(errorThrown);
						}
					});
			});
};
