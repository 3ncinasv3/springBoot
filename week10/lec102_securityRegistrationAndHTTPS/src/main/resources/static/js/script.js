function verify() {
    var password1 = document.forms['form']['password'].value;
    var password2 = document.forms['form']['verifyPassword'].value;
    var username = document.forms['form']['username'].value;
    if (password1.length < 7 || password1 == null || password1 == "" || password1 != password2) {
        document.getElementById("error").innerHTML = "Please check your passwords";
        return false;
    }
    if (username.length < 10) {
        document.getElementById("error").innerHTML = "Please check your email length";
        return false;
    }

}