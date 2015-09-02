package model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the SHOPPINGCOMMENTS database table.
 * 
 */
@Entity
@Table(name="SHOPPINGCOMMENTS", schema="TESTDB")
@NamedQuery(name="Shoppingcomment.findAll", query="SELECT s FROM Shoppingcomment s")
public class Shoppingcomment implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;

	@Temporal(TemporalType.DATE)
	private Date commentdate;

	private BigDecimal stars;

	private String username;

	public Shoppingcomment() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getCommentdate() {
		return this.commentdate;
	}

	public void setCommentdate(Date commentdate) {
		this.commentdate = commentdate;
	}

	public BigDecimal getStars() {
		return this.stars;
	}

	public void setStars(BigDecimal stars) {
		this.stars = stars;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}