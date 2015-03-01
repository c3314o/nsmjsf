package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 3:31:39 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * FiscalYear generated by hbm2java
 */
@Entity
@Table(name = "fiscal_year", catalog = "admin_nsmjsf")
public class FiscalYear implements java.io.Serializable {

	private Integer fiscalYearId;
	private String fiscalYearLabel;
	private Set<Agm> agms = new HashSet<Agm>(0);
	private Set<BonusDividendApproved> bonusDividendApproveds = new HashSet<BonusDividendApproved>(
			0);
	private Set<BonusDividend> bonusDividends = new HashSet<BonusDividend>(0);
	private Set<FinancialHighlight> financialHighlights = new HashSet<FinancialHighlight>(
			0);
	private Set<FinancialReport> financialReports = new HashSet<FinancialReport>(
			0);
	private Set<MonthlyFinancialHighlight> monthlyFinancialHighlights = new HashSet<MonthlyFinancialHighlight>(
			0);
	private Set<Bod> bods = new HashSet<Bod>(0);
	private Set<CertificateDividendDistribution> certificateDividendDistributions = new HashSet<CertificateDividendDistribution>(
			0);

	public FiscalYear() {
	}

	public FiscalYear(String fiscalYearLabel) {
		this.fiscalYearLabel = fiscalYearLabel;
	}

	public FiscalYear(
			String fiscalYearLabel,
			Set<Agm> agms,
			Set<BonusDividendApproved> bonusDividendApproveds,
			Set<BonusDividend> bonusDividends,
			Set<FinancialHighlight> financialHighlights,
			Set<FinancialReport> financialReports,
			Set<MonthlyFinancialHighlight> monthlyFinancialHighlights,
			Set<Bod> bods,
			Set<CertificateDividendDistribution> certificateDividendDistributions) {
		this.fiscalYearLabel = fiscalYearLabel;
		this.agms = agms;
		this.bonusDividendApproveds = bonusDividendApproveds;
		this.bonusDividends = bonusDividends;
		this.financialHighlights = financialHighlights;
		this.financialReports = financialReports;
		this.monthlyFinancialHighlights = monthlyFinancialHighlights;
		this.bods = bods;
		this.certificateDividendDistributions = certificateDividendDistributions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "fiscal_year_id", unique = true, nullable = false)
	public Integer getFiscalYearId() {
		return this.fiscalYearId;
	}

	public void setFiscalYearId(Integer fiscalYearId) {
		this.fiscalYearId = fiscalYearId;
	}

	@Column(name = "fiscal_year_label", nullable = false)
	public String getFiscalYearLabel() {
		return this.fiscalYearLabel;
	}

	public void setFiscalYearLabel(String fiscalYearLabel) {
		this.fiscalYearLabel = fiscalYearLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<Agm> getAgms() {
		return this.agms;
	}

	public void setAgms(Set<Agm> agms) {
		this.agms = agms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<BonusDividendApproved> getBonusDividendApproveds() {
		return this.bonusDividendApproveds;
	}

	public void setBonusDividendApproveds(
			Set<BonusDividendApproved> bonusDividendApproveds) {
		this.bonusDividendApproveds = bonusDividendApproveds;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<BonusDividend> getBonusDividends() {
		return this.bonusDividends;
	}

	public void setBonusDividends(Set<BonusDividend> bonusDividends) {
		this.bonusDividends = bonusDividends;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<FinancialHighlight> getFinancialHighlights() {
		return this.financialHighlights;
	}

	public void setFinancialHighlights(
			Set<FinancialHighlight> financialHighlights) {
		this.financialHighlights = financialHighlights;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<FinancialReport> getFinancialReports() {
		return this.financialReports;
	}

	public void setFinancialReports(Set<FinancialReport> financialReports) {
		this.financialReports = financialReports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<MonthlyFinancialHighlight> getMonthlyFinancialHighlights() {
		return this.monthlyFinancialHighlights;
	}

	public void setMonthlyFinancialHighlights(
			Set<MonthlyFinancialHighlight> monthlyFinancialHighlights) {
		this.monthlyFinancialHighlights = monthlyFinancialHighlights;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<Bod> getBods() {
		return this.bods;
	}

	public void setBods(Set<Bod> bods) {
		this.bods = bods;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "fiscalYear")
	public Set<CertificateDividendDistribution> getCertificateDividendDistributions() {
		return this.certificateDividendDistributions;
	}

	public void setCertificateDividendDistributions(
			Set<CertificateDividendDistribution> certificateDividendDistributions) {
		this.certificateDividendDistributions = certificateDividendDistributions;
	}

}
