/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function() {
    var id = sessionStorage.getItem("id");
    var name = sessionStorage.getItem("name");
    if (id === null) {
        window.location.href = "../index.html";
    }
    else {

        $("#sname").text(name);
        var url = '../webresources/expense/' + id;

        $.getJSON(url, function(data) {
            if (data.length > 0) {
                var outHtml = "<table class=\"table table-striped\"><thead><tr><th>Category</th><th>Budget</th><th>Amount</th></tr></thead><tbody>";
                for (var i = 0; i < data.length; i++) {
                    outHtml += "<tr><div id=\"data\"><td>" + data[i].name + "</td>"
                            + "<td>" + data[i].budget + "</td>"
                            + "<td>" + data[i].amount + "</td></div></tr>";
                }
                outHtml += "</tbody></table>";
                $('#income_data').html(outHtml);
            }
            else {
                $("#error").text("No expense Details found!! Please add to continue...");
            }
        });
    }

    $("#logout").click(function() {
        sessionStorage.setItem("id", null);
        sessionStorage.setItem("name", null);
        window.location.href = "../index.html";
    });

    $("#addExpense").click(function() {
        window.location.href = "./addExpense.html";
    });

});
