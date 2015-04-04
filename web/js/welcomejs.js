/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {
    var id = sessionStorage.getItem("id");
    var name = sessionStorage.getItem("name");
    if(id === null){
        window.location.href = "../index.html";
    }
    console.log("Name: "+name);
    $("#name").text(name);
    
});


