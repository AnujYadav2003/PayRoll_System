import java.util.ArrayList;
import java.util.Scanner;

abstract class Employee {
    private String name;
    private int id;

    public Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateSalary();

    @Override
    public String toString() {
        return "Employee name=" + name + ", id=" + id + ", salary=" + calculateSalary() + ";";
    }
}

class FullTimeEmployee extends Employee {
    private double monthlySalary;

    public FullTimeEmployee(String name, int id, double monthlySalary) {
        super(name, id);
        this.monthlySalary = monthlySalary;
    }

    @Override
    public double calculateSalary() {
        return monthlySalary;
    }
}

class PartTimeEmployee extends Employee {
    private int hours;
    private double rate;

    public PartTimeEmployee(String name, int id, int hours, double rate) {
        super(name, id);
        this.hours = hours;
        this.rate = rate;
    }

    @Override
    public double calculateSalary() {
        return rate * hours;
    }
}

class PayrollSystem {
    private ArrayList<Employee> empList;

    public PayrollSystem() {
        empList = new ArrayList<>();
    }

    public void addEmp(Employee emp) {
        empList.add(emp);
    }

    public void removeEmp(int id) {
        Employee toRemove = null;
        for (Employee emp : empList) {
            if (emp.getId() == id) {
                toRemove = emp;
                break;
            }
        }
        if (toRemove != null) {
            empList.remove(toRemove);
            System.out.println("Employee with id " + id + " has been removed.");
        } else {
            System.out.println("Employee with id " + id + " is not in the list to remove.");
        }
    }

    public void display() {
        for (Employee emp : empList) {
            System.out.println(emp);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        PayrollSystem payroll = new PayrollSystem();

        System.out.println("Welcome to the Payroll System!");

        while (true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Add Full-Time Employee");
            System.out.println("2. Add Part-Time Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Display All Employees");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            switch (choice) {
                case 1 -> {
                    System.out.println("Enter Full-Time Employee Details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Monthly Salary: ");
                    double monthlySalary = scanner.nextDouble();
                    payroll.addEmp(new FullTimeEmployee(name, id, monthlySalary));
                    System.out.println("Full-Time Employee added.");
                }
                case 2 -> {
                    System.out.println("Enter Part-Time Employee Details:");
                    System.out.print("Name: ");
                    String name = scanner.nextLine();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    System.out.print("Hours Worked: ");
                    int hours = scanner.nextInt();
                    System.out.print("Hourly Rate: ");
                    double rate = scanner.nextDouble();
                    payroll.addEmp(new PartTimeEmployee(name, id, hours, rate));
                    System.out.println("Part-Time Employee added.");
                }
                case 3 -> {
                    System.out.print("Enter Employee ID to remove: ");
                    int id = scanner.nextInt();
                    payroll.removeEmp(id);
                }
                case 4 -> {
                    System.out.println("Employee Details:");
                    payroll.display();
                }
                case 5 -> {
                    System.out.println("Exiting the Payroll System. Goodbye!");
                    scanner.close();
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
