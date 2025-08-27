# 🛠️ Service Provider Role-Based Web App

A full-stack web application that enables **role-based access control** for three types of users: **Admin**, **Provider**, and **Consumer**. Built with **React**, **Spring Boot**, and **MySQL**, the app allows users to interact with services in a structured and secure way.

---
   
## 🚀 Features
 
### 🔐 Role-Based Access:
- **Admin**
  - Add new services
  - View all available services
  - View enrolled service providers
- **Provider**
  - Enroll into services
  - Update availability status
  - View consumer feedback & ratings
- **Consumer**
  - Explore available services
  - View providers enrolled in a service
  - Book providers based on ratings and availability

### 🧾 Authentication & Authorization:
- Role selection during sign-up
- Role-specific dashboard after login
- Email-based OTP verification for password reset

---

## 🧑‍💻 Tech Stack

| Layer        | Technology         |
|--------------|--------------------|
| Frontend     | React.js           |
| Backend      | Spring Boot (Java) |
| Database     | MySQL              |
| Auth & OTP   | Custom Email Service |
| Other Skills | REST APIs, Axios, Session Handling |

> ✅ *Note:* Although this project uses Spring Boot, I’m also proficient with **Node.js and Express.js**, and can implement similar logic using the MERN stack.

---

## 📸 Screenshots 

| Login Page | Admin Dashboard | Consumer View |
|------------|------------------|----------------|
| ![login](./screenshots/login.png) | ![admin](./screenshots/admin.png) | ![consumer](./screenshots/consumer.png) |

---

## 🛠️ Setup Instructions

### 🔧 Prerequisites:
- Node.js and npm
- Java 17+ and Maven
- MySQL Server

---

### ⚙️ Backend Setup (Spring Boot)

```bash
cd backend
# Configure `application.properties` for your MySQL DB
mvn clean install
mvn spring-boot:run
