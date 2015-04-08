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
       // console.log("Name: " + name);
        $("#sname").text(name);
        $("#income").click(function(){
             window.location.href = "./income.html";
        });
        $("#expense").click(function(){
             window.location.href = "./expense.html";
        });
        $("#report").click(function(){
             window.location.href = "./report.html";
        });
    }
});


