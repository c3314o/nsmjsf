package com.nsmjsf.web.datamodels;

// Generated Mar 14, 2015 9:52:11 PM by Hibernate Tools 3.4.0.CR1

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
 * Auction generated by hbm2java
 */
@Entity
@Table(name = "auction", catalog = "admin_nsmjsf")
public class Auction implements java.io.Serializable {

	private Integer auctionId;
	private Announcement announcement;
	private IssueManager issueManager;
	private Date auctionOpenDate;
	private Date auctionCloseDate;
	private Integer auctionOrdinaryQty;
	private Integer auctionPromoterQty;

	public Auction() {
	}

	public Auction(Announcement announcement, IssueManager issueManager) {
		this.announcement = announcement;
		this.issueManager = issueManager;
	}

	public Auction(Announcement announcement, IssueManager issueManager,
			Date auctionOpenDate, Date auctionCloseDate,
			Integer auctionOrdinaryQty, Integer auctionPromoterQty) {
		this.announcement = announcement;
		this.issueManager = issueManager;
		this.auctionOpenDate = auctionOpenDate;
		this.auctionCloseDate = auctionCloseDate;
		this.auctionOrdinaryQty = auctionOrdinaryQty;
		this.auctionPromoterQty = auctionPromoterQty;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "auction_id", unique = true, nullable = false)
	public Integer getAuctionId() {
		return this.auctionId;
	}

	public void setAuctionId(Integer auctionId) {
		this.auctionId = auctionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_announcement_id", nullable = false)
	public Announcement getAnnouncement() {
		return this.announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "auction_issue_manager_id", nullable = false)
	public IssueManager getIssueManager() {
		return this.issueManager;
	}

	public void setIssueManager(IssueManager issueManager) {
		this.issueManager = issueManager;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "auction_open_date", length = 0)
	public Date getAuctionOpenDate() {
		return this.auctionOpenDate;
	}

	public void setAuctionOpenDate(Date auctionOpenDate) {
		this.auctionOpenDate = auctionOpenDate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "auction_close_date", length = 0)
	public Date getAuctionCloseDate() {
		return this.auctionCloseDate;
	}

	public void setAuctionCloseDate(Date auctionCloseDate) {
		this.auctionCloseDate = auctionCloseDate;
	}

	@Column(name = "auction_ordinary_qty")
	public Integer getAuctionOrdinaryQty() {
		return this.auctionOrdinaryQty;
	}

	public void setAuctionOrdinaryQty(Integer auctionOrdinaryQty) {
		this.auctionOrdinaryQty = auctionOrdinaryQty;
	}

	@Column(name = "auction_promoter_qty")
	public Integer getAuctionPromoterQty() {
		return this.auctionPromoterQty;
	}

	public void setAuctionPromoterQty(Integer auctionPromoterQty) {
		this.auctionPromoterQty = auctionPromoterQty;
	}

}
