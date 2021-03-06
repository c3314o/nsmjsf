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
 * TodaysPrice generated by hbm2java
 */
@Entity
@Table(name = "todays_price", catalog = "admin_nsmjsf")
public class TodaysPrice implements java.io.Serializable {

	private Integer todaysPriceId;
	private Post post;
	private Company company;
	private int todaysPriceTransactionCount;
	private float odaysPriceOpen;
	private float todaysPriceHigh;
	private float todaysPriceLow;
	private float todaysPriceClose;
	private float todaysPricePrevious;
	private int todaysPriceTradedVolume;
	private float todaysPriceTradedAmount;
	private float todaysPriceDiff;

	public TodaysPrice() {
	}

	public TodaysPrice(Post post, Company company,
			int todaysPriceTransactionCount, float odaysPriceOpen,
			float todaysPriceHigh, float todaysPriceLow,
			float todaysPriceClose, float todaysPricePrevious,
			int todaysPriceTradedVolume, float todaysPriceTradedAmount,
			float todaysPriceDiff) {
		this.post = post;
		this.company = company;
		this.todaysPriceTransactionCount = todaysPriceTransactionCount;
		this.odaysPriceOpen = odaysPriceOpen;
		this.todaysPriceHigh = todaysPriceHigh;
		this.todaysPriceLow = todaysPriceLow;
		this.todaysPriceClose = todaysPriceClose;
		this.todaysPricePrevious = todaysPricePrevious;
		this.todaysPriceTradedVolume = todaysPriceTradedVolume;
		this.todaysPriceTradedAmount = todaysPriceTradedAmount;
		this.todaysPriceDiff = todaysPriceDiff;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "todays_price_id", unique = true, nullable = false)
	public Integer getTodaysPriceId() {
		return this.todaysPriceId;
	}

	public void setTodaysPriceId(Integer todaysPriceId) {
		this.todaysPriceId = todaysPriceId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "todays_price_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "todays_price_company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "todays_price_transaction_count", nullable = false)
	public int getTodaysPriceTransactionCount() {
		return this.todaysPriceTransactionCount;
	}

	public void setTodaysPriceTransactionCount(int todaysPriceTransactionCount) {
		this.todaysPriceTransactionCount = todaysPriceTransactionCount;
	}

	@Column(name = "odays_price_open", nullable = false, precision = 12, scale = 0)
	public float getOdaysPriceOpen() {
		return this.odaysPriceOpen;
	}

	public void setOdaysPriceOpen(float odaysPriceOpen) {
		this.odaysPriceOpen = odaysPriceOpen;
	}

	@Column(name = "todays_price_high", nullable = false, precision = 12, scale = 0)
	public float getTodaysPriceHigh() {
		return this.todaysPriceHigh;
	}

	public void setTodaysPriceHigh(float todaysPriceHigh) {
		this.todaysPriceHigh = todaysPriceHigh;
	}

	@Column(name = "todays_price_low", nullable = false, precision = 12, scale = 0)
	public float getTodaysPriceLow() {
		return this.todaysPriceLow;
	}

	public void setTodaysPriceLow(float todaysPriceLow) {
		this.todaysPriceLow = todaysPriceLow;
	}

	@Column(name = "todays_price_close", nullable = false, precision = 12, scale = 0)
	public float getTodaysPriceClose() {
		return this.todaysPriceClose;
	}

	public void setTodaysPriceClose(float todaysPriceClose) {
		this.todaysPriceClose = todaysPriceClose;
	}

	@Column(name = "todays_price_previous", nullable = false, precision = 12, scale = 0)
	public float getTodaysPricePrevious() {
		return this.todaysPricePrevious;
	}

	public void setTodaysPricePrevious(float todaysPricePrevious) {
		this.todaysPricePrevious = todaysPricePrevious;
	}

	@Column(name = "todays_price_traded_volume", nullable = false)
	public int getTodaysPriceTradedVolume() {
		return this.todaysPriceTradedVolume;
	}

	public void setTodaysPriceTradedVolume(int todaysPriceTradedVolume) {
		this.todaysPriceTradedVolume = todaysPriceTradedVolume;
	}

	@Column(name = "todays_price_traded_amount", nullable = false, precision = 12, scale = 0)
	public float getTodaysPriceTradedAmount() {
		return this.todaysPriceTradedAmount;
	}

	public void setTodaysPriceTradedAmount(float todaysPriceTradedAmount) {
		this.todaysPriceTradedAmount = todaysPriceTradedAmount;
	}

	@Column(name = "todays_price_diff", nullable = false, precision = 12, scale = 0)
	public float getTodaysPriceDiff() {
		return this.todaysPriceDiff;
	}

	public void setTodaysPriceDiff(float todaysPriceDiff) {
		this.todaysPriceDiff = todaysPriceDiff;
	}

}
