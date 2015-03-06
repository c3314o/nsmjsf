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

import com.nsmjsf.web.datasources.FiscalYearDataSource;
import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.lazymodels.LazyFiscalYearDataModel;

import com.nsmjsf.web.adapters.BonusDividendApprovedAdapter;

import com.nsmjsf.web.datasources.BonusDividendApprovedDataSource;

import com.nsmjsf.web.datamodels.BonusDividendApproved;

import com.nsmjsf.web.wrappers.BonusDividendApprovedWrapper;

import com.nsmjsf.web.adapters.BonusDividendAdapter;

import com.nsmjsf.web.datasources.BonusDividendDataSource;

import com.nsmjsf.web.datamodels.BonusDividend;

import com.nsmjsf.web.wrappers.BonusDividendWrapper;

import com.nsmjsf.web.adapters.FinancialHighlightAdapter;

import com.nsmjsf.web.datasources.FinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.FinancialHighlight;

import com.nsmjsf.web.wrappers.FinancialHighlightWrapper;

import com.nsmjsf.web.adapters.AgmAdapter;

import com.nsmjsf.web.datasources.AgmDataSource;

import com.nsmjsf.web.datamodels.Agm;

import com.nsmjsf.web.wrappers.AgmWrapper;

import com.nsmjsf.web.adapters.CertificateDividendDistributionAdapter;

import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;

import com.nsmjsf.web.datamodels.CertificateDividendDistribution;

import com.nsmjsf.web.wrappers.CertificateDividendDistributionWrapper;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

import com.nsmjsf.web.adapters.BodAdapter;

import com.nsmjsf.web.datasources.BodDataSource;

import com.nsmjsf.web.datamodels.Bod;

import com.nsmjsf.web.wrappers.BodWrapper;

import com.nsmjsf.web.adapters.MonthlyFinancialHighlightAdapter;

import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;

import com.nsmjsf.web.wrappers.MonthlyFinancialHighlightWrapper;

@ManagedBean
@ViewScoped
public class ViewFiscalYearBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewFiscalYearBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<FiscalYear> fiscalYearList;
	List<FiscalYear> selectedFiscalYearList;
	List<FiscalYear> filteredFiscalYearList;
	FiscalYear selectedFiscalYear;
	LazyDataModel<FiscalYear> lazyModel;
	FiscalYearDataSource fiscalYearDataSource;
	int editFiscalYearId = 0;

	public ViewFiscalYearBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyFiscalYearDataModel(this.fiscalYearList);

	}

	private void initDataSources() {
		fiscalYearDataSource = new FiscalYearDataSource();

	}

	public void refreshDataSource() {
		this.fiscalYearList = fiscalYearDataSource.getAll();
		lazyModel = new LazyFiscalYearDataModel(this.fiscalYearList);

	}

	private void populateData() {
		fiscalYearList = fiscalYearDataSource.getAll();

	}

	public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	public LazyDataModel<FiscalYear> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<FiscalYear> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public FiscalYear getSelectedFiscalYear() {
		return selectedFiscalYear;
	}

	public void setSelectedFiscalYear(FiscalYear selectedFiscalYear) {
		this.selectedFiscalYear = selectedFiscalYear;
	}

	public List<FiscalYear> getSelectedFiscalYearList() {
		return selectedFiscalYearList;
	}

	public void setSelectedFiscalYearList(
			List<FiscalYear> selectedFiscalYearList) {
		this.selectedFiscalYearList = selectedFiscalYearList;
	}

	public List<FiscalYear> getFilteredFiscalYearList() {
		return filteredFiscalYearList;
	}

	public void setFilteredFiscalYearList(
			List<FiscalYear> filteredFiscalYearList) {
		this.filteredFiscalYearList = filteredFiscalYearList;
	}

	public void newFiscalYear() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFiscalYear",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("FiscalYear Selected"
				+ ((FiscalYear) event.getObject()).getFiscalYearId());
		for (FiscalYear cat : selectedFiscalYearList) {
			// System.out.println(cat.getFiscalYearLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((FiscalYear) event.getObject()).getFiscalYearId());

	}

	public void deleteSelectedFiscalYear() {
		for (FiscalYear fiscalYear : selectedFiscalYearList) {
			// System.out.println(fiscalYear.getFiscalYearLabel());
			this.deleteFiscalYear(fiscalYear);
		}
	}

	public void deleteFiscalYear(FiscalYear fiscalYear) {
		try {
			fiscalYearDataSource.delete(fiscalYear);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditFiscalYearId() {
		return editFiscalYearId;
	}

	public void setEditFiscalYearId(int editFiscalYearId) {
		this.editFiscalYearId = editFiscalYearId;
	}

	public void editFiscalYear(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFiscalYear",
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
