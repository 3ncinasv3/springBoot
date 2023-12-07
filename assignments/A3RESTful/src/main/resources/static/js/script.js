function verify() {
    var password1 = document.forms['form']['password'].value;
    var password2 = document.forms['form']['verifyPassword'].value;
    var email = document.forms['form']['email'].value;

    // Password matching check
    if (password1 === null || password1 === "" || password1 !== password2) {
        document.getElementById("error").innerHTML = "Please check your passwords";
        return false;
    }

    // Email length and format check
    if (email.length < 10 || !isValidEmail(email)) {
        document.getElementById("error").innerHTML = "Please provide a valid email (10 characters or longer)";
        return false;
    }
}

// Function to validate email format
function isValidEmail(email) {
    // Basic email format check using regular expression
    var emailPattern = /\S+@\S+\.\S+/;
    return emailPattern.test(email);
}
