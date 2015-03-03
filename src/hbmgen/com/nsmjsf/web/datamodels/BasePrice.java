package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

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
 * BasePrice generated by hbm2java
 */
@Entity
@Table(name = "base_price", catalog = "admin_nsmjsf")
public class BasePrice implements java.io.Serializable {

	private Integer basePriceId;
	private Company company;
	private float basePriceValue;
	private Date basePriceDate;

	public BasePrice() {
	}

	public BasePrice(Company company, float basePriceValue, Date basePriceDate) {
		this.company = company;
		this.basePriceValue = basePriceValue;
		this.basePriceDate = basePriceDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "base_price_id", unique = true, nullable = false)
	public Integer getBasePriceId() {
		return this.basePriceId;
	}

	public void setBasePriceId(Integer basePriceId) {
		this.basePriceId = basePriceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "base_price_company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "base_price_value", nullable = false, precision = 12, scale = 0)
	public float getBasePriceValue() {
		return this.basePriceValue;
	}

	public void setBasePriceValue(float basePriceValue) {
		this.basePriceValue = basePriceValue;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "base_price_date", nullable = false, length = 0)
	public Date getBasePriceDate() {
		return this.basePriceDate;
	}

	public void setBasePriceDate(Date basePriceDate) {
		this.basePriceDate = basePriceDate;
	}

}
