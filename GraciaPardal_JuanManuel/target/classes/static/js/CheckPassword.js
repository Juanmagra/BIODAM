$(document).ready(function () {
     $('#txtPassword').keyup(function () {
         $('#strengthMessage').html(checkStrength($('#txtPassword').val()))
     })
     function checkStrength(password) {
         var strength = 0
         if (password.length < 6) {
             $('#strengthMessage').removeClass()
             $('#strengthMessage').addClass('Short')
             return 'Too short'
         }
         if (password.length > 7) strength += 1
         // If password contains both lower and uppercase characters, increase strength value.
         if (password.match(/([a-z].*[A-Z])|([A-Z].*[a-z])/)) strength += 1
         // If it has numbers and characters, increase strength value.
         if (password.match(/([a-zA-Z])/) && password.match(/([0-9])/)) strength += 1
         // If it has one special character, increase strength value.
         if (password.match(/([!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
         // If it has two special characters, increase strength value.
         if (password.match(/(.*[!,%,&,@,#,$,^,*,?,_,~].*[!,%,&,@,#,$,^,*,?,_,~])/)) strength += 1
         // Calculated strength value, we can return messages
         // If value is less than 2
         if (strength < 2) {
             $('#strengthMessage').removeClass()
             $('#strengthMessage').addClass('Weak')
             return 'Weak'
         } else if (strength == 2) {
             $('#strengthMessage').removeClass()
             $('#strengthMessage').addClass('Good')
             return 'Good'
         } else {
             $('#strengthMessage').removeClass()
             $('#strengthMessage').addClass('Strong')
             return 'Strong'
         }
     }

     
 } 
 );

 window.onload = function(){
    document.getElementById("txtPassword").onblur = contra;

}

function validarContra(){
    var contra1 = document.getElementById("txtPassword");
       var contra2 = document.getElementById("txtConfirmPassword");
    var resultado = contra1.value !=="" && contra2.value !=="";
if(resultado){
    if(contra1.value != contra2.value){
     
        resultado=false;
    }else{
    return resultado;
}
}
}