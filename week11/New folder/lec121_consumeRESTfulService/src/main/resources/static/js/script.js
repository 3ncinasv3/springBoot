// function getStudent(id) {
//     var textToDisplay=""; // create and append to a blank var
//     textToDisplay += "ID: " + student.id + "<br>";
//     textToDisplay += "Grade: " + student.grade + "<br>";
//     textToDisplay += "Letter Grade: " + student.letterGrade + "<br>";
//     textToDisplay += "Attended: " + student.attended + "<br>";
//     textToDisplay += "Participated: " + student.participated + "<br>";
//
//
//     if (document.getElementById("student"+id).innerHTML=="") {
//
//         document.getElementById("student"+id).innerHTML="hello!";
//         fetch('http://localhost:8080/getStudent/' + id)//use HomeController to fetch
//             .then(student => student.json()) // JSONify the data returned
//             .then(function(student) { // with the JSON data
// // modify textToDisplay below here!
// // finally, change our relevant div to display the var
//                 document.getElementById("student"+id).innerHTML=textToDisplay;
//             });
//     } else {
//         document.getElementById("student"+id).innerHTML="";
//     }
// }
// // fetch('http://localhost:8080/getStudent/' + id)//use HomeController to fetch
// //     .then(student => student.json()) // JSONify the data returned
// //     .then(function(student) { // with the JSON data
// // // modify textToDisplay below here!
// // // finally, change our relevant div to display the var
// //         document.getElementById("student"+id).innerHTML=textToDisplay;
// //     });



// function getStudent(id) {
//     var textToDisplay=""; // create and append to a blank var
//     textToDisplay += "ID: " + student.id + "<br>";
//     textToDisplay += "Grade: " + student.grade + "<br>";
//     textToDisplay += "Letter Grade: " + student.letterGrade + "<br>";
//     textToDisplay += "Attended: " + student.attended + "<br>";
//     textToDisplay += "Participated: " + student.participated + "<br>";
//
//
//     if (document.getElementById("student"+id).innerHTML=="") {
//
//         document.getElementById("student"+id).innerHTML="hello!";
//         fetch('http://localhost:8080/getStudent/' + id)//use HomeController to fetch
//             .then(student => student.json()) // JSONify the data returned
//             .then(function(student) { // with the JSON data
// // modify textToDisplay below here!
// // finally, change our relevant div to display the var
//                 document.getElementById("student"+id).innerHTML=textToDisplay;
//             });
//     } else {
//         document.getElementById("student"+id).innerHTML="";
//     }
// }
// fetch('http://localhost:8080/getStudent/' + id)//use HomeController to fetch
//     .then(student => student.json()) // JSONify the data returned
//     .then(function(student) { // with the JSON data
// // modify textToDisplay below here!
// // finally, change our relevant div to display the var
//         document.getElementById("student"+id).innerHTML=textToDisplay;
//     });

function getStudent(id) {
    var textToDisplay = "";

    // Check if the content is already displayed
    if (document.getElementById("student" + id).innerHTML == "") {
        fetch('http://localhost:8080/getStudent/' + id)
        fetch('http://localhost:8080/api/v1/students/' + id)
            .then(response => response.json())
            .then(function (student) {
                // Update textToDisplay with the fetched student data
                textToDisplay += "ID: " + student.id + "<br>";
                textToDisplay += "Grade: " + student.grade + "<br>";
                textToDisplay += "Letter Grade: " + student.letterGrade + "<br>";
                textToDisplay += "Attended: " + student.attended + "<br>";
                textToDisplay += "Participated: " + student.participated + "<br>";

                // Display the formatted data in the relevant div
                document.getElementById("student" + id).innerHTML = textToDisplay;
            })
            .catch(function (error) {
                console.error('Error fetching student data:', error);
            });
    } else {
        // If content is already displayed, hide it
        document.getElementById("student" + id).innerHTML = "";
    }
}
// function getStudent(id) {
//     // var textToDisplay = "";
//
//     // Check if the content is already displayed
//     if (document.getElementById("student" + id).innerHTML == "") {
//         fetch('http://localhost:8080/getStudent/' + id)
//             .then(response => response.json())
//             .then(function (student) {
//                 // Update textToDisplay with the fetched student data
//                 var textToDisplay = "";
//                 textToDisplay += "ID: " + student.id + "<br>";
//                 textToDisplay += "Grade: " + student.grade + "<br>";
//                 textToDisplay += "Letter Grade: " + student.letterGrade + "<br>";
//                 textToDisplay += "Attended: " + student.attended + "<br>";
//                 textToDisplay += "Participated: " + student.participated + "<br>";
//
//                 // Display the formatted data in the relevant div
//                 document.getElementById("student" + id).innerHTML = textToDisplay;
//             })
//             .catch(function (error) {
//                 console.error('Error fetching student data:', error);
//             });
//     } else {
//         // If content is already displayed, hide it
//         document.getElementById("student" + id).innerHTML = "";
//     }
// }
