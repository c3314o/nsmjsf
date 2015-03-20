package com.nsmjsf.web.datamodels;

// Generated Mar 14, 2015 9:52:11 PM by Hibernate Tools 3.4.0.CR1

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

/**
 * Company generated by hbm2java
 */
@Entity
@Table(name = "company", catalog = "admin_nsmjsf")
public class Company implements java.io.Serializable {

	private Integer companyId;
	private CompanyDetail companyDetail;
	private Post post;
	private Sector sector;
	private String companyQuote;
	private String companyLabel;
	private String companyName;
	private boolean companyListed;
	private Set<Interview> interviews = new HashSet<Interview>(0);
	private Set<Agm> agms = new HashSet<Agm>(0);
	private Set<FinancialReport> financialReports = new HashSet<FinancialReport>(
			0);
	private Set<UserStock> userStocks = new HashSet<UserStock>(0);
	private Set<Bod> bods = new HashSet<Bod>(0);
	private Set<MapPostCompany> mapPostCompanies = new HashSet<MapPostCompany>(
			0);
	private Set<AllotmentResult> allotmentResults = new HashSet<AllotmentResult>(
			0);
	private Set<UserWatchListStock> userWatchListStocks = new HashSet<UserWatchListStock>(
			0);
	private Set<TodaysPrice> todaysPrices = new HashSet<TodaysPrice>(0);
	private Set<NewLow> newLows = new HashSet<NewLow>(0);
	private Set<Issue> issues = new HashSet<Issue>(0);
	private Set<LatestPrice> latestPrices = new HashSet<LatestPrice>(0);
	private Set<SemiYearAverage> semiYearAverages = new HashSet<SemiYearAverage>(
			0);
	private Set<NewHigh> newHighs = new HashSet<NewHigh>(0);
	private Set<BonusDividend> bonusDividends = new HashSet<BonusDividend>(0);
	private Set<FinancialHighlight> financialHighlights = new HashSet<FinancialHighlight>(
			0);
	private Set<Floorsheet> floorsheets = new HashSet<Floorsheet>(0);
	private Set<BasePrice> basePrices = new HashSet<BasePrice>(0);

	public Company() {
	}

	public Company(CompanyDetail companyDetail, Post post, Sector sector,
			String companyQuote, String companyLabel, String companyName,
			boolean companyListed) {
		this.companyDetail = companyDetail;
		this.post = post;
		this.sector = sector;
		this.companyQuote = companyQuote;
		this.companyLabel = companyLabel;
		this.companyName = companyName;
		this.companyListed = companyListed;
	}

	public Company(CompanyDetail companyDetail, Post post, Sector sector,
			String companyQuote, String companyLabel, String companyName,
			boolean companyListed, Set<Interview> interviews, Set<Agm> agms,
			Set<FinancialReport> financialReports, Set<UserStock> userStocks,
			Set<Bod> bods, Set<MapPostCompany> mapPostCompanies,
			Set<AllotmentResult> allotmentResults,
			Set<UserWatchListStock> userWatchListStocks,
			Set<TodaysPrice> todaysPrices, Set<NewLow> newLows,
			Set<Issue> issues, Set<LatestPrice> latestPrices,
			Set<SemiYearAverage> semiYearAverages, Set<NewHigh> newHighs,
			Set<BonusDividend> bonusDividends,
			Set<FinancialHighlight> financialHighlights,
			Set<Floorsheet> floorsheets, Set<BasePrice> basePrices) {
		this.companyDetail = companyDetail;
		this.post = post;
		this.sector = sector;
		this.companyQuote = companyQuote;
		this.companyLabel = companyLabel;
		this.companyName = companyName;
		this.companyListed = companyListed;
		this.interviews = interviews;
		this.agms = agms;
		this.financialReports = financialReports;
		this.userStocks = userStocks;
		this.bods = bods;
		this.mapPostCompanies = mapPostCompanies;
		this.allotmentResults = allotmentResults;
		this.userWatchListStocks = userWatchListStocks;
		this.todaysPrices = todaysPrices;
		this.newLows = newLows;
		this.issues = issues;
		this.latestPrices = latestPrices;
		this.semiYearAverages = semiYearAverages;
		this.newHighs = newHighs;
		this.bonusDividends = bonusDividends;
		this.financialHighlights = financialHighlights;
		this.floorsheets = floorsheets;
		this.basePrices = basePrices;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "company_id", unique = true, nullable = false)
	public Integer getCompanyId() {
		return this.companyId;
	}

	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_details_id", nullable = false)
	public CompanyDetail getCompanyDetail() {
		return this.companyDetail;
	}

	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comapny_sector_id", nullable = false)
	public Sector getSector() {
		return this.sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	@Column(name = "company_quote", nullable = false, length = 25)
	public String getCompanyQuote() {
		return this.companyQuote;
	}

	public void setCompanyQuote(String companyQuote) {
		this.companyQuote = companyQuote;
	}

	@Column(name = "company_label", nullable = false)
	public String getCompanyLabel() {
		return this.companyLabel;
	}

	public void setCompanyLabel(String companyLabel) {
		this.companyLabel = companyLabel;
	}

	@Column(name = "company_name", nullable = false)
	public String getCompanyName() {
		return this.companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	@Column(name = "company_listed", nullable = false)
	public boolean isCompanyListed() {
		return this.companyListed;
	}

	public void setCompanyListed(boolean companyListed) {
		this.companyListed = companyListed;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Interview> getInterviews() {
		return this.interviews;
	}

	public void setInterviews(Set<Interview> interviews) {
		this.interviews = interviews;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Agm> getAgms() {
		return this.agms;
	}

	public void setAgms(Set<Agm> agms) {
		this.agms = agms;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<FinancialReport> getFinancialReports() {
		return this.financialReports;
	}

	public void setFinancialReports(Set<FinancialReport> financialReports) {
		this.financialReports = financialReports;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<UserStock> getUserStocks() {
		return this.userStocks;
	}

	public void setUserStocks(Set<UserStock> userStocks) {
		this.userStocks = userStocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Bod> getBods() {
		return this.bods;
	}

	public void setBods(Set<Bod> bods) {
		this.bods = bods;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<MapPostCompany> getMapPostCompanies() {
		return this.mapPostCompanies;
	}

	public void setMapPostCompanies(Set<MapPostCompany> mapPostCompanies) {
		this.mapPostCompanies = mapPostCompanies;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<AllotmentResult> getAllotmentResults() {
		return this.allotmentResults;
	}

	public void setAllotmentResults(Set<AllotmentResult> allotmentResults) {
		this.allotmentResults = allotmentResults;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<UserWatchListStock> getUserWatchListStocks() {
		return this.userWatchListStocks;
	}

	public void setUserWatchListStocks(
			Set<UserWatchListStock> userWatchListStocks) {
		this.userWatchListStocks = userWatchListStocks;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<TodaysPrice> getTodaysPrices() {
		return this.todaysPrices;
	}

	public void setTodaysPrices(Set<TodaysPrice> todaysPrices) {
		this.todaysPrices = todaysPrices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<NewLow> getNewLows() {
		return this.newLows;
	}

	public void setNewLows(Set<NewLow> newLows) {
		this.newLows = newLows;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Issue> getIssues() {
		return this.issues;
	}

	public void setIssues(Set<Issue> issues) {
		this.issues = issues;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<LatestPrice> getLatestPrices() {
		return this.latestPrices;
	}

	public void setLatestPrices(Set<LatestPrice> latestPrices) {
		this.latestPrices = latestPrices;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<SemiYearAverage> getSemiYearAverages() {
		return this.semiYearAverages;
	}

	public void setSemiYearAverages(Set<SemiYearAverage> semiYearAverages) {
		this.semiYearAverages = semiYearAverages;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<NewHigh> getNewHighs() {
		return this.newHighs;
	}

	public void setNewHighs(Set<NewHigh> newHighs) {
		this.newHighs = newHighs;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<BonusDividend> getBonusDividends() {
		return this.bonusDividends;
	}

	public void setBonusDividends(Set<BonusDividend> bonusDividends) {
		this.bonusDividends = bonusDividends;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<FinancialHighlight> getFinancialHighlights() {
		return this.financialHighlights;
	}

	public void setFinancialHighlights(
			Set<FinancialHighlight> financialHighlights) {
		this.financialHighlights = financialHighlights;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<Floorsheet> getFloorsheets() {
		return this.floorsheets;
	}

	public void setFloorsheets(Set<Floorsheet> floorsheets) {
		this.floorsheets = floorsheets;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "company")
	public Set<BasePrice> getBasePrices() {
		return this.basePrices;
	}

	public void setBasePrices(Set<BasePrice> basePrices) {
		this.basePrices = basePrices;
	}

}
