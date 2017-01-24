/**
 * 
 */

function appelAjax(ref, qte) {
		
		var xhr; 
		xhr = new XMLHttpRequest(); 
		xhr.onreadystatechange = function() {
			if(xhr.readyState == 4) {
				// recuperation balise span 
				var total = document.getElementById("total");
				var qteTot = document.getElementById("qteTot");
				var qteTotPanier = document.getElementById("qteTotPanier");
				
				if(xhr.status == 200) {
					
					var resultObjet = JSON.parse(xhr.responseText);
					//alert(xhr.responseText);
					//var result = resultAvecSeparateur.split(":");
							
					
					// on remplit le corps du span avec ce qui vient du serveur
					total.innerHTML = resultObjet['prixTotal'];
					qteTot.innerHTML = resultObjet['qte'];
					qteTotPanier.innerHTML = resultObjet['qte'];
					
				}  
			}
		};
		
		//var qte = document.getElementById("qte");
		
		xhr.open("GET", "PanierAsync?ref=" + ref + "&qte=" + qte
				+ "&modifier=true", true); 
		
			//	+ document.getElementById('total').value, true); 
		xhr.send(null);
}

function appelAjaxAjouter(ref, qte) {

	var xhr;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			// recuperation balise span 				
			var qteTotPanier = document.getElementById("qteTotPanier");

			if (xhr.status == 200) {
				
				var resultObjet = JSON.parse(xhr.responseText);
				//alert(xhr.responseText);
				//var result = resultAvecSeparateur.split(":");
				//alert(resultObjet);
				// on remplit le corps du span avec ce qui vient du serveur
				
				qteTotPanier.innerHTML = resultObjet.qte;

			}
		}
	};

	//var qte = document.getElementById("qte");

	xhr.open("GET", "PanierAsync?ref=" + ref + "&qte=" + qte
			+ "&ajouter=true", true);

	//	+ document.getElementById('total').value, true); 
	xhr.send(null);
}

