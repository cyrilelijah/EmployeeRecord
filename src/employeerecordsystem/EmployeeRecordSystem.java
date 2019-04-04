
package employeerecordsystem;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

/**
 *
 * @author Cyril
 */
public class EmployeeRecordSystem {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        File file = new File("employees.txt");
        try {
            file.createNewFile();
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("Employee Record System!");
        while (true) {
            System.out.println("Choose an operation:");
            System.out.println("(1) View Employees");
            System.out.println("(2) Add Employee");
            System.out.println("(3) Serialized Employee");
            System.out.println("(4) Generate CSV");
            System.out.println("(5) Exit");
            System.out.println("Select an operation:");
            int op = sc.nextInt();
            switch (op) {
                case 1:
                    Utils.viewEmployees(file);
                    break;
                case 2:
                    Employee employee = new Employee();
                    employee.setId(Utils.genId());
                    System.out.print("Enter name: ");
                    employee.setName(reader.readLine());
                    System.out.print("Enter age: ");
                    try {
                        employee.setAge(Integer.parseInt(reader.readLine()));
                    } catch (NumberFormatException e) {
                        employee.setAge(0);
                    }
                    System.out.print("Enter position: ");
                    employee.setPosition(reader.readLine());
                    System.out.print("Enter salary: ");
                    try {
                        employee.setSalary(Double.parseDouble(reader.readLine()));
                    } catch (NumberFormatException e){
                        employee.setSalary(0);
                    }
                    employee.saveTo(file);
                    break;
                case 3:
                    System.out.println("Enter Employee Id:");
                    int empId = sc.nextInt();
                    Employee emp = Utils.getEmployee(file, empId);
                    if (emp != null) {
                        emp.serialized();
                    }
                    break;
                case 4:
                    Utils.genEmployeeCSV(file);
                    break;
                case 5:
                    System.exit(0);
                    break;
            }
        }
    }
}