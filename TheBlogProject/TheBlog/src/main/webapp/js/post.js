/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



/* global tinymce */

$(document).ready(function (){
     $('#saveBtn').click(function (event){
         tinymce.triggerSave();
     });
});