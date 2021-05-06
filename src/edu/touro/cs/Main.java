package edu.touro.cs;

import java.io.*;
import java.util.ArrayList;

// Reflection - the programmatic ability to inspect
// a class fields and methods...AND call them


class Student implements Serializable // tagging interface
{
    private static final long serialVersionUID = 1405787678759L;

    transient private int studentId;
    transient private String firstName, lastName;
    transient private String initials;

    Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getInitials() {
        if (initials == null) // lazy loading ( vs eager loading )
        {
            initials = firstName.substring(0,1) + lastName.substring(0,1);
        }
        return initials;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    // custom Serialization
    private void writeObject(ObjectOutputStream oos) throws IOException
    {
        oos.defaultWriteObject();

        oos.writeInt(studentId);
        oos.writeObject(firstName);
        oos.writeObject(lastName);
    }


    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException{
        ois.defaultReadObject();

        studentId = ois.readInt();
        firstName = (String) ois.readObject();
        lastName = (String) ois.readObject();
    }
}

public class Main {

    public static void main(String[] args) {
        new ArrayList<String>();
        Student s1 = new Student(66,"Jon","Dough");

        try (ObjectOutputStream objectOutputStream =
                     new ObjectOutputStream(
                new FileOutputStream("student.bin"))) {
            objectOutputStream.writeObject(s1);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream objectinputStream = new ObjectInputStream(
                new FileInputStream("student.bin"))) {
            Object ob = objectinputStream.readObject();
            if (!(ob instanceof Student))
            {
                writeError(ob);
                return;
            }
            Student s2 = (Student) ob;
            System.out.println(s2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static void writeError(Object ob) {
    }
}
