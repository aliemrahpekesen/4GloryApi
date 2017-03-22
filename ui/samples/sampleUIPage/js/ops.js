var baseUrl = 'http://localhost:8080';

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
			   "cards":[
					{
						"number":"123654789"
					}
			   ]
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
var memberList = data._embedded.members;
$.each(memberList,function(i,obj){
	var objId = retrieveMemberIdOfObject(obj);
	var manipulationColumn   = '<button type="button" class="btn btn-blue btn-sm">'
					  +'<span class="glyphicon glyphicon-edit" aria-hidden="true"></span>'
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
									$('<td align="right">').append(manipulationColumn)
								)
				)
});
};

function deleteMember(id){
$('#confirmModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).one('click', '#delete', function(e) {
			     $.ajax({
					type:'DELETE',
					url: baseUrl+'/member?id='+id,
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

function updateMember(id){
$('#updateModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).one('click', '#sa', function(e) {
			     $.ajax({
					type:'DELETE',
					url: baseUrl+'/member?id='+id,
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
}
