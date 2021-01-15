/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $('#tag-btn').on('click', function(){
        var name = $('#tag').val();
        var badge = '<span class="badge badge-pill badge-secondary"> #' + name + '</span>';
        $('#tagDiv').append(badge);
    });
});

s