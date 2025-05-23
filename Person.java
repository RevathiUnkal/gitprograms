import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

public class Person {
    private String personName;
    private Date dateOfBirth; // Storing as java.util.Date

    // Constructor
    public Person(String personName, Date dateOfBirth) {
        this.personName = personName;
        this.dateOfBirth = dateOfBirth;
    }

    // Function 1: Display person name
    public void displayPersonName() {
        System.out.println("Person Name: " + personName);
    }

    // Function 2: Display age of person by accepting date of birth
    // This method will provide accurate age using java.time API after converting java.util.Date
    public void displayAge() {
        if (dateOfBirth == null) {
            System.out.println("Date of Birth not set. Cannot calculate age.");
            return;
        }

        // Convert java.util.Date to java.time.LocalDate for accurate age calculation
        LocalDate dobLocalDate = dateOfBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();
        int age = Period.between(dobLocalDate, currentDate).getYears();

        System.out.println("Age: " + age + " years");
    }

    // Helper method to parse date from string input
    public static Date parseDate(Scanner scanner, String prompt) {
        SimpleDateFormat formatterDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        formatterDDMMYYYY.setLenient(false); // Strict parsing
        formatterYYYYMMDD.setLenient(false); // Strict parsing

        Date dob = null;
        boolean dateParsed = false;
        while (!dateParsed) {
            System.out.print(prompt);
            String dobStr = scanner.nextLine();
            try {
                if (dobStr.matches("\\d{2}-\\d{2}-\\d{4}")) { // Matches DD-MM-YYYY
                    dob = formatterDDMMYYYY.parse(dobStr);
                } else if (dobStr.matches("\\d{4}-\\d{2}-\\d{2}")) { // Matches YYYY-MM-DD
                    dob = formatterYYYYMMDD.parse(dobStr);
                } else {
                    throw new ParseException("Invalid format", 0);
                }
                dateParsed = true;
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use DD-MM-YYYY or YYYY-MM-DD.");
            }
        }
        return dob;
    }

    // Main method for testing Person class
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Person Name: ");
        String name = scanner.nextLine();

        Date dob = parseDate(scanner, "Enter Date of Birth (DD-MM-YYYY or YYYY-MM-DD): ");

        Person person = new Person(name, dob);

        System.out.println("\n--- Person Details ---");
        person.displayPersonName();
        person.displayAge();

        scanner.close();
    }
}