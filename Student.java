import java.util.Date;
import java.util.Scanner;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Student {
    private String studentName;
    private Date dateOfBirth; // Storing as Date object

    public Student(String studentName, Date dateOfBirth) {
        this.studentName = studentName;
        this.dateOfBirth = dateOfBirth;
    }

    // This method uses deprecated Date methods and provides year-based age.
    // It's not accurate for age calculation based on month/day.
    public int calculateAge() {
        Date currentDate = new Date();
        // getYear() returns year - 1900.
        // This calculation is simplistic and might be off by a year depending on birth month/day.
        return currentDate.getYear() - dateOfBirth.getYear();
    }

    public void displayStudentInfo() {
        System.out.println("Student Name: " + studentName);
        System.out.println("Date of Birth: " + new SimpleDateFormat("dd-MM-yyyy").format(dateOfBirth));
        System.out.println("Age: " + calculateAge() + " years (Approximate)");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SimpleDateFormat formatterDDMMYYYY = new SimpleDateFormat("dd-MM-yyyy");
        SimpleDateFormat formatterYYYYMMDD = new SimpleDateFormat("yyyy-MM-dd");
        formatterDDMMYYYY.setLenient(false); // Strict parsing
        formatterYYYYMMDD.setLenient(false); // Strict parsing

        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();

        Date dob = null;
        boolean dateParsed = false;
        while (!dateParsed) {
            System.out.print("Enter Date of Birth (DD-MM-YYYY or YYYY-MM-DD): ");
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

        Student student = new Student(name, dob);
        student.displayStudentInfo();

        scanner.close();
    }
}