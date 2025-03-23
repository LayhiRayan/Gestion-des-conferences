# 🎤 Gestion des Conférences Scientifiques

## 📌 Description
Ce projet est une **application de gestion des conférences scientifiques**.  
Elle permet de **gérer les événements et  les intervenants**.

L'application est développée en **Java (Swing)** pour l'interface graphique et utilise **MySQL** comme base de données.

---

## ✅ **Fonctionnalités**
- 🎟 **Ajouter un événement** : Permet d'ajouter un nouvel événement avec son titre, son thème, sa date et son lieu.
- 👨‍🏫 **Gérer les intervenants** : Ajouter, modifier ou supprimer des intervenants spécialisés.
- 📝 **Associer des intervenants aux événements** : Un intervenant peut être affecté à une conférence.
- 🔎 **Rechercher un événement par date**.
- 🔍 **Rechercher un intervenant par nom**.


---

## 🏗 **Structure de la Base de Données**
La base de données **`gestion_conferences`** contient les tables suivantes :

| Table                     | Description                                      |
|---------------------------|--------------------------------------------------|
| **`intervenant`**         | Stocke les intervenants des conférences         |
| **`evenement`**           | Stocke les événements (titre, thème, lieu, date)|
| **`participation_evenement`** | Relie les intervenants aux événements       |
| **`user`**                | Gère les comptes utilisateurs                   |

---

## 📊 **Schéma de la Base de Données (SQL)**
```sql
-- Création de la base de données
CREATE DATABASE IF NOT EXISTS gestion_conferences;
USE gestion_conferences;

-- Table des Intervenants
CREATE TABLE IF NOT EXISTS intervenant (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    specialite VARCHAR(100) NOT NULL
);

-- Table des Événements
CREATE TABLE IF NOT EXISTS evenement (
    id INT AUTO_INCREMENT PRIMARY KEY,
    titre VARCHAR(100) NOT NULL,
    theme ENUM('SCIENCE', 'TECHNOLOGIE', 'INNOVATION', 'EDUCATION') NOT NULL,
    date_evenement DATE NOT NULL,
    lieu VARCHAR(100) NOT NULL,
    intervenant_id INT,
    FOREIGN KEY (intervenant_id) REFERENCES intervenant(id) ON DELETE SET NULL
);

-- Table des Participations (Relation entre intervenants et événements)
CREATE TABLE IF NOT EXISTS participation_evenement (
    evenement_id INT,
    intervenant_id INT,
    PRIMARY KEY (evenement_id, intervenant_id),
    FOREIGN KEY (evenement_id) REFERENCES evenement(id) ON DELETE CASCADE,
    FOREIGN KEY (intervenant_id) REFERENCES intervenant(id) ON DELETE CASCADE
);

-- Table des Utilisateurs
CREATE TABLE IF NOT EXISTS user (
    login VARCHAR(50) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    question_securite VARCHAR(255),
    reponse_securite VARCHAR(255)
);
```
