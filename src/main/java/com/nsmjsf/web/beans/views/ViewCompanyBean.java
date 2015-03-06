package com.nsmjsf.web.beans.views;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.context.RequestContext;

import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.lazymodels.LazyCompanyDataModel;

import com.nsmjsf.web.adapters.SectorAdapter;

import com.nsmjsf.web.datasources.SectorDataSource;

import com.nsmjsf.web.datamodels.Sector;

import com.nsmjsf.web.wrappers.SectorWrapper;

import com.nsmjsf.web.adapters.UserWatchListStockAdapter;

import com.nsmjsf.web.datasources.UserWatchListStockDataSource;

import com.nsmjsf.web.datamodels.UserWatchListStock;

import com.nsmjsf.web.wrappers.UserWatchListStockWrapper;

import com.nsmjsf.web.adapters.InterviewAdapter;

import com.nsmjsf.web.datasources.InterviewDataSource;

import com.nsmjsf.web.datamodels.Interview;

import com.nsmjsf.web.wrappers.InterviewWrapper;

import com.nsmjsf.web.adapters.BasePriceAdapter;

import com.nsmjsf.web.datasources.BasePriceDataSource;

import com.nsmjsf.web.datamodels.BasePrice;

import com.nsmjsf.web.wrappers.BasePriceWrapper;

import com.nsmjsf.web.adapters.UserStockAdapter;

import com.nsmjsf.web.datasources.UserStockDataSource;

import com.nsmjsf.web.datamodels.UserStock;

import com.nsmjsf.web.wrappers.UserStockWrapper;

import com.nsmjsf.web.adapters.TodaysPriceAdapter;

import com.nsmjsf.web.datasources.TodaysPriceDataSource;

import com.nsmjsf.web.datamodels.TodaysPrice;

import com.nsmjsf.web.wrappers.TodaysPriceWrapper;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

import com.nsmjsf.web.adapters.NewLowAdapter;

import com.nsmjsf.web.datasources.NewLowDataSource;

import com.nsmjsf.web.datamodels.NewLow;

import com.nsmjsf.web.wrappers.NewLowWrapper;

import com.nsmjsf.web.adapters.LatestPriceAdapter;

import com.nsmjsf.web.datasources.LatestPriceDataSource;

import com.nsmjsf.web.datamodels.LatestPrice;

import com.nsmjsf.web.wrappers.LatestPriceWrapper;

import com.nsmjsf.web.adapters.MapPostCompanyAdapter;

import com.nsmjsf.web.datasources.MapPostCompanyDataSource;

import com.nsmjsf.web.datamodels.MapPostCompany;

import com.nsmjsf.web.wrappers.MapPostCompanyWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.BonusDividendAdapter;

import com.nsmjsf.web.datasources.BonusDividendDataSource;

import com.nsmjsf.web.datamodels.BonusDividend;

import com.nsmjsf.web.wrappers.BonusDividendWrapper;

import com.nsmjsf.web.adapters.AgmAdapter;

import com.nsmjsf.web.datasources.AgmDataSource;

import com.nsmjsf.web.datamodels.Agm;

import com.nsmjsf.web.wrappers.AgmWrapper;

import com.nsmjsf.web.adapters.FinancialHighlightAdapter;

import com.nsmjsf.web.datasources.FinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.FinancialHighlight;

import com.nsmjsf.web.wrappers.FinancialHighlightWrapper;

import com.nsmjsf.web.adapters.CompanyDetailAdapter;

import com.nsmjsf.web.datasources.CompanyDetailDataSource;

import com.nsmjsf.web.datamodels.CompanyDetail;

import com.nsmjsf.web.wrappers.CompanyDetailWrapper;

import com.nsmjsf.web.adapters.SemiYearAverageAdapter;

import com.nsmjsf.web.datasources.SemiYearAverageDataSource;

import com.nsmjsf.web.datamodels.SemiYearAverage;

import com.nsmjsf.web.wrappers.SemiYearAverageWrapper;

import com.nsmjsf.web.adapters.NewHighAdapter;

import com.nsmjsf.web.datasources.NewHighDataSource;

import com.nsmjsf.web.datamodels.NewHigh;

import com.nsmjsf.web.wrappers.NewHighWrapper;

import com.nsmjsf.web.adapters.IssueAdapter;

import com.nsmjsf.web.datasources.IssueDataSource;

import com.nsmjsf.web.datamodels.Issue;

import com.nsmjsf.web.wrappers.IssueWrapper;

import com.nsmjsf.web.adapters.FloorsheetAdapter;

import com.nsmjsf.web.datasources.FloorsheetDataSource;

import com.nsmjsf.web.datamodels.Floorsheet;

import com.nsmjsf.web.wrappers.FloorsheetWrapper;

import com.nsmjsf.web.adapters.BodAdapter;

import com.nsmjsf.web.datasources.BodDataSource;

import com.nsmjsf.web.datamodels.Bod;

import com.nsmjsf.web.wrappers.BodWrapper;

import com.nsmjsf.web.adapters.AllotmentResultAdapter;

import com.nsmjsf.web.datasources.AllotmentResultDataSource;

import com.nsmjsf.web.datamodels.AllotmentResult;

import com.nsmjsf.web.wrappers.AllotmentResultWrapper;

@ManagedBean
@ViewScoped
public class ViewCompanyBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewCompanyBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Company> companyList;
	List<Company> selectedCompanyList;
	List<Company> filteredCompanyList;
	Company selectedCompany;
	LazyDataModel<Company> lazyModel;
	CompanyDataSource companyDataSource;
	int editCompanyId = 0;

	List<Sector> sectorList;
	SectorDataSource sectorDataSource;

	public List<Sector> getSectorList() {
		return sectorList;
	}

	public void setSectorList(List<Sector> sectorList) {
		this.sectorList = sectorList;
	}

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	List<CompanyDetail> companyDetailList;
	CompanyDetailDataSource companyDetailDataSource;

	public List<CompanyDetail> getCompanyDetailList() {
		return companyDetailList;
	}

	public void setCompanyDetailList(List<CompanyDetail> companyDetailList) {
		this.companyDetailList = companyDetailList;
	}

	public ViewCompanyBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyCompanyDataModel(this.companyList);

	}

	private void initDataSources() {
		companyDataSource = new CompanyDataSource();

		sectorDataSource = new SectorDataSource();

		postDataSource = new PostDataSource();

		companyDetailDataSource = new CompanyDetailDataSource();

	}

	public void refreshDataSource() {
		this.companyList = companyDataSource.getAll();
		lazyModel = new LazyCompanyDataModel(this.companyList);

	}

	private void populateData() {
		companyList = companyDataSource.getAll();

		sectorList = sectorDataSource.getAll();

		postList = postDataSource.getAll();

		companyDetailList = companyDetailDataSource.getAll();

	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public LazyDataModel<Company> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Company> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Company getSelectedCompany() {
		return selectedCompany;
	}

	public void setSelectedCompany(Company selectedCompany) {
		this.selectedCompany = selectedCompany;
	}

	public List<Company> getSelectedCompanyList() {
		return selectedCompanyList;
	}

	public void setSelectedCompanyList(List<Company> selectedCompanyList) {
		this.selectedCompanyList = selectedCompanyList;
	}

	public List<Company> getFilteredCompanyList() {
		return filteredCompanyList;
	}

	public void setFilteredCompanyList(List<Company> filteredCompanyList) {
		this.filteredCompanyList = filteredCompanyList;
	}

	public void newCompany() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCompany",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Company Selected"
				+ ((Company) event.getObject()).getCompanyId());
		for (Company cat : selectedCompanyList) {
			// System.out.println(cat.getCompanyLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Company) event.getObject()).getCompanyId());

	}

	public void deleteSelectedCompany() {
		for (Company company : selectedCompanyList) {
			// System.out.println(company.getCompanyLabel());
			this.deleteCompany(company);
		}
	}

	public void deleteCompany(Company company) {
		try {
			companyDataSource.delete(company);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditCompanyId() {
		return editCompanyId;
	}

	public void setEditCompanyId(int editCompanyId) {
		this.editCompanyId = editCompanyId;
	}

	public void editCompany(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCompany",
				options, params);
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public boolean isDataGrid() {
		return this.viewType == ViewType.DATAGRID;
	}

	public boolean isDataTable() {
		return this.viewType == ViewType.DATATABLE;
	}

	public boolean isDataScroller() {
		return this.viewType == ViewType.DATASCROLLER;
	}

	public boolean isDataTableLive() {
		return this.viewType == ViewType.DATATABLELIVE;
	}

	public void toDataTable() {
		this.viewType = ViewType.DATATABLE;
	}

	public void toDataGrid() {
		this.viewType = ViewType.DATAGRID;
	}

	public void toDataScroll() {
		this.viewType = ViewType.DATASCROLLER;
	}

}
