/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
$(document).ready(function () {
    var id = sessionStorage.getItem("id");
    var name = sessionStorage.getItem("name");
    if (id === null) {
        window.location.href = "../index.html";
    }
    else {
        $("#name").text(name);
        var url = '../webresources/report/' + id;

        $.getJSON(url, function (data) {
            if (data.length > 0) {
                var incomeName = "";
                var incomeBud = "";
                var incomeAmt = "";
                var expenseName = "";
                var expenseBud = "";
                var expenseAmt = "";
                var outHtml = "";
                for (var i = 0; i < data.length; i++) {
                    outHtml += "<div id=\"data\"><p>" + data[i].name + "</p>"
                            + "<p>" + data[i].amount + "</p>"
                            + "<p>" + data[i].budget + "</p></div>";
                    if (data[i].name === "income") {
                        incomeName = data[i].name;
                        incomeBud = data[i].budget;
                        incomeAmt = data[i].amount;
                        
                    }
                    else {
                        expenseName = data[i].name;
                        expenseBud = data[i].budget;
                        expenseAmt = data[i].amount;
                       
                    }
                     var chart = AmCharts.makeChart("chartdiv", {
                        "type": "pie",
                        "theme": "none",
                        "dataProvider": [{
                                "title": "Income",
                                "value": incomeAmt
                            }, {
                                "title": "Expense",
                                "value": expenseAmt
                            }],
                        "titleField": "title",
                        "valueField": "value",
                        "labelRadius": 5,
                        "radius": "30%",
                        "innerRadius": "60%",
                        "labelText": "[[title]]"
                    });
                    
                }
                $('#report_data').html(outHtml);
            }
            else {
                $("#error").text("No Income or Expense Details found for this month!! Please add to continue...");
            }
        });
    }


});


