package employeerecordsystem;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Cyril
 */
public class Utils {

    public static void viewEmployees(File file) {
        DataInputStream dataIn = null;
        try {
            dataIn = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));
            while (dataIn.available() > 0) {
                Employee employee = new Employee(dataIn.readInt(),
                        dataIn.readUTF(), dataIn.readInt(),
                        dataIn.readUTF(), dataIn.readDouble());
                System.out.println(employee);

            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                dataIn.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }

    public static int genId() {
        return new Random().nextInt(1000);
    }

    public static Employee getEmployee(File file, int id) {
        boolean found = false;
        Employee employee = null;
        DataInputStream dataIn = null;
        try {
            dataIn = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));
            while (dataIn.available() > 0) {
                int empId = dataIn.readInt();
                String name = dataIn.readUTF();
                int age = dataIn.readInt();
                String position = dataIn.readUTF();
                double salary = dataIn.readDouble();
                if (empId == id) {
                    employee = new Employee(empId, name,
                            age, position, salary);
                    found = true;
                    break;
                }
            }
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                dataIn.close();
                if (!found) {
                    System.err.println("Employee not found!");
                }
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
        return employee;
    }

    public static void genEmployeeCSV(File file) throws IOException {
        File csvFile = new File("employees.csv");
        FileWriter fileWriter = new FileWriter(csvFile);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        DataInputStream dataIn = null;
        try {
            dataIn = new DataInputStream(
                    new BufferedInputStream(
                            new FileInputStream(file)));
            while (dataIn.available() > 0) {
                Employee employee = new Employee(dataIn.readInt(),
                        dataIn.readUTF(), dataIn.readInt(),
                        dataIn.readUTF(), dataIn.readDouble());
                bufferedWriter.write(employee.toCSVFormat());
            }
            System.out.println("Record generated in: " + csvFile.getAbsolutePath());
        } catch (FileNotFoundException ex) {
            System.err.println("File not found");
        } catch (IOException ex) {
            System.err.println(ex);
        } finally {
            try {
                dataIn.close();
                bufferedWriter.close();
            } catch (IOException ex) {
                System.err.println(ex);
            }
        }
    }
}