🏫 UoH Student Document Verification System
📌 Project Overview

The UoH Student Document Verification System is a console-based Java application designed to manage and verify student documents in a university environment.
The system allows students to submit documents and admins to verify or reject them, ensuring a structured and secure verification process.

This project demonstrates the use of Object-Oriented Programming (OOP) concepts such as inheritance, encapsulation, polymorphism, and abstraction.

🎯 Objectives

To provide a simple system for student document submission

To allow administrators to verify documents

To maintain document status tracking

To apply core Java OOP principles in a real-world scenario

🛠️ Technologies Used

Programming Language: Java

Interface: Console (CLI)

IDE: Any Java IDE (VS Code / IntelliJ / Eclipse)

JDK Version: JDK 8 or above

👥 User Roles
1️⃣ Student

Login using Student ID and Password

View student dashboard

Submit documents

View submitted documents and verification status

Logout

2️⃣ Admin

Login using Admin ID and Password

View admin dashboard

View pending documents

Approve or reject documents with comments

View registered students

Logout

📂 Project Structure
uoh_document_verification/
│
├── Main.java
├── User.java
├── Student.java
├── Admin.java
├── Document.java
├── VerificationSystem.java
└── README.md

🧩 Class Description
🔹 User (Base Class)

Stores common user details

Implements login and password verification

Provides a common dashboard interface

🔹 Student (Extends User)

Submits documents

Views document status

Displays student-specific dashboard

🔹 Admin (Extends User)

Verifies or rejects documents

Views pending documents

Displays admin-specific dashboard

🔹 Document

Stores document details

Maintains verification status

Handles approve/reject logic

🔹 VerificationSystem

Manages students, admins, and documents

Acts as the central system controller
