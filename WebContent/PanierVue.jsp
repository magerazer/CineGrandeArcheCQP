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

	<c:import url="/Menu.jsp" />
<div class = "contentListe">
<span class="titre"><h1>Votre panier</h1></span>
	<%-- <p>panier : ${ lignes }</p> --%>


	<div class="listePanier">

		<ul>
		<!-- <div class="tabPanier"> -->
		<!-- 	<li class="liPanier">
				
					<div class="imagePanier"><img src="" width="50px" /></div>
					<div class="infosPanier"></div>
					<div class="prixPanier">Prix</div>
					<div class="qtePanier">Quantite</div>
					<div class="prixLignePanier">Total Article</div>
						
			</li> -->
				<!-- </div> -->
			<c:forEach var="lignePanier" items="${panier.iterator()}">
				 <div class="tabPanier"> 
				<li class="liPanier">
					
						<div class="imagePanier">
							<img src="${ lignePanier.article.image }" width="50px" />
						</div>
						<div class="infosPanier">

							<h2>${lignePanier.article.nom}</h2>

							<p class="prixPanier">Prix :  ${lignePanier.article.prixHT} €</p>

							<p class="prixLignePanier">
						Total article: ${ lignePanier.prixTot } €
						</p>
						<c:if test="${not empty lignePanier.article.mat }">
						<p>
						Stock : ${lignePanier.article.mat.stock }
						</p>
						</c:if>
						</div>
						<%-- <div class="prixPanier">Prix :  ${lignePanier.article.prixHT} €</div> --%>

						<div class="qtePanier">
							<form action="PanierControleur" method="POST">
								<input type="hidden" name="ref"
									value="${lignePanier.article.ref}" /> <input type="number"
									min="0" name="qte" value="${ lignePanier.quantite }" /> <input
									type="submit" value="Modifier quantité" name="modifierPanier" />									
									<input type="submit" value="Delete" name="delete" /> <br>
									<span class="erreur">${erreurs[lignePanier.article] }</span>
									<span class="erreur">${erreurStock[lignePanier.article] }</span>
							</form>

					<%-- 	<span class="erreur">${erreurs[article] }</span> --%>


						</div>
						<%-- <div>
						<form action="PanierControleur" method="POST">
								<input type="hidden" name="ref"
									value="${lignePanier.article.ref}" /> <input type="submit"
									value="Delete" name="delete" />
							</form>
						</div> --%>
						
						
					
				</li>
			</div> 
			</c:forEach>
		</ul>
	</div>

<div class="conteneurBoutonCommande">
	<div class="erreurCommande">
	
	<em>Sous total ( ${ panier.quantite }) </em>
	
	<div>Montant : <span class="prixPanier"> ${ panier.prixTotal } €</span></div>

	<form action="CommandeControleur" method="POST">
		<input type="submit" value="Commander" name="commander" />
		
	</form>
	<div><span class="erreur">${ nonConnecte }</span></div>
	</div>
</div>


</div>
<!-- 
<div class="column-layout">
  <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Velit quia, quam delectus sequi odio fugit pariatur architecto, deleniti exercitationem quae adipisci ipsum ullam officia ea!</div>
  <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Consectetur adipisci, quod voluptates, vel nostrum ea distinctio, a tempore voluptas reiciendis doloremque veniam, accusantium. Veritatis ipsum modi doloribus enim sint magnam, delectus dolorum, aperiam! Eaque dolor, veniam eum sunt dolorem nostrum. Dolores, corporis! Magnam, amet quas? Voluptates eaque quaerat quia inventore, earum eveniet hic alias rem atque neque ab, reprehenderit dolore culpa, blanditiis! Maiores enim rerum architecto voluptatibus cumque. Debitis accusamus ea aut incidunt voluptatibus quibusdam rem sint laboriosam qui sit odio expedita illum iste consectetur fugit eaque commodi pariatur, porro ex delectus officia ratione necessitatibus totam. Necessitatibus est autem et.</div>
  <div>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Atque velit dolores ipsa maiores molestias minus iusto nulla consequatur saepe. Aspernatur nulla optio laborum ducimus dignissimos nisi itaque aliquam doloremque, iure qui. Corporis quas atque minus, eveniet delectus officiis dolor, neque, animi, deleniti adipisci esse! Ullam nesciunt, nemo fugit totam itaque.</div>
</div>
 -->

	<%-- <div>${ panier.prixTotal } €</div>

	<form action="CommandeControleur" method="POST">
		<input type="submit" value="Commander" name="commander" />
	</form>
 --%>

</body>
</html>