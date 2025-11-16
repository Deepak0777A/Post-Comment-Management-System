# Post & Comment Management System (Mini Blog)

A full-featured **Mini Blog Application** built using **Spring Boot, Spring Security, Thymeleaf, and MySQL**.  
This project was developed as part of the **Backend Development Track (API + DB)** assessment to demonstrate CRUD operations, authentication, authorization, UI integration, and clean code structure.

---

## 🚀 Features

### 👤 Authentication & User Management
- User Registration  
- Login / Logout  
- Secure password encryption using BCrypt  
- Only post owners can **Edit/Delete** their posts  
- Only comment authors can delete their comments  

### 📝 Blog Post Features
- Create, Edit, Delete Posts  
- View single Post  
- Truncated preview on listing page  
- Rich content display using `<pre>` formatting  

### 💬 Comments
- Add comments to any post  
- Delete own comments  
- Timestamped comment history  

### 🔍 Search + Pagination
- Search posts by title or content  
- Pagination for large datasets  

### 🎨 UI/UX
- Clean and responsive templates using **Thymeleaf + Internal CSS**  
- Fully styled: Login, Register, New Post, Edit Post, View Post, Comments pages  

### 🔒 Security
- Spring Security login system  
- CSRF protection  
- Authorization based on logged-in user  
- Hidden CSRF tokens in all POST forms  

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot |
| Security | Spring Security |
| Database | **MySQL** |
| ORM | Spring Data JPA + Hibernate |
| View Layer | Thymeleaf Templates |
| Build Tool | Maven |
| Language | Java 17+ |
| IDE | IntelliJ IDEA |

---

## ⚙️ Setup Instructions

2️⃣ Configure the MySQL Database

Open:

src/main/resources/application.properties


Use this config:

spring.datasource.url=jdbc:mysql://localhost:3306/miniblog?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true


MySQL will automatically create the database if it doesn't exist.

3️⃣ Build and Run the Application

Using Maven:

mvn clean install
mvn spring-boot:run


Or run the main class MiniBlogApplication.java from your IDE.

4️⃣ Access the Application

Open browser:

http://localhost:8080

URL	Function
/register	Create a new user account
/login	Login to the system
/posts	View all posts
/posts/new	Create a new post
/posts/view/{id}	View a post + comments
📂 Project Structure
src/main/java/com/example/
 ├── controller/        -> Handles page routing & requests
 ├── model/             -> Entities (User, Post, Comment)
 ├── repository/        -> JPA Repositories
 ├── service/           -> Business logic layer
 ├── security/          -> Security configuration
 └── MiniBlogApplication.java

### 1️⃣ Clone the repository

```bash
git clone https://github.com/Deepak0777A/Post-Comment-Management-System.git
cd Post-Comment-Management-System
👨‍💻 Author

Deepak A
Backend Developer – Java & Spring Boot
Email: deepakantony360@gamil.com

