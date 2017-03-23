$(document).ready(function(){
	  console.log('Login page is opened!');
});

function checkLogin(){
	var username = $('#username').val();
	var password = $('#password').val();
	if(username=="admin" && password=="1"){
		self.location = "http://www.example.com";
	}
	else{
		$('#warningModal').modal({
			  backdrop: 'static',
			  keyboard: false,
			  show:true
			}).find('#okButton').unbind("click").click(function(){
				  console.log('Warning Message Logged');
			});
	}
	
}