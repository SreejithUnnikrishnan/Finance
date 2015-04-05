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
        var url = '../webresources/income/' + id;
            
            $.getJSON(url, function (data) {
                if(data.length > 0){
                    var outHtml = "";
                        for (var i = 0; i < data.length; i++){
                            outHtml += "<div id=\"data\"><p>" + data[i].name +"</p>"
                                    + "<p>" + data[i].budget + "</p>"
                                    + "<p>" + data[i].start_date + "</p>"
                                    +  "<p>"+data[i].amount+"</p></div>";
                        }
                       $('#income_data').html(outHtml);     
                }
                else{
                    $("#error").text("No Income Details found!! Please add to continue...");
                }
            });
    }
    
    $("#addIncome").click(function(){
        window.location.href = "./addIncome.html";
    });

});
