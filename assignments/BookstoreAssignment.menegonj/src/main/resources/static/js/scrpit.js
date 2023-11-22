function verify() {
var password1 = document.forms['form']['password'].value;
var password2 = document.forms['form']['verifyPassword'].value;
var email = document.forms['form']['username'].value;
if (password1 == null || password1 == "" || password1 != password2) {
document.getElementById("error").innerHTML = "Please check your passwords";
return false;
}

if(email.length < 10){
	document.getElementById("error").innerHTML = "Email must be 10 characters or longer";
	return false;
}
}