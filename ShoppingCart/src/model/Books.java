package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the BOOKS database table.
 * 
 */
@Entity
@Table(name="Books", schema="TESTDB")
@NamedQuery(name="Books.findAll", query="SELECT b FROM Books b")
public class Books implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	private String author;

	private String description;

	private String image;

	private BigDecimal price;

	private String title;

	public Books() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return this.image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public BigDecimal getPrice() {
		return this.price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}