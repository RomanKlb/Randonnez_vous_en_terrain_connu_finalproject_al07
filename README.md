# Randonnez_vous_en_terrain_connu_finalproject_al07

Présentation du projet : 
Ce site web (frontend et backend) a été developpé dans le cadre d'un projet de plus d'un mois, après 10 mois de formation intensive au sein du centre de formation ISIKA.

Le but était de concevoir, développer et déployer (en ayant une intégration continue local, pré-production, production) l'architecture d'un projet au choix avec des obligations telles que :
Récupérer des données d'une API externe et permettre de les collecter une ou plusieurs fois par jours
Avoir une architecture sous forme de microservices
Avoir une gestion d'utilisateur avec mot de passe crypté en base de données
Utiliser une base de donnée relationnelle et non relationnelle
Une intégration continue
Une gestion des versions
...

Architecture du projet :
Back-end
2 microservices Java 11 / spring
2 microservices NodeJS
1 API externe (Open Weather Map)

Front-end
Angular v11

-----------------------
Comment ça fonctionne
-----
Scheduler :
- créer un compte pour avoir accès à l'API Open Weather Map : demander une clef API 
- remplacer dans le fichier app/services/weather.service.js => line 19 => '&appid=VOTRE_API_ID&units=metric&lang=fr'par VOTRE_API_ID (exemple type : ae6789esfdf5678)

-----
MS NodeJS : installation des packages => npm install
MS java : installation maven => mvn install
angular : npm install
