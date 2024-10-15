package io.reactivestax.entity.manytomany;
import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import  io.reactivestax.entity.manytomany.Course;

import java.util.HashSet;
import java.util.Set;

public class Runner {

    public static void main(String[] args) {
        saveCourse();
    }

    public static void saveCourse() {
        try (Session session = HibernateUtil.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Set<Student> studentSet = new HashSet<>();

               Course course = new Course();
                course.setCourseName("BIOLOGY");

//                session.persist(course);

               Student student = new Student();
                student.setName("Advhik");
                studentSet.add(student);
                Student student1 = new Student();
                student1.setName("Peppeie");
                studentSet.add(student1);

                course.setStudent(studentSet);

                session.persist(course);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}
