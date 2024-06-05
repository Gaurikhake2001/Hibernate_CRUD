package org.example;

import jakarta.transaction.SystemException;
import org.example.config.HibernateConfig;
import org.example.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.w3c.dom.ls.LSOutput;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Transaction transaction = null;
   static Scanner sc = new Scanner(System.in);

    public static void createData() {
        Student s1 = new Student("Gauri", "Khake", "gaurikhake54321@gmail.com");

        Student s2 = new Student("Suyash", "Waghmare", "suyashwaghmare321@gmail.com");


        try (Session session = HibernateConfig.getSessionfactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(s1);
            session.persist(s2);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }


    private static void getAlldata() {
        try (Session session = HibernateConfig.getSessionfactory().openSession()) {
            List<Student> students = session.createQuery("from Student", Student.class).list();
            students.forEach(s -> System.out.println(s.getFirstName()));
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void deleteData() {
        try (Session session = HibernateConfig.getSessionfactory().openSession()) {
            transaction = session.beginTransaction();

            // Use HQL to delete student(s) by email
            System.out.println("Enter ID");
            int id = sc.nextInt();
            Query query = session.createQuery("DELETE FROM Student WHERE id =" + id);
            int i = query.executeUpdate();
            System.out.println("Student ID deleted" + i);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void retrieveSingleData() {
        try (Session session = HibernateConfig.getSessionfactory().openSession()) {
            transaction = session.beginTransaction();
            System.out.println("Enter Id");
            Student student = session.get(Student.class, sc.nextInt());
            System.out.println("Id : " + student.getId());
            System.out.println("FirstName: "+ student.getFirstName());
            System.out.println("LastName: "+ student.getLastName());
            System.out.println("Email: "+ student.getEmail());
            transaction.commit();;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void updateData()
    {
        try (Session session = HibernateConfig.getSessionfactory().openSession())
        {
            transaction = session.beginTransaction();

            System.out.println("Enter Id to be updated: ");
            Student student = session.get(Student.class, sc.nextInt());
            System.out.println("Enter firstname: ");
            student.setFirstName(sc.next());

            System.out.println("Enter lastname: ");
            student.setLastName(sc.next());

            System.out.println("Enter email: ");
            student.setEmail(sc.next());

            System.out.println("Id : " + student.getId());
            System.out.println("FirstName: "+ student.getFirstName());
            System.out.println("LastName: "+ student.getLastName());
            System.out.println("Email: "+ student.getEmail());

            transaction.commit();;
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {

//         createData();
//         getAlldata();
//         deleteData();
//         retrieveSingleData();
        updateData();
    }
}


