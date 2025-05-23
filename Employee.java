import java.util.Date;
import java.util.Scanner;
// No need to import SimpleDateFormat, etc. here if Person.java handles it
// The Employee class extends Person, so it uses Person's constructor and methods.

public class Employee extends Person {
    private int empId;
    private double salary;

    // Constructor for Employee, calling the Person constructor
    public Employee(String personName, Date dateOfBirth, int empId, double salary) {
        super(personName, dateOfBirth); // Call Person's constructor
        this.empId = empId;
        this.salary = salary;
    }

    // New function to display all employee details including inherited ones
    public void displayEmployeeDetails() {
        System.out.println("\n--- Employee Details ---");
        super.displayPersonName(); // Display name from Person class
        super.displayAge();        // Display age from Person class
        System.out.println("Employee ID: " + empId);
        System.out.println("Salary: $" + String.format("%.2f", salary)); // Format salary to 2 decimal places
    }

    // Main method for testing Employee class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Employee Name: ");
        String name = scanner.nextLine();

        Date dob = Person.parseDate(scanner, "Enter Date of Birth (DD-MM-YYYY or YYYY-MM-DD): ");

        System.out.print("Enter Employee ID: ");
        int empId = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        System.out.print("Enter Employee Salary: ");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume the newline

        Employee employee = new Employee(name, dob, empId, salary);

        employee.displayEmployeeDetails();

        scanner.close();
    }
}