package com.DAOClasses;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.entityClasses.Book;

public class BookDAO {

	// Function for adding a new book
	public static void addBook(Book book) {
		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			session.save(book);

			transaction.commit();
			session.close();
			sf.close();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Function for getting the list of the books available in library
	public static List<Book> listBooks() {
		try (Session session = new Configuration().configure().buildSessionFactory().openSession()) {
			return session.createQuery("from Book", Book.class).list();
		}
	}

	// Function for updating an existing book information
	public static void updateBook(int bookId, String updatedAuthor, String updatedTitle) {
		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Book updatedBook = session.get(Book.class, bookId);

			updatedBook.setTitle(updatedTitle);
			updatedBook.setAuthor(updatedAuthor);
			session.update(updatedBook);

			transaction.commit();
			session.close();
			sf.close();

		} catch (Exception e) {
			e.getMessage();
		}
	}

	// Function for deleting an existing book
	public static void deleteBook(int bookId) {

		try {

			Configuration cfg = new Configuration();
			cfg.configure("hibernate.cfg.xml");
			SessionFactory sf = cfg.buildSessionFactory();
			Session session = sf.openSession();
			Transaction transaction = session.beginTransaction();

			Book deletedBook = session.get(Book.class, bookId);

			if (deletedBook != null) {
				session.delete(deletedBook);
			} else {
				System.out.println("Book doesn't exists!!!");
			}

			transaction.commit();
			session.close();
			sf.close();
		} catch (Exception e) {
			e.getMessage();
		}
	}
}