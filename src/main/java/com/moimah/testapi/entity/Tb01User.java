package com.moimah.testapi.entity;


import java.util.Date;
import javax.persistence.*;

/**
 * Tb01User JPA Entity
 */
@Entity
@Table(name = "tb01_user")
public class Tb01User implements java.io.Serializable {

	private Long tb01UserId;
	private String tb01Name;
	private String tb01Surname;
	private Boolean tb01Active;
	private String tb01Email;
	private String tb01City;
	private Date tb01Dob;
	private Date tb01CreatedOn;

	public Tb01User() {
	}

	public Tb01User(Long tb01UserId) {
		this.tb01UserId = tb01UserId;
	}


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "tb01_user_id", unique = true, nullable = false)
	public Long getTb01UserId() {
		return this.tb01UserId;
	}

	public void setTb01UserId(Long tb01UserId) {
		this.tb01UserId = tb01UserId;
	}

	@Column(name = "tb01_name")
	public String getTb01Name() {
		return this.tb01Name;
	}

	public void setTb01Name(String tb01Name) {
		this.tb01Name = tb01Name;
	}

	@Column(name = "tb01_surname")
	public String getTb01Surname() {
		return tb01Surname;
	}

	public void setTb01Surname(String tb01Surname) {
		this.tb01Surname = tb01Surname;
	}

	@Column(name = "tb01_active")
	public Boolean getTb01Active() {
		return this.tb01Active;
	}

	public void setTb01Active(Boolean tb01Active) {
		this.tb01Active = tb01Active;
	}

	@Column(name = "tb01_email", length = 500)
	public String getTb01Email() {
		return this.tb01Email;
	}

	public void setTb01Email(String tb01Email) {
		this.tb01Email = tb01Email;
	}

	@Column(name = "tb01_city")
	public String getTb01City() {
		return this.tb01City;
	}

	public void setTb01City(String tb01City) {
		this.tb01City = tb01City;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tb01_dob", length = 10)
	public Date getTb01Dob() {
		return this.tb01Dob;
	}

	public void setTb01Dob(Date tb01Dob) {
		this.tb01Dob = tb01Dob;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "tb01_created_on", length = 10)
	public Date getTb01CreatedOn() {
		return this.tb01CreatedOn;
	}

	public void setTb01CreatedOn(Date tb01CreatedOn) {
		this.tb01CreatedOn = tb01CreatedOn;
	}

}
