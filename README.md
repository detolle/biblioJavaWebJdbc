# biblioJavaWebJdbc
site web en java sans framework

-----------------------------

prerequis
- [ ] java 11
- [ ] tomcat 9.x
-----------------------------
* installer tomcat 9,  
l'inclure dans eclipse (windows/preference/server/runtime)  
pour tester si ok,  
creer un projet **web/dynamic web project**  

* dans /WebContent, creer la page index.html ou vous mettez hello dans le body
* démarrer le projet avec un clic droit sur le nom du projet dans project explorer, run/run on server
vous devez voir la page créée

## Maven
projet sous maven (totalement intégré dans eclipse)

###### pour importer le projet dans eclipse
* import /maven / existing Maven Project
* choisir le fichier biblioJavaWebJdbc/pom.xml

> le fichier pom.xml est le point d'entrée d'un projet maven
* on y trouve les identifiants du projet et les jar utilisés  
les jar en scope compile ou runtime sont fournis par le serveur, mais sont indiqués pour la compilation  
pour le pilote oracle (ojdbc8.jar), il y a un soucis de sécurité imposé par oracle, et pour simplifier, je l'ai embarqué dans le projet 
* sous lib:
> /biblioJavaWebJdbc/src/main/webapp/WEB-INF/lib  
le dossier lib est le dossier ou traditionnellement on place les jar,  
_mais cette pratique est déconseillée car cela pose des problèmes sur les serveurs de production, où le jar peut déjà exister dans une autre version (exemple: la classe oracle.jdbc.driver.OracleDriver est toujours la même alors que cela peut un une version ojdbc6 ou ojdbc8)_

**_Maven solutionne ce problème car on y indique le niveau de version_**

* PS: on trouve toutes les définitions existantes des jar sur le site:
> https://mvnrepository.com/ 

###### Ce projet a été créé en faisant:
* new project / maven / maven project
* archetype : maven-archetype-webapp
* group-id: com.bibliotheque
* artifact-id: biblioJavaWebJdbc
* package: com.bibliotheque

## WEB APPlication
### Principe
* la navigateur demande une requete (url)
* la requete est traitée par une servlet ou directement un fichier html ou une jsp
* une jsp est un fichier contenant de l'html et du code java appelée "scriplet", à l'intérieur de balises <% code java %>

### Structure d'une web app
* /webapp est la racine du projet web, soit l'url "/" ou l'url complete : http://localhost:8080/biblioJavaWebJdbc/
* /webapp/WEB-INF/ est un dossier interne invible de l'extérieur

### Configuration
* le fichier de configuration est traditionnellement le web.xml, mais optionnel depuis la version 3.0 des servlets
> /biblioJavaWebJdbc/src/main/webapp/WEB-INF/web.xml

### Servlet
* C'est une classe java qui étend HttpServlet, disponible dans un server java
* Une servlet peut effectuer des traitements et renvoyer un contenu via hhttp, ce contenu pourra être du html, du json, du pdf, etc.
ou rediriger la réponse sur une autre ressource comme sur une jsp
* La servlet a des méthodes, et les plus utilisés sont: init, doGet, doPost

* Normalement, chaque URL traitée par une servlet est configurée dans le **web.xml**
* Mais j'ai utilisé les annotations pour un site plus moderne (servlet v3 et sup), on place l'annolation sur la classe servlet
> @WebServlet("/hello")
* ou hello sera l'url demandé http://localhost:8080/biblioJavaWebJdbc/hello

**Attention** : une servlet n'a qu'une seule instance, alors que l'url peut être demandée en même temps
* Mettre impérativement les variables/object à l'intérieur des méthodes
* (ne rien mettre en variable de classes, sauf static ou autres variables partagées)

### JSP
* Une jsp est compilé à la volée pour en faire une servlet (se retrouve donc dans le /webapp/WEB-INF/classes)
* je n'ai pas utilisé de scriplets <% %> mais des technologies plus moderne et toujours utilisé même avec des framework
* **EL**
* **JSTL**
> Ils permettent un lecture plus simple des jsp et c'est recommandé aujourd'hui

* EL permet d'utiliser la notation ${mavar} ou ${maclasse} ou ${maclasse.proprietee}
* JSTL permet en autre, d'utiliser des if conditionnel ou des boucles forEach

* JSTL doit être déclaré en tant que taglib au début de la jsp
> <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
* signifie que le lettre c sera utilisé comme raccourci
* Exemple: <c:forEach>

### les scopes
* dans les webapp, les variables ont 3 niveaux de portées
* page: la variable/classe ne dure que le temps de la page, ou entre la servlet et la jsp
--c'est l'usage standard--
* session: il est possible de mettre dans l'objet session, des variables propres à un utilisateur connecté (via un cookies généré automatiquement)
* application context: les variables sont valables pour toute l'application

### MVC
1 Model=classe entity
2 V=view=jsp
3 C=controller=servlet

* En MVC, l'url est traité par une servlet qui joue le role de controleur
* Ce controleur utilisera des services/DAO/etc et accèdera aux données via jdbc

* Il place dans la requete (HttpServletRequest) les objets nécessaires à la vues 
* C'est un hashmap avec clé+contenu
* request.setAttribute("maclasse", maclasse);

* Il redirige la réponse vers la jsp
> request.getRequestDispatcher("/WEB-INF/majsp.jsp").forward(request, response);	

**--ici, la jsp est placé dans le WEB-INF par sécurité--**
* En effet, le WEB-INF est inaccessible de l'extérieure et un pirate ne pourra pas demandé la ressource 

* Enfin, la jsp présente les données avec ${maclasse.propriete} en EL/JSTL

## l'application biblioJavaWebJdbc
* il y a 3 exemples simples : HelloServlet, PdfServlet et un exemple MVC basique
* Un CRUD complet sur les exemplaires (pas trop compliqué)
* L'emprunt et le retour

__l'emprunt est assez ardu car il faut gérer 3 étapes, et la serlet ne peut utilisé qu'une seule url__







