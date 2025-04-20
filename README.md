# Simple Java Swing Banking System

![Java Version](https://img.shields.io/badge/Java-8%2B-blue?style=flat-square&logo=java)
![Platform](https://img.shields.io/badge/Platform-Desktop-lightgrey?style=flat-square)
![Status](https://img.shields.io/badge/Status-Completed-success?style=flat-square)

A basic desktop banking application built using Java and the Swing GUI toolkit. This project allows users to manage simple bank accounts, perform transactions, and view history.

**Developed for:** CSE215 (Object Oriented Programming) course at North South University (NSU).

**Developer:** [Mahin Anowar/MahinAnowar]

---

## Features

*   **Account Creation:** Register new users with a unique username and password.
*   **User Login:** Secure login for registered users.
*   **Dashboard:** View account balance and access banking functions.
*   **Deposit:** Add funds to the account.
*   **Withdraw:** Remove funds from the account (checks for sufficient balance).
*   **Transfer Funds:** Transfer money to another registered user (checks balance and recipient existence).
*   **Transaction History:** View a log of deposits, withdrawals, and transfers for the logged-in account.
*   **Data Persistence:** Account data (including balance and individual transaction lists) is saved to `data/accounts.txt` using Java Serialization. A simple transaction log is also kept in `data/transactions.txt`.
*   **Logout:** Securely end the user session.

## Technologies Used

*   **Language:** Java
*   **GUI:** Java Swing
*   **Persistence:** Java Object Serialization (for account objects), Basic File I/O (for transaction log)
*   **Core Concepts Implemented:** GUI, Exception Handling, Generics (`List<>`), Polymorphism (`ActionListener`), Basic Multithreading (`SwingUtilities.invokeLater` for EDT safety).

## Prerequisites

*   Java Development Kit (JDK) version 8 or higher installed and configured.

## How to Run

There are two ways to run the application:

**1. Running from Source Code:**

    ```bash
    # 1. Clone the repository
    git clone https://github.com/MahinAnowar/NSU-CSE215-Banking-Project.git  #

    # 2. Navigate to the project directory
    cd NSU-CSE215-Banking-Project

    # 3. Compile all Java files (make sure your terminal is in the directory containing the .java files)
    javac *.java

    # 4. Run the main class
    java Main
    ```
    *   The application will create a `data` subdirectory in the location where you run the `java Main` command, if it doesn't exist. This folder stores `accounts.txt` and `transactions.txt`.

**2. Running the JAR File (from GitHub Releases):**

    *   Download the `.jar` file (e.g., `SimpleBankApp.jar`) from the [Releases section](https://github.com/MahinAnowar/NSU-CSE215-Banking-Project/releases) of this repository.
    *   Open a terminal or command prompt.
    *   Navigate to the directory where you downloaded the JAR file.
    *   Run the JAR file using the following command:

    ```bash
    java -jar AppName.jar  # Replace AppName.jar with the actual downloaded JAR file name
    ```
    *   The `data` directory will be created in the same folder where the JAR file is located upon first use.

## Usage Workflow

1.  **Launch:** Run the application using one of the methods above. The **Login** window appears.
2.  **Create Account (New User):**
    *   Click `Create Account`.
    *   Enter a unique `Username` and `Password`.
    *   Click `Create Account`. You'll be returned to the Login window on success.
3.  **Login:**
    *   Enter your registered `Username` and `Password`.
    *   Click `Login`.
4.  **Dashboard:**
    *   Upon successful login, the **Dashboard** is displayed, showing your balance.
    *   Use the buttons (`Deposit`, `Withdraw`, `Transfer`, `Transaction History`) to perform banking operations. Follow the on-screen prompts (dialog boxes) for amounts or recipient details.
    *   Click `Logout` to return to the Login screen.

## Project Context

This application was developed as an individual project requirement for the CSE215 (Object Oriented Programming) course at North South University. It demonstrates the application of core Java concepts and OOP principles, including GUI development, data handling, and implementation of required features like polymorphism, exception handling, generics, and basic threading considerations for Swing.

## Author

*   **Mahin Anowar** - North South University (NSU)
    *   *Developed as a project for the CSE215 Java Programming course.*

---
