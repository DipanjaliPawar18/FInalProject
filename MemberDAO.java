package com.DAOClasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entityClasses.Member;

public class MemberDAO {

	// Function for adding a new member
	public static void registerMember(Member member) {

		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(member);

			transaction.commit();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Function for getting the list of the members available in library
	public static List<Member> listMembers() {
		try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
			return session.createQuery("from Member", Member.class).list();
		}
	}

	// Function for updating an existing member information
	public static void updateMember(int newId, String newName, String newEmail) {

		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Member updatedMember = session.get(Member.class,newId);
			
			updatedMember.setName(newName);
			updatedMember.setEmail(newEmail);

			transaction.commit();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
	
	// Function for deleting an existing member
	public static void deleteMember(int memberId) {

		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Member deletedMember = session.get(Member.class,memberId);

			if(deletedMember != null) {
				session.delete(deletedMember);
			} 
			else {
				System.out.println("Member doesn't exists!!!");
			}

			transaction.commit();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}