package com.entityClasses;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int bId;
	private String title;
	private String author;
	private boolean isAvailable;

	public Book() {
		this.isAvailable = true;
	}

	public Book(int bId, String title, String author, boolean isAvailable) {
		super();
		this.bId = bId;
		this.title = title;
		this.author = author;
		this.isAvailable = isAvailable;
	}

	public int getbId() {
		return bId;
	}

	public void setbId(int bId) {
		this.bId = bId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	@Override
	public String toString() {
		return "Book{id=" + bId + ", title='" + title + "', author='" + author + "', available=" + isAvailable + "}";
	}
}