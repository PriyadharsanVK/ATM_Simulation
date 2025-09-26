# 🏧 ATM Simulation System (Java Console Project)

![Java](https://img.shields.io/badge/Java-17%2B-orange?logo=java&logoColor=white)
![Contributions](https://img.shields.io/badge/Contributions-Welcome-brightgreen?logo=github)
![License](https://img.shields.io/badge/License-MIT-blue?logo=open-source-initiative)
![Platform](https://img.shields.io/badge/Platform-Console-lightgrey)

---

## 📌 Overview
This project is a **console-based ATM Simulation System** implemented in **Java**.  
It mimics the real-world flow of ATM operations such as:
- User Authentication
- View Balance
- Deposit Cash
- Withdraw Cash
- Mini Statement
- PIN Change
- Exit

It is designed to provide a **realistic banking experience** while practicing **OOP concepts, collections, and file handling** in Java.

---

## 🚀 Features
- 🔑 **Login Authentication**: Secure user login with account number and PIN.
- 💰 **View Balance**: Check available account balance.
- ➕ **Deposit Cash**: Deposit money into the account.
- 💸 **Withdraw Cash**: Withdraw money with sufficient balance check.
- 📜 **Mini Statement**: View recent transactions.
- 🔐 **PIN Change**: Update ATM PIN for security.
- 🏦 **Account Types**:
    - **Savings Account** → Requires an **initial deposit** at creation.
    - **Current Account** → Created without an initial deposit.

---

## 🛠️ Tech Stack
- **Language:** Java
- **Paradigm:** Object-Oriented Programming (OOP)
- **Tools:** Java Collections, Scanner (for input), File Handling (optional extension)

---

## 📂 Project Structure

```bash
ATM-Simulation/
│── src/
│   ├── ATM_Main.java        # Entry point of the project
│   ├── ATM.java             # Core ATM operations
│   ├── Account.java         # Base class for accounts
│   └── AccountType.java     # To define Account Types
└── README.md                # Project documentation

```

---

## ▶️ How to Run
1. Clone this repository:  
   ```bash
   git clone https://github.com/<your-username>/ATM-Simulation.git
   cd ATM-Simulation/src
   ```
2. Compile the Java Files:
    ```bash
   javac ATM_Main.java
   ```
   
3. Run the Program
    ```bash
   java ATM_Main
   ```

## 📸 SAMPLE FLOW
```bash
Welcome to the ATM Simulation!
Enter your Account Number: 12345
Enter your PIN: ****

1. View Balance
2. Deposit Cash
3. Withdraw Cash
4. Mini Statement
5. Change PIN
6. Exit
   Enter your choice:
```
---

## 🛠️ Tech Stack

 - Language: Java
 - Paradigm: Object-Oriented Programming (OOP)
 - Tools: Java Collections, Scanner (for input)

---
## 🔮 Future Enhancements

 - Add file/database storage for persistent account data.
 - Integrate exception handling for smoother flow.
 - Add admin panel to create/manage accounts.
 - Implement GUI version using JavaFX or Swing.

---

## 🤝 Contributing
 - Contributions are welcome! Feel free to fork this repo, raise issues, or submit PRs.

---

## 📜 License
 - This project is licensed under the MIT License – you are free to use, modify, and distribute it.

---