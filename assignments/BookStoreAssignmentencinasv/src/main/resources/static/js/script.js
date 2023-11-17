function verify() {
    var password1 = document.forms['form']['password'].value;
    var password2 = document.forms['form']['verifyPassword'].value;
    var username = document.forms['form']['username'].value;

    if (password1 == null || password1 == "" || password1 != password2) {
        return false;
    }

    if (username.length < 10) {
        // document.getElementById();
        return false;
    }
}