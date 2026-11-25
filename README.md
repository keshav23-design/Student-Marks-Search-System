# 🎓 Student Marks Management & Search System

A lightweight, console-based Java application designed to manage student records, sort them efficiently, and perform fast lookups using Binary Search. Data is automatically persisted to a local text file (`students.txt`) so your records are always saved between sessions.

---

## ✨ Features

* **Add Student Records:** Automatically generates sequential Student IDs (starting from `S101`) with built-in input validation for names and marks ($0$–$100$).
* **View All Records:** Cleanly displays all stored student IDs, names, and marks in the console.
* **Insertion Sort Integration:** Sorts student records in ascending order based on their marks ($O(n^2)$ time complexity) while keeping parallel arrays (`ids`, `names`, `marks`) synchronized.
* **Binary Search with Duplicate Handling:** Quickly locates students by their exact marks ($O(\log n)$ time complexity). If multiple students share the same score, the system automatically finds and displays all matching records.
* **Persistent File Storage:** Automatically loads existing records from `students.txt` at startup and saves any new changes upon exiting the application.
* **Robust Error Handling:** Gracefully handles invalid menu choices, non-numeric inputs, and missing data files without crashing.

---

## 🛠️ Tech Stack & Concepts Used

* **Language:** Java (Standard Edition)
* **Data Structures:** Parallel Arrays
* **Algorithms:** Insertion Sort & Binary Search
* **File I/O:** `java.io.FileReader` & `java.io.FileWriter`
* **Exception Handling:** `InputMismatchException`, `IOException`, `NumberFormatException`

---

## 🚀 Getting Started

### Prerequisites
Make sure you have the **Java Development Kit (JDK 8 or higher)** installed on your system. You can verify this by running:
```bash
java -version
```

### Installation & Execution

1. **Clone the repository:**
   ```bash
   git clone https://github.com/<your-username>/<your-repo-name>.git
   cd <your-repo-name>
   ```

2. **Compile the Java program:**
   ```bash
   javac Student_Marks_Search_Sys.java
   ```

3. **Run the application:**
   ```bash
   java Student_Marks_Search_Sys
   ```

---

## 💻 Usage Example

```text
MAIN MENU
1. Add Student
2. Display All Students
3. Sort by Marks
4. Search by Marks (Binary Search)
5. Exit
Enter your choice: 1
Name: Aarav Sharma
Marks: 92
Student added. ID: S101 | Aarav Sharma | 92

MAIN MENU
1. Add Student
2. Display All Students
3. Sort by Marks
4. Search by Marks (Binary Search)
5. Exit
Enter your choice: 4
Enter marks to search: 92
Students sorted by marks (low to high):
Found at index 0: S101 | Aarav Sharma | 92
```

---

## 📂 Project Structure

```text
├── Student_Marks_Search_Sys.java   # Main source code containing logic, sorting, searching, and file I/O
├── students.txt                    # Auto-generated flat-file database (created upon exiting the program)
└── README.md                       # Project documentation
```

---

## 🔮 Future Enhancements

* [ ] Migrate from parallel arrays to an Object-Oriented `Student` class with `ArrayList<Student>`.
* [ ] Add options to update or delete existing student records.
* [ ] Implement search by Student ID or Name.
* [ ] Export student reports to CSV format.

---

## 👤 Author

**Keshav Yadav**  
* GitHub: [keshav23-design](https://github.com/keshav23-design)  
* LinkedIn: [Keshav Yadav](https://www.linkedin.com/in/keshav-yadav-607389383/)  

---

*Feel free to fork this repository, open issues, or submit pull requests if you'd like to contribute!*
