# FatmaUnit - Firefighting Management System

## 📝 Overview
FatmaUnit is a robust information system designed to manage firefighting operations, personnel, and resources. Developed as part of an Advanced Object-Oriented Programming course, the system facilitates the coordination of fire events by efficiently assigning qualified firefighters and specialized vehicles to emergencies.

---

## 🚀 Key Features
* **Personnel Management:** Handles different roles including Firefighters and Fire Commanders, each with unique salary structures and experience levels.
* **Resource Allocation:** Manages a fleet of firefighting vehicles, distinguishing between Fire Trucks and Fire Planes with specific operational capabilities.
* **Event Coordination:** Automates the process of matching fire events with the necessary manpower and equipment.
* **Financial Tracking:** Calculates compensation based on participation in events, including bonuses and volunteer structures.

---

## 💻 Technical Implementation (OOP Principles)
This project demonstrates core OOP concepts as required by the assignment:
* **Inheritance & Polymorphism:** Hierarchical structure for personnel and vehicles.
* **Abstraction:** Implemented abstract classes and interfaces to define shared behaviors.
* **Encapsulation:** Strict management of class fields and methods to ensure data integrity.
* **Exception Handling:** Robust error management for system constraints (e.g., minimum experience requirements).

---

## 🏗️ System Architecture
The project is organized into several key components:
* `FireFighter` & `FireCommander`: Personnel management.
* `FireVehicle`, `FireTruck` & `FirePlane`: Resource management.
* `FireEvent`: Emergency logic and requirements.
* `FatmaUnit`: The core engine orchestrating the system.

---

## 🛠 Installation & Usage
1. Clone the repository:
   ```bash
   git clone [https://github.com/yahoov12/FatmaUnit.git](https://github.com/yahoov12/FatmaUnit.git)
