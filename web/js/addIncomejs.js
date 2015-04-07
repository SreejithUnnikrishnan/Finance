/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var catUrl = '../webresources/category/Income';
    $.getJSON(catUrl, function (data) {

        if (data.length > 0) {
            var output = "<select id=\"categoryOption\" class=\"input-group\">";

            for (var i = 0; i < data.length; i++)
                output += "<option value=" + data[i].name + ">" + data[i].name + "</option>";
        }
        output += "</select>";
        $('#name').html(output);

    });



    $('#add').click(function () {


        if ($('#budget').val() === "" || $('#datepicker').val() === "" || $('#amount').val() === "") {
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
            var arr = {name: $('#categoryOption').val(), budget: $('#budget').val(), start_date: $('#datepicker').val(), amount: $('#amount').val(), user_id: id};
            $.ajax({
                url: '../webresources/income/',
                type: 'POST',
                data: JSON.stringify(arr),
                contentType: 'application/json; charset=utf-8',
                dataType: 'json',
                success: function (data) {
                   
                    if (data === "0") {

                        $('#errorMsg').text("Sorry!!! something went wrong. Try again later!");
                    }
                    else {

                        window.location.href = "./income.html";
                    }
                }
            });
        }
    });
});


