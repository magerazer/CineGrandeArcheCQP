<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<header>

	<div id="conteneurMenu">
		<div class="logo">
			<a href="ListeArticlesControleur"> <img src="images/CineMag.png"
				alt="CineMag">
			</a>
		</div>
		<!-- Connection de l'utilisateur -->
		<c:if test="${ empty sessionScope.compte }">
			<div class="connexionMenu">
				<form action="CompteControleur" method="POST">
					<p>
					<span class="erreur">${ loginErreur }</span><br>
						<input type="text" name="mail" value="${ param.mail }" placeholder="mail" /><br> 
						 <input type="password" name="pwd" value="" placeholder="mot de passe" /><br>  
						 <input type="submit" value="Se connecter" name="connexion" />
					</p>
				</form>
			</div>
			<div class="creeMenu">
				<form action="CompteControleur" method="POST">
					<p>
						<input type="submit" value="Créer un compte" name="creationCompte" />
					</p>
				</form>
			</div>
		</c:if>
		<!-- Si l'utilisateur est déjà connecté -->
		<c:if test="${ not empty sessionScope.compte }">
			<div class="msgConnecte">
				<span class="connecteMsg">${sessionScope.compte.mail} est
					connecté</span> 
				<form action="CompteControleur" method="POST">
					<p>
						<input type="submit" value="Se déconnecter" name="deconnexion" />
						<input type="submit" value="Consulter votre compte" name="consulterCompte" />
					</p>
				</form>
			</div>
		</c:if>
		<!-- accéder au panier -->
		<div class="panierMenu">
			<p>
				<a href="PanierControleur" class="panierStyle">Panier : ${ panier.quantite }</a>
			</p>
		</div>

	</div>
</header>