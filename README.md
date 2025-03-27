# Password Generator Tool

![Password Generator Tool](https://github.com/KulalMithun/Password-Generator-Tool/blob/Main/Screenshot%202025-03-26%20190300.png)

## Overview
The **Password Generator Tool** is a Java-based desktop application built using **Java Swing (AWT)**. It provides two primary functionalities:
1. **Generate Strong Passwords** – Allows users to generate customizable passwords based on length and character types.
2. **Check Password Strength** – Evaluates password strength based on composition and provides a rating (Weak, Medium, Strong).

This tool features a modern UI with a dark theme, neon accents, and rounded buttons for an enhanced user experience.

---

## Features
✅ **Customizable Password Generation**
- Choose password length (4 - 32 characters)
- Include uppercase letters, lowercase letters, numbers, and special characters
- Ensures at least one character from each selected category
- Randomized password shuffling for added security

✅ **Password Strength Checker**
- Input any password to check its strength
- Strength ratings: **Weak**, **Medium**, **Strong**
- Provides instant feedback on security level

✅ **Clipboard Integration**
- Copy generated passwords to the clipboard
- Paste passwords directly from the clipboard

✅ **Interactive UI**
- Dark theme with neon UI elements
- Hover effects on buttons and checkboxes

---

## Installation & Setup
### Prerequisites
Ensure you have **Java 8 or later** installed on your system.

### Steps to Run
1. Clone this repository:
   ```sh
   git clone https://github.com/KulalMithun/Password-Generator-Tool.git
   ```
2. Navigate to the project directory:
   ```sh
   cd Password-Generator-Tool
   ```
3. Compile the Java file:
   ```sh
   javac PasswordGeneratorTool.java
   ```
4. Run the application:
   ```sh
   java PasswordGeneratorTool
   ```

---

## Usage
### Generating a Password
1. Click **"Generate Password"** on the main menu.
2. Choose password length and character types.
3. Click **"Generate Password"** to create a password.
4. Copy the password using **"Copy to Clipboard"**.

### Checking Password Strength
1. Click **"Password Strength Check"** on the main menu.
2. Enter or paste a password.
3. Click **"Check Strength"** to get a security rating.

---

## Code Structure
- `PasswordGeneratorTool.java` – Main class handling UI and logic
- Uses **AWT & Swing** for GUI
- Implements **MouseAdapter** for hover effects
- **Clipboard integration** for copy-paste functionality

---
## To-Do
1. Need to make interface more user friendly.
2. Password store in txt/csv.
3. Using encryption to store password.
4. Login system for users to use this tool.
5. **Help** button for clearing confusion.
6. Need to implement Secure password while generating password.
7. Need to add more factors while determining strength of password.
8. Strength check meter to enchance visualization kf strength of password.
   

## Contribution
If you’d like to improve the tool, feel free to fork the repo, make changes, and submit a pull request.

---

## License
This project is licensed under the **MIT License** – free to use and modify.

---

## Contact
📧 **Kulal Mithun**  
GitHub: [KulalMithun](https://github.com/KulalMithun)

