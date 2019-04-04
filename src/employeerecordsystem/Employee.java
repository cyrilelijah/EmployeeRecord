
package employeerecordsystem;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 *
 * @author Cyril
 */
public class Employee implements Serializable{
    private transient int id;
    private String name;
    private int age;
    private double salary;
    private String position;

    public Employee() {

    }

    public Employee(int id, String name, int age, String position, double salary) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.salary = salary;
        this.position = position;
    }

    
    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAge() {
        return age;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public String getPosition() {
        return position;
    }

    public void saveTo(File file) throws IOException {
        DataOutputStream dataOut = null;
        try {
            dataOut = new DataOutputStream(
                    new BufferedOutputStream(
                            new FileOutputStream(file, true)));
            dataOut.writeInt(id);
            dataOut.writeUTF(name);
            dataOut.writeInt(age);
            dataOut.writeUTF(position);
            dataOut.writeDouble(salary);
            System.out.println("Successfully Added!");
        } catch (FileNotFoundException ex) {
            System.out.println(ex);
        } finally {
            dataOut.close();
        }   
    }
    
    public String toCSVFormat(){
        return id + "," + name + "," + age + "," +
                position + "," + String.format("%.2f", salary) + "\n";
    }

    @Override
    public String toString() {
        return "ID: " + id + "\nName: " + name + 
                "\nAge: " + age + "\nPosition: " + position +
                "\nSalary: " + String.format("%.2f", salary) + "\n";
    }
    
    public void serialized(){
        File file = new File("employee.ser");
        try {
            FileOutputStream fileOut
                    = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(this);
            out.close();
            fileOut.close();
            System.out.print("Serialized data is saved in " + file.getAbsolutePath() + "\n");
        } catch (IOException i) {
            i.printStackTrace();
        }
    }
}