<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="style.css" />
<link type="text/css" rel="stylesheet" href="styleConsultation.css" />
</head>
<body>


<!-- <div class="corps"> -->
	<jsp:include page="/Menu.jsp" />

<div class = "contentListe">


<span class="titre"><h1>Vos commandes</h1></span>

<div class="historiqueCentre">
	<div class="blocCommande">
		<div class="enteteBlocCommande">
			<div class="texteCentreEntete">
			<div>
			Commande effectuée le : <br>${ commande.date }
			</div>
			<div>Total : <br>${ tot } €</div>
			<div>
			Livraison à : ${ commande.adrLiv }
			</div>
			</div>
			
		</div>
		<div class="corpsBlocCommande">
			<div class="texteCentre">
			<div class="listePanier">

		<ul>		
						
		
			<c:forEach var="ligneCommande" items="${commande.iterator()}">
				 <div class="tabPanier"> 
				<li class="liCde">
					
						<div class="imageCde">
							<img src="${ ligneCommande.img }" width="50px" />
						</div>
						<div class="infosPanier">

							<h2>${ligneCommande.nom}</h2>

							<p class="prixPanier">Prix :  ${ligneCommande.prixHT} €</p>
							<p class="prixLignePanier">	Qte : ${ ligneCommande.qte } </p>
							<p class="prixLignePanier">
						Total article: ${ ligneCommande.prixHT * ligneCommande.qte } €
						</p>
						
						</div>
												
					
				</li>
			</div> 
			</c:forEach>
		</ul>
	</div>
			</div>
		</div>	
	</div>
</div>




</div>


</body>
</html>