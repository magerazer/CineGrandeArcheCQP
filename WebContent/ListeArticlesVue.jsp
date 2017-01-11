<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link type="text/css" rel="stylesheet" href="style.css" />
</head>
<body>

<!-- <div class="corps"> -->
	<jsp:include page="/Menu.jsp" />

<div class = "contentListe">

	<c:import url="/RechercheVue.jsp" />



	<%-- <p>la liste des articles : ${listeArticles}</p> --%>
	
<ul>
		<c:forEach var="article" items="${listeArticles}">
			<li>
			<div class="container">
				<div class="elementLi1">
				<%-- 				<td>${article.getClass()}</td> --%>
				<div class="imgLi">
				<a
					href="ListeArticlesControleur?consultation=true&ref=${ article.ref }">
				<img src="${ article.image }" alt="test1"  width="160" /></a>
				
				</div>
				<div class="infoListe">
				<div>
				<span class="nomLi">
				<a
					href="ListeArticlesControleur?consultation=true&ref=${ article.ref }">${article.nom}</a>
				<%-- <c:if test="${ not empty article }">${article.getClass().name}</c:if> --%>
								
				<%-- <td>${article.auteur}</td> --%>
				</span>
				
				<!-- =================si c'est un article materialisé ============== -->
			<c:if test="${ not empty article.mat }">
				<!-- =================si c'est un article livre materialisé ============== -->
				<c:if test="${ article.type == 'Livre' }">
					<span class="dateEtFormat">				
					${ article.format } - ${ article.dateParution }				
					</span>
					</div>					
					<div>				
						Auteur : ${ article.auteur }				
					</div>					
					<div>Etat : ${ article.mat.etat }</div>				
					<div class="prix">
					Prix : ${article.prixHT}€
					</div>				
					Stock : ${article.mat.stock } <br>
					<span class="erreur">${erreurs[article] }</span>
				</c:if>
				
				<!-- =================si c'est un article divers materialisé ============== -->
				<c:if test="${ article.type == 'Divers' }">
					<span class="dateEtFormat">
										
					${ article.typeDivers }
								
					</span>
					</div>	
					
					<div>Etat : ${ article.mat.etat }</div>
					
					<div class="prix">
					Prix : ${article.prixHT}€
					</div>				
					Stock : ${article.mat.stock } <br>
					<span class="erreur">${erreurs[article] }</span>
								
				</c:if>
			</c:if>
				<!-- =================si c'est un article dématerialisé ============== -->
			<c:if test="${ empty article.mat }">
				<!-- =================si c'est un article livre dématerialisé ============== -->
				<c:if test="${ article.type == 'Livre' }">
					<span class="dateEtFormat">				
					${ article.demat.format }				
					- ${ article.dateParution }
								
					</span>
					</div>					
					<div>				
						Auteur : ${ article.auteur }				
					</div>			
					
					<div class="prix">
					Prix : ${article.prixHT}€
					</div>
									
					<div>Format : ${article.demat.format }</div>	
					<div>Url : ${article.demat.url }</div>
				
				</c:if>
				
				<!-- =================si c'est un article divers dématerialisé ============== -->
				<c:if test="${ article.type == 'Divers' }">
					<span class="dateEtFormat">
					${ article.typeDivers }
					</span>
					</div>				
					<div class="prix">
					Prix : ${article.prixHT}€
					</div>								
					<div>Format : ${article.demat.format }</div>	
					<div>Url : ${article.demat.url }</div>			
				</c:if>
				
			</c:if>


				<%-- <span class="dateEtFormat">
				<c:if test="${ article.type == 'Livre' }">
				<c:if test="${ not empty article.mat }">
				${ article.format } 
				</c:if>
				<c:if test="${ empty article.mat }">
				${ article.demat.format }
				</c:if>
				- ${ article.dateParution }
				</c:if>
				<c:if test="${ article.type == 'Divers' }">
				
				${ article.typeDivers }
				
				</c:if>
				</span>
				</div>								
				<c:if test="${ article.type == 'Livre' }">
				<div>				
					Auteur : ${ article.auteur }				
				</div>
				</c:if>
				<c:if test="${not empty article.mat }">
				<div>Etat : ${ article.mat.etat }</div>
				</c:if>
				<div class="prix">
				Prix : ${article.prixHT}€
				</div>
				<c:if test="${not empty article.mat }">
				
				Stock : ${article.mat.stock } <br>
				<span class="erreur">${erreurs[article] }</span>
				</c:if>
				<c:if test="${not empty article.demat }">				
				<div>Format : ${article.demat.format }</div>	
				<div>Url : ${article.demat.url }</div>
				</c:if> --%>
				</div>
			</div>
				<div class="elementLi2">			
									<form action="PanierControleur" method="POST">
						<p>
							<input type="hidden" name="ref" value="${article.ref}" /> 
							<input type="hidden" name="qte" value="1" /> 
							<input type="hidden" name="consulter" value="false" /> 
							<input type="submit" value="Ajouter" name="ajouter" />
						</p>
					</form>
					
				
				</div>
				
				
					<%-- <span class="erreur">${erreurs[article] }</span> --%>
				</div>
			</li>
				
				<br>
			
		</c:forEach>

</ul>
	
<!-- </div> -->
</div>

</body>
</html>