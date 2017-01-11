<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<div class="recherche">
<form action="ListeArticlesControleur" method="POST">
	<p>
		<!-- <input type="text" name="ref" value="" placeholder="Référence"/>  -->
		<input type="text" name="critere" value="" placeholder=""/> 
			
		<input type="submit" value="Rechercher" name="action" />

	</p>
</form>
</div>
