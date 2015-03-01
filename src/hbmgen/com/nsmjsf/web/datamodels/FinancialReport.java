package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

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
 * FinancialReport generated by hbm2java
 */
@Entity
@Table(name = "financial_report", catalog = "admin_nsmjsf")
public class FinancialReport implements java.io.Serializable {

	private Integer financialReportId;
	private Company company;
	private FiscalYear fiscalYear;
	private Quarter quarter;
	private AuditStatus auditStatus;
	private Post post;
	private String financialReportLabel;
	private String financialReportEnteryBy;
	private Date financialReportEntryDate;
	private Set<ProfitLoss> profitLosses = new HashSet<ProfitLoss>(0);
	private Set<KeyRatio> keyRatios = new HashSet<KeyRatio>(0);
	private Set<Balancesheet> balancesheets = new HashSet<Balancesheet>(0);

	public FinancialReport() {
	}

	public FinancialReport(Company company, FiscalYear fiscalYear,
			Quarter quarter, AuditStatus auditStatus, Post post,
			String financialReportLabel, String financialReportEnteryBy,
			Date financialReportEntryDate) {
		this.company = company;
		this.fiscalYear = fiscalYear;
		this.quarter = quarter;
		this.auditStatus = auditStatus;
		this.post = post;
		this.financialReportLabel = financialReportLabel;
		this.financialReportEnteryBy = financialReportEnteryBy;
		this.financialReportEntryDate = financialReportEntryDate;
	}

	public FinancialReport(Company company, FiscalYear fiscalYear,
			Quarter quarter, AuditStatus auditStatus, Post post,
			String financialReportLabel, String financialReportEnteryBy,
			Date financialReportEntryDate, Set<ProfitLoss> profitLosses,
			Set<KeyRatio> keyRatios, Set<Balancesheet> balancesheets) {
		this.company = company;
		this.fiscalYear = fiscalYear;
		this.quarter = quarter;
		this.auditStatus = auditStatus;
		this.post = post;
		this.financialReportLabel = financialReportLabel;
		this.financialReportEnteryBy = financialReportEnteryBy;
		this.financialReportEntryDate = financialReportEntryDate;
		this.profitLosses = profitLosses;
		this.keyRatios = keyRatios;
		this.balancesheets = balancesheets;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "financial_report_id", unique = true, nullable = false)
	public Integer getFinancialReportId() {
		return this.financialReportId;
	}

	public void setFinancialReportId(Integer financialReportId) {
		this.financialReportId = financialReportId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_report_company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_report_fiscal_year_id", nullable = false)
	public FiscalYear getFiscalYear() {
		return this.fiscalYear;
	}

	public void setFiscalYear(FiscalYear fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_report_quarter_id", nullable = false)
	public Quarter getQuarter() {
		return this.quarter;
	}

	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_report_audited_status_id", nullable = false)
	public AuditStatus getAuditStatus() {
		return this.auditStatus;
	}

	public void setAuditStatus(AuditStatus auditStatus) {
		this.auditStatus = auditStatus;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "financial_report_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Column(name = "financial_report_label", nullable = false)
	public String getFinancialReportLabel() {
		return this.financialReportLabel;
	}

	public void setFinancialReportLabel(String financialReportLabel) {
		this.financialReportLabel = financialReportLabel;
	}

	@Column(name = "financial_report_entery_by", nullable = false)
	public String getFinancialReportEnteryBy() {
		return this.financialReportEnteryBy;
	}

	public void setFinancialReportEnteryBy(String financialReportEnteryBy) {
		this.financialReportEnteryBy = financialReportEnteryBy;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "financial_report_entry_date", nullable = false, length = 0)
	public Date getFinancialReportEntryDate() {
		return this.financialReportEntryDate;
	}

	public void setFinancialReportEntryDate(Date financialReportEntryDate) {
		this.financialReportEntryDate = financialReportEntryDate;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "financialReport")
	public Set<ProfitLoss> getProfitLosses() {
		return this.profitLosses;
	}

	public void setProfitLosses(Set<ProfitLoss> profitLosses) {
		this.profitLosses = profitLosses;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "financialReport")
	public Set<KeyRatio> getKeyRatios() {
		return this.keyRatios;
	}

	public void setKeyRatios(Set<KeyRatio> keyRatios) {
		this.keyRatios = keyRatios;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "financialReport")
	public Set<Balancesheet> getBalancesheets() {
		return this.balancesheets;
	}

	public void setBalancesheets(Set<Balancesheet> balancesheets) {
		this.balancesheets = balancesheets;
	}

}
