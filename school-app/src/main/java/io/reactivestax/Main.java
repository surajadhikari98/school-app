package io.reactivestax;

import io.reactivestax.entity.Course;
import io.reactivestax.entity.Enrollment;
import io.reactivestax.entity.EnrollmentId;
import io.reactivestax.entity.Student;
import io.reactivestax.utils.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {
    public static void main(String[] args) {
//        saveCourse();
        deleteCourse(1);
    }

    public static void saveCourse() {
        try (Session session = HibernateUtil.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();

                Course course = new Course();
                course.setCourseName("Physics");

                session.persist(course);

                Student student = new Student();
                student.setName("Advhik");

                session.persist(student);

                Enrollment enrollment = new Enrollment();
                EnrollmentId enrollmentId = new EnrollmentId(course.getId(), student.getId());
                enrollment.setId(enrollmentId);
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setGrade(99.00);

                session.persist(enrollment);

//                course.setEnrollments(enrollment);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void updateCourseAndStudent(Course course, Student student) {
        try (Session session = HibernateUtil.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Course course1 = session.get(Course.class, course.getId());

                course1.setCourseName("Computer");

                session.persist(course);

                Student student1 = session.get(Student.class, student.getId());
                student1.setName("Avaa");

                session.persist(student);

                Enrollment enrollment = new Enrollment();
                EnrollmentId enrollmentId = new EnrollmentId(course.getId(), student.getId());
                enrollment.setId(enrollmentId);
                enrollment.setStudent(student);
                enrollment.setCourse(course);
                enrollment.setGrade(99.00);

                session.persist(enrollment);

//                course.setEnrollments(enrollment);

                transaction.commit();
            } catch (Exception e) {
                if (transaction != null) {
                    transaction.rollback();
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public static void deleteCourse(int courseId) {
        try (Session session = HibernateUtil.getInstance().getSession()) {
            Transaction transaction = null;
            try {
                transaction = session.beginTransaction();
                Course course1 = session.get(Course.class, courseId);
                session.remove(course1);
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
