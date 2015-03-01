package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 3:31:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * NewLow generated by hbm2java
 */
@Entity
@Table(name = "new_low", catalog = "admin_nsmjsf")
public class NewLow implements java.io.Serializable {

	private Integer newLowId;
	private Company company;
	private Date newLowPreviousDate;
	private float newLowPreviousPrice;
	private Date newLowCurrentDate;
	private float newLowCurrentPrice;

	public NewLow() {
	}

	public NewLow(Company company, Date newLowPreviousDate,
			float newLowPreviousPrice, Date newLowCurrentDate,
			float newLowCurrentPrice) {
		this.company = company;
		this.newLowPreviousDate = newLowPreviousDate;
		this.newLowPreviousPrice = newLowPreviousPrice;
		this.newLowCurrentDate = newLowCurrentDate;
		this.newLowCurrentPrice = newLowCurrentPrice;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "new_low_id", unique = true, nullable = false)
	public Integer getNewLowId() {
		return this.newLowId;
	}

	public void setNewLowId(Integer newLowId) {
		this.newLowId = newLowId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "new_low_company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "new_low_previous_date", nullable = false, length = 0)
	public Date getNewLowPreviousDate() {
		return this.newLowPreviousDate;
	}

	public void setNewLowPreviousDate(Date newLowPreviousDate) {
		this.newLowPreviousDate = newLowPreviousDate;
	}

	@Column(name = "new_low_previous_price", nullable = false, precision = 12, scale = 0)
	public float getNewLowPreviousPrice() {
		return this.newLowPreviousPrice;
	}

	public void setNewLowPreviousPrice(float newLowPreviousPrice) {
		this.newLowPreviousPrice = newLowPreviousPrice;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "new_low_current_date", nullable = false, length = 0)
	public Date getNewLowCurrentDate() {
		return this.newLowCurrentDate;
	}

	public void setNewLowCurrentDate(Date newLowCurrentDate) {
		this.newLowCurrentDate = newLowCurrentDate;
	}

	@Column(name = "new_low_current_price", nullable = false, precision = 12, scale = 0)
	public float getNewLowCurrentPrice() {
		return this.newLowCurrentPrice;
	}

	public void setNewLowCurrentPrice(float newLowCurrentPrice) {
		this.newLowCurrentPrice = newLowCurrentPrice;
	}

}
