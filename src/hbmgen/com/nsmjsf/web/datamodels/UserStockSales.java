package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

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
 * UserStockSales generated by hbm2java
 */
@Entity
@Table(name = "user_stock_sales", catalog = "admin_nsmjsf")
public class UserStockSales implements java.io.Serializable {

	private Integer userStockSalesId;
	private User user;
	private UserStock userStock;
	private float userStockSalesPrice;
	private int userStockSalesUnit;
	private float userStockSalesProfit;
	private Date userStockSalesDate;

	public UserStockSales() {
	}

	public UserStockSales(User user, UserStock userStock,
			float userStockSalesPrice, int userStockSalesUnit,
			float userStockSalesProfit, Date userStockSalesDate) {
		this.user = user;
		this.userStock = userStock;
		this.userStockSalesPrice = userStockSalesPrice;
		this.userStockSalesUnit = userStockSalesUnit;
		this.userStockSalesProfit = userStockSalesProfit;
		this.userStockSalesDate = userStockSalesDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_stock_sales_id", unique = true, nullable = false)
	public Integer getUserStockSalesId() {
		return this.userStockSalesId;
	}

	public void setUserStockSalesId(Integer userStockSalesId) {
		this.userStockSalesId = userStockSalesId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_stock_sales_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_stock_sales_stock_id", nullable = false)
	public UserStock getUserStock() {
		return this.userStock;
	}

	public void setUserStock(UserStock userStock) {
		this.userStock = userStock;
	}

	@Column(name = "user_stock_sales_price", nullable = false, precision = 12, scale = 0)
	public float getUserStockSalesPrice() {
		return this.userStockSalesPrice;
	}

	public void setUserStockSalesPrice(float userStockSalesPrice) {
		this.userStockSalesPrice = userStockSalesPrice;
	}

	@Column(name = "user_stock_sales_unit", nullable = false)
	public int getUserStockSalesUnit() {
		return this.userStockSalesUnit;
	}

	public void setUserStockSalesUnit(int userStockSalesUnit) {
		this.userStockSalesUnit = userStockSalesUnit;
	}

	@Column(name = "user_stock_sales_profit", nullable = false, precision = 12, scale = 0)
	public float getUserStockSalesProfit() {
		return this.userStockSalesProfit;
	}

	public void setUserStockSalesProfit(float userStockSalesProfit) {
		this.userStockSalesProfit = userStockSalesProfit;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_stock_sales_date", nullable = false, length = 0)
	public Date getUserStockSalesDate() {
		return this.userStockSalesDate;
	}

	public void setUserStockSalesDate(Date userStockSalesDate) {
		this.userStockSalesDate = userStockSalesDate;
	}

}
