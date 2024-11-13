package com.DAOClasses;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entityClasses.Book;
import com.entityClasses.Loan;
import com.entityClasses.Member;

public class LoanDAO {

	// Function for adding a new borrowing statement
	public static void issueBook(int bookId, int memberId) {
		try {
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			Book book = session.get(Book.class, bookId);
			Member member = session.get(Member.class, memberId);

			if (book != null && member != null && book.isAvailable()) {
				Loan loan = new Loan();
				loan.setBook(book);
				loan.setMember(member);
				loan.setIssueDate(new Date());
				book.setAvailable(false);

				session.save(loan);
				session.update(book);
				transaction.commit();
				session.close();
				sf.close();
				System.out.println("Book issued successfully!");
			} else {
				System.out.println("Book is unavailable or invalid member ID.");
			}
		} catch(Exception e) {
			e.getMessage();
		}
	}

	// Function for returning a borrowed book
	public static void returnBook(int loanId) {
		
		try {
			
			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();
			
			Loan loan = session.get(Loan.class, loanId);

			if (loan != null && loan.getReturnDate() == null) {
				loan.setReturnDate(new Date());
				loan.getBook().setAvailable(true);

				session.update(loan);
				session.update(loan.getBook());
				
				transaction.commit();
				session.close();
				sf.close();
				System.out.println("Book returned successfully!");
			}
			else {
				System.out.println("Loan ID invalid or book already returned.");
			}
			
		} 
		catch(Exception e) {
			e.getMessage();
		}
	}

	// Function for getting the list of the borrowing statements available in
	// library
	public static List<Loan> listLoans() {
		try  (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
			
			return session.createQuery("from Loan", Loan.class).list();
		}
	}
}