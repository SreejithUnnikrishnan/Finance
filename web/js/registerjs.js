/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {

    $('#register').click(function () {
//        var name = ('#name').val();
//        var pwd = ('#password').val();
//        var qst = ('#question').val();
//        var ans = ('#answer').val();
        if ($('#name').val() === "" || $('#password').val() === "") {
            $('#errorMsg').text("Name and Password is required!!!");
        }
        else if ($('#password').val().length < 5) {
            $('#errorMsg').text("Length for password must be five or more!!!");
        }
        else {
//            var url = '../webresources/user/';
//            var data = JSON.stringify({
//                name: $('#name').val(),
//                password: $('#password').val(),
//                question : $('#question').val(),
//                answer: $('#answer').val()
//            });
//            $.post(url, function (data) {
//                if (data === "1") {
//                    console.log("succcess");
//                }
//                else {
//                    console.log("Error");
//                }
//            });
            var arr = {name: $('#name').val(), password: $('#password').val(), question: $('#question').val(), answer: $('#answer').val()};
            $.ajax({
                url: '../webresources/user/',
                type: 'POST',
                data: JSON.stringify(arr),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    if (data === "0") {
                        console.log("inside if");
                        $('#errorMsg').text("Sorry!!! something went wrong. Try again later!");
                    }
                    else {
                        //console.log("inside else");
                        sessionStorage.setItem("id", data);
                        sessionStorage.setItem("name", $('#name').val());
                        alert("Please make note of User Id: " + data);
                        window.location.href = "./welcome.html";
                    }
                }
            });
        }
    });
});


