# biblioJavaWebJdbc
site web en java sans framework

================================================================================
prerequis
java 11
tomcat 9.x


Maven
================================================================================
projet sous maven (totalement intégré dans eclipse)

pour importer le projet dans eclipse
-----------------------------
- import /maven / existing Maven Project
choisir le fichier biblioJavaWebJdbc/pom.xml
-----------------------------

le fichier pom.xml est le point d'entrée d'un projet maven
on y trouve les identifiants du projet et les jar utilisés
-les jar en scope compile ou runtime sont fournis par le serveur, mais sont indiqués pour la compilation
-pour le pilote oracle (ojdbc8.jar), il y a un soucis de sécurité imposé par oracle, et pour simplifier, je l'ai embarqué dans le projet sous lib:
/biblioJavaWebJdbc/src/main/webapp/WEB-INF/lib
-le dossier lib est le dossier ou traditionnellement on place les jar,
mais cette pratique est déconseillée car cela pose des problèmes sur les serveurs de production, où le jar peut déjà exister dans une autre version (exemple: la classe oracle.jdbc.driver.OracleDriver est toujours la même alors que cela peut un une version ojdbc6 ou ojdbc8)
Maven solutionne ce problème car on y indique le niveau de version
PS: on trouve toute les définitions existantes des jar sur le site:
https://mvnrepository.com/ 

-----------------------------
Ce projet a été créé en faisant:
-new project / maven / maven project
archetype : maven-archetype-webapp
group-id: com.bibliotheque
artifact-id: biblioJavaWebJdbc
package: com.bibliotheque
-----------------------------
