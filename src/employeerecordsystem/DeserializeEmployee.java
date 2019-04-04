
package employeerecordsystem;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author cyrilelijahaurino
 */
public class DeserializeEmployee {

    public static void main(String[] args) {
        Employee e = null;

        FileInputStream fileIn;
        try {
            fileIn = new FileInputStream("employee.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (Employee) in.readObject();
            in.close();
            fileIn.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found1");
        } catch (IOException ex) {

        } catch (ClassNotFoundException ex) {
            
        }

        System.out.println("Deserialized Employee...");
        System.out.println("ID: " + e.getId());
        System.out.println("Name: " + e.getName());
        System.out.println("Age: " + e.getAge());
        System.out.println("Position: " + e.getPosition());
        System.out.printf("Salary: %.2f\n", e.getSalary());
    }
}