#PhotoAlarm server
Cette application application permet de soumettre via un formulaire des photo sur le Cloud Storage de Google.

### Prérequis
- Java 6 ou supérieur 
- Maven 3.1.0 (Car utilisation App Engine 1.8.3) 

## Description technique de l’application
L’application permet d’envoyer sur un Bucket Cloud Storage spécifique spécifique 
### Récupération d’URL d’envoi 
Pour pouvoir envoyer une donnée au serveur Cloud il faut dans un premier temps interroger l’URL `/generator`qui génère un id unique. 
### Envoyer une photo 
Pour envoyer une photo, il faut passer par un formulaire avec une action post dont l’URL est celle que nous avons générée précédemment. Ce formulaire contient un input nommé `photo`
## Exécution de l'application

### Exécution en local

1. Installer le projet depuis la racine (où se trouve le pom du projet) : `$ mvn clean install`
2. Lancez le projet : `$ mvn clean appengine:devserver`


