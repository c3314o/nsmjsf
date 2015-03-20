package com.nsmjsf.web.datamodels;

// Generated Mar 14, 2015 9:52:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Announcement generated by hbm2java
 */
@Entity
@Table(name = "announcement", catalog = "admin_nsmjsf")
public class Announcement implements java.io.Serializable {

	private Integer announcementId;
	private AnnouncementType announcementType;
	private Post post;
	private Date announcementDate;
	private Set<Auction> auctions = new HashSet<Auction>(0);
	private Set<AllotmentResult> allotmentResults = new HashSet<AllotmentResult>(
			0);
	private Set<Agm> agms = new HashSet<Agm>(0);
	private Set<BonusDividendApproved> bonusDividendApproveds = new HashSet<BonusDividendApproved>(
			0);
	private Set<CertificateDividendDistribution> certificateDividendDistributions = new HashSet<CertificateDividendDistribution>(
			0);
	private Set<Issue> issues = new HashSet<Issue>(0);
	private Set<MonthlyFinancialHighlight> monthlyFinancialHighlights = new HashSet<MonthlyFinancialHighlight>(
			0);

	public Announcement() {
	}

	public Announcement(AnnouncementType announcementType, Post post,
			Date announcementDate) {
		this.announcementType = announcementType;
		this.post = post;
		this.announcementDate = announcementDate;
	}

	public Announcement(
			AnnouncementType announcementType,
			Post post,
			Date announcementDate,
			Set<Auction> auctions,
			Set<AllotmentResult> allotmentResults,
			Set<Agm> agms,
			Set<BonusDividendApproved> bonusDividendApproveds,
			Set<CertificateDividendDistribution> certificateDividendDistributions,
			Set<Issue> issues,
			Set<MonthlyFinancialHighlight> monthlyFinancialHighlights) {
		this.announcementType = announcementType;
		this.post = post;
		this.announcementDate = announcementDate;
		this.auctions = auctions;
		this.allotmentResults = allotmentResults;
		this.agms = agms;
		this.bonusDividendApproveds = bonusDividendApproveds;
		this.certificateDividendDistributions = certificateDividendDistributions;
		this.issues = issues;
		this.monthlyFinancialHighlights = monthlyFinancialHighlights;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "announcement_id", unique = true, nullable = false)
	public Integer getAnnouncementId() {
		return this.announcementId;
	}

	public void setAnnouncementId(Integer announcementId) {
		this.announcementId = announcementId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_type_id", nullable = false)
	public AnnouncementType getAnnouncementType() {
		return this.announcementType;
	}

	public void setAnnouncementType(AnnouncementType announcementType) {
		this.announcementType = announcementType;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "announcement_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "announcement_date", nullable = false, length = 0)
	public Date getAnnouncementDate() {
		return this.announcementDate;
	}

	public void setAnnouncementDate(Date announcementDate) {
		this.announcementDate = announcementDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<Auction> getAuctions() {
		return this.auctions;
	}

	public void setAuctions(Set<Auction> auctions) {
		this.auctions = auctions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<AllotmentResult> getAllotmentResults() {
		return this.allotmentResults;
	}

	public void setAllotmentResults(Set<AllotmentResult> allotmentResults) {
		this.allotmentResults = allotmentResults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<Agm> getAgms() {
		return this.agms;
	}

	public void setAgms(Set<Agm> agms) {
		this.agms = agms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<BonusDividendApproved> getBonusDividendApproveds() {
		return this.bonusDividendApproveds;
	}

	public void setBonusDividendApproveds(
			Set<BonusDividendApproved> bonusDividendApproveds) {
		this.bonusDividendApproveds = bonusDividendApproveds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<CertificateDividendDistribution> getCertificateDividendDistributions() {
		return this.certificateDividendDistributions;
	}

	public void setCertificateDividendDistributions(
			Set<CertificateDividendDistribution> certificateDividendDistributions) {
		this.certificateDividendDistributions = certificateDividendDistributions;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcement")
	public Set<MonthlyFinancialHighlight> getMonthlyFinancialHighlights() {
		return this.monthlyFinancialHighlights;
	}

	public void setMonthlyFinancialHighlights(
			Set<MonthlyFinancialHighlight> monthlyFinancialHighlights) {
		this.monthlyFinancialHighlights = monthlyFinancialHighlights;
	}

}
