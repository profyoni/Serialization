package edu.touro.cs;

import java.io.*;

// Reflection - the programmatic ability to inspect
// a class fields and methods...AND call them


class Student implements Serializable // tagging interface
{
    private int studentId;
    private String firstName, lastName;

    Student(int studentId, String firstName, String lastName) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}

public class Main {

    public static void main(String[] args) {
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
            Student s2 = (Student) objectinputStream.readObject();
            System.out.println(s2);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
