
# 🎤 Gestion des Conférences Scientifiques

## 📌 Description
**Gestion des Conférences Scientifiques** est une application de bureau développée en **Java (Swing)** avec **MySQL** comme base de données.  
Elle permet de gérer des **événements scientifiques**, leurs **intervenants**, et les **associations entre les deux**.

---

## ✅ Fonctionnalités

- 🎟 **Ajouter un événement**  
  Créez un nouvel événement avec son **titre**, **thème**, **date** et **lieu**.

- 👨‍🏫 **Gérer les intervenants**  
  Ajoutez, modifiez ou supprimez des intervenants spécialisés dans un domaine.

- 📝 **Associer des intervenants à un événement**  
  Affectez plusieurs intervenants à un événement via une interface dédiée.

- 🔎 **Recherche d’événements par date**

- 🔍 **Recherche d’intervenants par nom**

- 🔐 **Authentification utilisateur**  
  Connexion sécurisée avec **gestion de mot de passe** et **question de sécurité**.

---

## 🏗 Structure de la Base de Données

Nom de la base de données : `gestion_conferences`

| Table                     | Description                                         |
|---------------------------|-----------------------------------------------------|
| `intervenant`             | Informations sur les intervenants                  |
| `evenement`               | Détails des événements (titre, thème, date, lieu)  |
| `participation_evenement`| Relation plusieurs-à-plusieurs entre les deux      |
| `user`                    | Stocke les informations de connexion des utilisateurs |

---

## 📊 Schéma de la Base de Données (SQL)

```sql
CREATE DATABASE IF NOT EXISTS gestion_conferences;
USE gestion_conferences;

CREATE TABLE IF NOT EXISTS intervenant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    specialite VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS evenement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    theme ENUM('SCIENCE', 'TECHNOLOGIE', 'INNOVATION', 'EDUCATION') NOT NULL,
    date_evenement DATE NOT NULL,
    lieu VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS participation_evenement (
    evenement_id INT NOT NULL,
    intervenant_id INT NOT NULL,
    FOREIGN KEY (evenement_id) REFERENCES evenement(id) ON DELETE CASCADE,
    FOREIGN KEY (intervenant_id) REFERENCES intervenant(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS user (
    login VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    question_securite VARCHAR(255),
    reponse_securite VARCHAR(255)
);
```

---
## 🧱 Architecture en Couches – Gestion des Conférences Scientifiques
![Schema1 drawio](https://github.com/user-attachments/assets/a3b0a100-d441-4ca4-9a6c-dcb342240fab)


---


## 🏗️ Structure Complète du Projet GestionConferences

```
GestionConferences/
│
├── beans/                        # 🧩 Modèles de données (JavaBeans)
│   ├── EThemeEvenement.java           # Enum des thèmes de conférence
│   ├── Evenement.java                 # Classe de l’événement
│   ├── Intervenant.java               # Classe de l’intervenant
│   ├── ParticipationEvenement.java   # Classe de liaison événement ↔ intervenant
│   └── User.java                      # Classe utilisateur
│
├── connexion/                    # 🔌 Connexion à la base de données
│   └── Connexion.java                 # Classe de connexion unique (singleton)
│
├── dao/                         # 📦 Interfaces DAO
│   ├── IDao.java                     # Interface générique CRUD
│   └── IUserDao.java                 # Interface spécifique utilisateur
│
├── gui/                         # 🖥️ Interface utilisateur (Swing)
│   ├── ConferenceBarChart.java       # Diagramme de conférences
│   ├── EvenementForm.java            # Création/modification d’événement
│   ├── EvenementParDate.java         # Recherche par date
│   ├── IntervenantByNom.java         # Recherche par nom
│   ├── IntervenantForm.java          # Formulaire intervenant
│   ├── MDIApplication.java           # Fenêtre principale (interface multi-documents)
│   ├── Main.java                     # Classe principale (avec `main`)
│   └── *.png                         # Images d’illustration (logo, interface)
│
├── jdbc/                        # 📡 Connexions manuelles JDBC (tests ou essais)
│   ├── JDBC.java
│   ├── JDBC2.java
│   └── JDBC3.java
│
├── services/                    # 🧠 Logique métier (service layer)
│   ├── EvenementService.java
│   ├── IntervenantService.java
│   ├── ParticipationEvenementService.java
│   └── UserService.java
│
├── test/                        # 🧪 Tests simples
│   └── Test.java
│
├── util/                        # 🛠️ Classes utilitaires
│   └── SecurityUtil.java
├── lib/                      # Bibliothèques externes (MySQL Connector, JCalendar, JFreeChart)
│   ├── jcalendar-1.4.jar
│   └── autres fichiers .jar
│
└── README.md                    # 📘 Documentation du projet
```
---


## 📐 Modélisation UML

### 📌 Diagramme de Cas d’Utilisation
![Captureeeeeeeeeeeeeeeeeeeeeeeeeeeeeeeee](https://github.com/user-attachments/assets/5572d8ed-72e9-4b6c-9a17-45022142e6f0)




### 🧩 Diagramme de Classes Simplifié

![Cazzzzzzzzzzzzzzzzzzzzzzzzzzpture](https://github.com/user-attachments/assets/0e857b61-2c5d-47f7-b9f9-bea1a366c33d)




---

## 🧰 Technologies & Bibliothèques Utilisées

| Outil / Bibliothèque       | Description                                                                 |
|---------------------------|-----------------------------------------------------------------------------|
| **NetBeans 8.0.2**         | Environnement de développement intégré (IDE) pour programmer l'application en Java. |
| **MySQL Connector/J**      | Pilote JDBC permettant la connexion et la communication avec la base de données MySQL. |
| **JCalendar**              | Composant graphique facilitant la sélection et la gestion des dates dans l’interface. |
| **JFreeChart**             | Bibliothèque pour générer des graphiques (diagrammes circulaires, histogrammes, etc.). |
| **Swing**                  | Bibliothèque Java utilisée pour concevoir l’interface graphique utilisateur (GUI). |



---











## 🎥 Vidéo Démonstrative

➡️ Une vidéo de démonstration est disponible pour présenter :

- Le lancement de l'application
- L’ajout/modification d’événements et intervenants
- L'association entre intervenants et événements
- La navigation et l’interface utilisateur
- Visualisation des statistiques(nombre des intervenants par conference)
  




https://github.com/user-attachments/assets/217997bd-225f-435a-af80-64ec9ee509e6





---

## 📌 Auteur
Développé par **Rayan LAYHI** – Étudiant à **Ecole normale supérieure de MARRAKECH**  
Dans le cadre du projet : **Programmation Java & Bases de Données**


