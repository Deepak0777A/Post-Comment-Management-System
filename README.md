# Post & Comment Management System (Mini Blog)

A full-featured **Mini Blog Application** built using **Spring Boot, Spring Security, Thymeleaf, and a relational database**.  
This project was developed as part of the **Backend Development Track (API + DB)** assessment to demonstrate CRUD operations, authentication, authorization, UI integration, and clean code structure.

---

## 🚀 Features

### 👤 Authentication & User Management
- User Registration  
- Login / Logout  
- Secure password encryption (BCrypt)  
- Only post owners can **Edit/Delete** their posts  
- Only comment authors can delete their comments  

### 📝 Blog Post Features
- Create, Edit, Delete Posts  
- View single Post  
- Truncated preview on listing page  
- Rich content support using `<pre>` formatting  

### 💬 Comments
- Add comments to any post  
- Delete your own comments  
- Timestamped comment history  

### 🔍 Search + Pagination
- Search posts by title or content  
- Pagination for large datasets  

### 🎨 UI/UX
- Clean and responsive pages with **Thymeleaf + Internal CSS**  
- Login, Register, New Post, Edit Post, View Post, Comments all fully styled  

### 🔒 Security
- Spring Security with CSRF protection  
- Authorization based on logged-in user  
- Hidden CSRF tokens in all POST forms  

---

## 🧰 Tech Stack

| Layer | Technology |
|-------|------------|
| Backend | Spring Boot |
| Security | Spring Security (Authentication + CSRF) |
| Database | PostgreSQL / MySQL (configurable) |
| ORM | Spring Data JPA + Hibernate |
| View Layer | Thymeleaf Templates + Internal CSS |
| Build Tool | Maven |
| Language | Java 17+ |
| IDE | IntelliJ IDEA |

---

## ⚙️ Setup Instructions
2️⃣ Configure the database

Open:

src/main/resources/application.properties


Example PostgreSQL configuration:

spring.datasource.url=jdbc:postgresql://localhost:5432/miniblog
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true


Create a database manually: miniblog
Update username/password as per your system.
3️⃣ Build and Run

Using Maven:

mvn clean install
mvn spring-boot:run


Or run the main class from your IDE.

4️⃣ Access the Application

Open browser:

http://localhost:8080


Pages:

URL	Description
/register	Create a new account
/login	Login to system
/posts	View all posts
/posts/new	Create new post
/posts/view/{id}	View post + comments

src/main/java/com/example/
 ├── controller/       -> Handles web requests
 ├── model/            -> Entities (User, Post, Comment)
 ├── repository/       -> JPA Repositories
 ├── service/          -> Business logic
 ├── security/         -> Login, config, filters
 └── MiniBlogApplication.java


### 1️⃣ Clone the repository
```bash
git clone https://github.com/Deepak0777A/Post-Comment-Management-System.git
cd Post-Comment-Management-System
