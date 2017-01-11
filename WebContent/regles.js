

// Fonction de désactivation de l'affichage des "tooltips"
function deactivateTooltips() {
	
//	alert('ji');
    var tooltips = document.querySelectorAll('.contentListe');
  //  alert('iJK' + tooltips.length + '' + document);
    var   tooltipsLength = tooltips.length;
   
    for (var i = 0; i < tooltipsLength; i++) {
        tooltips[i].style.display = 'none';
       
    }

}
(function() { // Utilisation d'une IIFE pour éviter les variables globales.
var myForm = document.getElementById('myForm');

myForm.addEventListener('submit', function() {
	alert("oops");

    deactivateTooltips();

});


});
deactivateTooltips();