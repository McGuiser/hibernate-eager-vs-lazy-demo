package com.corey.hibernate.demo;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.corey.hibernate.demo.entity.Instructor;
import com.corey.hibernate.demo.entity.InstructorDetail;
import com.corey.hibernate.demo.entity.Student;

public class GetInstructorDetailDemo {

	public static void main(String[] args) {

		// Create session factory
		SessionFactory factory = new Configuration()
				.configure("hibernate.cfg.xml")
				.addAnnotatedClass(Instructor.class)
				.addAnnotatedClass(InstructorDetail.class)
				.buildSessionFactory();
		
		// Create session
		Session session = factory.getCurrentSession();
		
		try {

			// Start a transaction
			session.beginTransaction();
			
			// Get the instructor detail object
			int theId = 1;
			InstructorDetail tempInstructorDetail = 
					session.get(InstructorDetail.class, theId);
			
			// Print the instructor detail
			System.out.println("tempInstructorDetail: " + tempInstructorDetail);
			
			// Print the associated instructor
			System.out.println("the assoiciated instructor: " + 
								tempInstructorDetail.getInstructor());
			
			// Commit transaction
			session.getTransaction().commit();
			System.out.println("Done!");
			
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// Handle connection leak
			session.close();
			
			factory.close();
		}

	}

}
