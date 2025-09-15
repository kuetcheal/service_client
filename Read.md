### Pourquoi des DTO et un Mapper ?

- **DTO** (Data Transfer Object) : objets d’échange exposés par l’API. Ils sont différents des entités JPA.
  - Sécurisent le contrat d’API (on ne renvoie que ce qui est nécessaire).
  - Permettent la **validation** des entrées (`spring-boot-starter-validation`).
  - Découplent l’API de la couche persistence.

- **Mapper** : convertit DTO ⇄ Entité au même endroit.
  - Simplifie les contrôleurs/services.
  - Évite la duplication et facilite les tests.



###  pourquoi utiliser CORS dans notre Microservice
# Dans ce microservice, on a activé une configuration CORS personnalisée dans SecurityConfig.java, avec les règles suivantes 
Origines autorisées : http://localhost:3000 (Nuxt.js en dev)
* Méthodes autorisées : GET, POST, PUT, DELETE, OPTIONS
* En-têtes acceptés : Authorization, Content-Type, etc.
* Stateless : aucune session, tout passe par le token JWT.

Cela garantit que seul un client front-end connu peut communiquer avec l’API, tout en respectant les standards de sécurité modernes.




### Message Broker avec RabbitMQ
- Objectif : un seul broker RabbitMQ partagé pour tes 3 micro-services (service_client, service_produit, service_commande) qui se publient/consomment des messages entre eux via un exchange unique et des routing keys propres.
  * Crée un réseau Docker externe commun 
  * un seul RabbitMQ tourne tourne sur service_client et les deux autres services s'y connectent via le réseau Docker externe ( le réseau ms-net)





### Docker : conteneurisation et construction de nos images :
* Compiler le jar   :     .\mvnw.cmd clean package -DskipTests
* Construire l’image Docker  :   docker compose build
* Lancer le conteneur :  docker compose -up d




### tests unitaires avec Junit 5 et Mockito
  * Rôle du test unitaire (ex: ClientServiceImplTest)  via @Mock, @InjectMocks
  Le test unitaire sert à vérifier que chaque composant (classe/méthode) fonctionne correctement de manière isolée, sans dépendre du reste de l’application.

 * ici les tests unitaires seront éffecutés sur les classes telles ques : 
- Les services (classes de logique métier)
- Les contrôleurs (API endpoints)
- les mappers (DTO ↔ Entity) qui assurent la conversion entre les objets exposés par l’API (DTO) et les entités persistées (Entity). Un défaut de mapping provoque des bugs silencieux (champs perdus, mauvais formats, null inattendus).
- les DTO pour vérifier que les contraintes @NotBlank, @Size, etc. sur ClientDto fonctionnent (rejets des valeurs invalides, DTO valide accepté).
- le Repository avec @DataJpaTest pour vérifier la persistance de base (save, findById, findAll) et les colonnes gérées par l’entité (ex. createdAt via @PrePersist).



### SonarQube analyse statiquement ton code et te donne un “bilan de santé” sur :
Fiabilité : bugs potentiels (NPE, erreurs de logique…)
Sécurité : vulnérabilités + Security Hotspots à revoir
Maintenabilité : code smells (complexité, duplications, conventions…)
Couverture de tests (via le plugin JaCoCo pour Java)
Duplications et dettes techniques



### Continuous Integration (CI) avec GitHub Actions + GHCR (GitHub Container Registry)TL;DR
- À chaque push sur develop, GitHub Actions build l’application, exécute les tests, construit l’image Docker et la pousse sur GHCR sous le nom ghcr.io/<owner>/<repo>:<tag>.
En local (ou sur un serveur), on tire l’image dans docker-compose avec TAG=develop (ou une version), puis docker compose up -d
Rendre l’image publique (facultatif mais pratique).

- Après le premier push réussi :
Va dans Packages → ton package service_client → Settings → Change visibility → Public.
sinon, laisse Private et authentifie-toi pour tirer l’image (PAT read:packages ou docker/login-action dans tes pipelines).




code sonarquebe : sqp_851d30fcab9b2ac092136d5ebc7648283fc2c82d

d5c222bbf0ceb16eab9f7869405fb15e107d3cc07e384a85197dcb9b4a69e216


# Faire taire les logs RabbitMQ pendant les tests
- Crée src/test/resources/application-test.properties 



### points communs des trois services :
tes trois microservices (client, produit, commande) sont homogènes :
- chacun déclare seulement sa DB et son service
- tous partagent le même RabbitMQ (dans service_client) via le réseau ms-net
- tous exposent leur Actuator pour Prometheus (8081, 8082, 8083)




### pourquoi centraliser le Promotheus 
prometheus-client → scrape uniquement service-client
prometheus-produit → scrape uniquement service-produit
prometheus-commande → scrape uniquement service-commande

👉 Problème : tu as 3 Prometheus séparés, chacun avec sa base de données interne, son UI, ses alertes.
Ça complique la supervision (tu dois ouvrir 3 dashboards, configurer 3 Grafana, etc.).
Solution : 1 seul Prometheus centralisé qui scrape tous les services.
- On va créer un seul service prometheus dans ton repo observability et Il aura un seul fichier prometheus.yml avec des scrape_configs pour client, produit et commande.
- Prometheus central va les scrapper via Docker network (service-client:8081, service-produit:8082, service-commande:8083).
- Un seul Grafana (ex: grafana:3000) branché sur ce Prometheus central.
- Tu pourras créer des dashboards multi-services (ex: comparer les métriques de client vs produit vs commande sur le même graphique).

pourquoi cette solution ?
- Ça te rapproche des pratiques pro : un repo infra/monitoring qui supervise tes microservices, sans dépendre d’eux.
- Tu pourras brancher tous tes services actuels et futurs dessus sans mélanger les configs.