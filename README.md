# Kata : Gestionnaire de joueurs de football

Bienvenue dans ce Kata ! Ce projet est conçu pour améliorer vos compétences en refactoring, tests unitaires, et ajout de nouvelles fonctionnalités à un code existant.

## Objectif
Votre mission est de refactorer un code mal structuré, d’écrire des tests unitaires et de couvrir le code à 100% avec JaCoCo. Ensuite, vous ajouterez de nouvelles fonctionnalités tout en appliquant les principes de génie logiciel.

---

## Instructions

### 1. **Tests unitaires**

- **Créer des tests pour chaque action :**
  - Ajouter un joueur (`add`).
  - Supprimer un joueur (`remove`).
  - Calculer la performance d'un joueur (`calculate`).
  - Afficher les détails des joueurs (`show`).

- **Couvrir 100% du code avec JaCoCo.**

### 2. **Refactoring**

- **Séparer les responsabilités** en plusieurs classes, telles que :
  - `Player`
  - `TeamManager`

- **Utiliser des noms explicites** pour les méthodes et variables.

- **Remplacer les tableaux** (ex. : `Object[]`) par des structures de données plus adaptées, comme des objets ou des collections.

### 3. **Ajout de fonctionnalités**

- **Classement des joueurs :**
  - Ajouter une fonctionnalité pour trier et afficher les joueurs en fonction de leur score.

- **Exportation JSON :**
  - Permettre l’exportation des données des joueurs au format JSON.

### 4. **Questions de réflexion**

- **Pourquoi les tests sont-ils nécessaires avant de refactorer ?**
  - Discuter de l’importance de protéger les fonctionnalités existantes grâce aux tests.

- **Quels principes de génie logiciel sont appliqués pendant le refactoring ?**
  - Expliquer comment le principe de responsabilité unique (SRP) et d'autres concepts sont utilisés.

---

## Déroulement

1. **Cloner le projet** : Téléchargez le code source initial et ouvrez-le dans votre IDE.
2. **Analyser le code** : Identifiez les parties du code mal structurées.
3. **Écrire des tests unitaires** : Utilisez JUnit ou un autre framework pour tester chaque méthode.
4. **Refactorer** : Organisez le code en suivant les bonnes pratiques.
5. **Ajouter des fonctionnalités** : Implémentez le classement et l’exportation JSON.
6. **Vérifier la couverture de code** : Utilisez JaCoCo pour vous assurer que toutes les branches du code sont couvertes.

---

## Outils requis

- **Java 11+**
- **JUnit** pour les tests unitaires.
- **JaCoCo** pour mesurer la couverture du code.
- **Jackson (ou Gson)** pour la gestion JSON.

---

Bonne chance et amusez-vous bien avec ce Kata !
