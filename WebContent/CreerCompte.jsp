<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="style.css" />
<link type="text/css" rel="stylesheet" href="styleConsultation.css" />
<!-- <script type="text/javascript" src="regles.js"></script> -->
</head>
<body>
<div class = "contentListe">

<jsp:include page="/Menu.jsp" />

	<h1>Créer un compte</h1>
	
	
	<c:if test="${ !consultationCompte && !validationConfirm}">
			<form id="myForm" action="CompteControleur" method="post">
			<fieldset class="compte">
					<label for="nom">Nom : </label> 
					<input type="text" name="nom" value="${ param.nom }"/><span class="tooltip">Le nom doit contenir au moins 2 caractères</span>
					<span class="erreur">${erreursCompte['nom']}</span><br><br>
					<!-- <span id="tooltip">Le nom doit contenir au moins 2 caractères</span><br><br> -->	
									
					<label for="prenom">Prénom  : </label> 
					<input type="text" name="prenom" value="${ param.prenom }"/> <span class="tooltip">Le prénom doit contenir au moins 2 caractères</span>
					<span class="erreur">${erreursCompte['prenom']}</span><br><br>
				<!-- 	<span class="tooltip">Le prénom doit contenir au moins 2 caractères</span><br><br> -->	
					
					<label for="email">Adresse email : </label>
					<input type="email" name="email" value="${ param.email }"/> 
					<span class="erreur">${erreursCompte['mail']}</span> <br><br>
					
					<label for="pwd">Mot de passe : </label> 
					<input type="password" name="pwd" value="${ param.pwd }"/> <span class="tooltip">Le mot de passe ne doit pas faire moins de 8 caractères et doit contenir au moins un chiffre et une lettre</span>
					<span class="erreur">${erreursCompte['pwd']}</span><br><br>
					<!-- <span class="tooltip">Le mot de passe ne doit pas faire moins de 8 caractères et doit contenir au moins un chiffre et une lettre</span><br><br> -->					
					
					<label for="pwdConfirm">Confirmez le mot de passe : </label>
					<input type="password" name="pwdConfirm" value="${ param.pwdConfirm }"/>
					<span class="erreur">${erreursCompte['pwdConfirm']}</span> <br> <br>
					
					<label for="adrLiv">Adresse de livraison : </label>
					<textarea rows="2" cols="50" name="adrLiv" value="${ param.adrLiv }"></textarea>  
					<span class="erreur">${erreursCompte['adrLiv']}</span> <br><br>
					<!-- <span class="tooltip">L'adresse doit contenir au moins 2 caractères</span><br><br>	 -->
					
					<label for="adrFact">Adresse de facturation </label> 
					<textarea rows="2" cols="50" name="adrFact" value="${ param.adrFact }"></textarea>	
					<span class="erreur">${erreursCompte['adrFact']}</span> <br><br>
					<!-- <span class="tooltip">L'adresse doit contenir au moins 2 caractères</span><br><br>	 -->
													
					<c:if test="${ !modifier }">
						<input type=submit value="Valider le compte" name="validerCompte" />
					</c:if>
					<c:if test="${ modifier }">
						<input type=submit value="Valider modifications" name="modifier" />
					</c:if>
					
					<span class="erreur">${erreursCompte['formNonRempli']}</span> <br>
				 </fieldset>
					</form>
	</c:if>
	<c:if test="${ consultationCompte && !validationConfirm}">
	<fieldset class="compte">
		<form action="CompteControleur" method="post">
					
		
					<label>Nom : ${ compte.nom } </label><br>									
									
					<label>Prénom : ${ compte.prenom } </label> <br>
					
					<label>Adresse email : ${ compte.mail } </label> <br>
										
					<label>Adresse de livraison : ${ compte.adrLiv }</label> <br>
					
					<label>Adresse de facturation : ${ compte.adrFact }</label> <br>
						
					<input type=submit value="Modifier votre compte" name="modifierCompte" />							
					
	</fieldset>
	</c:if>
	<c:if test="${ validationConfirm }">
	<fieldset class="compte">
		<h2>Votre compte a été crée avec succès.</h2><br><br>
					<label>Nom : ${ confirmCompte.nom } </label><br>									
									
					<label>Prénom : ${ confirmCompte.prenom } </label> <br>
					
					<label>Adresse email : ${ confirmCompte.mail } </label> <br>
										
					<label>Adresse de livraison : ${ confirmCompte.adrLiv }</label> <br>
					
					<label>Adresse de facturation : ${ confirmCompte.adrFact }</label> <br>
													
					
					
		</form>
	</fieldset>
	</c:if>

</div>
</body>
</html>