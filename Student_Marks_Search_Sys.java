import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Student_Marks_Search_Sys {
    private static final String FILE_NAME = "students.txt";
    private static final int MAX_STUDENTS = 100;

    private static String[] ids = new String[MAX_STUDENTS];
    private static String[] names = new String[MAX_STUDENTS];
    private static int[] marks = new int[MAX_STUDENTS];
    private static int studentCount = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        loadFromFile();

        while (true) {
            System.out.println("\nMAIN MENU");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Sort by Marks");
            System.out.println("4. Search by Marks (Binary Search)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            try {
                int choice = sc.nextInt();
                sc.nextLine();

                switch (choice) {
                    case 1:
                        addStudent(sc);
                        break;
                    case 2:
                        displayAll();
                        break;
                    case 3:
                        sortByMarks();
                        displayAll();
                        break;
                    case 4:
                        System.out.print("Enter marks to search: ");
                        try {
                            int searchMarks = sc.nextInt();
                            sc.nextLine();
                            binarySearchByMarks(searchMarks);
                        } catch (InputMismatchException e) {
                            sc.nextLine();
                            System.out.println("Invalid marks. Marks must be between 0 and 100.");
                        }
                        break;
                    case 5:
                        saveToFile();
                        System.out.println("Thank you!");
                        sc.close();
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                sc.nextLine();
                System.out.println("Invalid choice.");
            }
        }
    }

    public static void addStudent(Scanner sc) {
        if (studentCount >= MAX_STUDENTS) {
            System.out.println("Cannot add more students. Maximum limit reached.");
            return;
        }

        System.out.print("Name: ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) {
            System.out.println("Invalid name.");
            return;
        }

        System.out.print("Marks: ");
        try {
            int marksValue = sc.nextInt();
            sc.nextLine();

            if (marksValue < 0 || marksValue > 100) {
                System.out.println("Invalid marks. Marks must be between 0 and 100.");
                return;
            }

            int idNumber = studentCount + 101;
            String id = "S" + idNumber;
            ids[studentCount] = id;
            names[studentCount] = name;
            marks[studentCount] = marksValue;
            studentCount++;

            System.out.println("Student added. ID: " + id + " | " + name + " | " + marksValue);
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println("Invalid marks. Marks must be between 0 and 100.");
        }
    }

    public static void displayAll() {
        if (studentCount == 0) {
            System.out.println("No students available.");
            return;
        }

        for (int i = 0; i < studentCount; i++) {
            System.out.println(ids[i] + " | " + names[i] + " | " + marks[i]);
        }
    }

    public static void sortByMarks() {
        for (int i = 1; i < studentCount; i++) {
            int currentMarks = marks[i];
            String currentId = ids[i];
            String currentName = names[i];
            int j = i - 1;

            while (j >= 0 && marks[j] > currentMarks) {
                marks[j + 1] = marks[j];
                ids[j + 1] = ids[j];
                names[j + 1] = names[j];
                j--;
            }

            marks[j + 1] = currentMarks;
            ids[j + 1] = currentId;
            names[j + 1] = currentName;
        }
        System.out.println("Students sorted by marks (low to high):");
    }

    public static void binarySearchByMarks(int searchMarks) {
        if (searchMarks < 0 || searchMarks > 100) {
            System.out.println("Invalid marks. Marks must be between 0 and 100.");
            return;
        }

        sortByMarks();

        int low = 0;
        int high = studentCount - 1;
        boolean found = false;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (marks[mid] == searchMarks) {
                found = true;
                System.out.println("Found at index " + mid + ": " + ids[mid] + " | "
                        + names[mid] + " | " + marks[mid]);

                int first = mid;
                while (first > 0 && marks[first - 1] == searchMarks) {
                    first--;
                }
                for (int i = first; i < mid; i++) {
                    System.out.println(ids[i] + " | " + names[i] + " | " + marks[i]);
                }

                int last = mid + 1;
                while (last < studentCount && marks[last] == searchMarks) {
                    System.out.println(ids[last] + " | " + names[last] + " | " + marks[last]);
                    last++;
                }
                break;
            } else if (marks[mid] < searchMarks) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        if (!found) {
            System.out.println("No student found with marks " + searchMarks + ".");
        }
    }

    public static void saveToFile() {
        try (FileWriter writer = new FileWriter(FILE_NAME)) {
            for (int i = 0; i < studentCount; i++) {
                writer.write(ids[i] + "|" + names[i] + "|" + marks[i]);
                writer.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadFromFile() {
        try (FileReader reader = new FileReader(FILE_NAME)) {
            StringBuilder content = new StringBuilder();
            int character;
            while ((character = reader.read()) != -1) {
                content.append((char) character);
            }

            String[] fileLines = content.toString().split("\\r?\\n");
            for (String line : fileLines) {
                if (studentCount >= MAX_STUDENTS || line.trim().isEmpty()) {
                    break;
                }

                String[] parts = line.split("\\|", -1);
                if (parts.length == 3) {
                    try {
                        int loadedMarks = Integer.parseInt(parts[2].trim());
                        if (loadedMarks >= 0 && loadedMarks <= 100) {
                            ids[studentCount] = parts[0].trim();
                            names[studentCount] = parts[1].trim();
                            marks[studentCount] = loadedMarks;
                            studentCount++;
                        }
                    } catch (NumberFormatException ignored) {
                        // Ignore malformed file records.
                    }
                }
            }
        } catch (IOException e) {
            // The file is optional; it is created when the user exits.
        }
    }
}
