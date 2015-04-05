/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    

    $('#add').click(function () {

        if ($('#name').val() === "" || $('#budget').val() === "" || $('#datepicker').val() === "" || $('#amount').val() === "") {
            $('#errorMsg').text("Fields Marked * is required!!!");
        }
        else if (!(jQuery.isNumeric($('#budget').val()))) {
            $('#errorMsg').text("Budget should be Numeric value!!!");
        }
        else if (!(jQuery.isNumeric($('#amount').val()))) {
            $('#errorMsg').text("Amount should be Numeric value!!!");
        }
        else {
            
            var id = sessionStorage.getItem("id");
            var arr = {name: $('#name').val(), budget: $('#budget').val(), start_date: $('#datepicker').val(), amount: $('#amount').val(), user_id: id};
            $.ajax({
                url: '../webresources/income/',
                type: 'POST',
                data: JSON.stringify(arr),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                    alert("inside");
                    if (data === "fail") {
                        console.log("inside if");
                        $('#errorMsg').text("Sorry!!! something went wrong. Try again later!");
                    }
                    else {
                        console.log("inside else");
                        window.location.href = "./income.html";
                    }
                }
            });
        }
    });
});


