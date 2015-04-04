/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function () {

    $('#login_submit').click(function () {

        var user = $('#user').val();
        var pwd = $('#pwd').val();
        
        console.log(user + ' ' + pwd);
        var url = './webresources/user/' + user + '/' + pwd;
        console.log(url);
        $.getJSON(url, function (data) {
            console.log('data:'+jQuery.isEmptyObject(data));
            if (!jQuery.isEmptyObject(data)) {
                console.log('id: ' + data.id + ' name: ' + data.name);
            }
            else{
                $("#errorMsg").text("Error in login");
                console.log('Error in login');
            }
        });

    });

});
