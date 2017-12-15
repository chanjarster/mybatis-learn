package me.chanjar.mybatislearn.spring.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Book {

	private Integer id;
	private String title;
	private String subtitle;
	private Set<Author> authors = new HashSet<Author>();
	private Date publicationDate;

	public Set<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<Author> authors) {
		this.authors = authors;
	}

	public Book() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

  @Override
  public String toString() {
    return "Book{" +
        "id=" + id +
        ", title='" + title + '\'' +
        ", subtitle='" + subtitle + '\'' +
        ", authors=" + authors +
        ", publicationDate=" + publicationDate +
        '}';
  }
}
