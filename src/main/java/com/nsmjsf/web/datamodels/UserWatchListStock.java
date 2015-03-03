package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * UserWatchListStock generated by hbm2java
 */
@Entity
@Table(name = "user_watch_list_stock", catalog = "admin_nsmjsf")
public class UserWatchListStock implements java.io.Serializable {

	private Integer userWatchListStockId;
	private UserWatchList userWatchList;
	private Company company;

	public UserWatchListStock() {
	}

	public UserWatchListStock(UserWatchList userWatchList, Company company) {
		this.userWatchList = userWatchList;
		this.company = company;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_watch_list_stock_id", unique = true, nullable = false)
	public Integer getUserWatchListStockId() {
		return this.userWatchListStockId;
	}

	public void setUserWatchListStockId(Integer userWatchListStockId) {
		this.userWatchListStockId = userWatchListStockId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_watch_list_id", nullable = false)
	public UserWatchList getUserWatchList() {
		return this.userWatchList;
	}

	public void setUserWatchList(UserWatchList userWatchList) {
		this.userWatchList = userWatchList;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
