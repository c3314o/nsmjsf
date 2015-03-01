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
 * UserEnergySales generated by hbm2java
 */
@Entity
@Table(name = "user_energy_sales", catalog = "admin_nsmjsf")
public class UserEnergySales implements java.io.Serializable {

	private Integer userEnergySalesId;
	private UserEnergy userEnergy;
	private float userEnergySalesPrice;
	private float userEnergySalesUnitsSold;
	private double userEnergySalesProfitLoss;
	private Date userEnergySalesDate;

	public UserEnergySales() {
	}

	public UserEnergySales(UserEnergy userEnergy, float userEnergySalesPrice,
			float userEnergySalesUnitsSold, double userEnergySalesProfitLoss,
			Date userEnergySalesDate) {
		this.userEnergy = userEnergy;
		this.userEnergySalesPrice = userEnergySalesPrice;
		this.userEnergySalesUnitsSold = userEnergySalesUnitsSold;
		this.userEnergySalesProfitLoss = userEnergySalesProfitLoss;
		this.userEnergySalesDate = userEnergySalesDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_energy_sales_id", unique = true, nullable = false)
	public Integer getUserEnergySalesId() {
		return this.userEnergySalesId;
	}

	public void setUserEnergySalesId(Integer userEnergySalesId) {
		this.userEnergySalesId = userEnergySalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_energy_id", nullable = false)
	public UserEnergy getUserEnergy() {
		return this.userEnergy;
	}

	public void setUserEnergy(UserEnergy userEnergy) {
		this.userEnergy = userEnergy;
	}

	@Column(name = "user_energy_sales_price", nullable = false, precision = 12, scale = 0)
	public float getUserEnergySalesPrice() {
		return this.userEnergySalesPrice;
	}

	public void setUserEnergySalesPrice(float userEnergySalesPrice) {
		this.userEnergySalesPrice = userEnergySalesPrice;
	}

	@Column(name = "user_energy_sales_units_sold", nullable = false, precision = 12, scale = 0)
	public float getUserEnergySalesUnitsSold() {
		return this.userEnergySalesUnitsSold;
	}

	public void setUserEnergySalesUnitsSold(float userEnergySalesUnitsSold) {
		this.userEnergySalesUnitsSold = userEnergySalesUnitsSold;
	}

	@Column(name = "user_energy_sales_profit_loss", nullable = false, precision = 22, scale = 0)
	public double getUserEnergySalesProfitLoss() {
		return this.userEnergySalesProfitLoss;
	}

	public void setUserEnergySalesProfitLoss(double userEnergySalesProfitLoss) {
		this.userEnergySalesProfitLoss = userEnergySalesProfitLoss;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "user_energy_sales_date", nullable = false, length = 0)
	public Date getUserEnergySalesDate() {
		return this.userEnergySalesDate;
	}

	public void setUserEnergySalesDate(Date userEnergySalesDate) {
		this.userEnergySalesDate = userEnergySalesDate;
	}

}
