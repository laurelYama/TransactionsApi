# Secure Payment - Gestion Sécurisée des Transactions

## 📌 Description
Secure Payment est une API Spring Boot permettant la gestion sécurisée des transactions bancaires.
L'application assure le **chiffrement et le stockage sécurisé** des données sensibles conformément à la norme **PCI DSS**.

## 🚀 Fonctionnalités
- 📥 **Enregistrement sécurisé des transactions** (`POST /transactions/save`)
- 📤 **Consultation des transactions** (`GET /transactions/all`)
- 🔒 **Chiffrement des données sensibles** (Numéro de carte, date d'expiration, CVX)
- 🔄 **Déchiffrement automatique des données sensibles lors de la récupération**
- 📜 **Documentation Swagger pour tester les endpoints**

## 🛠️ Technologies Utilisées
- **Java 17**
- **Spring Boot 3** (Spring Data JPA, Spring Web, Spring Security)
- **MySQL 8** (Base de données des transactions)
- **Docker & Docker Compose** (Déploiement et orchestration)
- **Swagger UI** (Documentation API)
- **AES Encryption** (Chiffrement des données sensibles)

---

## 📂 Architecture du Projet
```
secure-payment/
│-- src/main/java/com/esiitech/secure_payment/
│   │-- entity/Transaction.java       # Entité Transaction
│   │-- repository/TransactionRepository.java  # Interface JPA Repository
│   │-- service/TransactionService.java  # Service de gestion des transactions
│   │-- controller/TransactionController.java  # API REST
│   │-- chiffrement/CryptoUtils.java  # Utilitaire pour chiffrement
│   │-- config/SecurityConfig.java  # Sécurité avec Spring Security
│   └-- config/SwaggerConfig.java  # Configuration Swagger
│-- src/main/resources/
│   │-- application.properties  # Configuration de l'application
│   └-- schema.sql  # Script de création des tables
│-- docker-compose.yml  # Déploiement Docker
│-- README.md  # Documentation
```

---

## 📦 Installation et Lancement
### 🏗️ **Prérequis**
1. **Java 17** installé
2. **Maven** installé
3. **Docker & Docker Compose** installés

### 🔹 **1. Cloner le projet**
```bash
git clone https://github.com/ton-repo/secure-payment.git
cd secure-payment
```

### 🔹 **2. Construire et exécuter avec Docker**
```bash
docker-compose up -d --build
```

### 🔹 **3. Accéder aux services**
- 📜 **Swagger UI** : [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html)
- 🗄️ **Base de données MySQL** :
  - **Host**: `localhost`
  - **Port**: `3307`
  - **Utilisateur**: `root`
  - **Mot de passe**: `123456789`

---

## 📌 Endpoints de l'API
### **1️⃣ Enregistrer une transaction**  
📍 **POST** `/transactions/save`
#### **📤 Corps de la requête (JSON)**
```json
{
  "idTrx": "TRX123",
  "dateTrx": "27-02-2025",
  "heureTrx": "14:30:15",
  "origTrx": "E-Commerce",
  "moyen": "Carte Bancaire",
  "numPan": "4979931234567890",
  "dateFin": "12/28",
  "cvx": "123",
  "montant": 1500.0
}
```
#### **📥 Réponse (200 OK)**
```json
{
  "idTrx": "TRX123",
  "dateTrx": "27-02-2025",
  "heureTrx": "14:30:15",
  "origTrx": "E-Commerce",
  "moyen": "Carte Bancaire",
  "numPanEncrypted": "OKbeQsypEMCWEpMeAgj3CAUBh6DN5amHLLqwkatz5VM=",
  "dateFinEncrypted": "QpySTBlNhprkN/msG3QUsA==",
  "cvxHashed": "DGYYLscQhABl66pHxebOkA==",
  "montant": 1500.0
}
```

---

### **2️⃣ Récupérer toutes les transactions**  
📍 **GET** `/transactions/all`
#### **📥 Réponse (200 OK)**
```json
[
  {
    "idTrx": "TRX123",
    "dateTrx": "27-02-2025",
    "heureTrx": "14:30:15",
    "origTrx": "E-Commerce",
    "moyen": "Carte Bancaire",
    "numPan": "4979 XXXX XXXX 7890",
    "dateFin": "**/**",
    "cvx": "***",
    "montant": 1500.0
  }
]
```

---

## 🔐 Sécurité & Chiffrement
- 🔒 **AES (Advanced Encryption Standard)** pour chiffrer **numPan, dateFin, CVX**
- 🛡️ **Masquage des données sensibles** avant affichage
- 🔑 **Spring Security** pour protéger les endpoints


---

## ✨ Auteurs
👨‍💻 **Développé par :** [Laurel YAMA]  
📧 **Contact :** [ngwambilaj@gmail.com]

