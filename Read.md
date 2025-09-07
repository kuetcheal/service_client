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