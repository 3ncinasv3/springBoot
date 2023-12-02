function verify() {
  var password1 = document.forms["form"]["password"].value;
  var password2 = document.forms["form"]["verifyPassword"].value;
  if (password1 == null || password1 == "" || password1 != password2) {
    document.getElementById("error").innerHTML = "Please check your passwords";
    return false;
  }
}


// document.addEventListener('DOMContentLoaded', function() {

//   console.log('Script is running');
//   console.log('DOM content loaded');
//   var addToCartForm = document.querySelector('.hidden-form');

//   addToCartForm.addEventListener('submit', function(event) {
//     event.preventDefault();
//     alert('Book is added to the cart');
//     // Uncomment the following line if you want to submit the form after showing the message
//     addToCartForm.submit();
//   });
// });