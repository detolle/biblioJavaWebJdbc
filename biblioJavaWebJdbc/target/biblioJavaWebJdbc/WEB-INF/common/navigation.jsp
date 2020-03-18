<body class="bg-light">
<nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
  <a class="navbar-brand" href="#">Bibliothèque</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  
	<div class="collapse navbar-collapse" id="navbarsExampleDefault">
		<ul class="navbar-nav mr-auto">
			<li class="nav-item active">
				<a class="nav-link" href="<%=request.getContextPath()%>" nClick="window.location.reload();">Home<span class="sr-only">(current)</span></a>
			</li>

			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="dropdown01"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Gestion</a>
				<div class="dropdown-menu" aria-labelledby="dropdown01">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/emprunt" >Emprunt</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/retour">Retour</a>
				</div></li>

			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="dropdown02"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Données de Base</a>
				<div class="dropdown-menu" aria-labelledby="dropdown02">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/exemplaire" >Exemplaire</a>
				</div></li>
				
			<li class="nav-item dropdown"><a class="nav-link dropdown-toggle" href="#" id="dropdown03"
				data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Exemple</a>
				<div class="dropdown-menu" aria-labelledby="dropdown03">
					<a class="dropdown-item" href="<%=request.getContextPath()%>/hello" >hello Servlet</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/editeur" >Exemple MVC</a>
					<a class="dropdown-item" href="<%=request.getContextPath()%>/pdf" >Pdf Servlet (à lancer dans un navigateur)</a>
				</div></li>
				
		</ul>
	</div>
</nav>
<!--div>&nbsp;</div-->
<br/><br/><br/>