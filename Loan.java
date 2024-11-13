package com.entityClasses;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Loan {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	private Book book;

	@ManyToOne
	private Member member;

	private Date issueDate;
	private Date returnDate;

	public Loan() {
		super();
	}

	public Loan(int id, Book book, Member member, Date issueDate, Date returnDate) {
		super();
		this.id = id;
		this.book = book;
		this.member = member;
		this.issueDate = issueDate;
		this.returnDate = returnDate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	@Override
	public String toString() {
		return "Loan{id=" + id + ", book=" + book + ", member=" + member + ", issueDate=" + issueDate + ", returnDate="
				+ returnDate + "}";
	}
}