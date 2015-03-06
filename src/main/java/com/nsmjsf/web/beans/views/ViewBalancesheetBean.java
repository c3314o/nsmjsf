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

import com.nsmjsf.web.datasources.BalancesheetDataSource;
import com.nsmjsf.web.datamodels.Balancesheet;
import com.nsmjsf.web.lazymodels.LazyBalancesheetDataModel;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

@ManagedBean
@ViewScoped
public class ViewBalancesheetBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewBalancesheetBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Balancesheet> balancesheetList;
	List<Balancesheet> selectedBalancesheetList;
	List<Balancesheet> filteredBalancesheetList;
	Balancesheet selectedBalancesheet;
	LazyDataModel<Balancesheet> lazyModel;
	BalancesheetDataSource balancesheetDataSource;
	int editBalancesheetId = 0;

	List<FinancialReport> financialReportList;
	FinancialReportDataSource financialReportDataSource;

	public List<FinancialReport> getFinancialReportList() {
		return financialReportList;
	}

	public void setFinancialReportList(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}

	public ViewBalancesheetBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyBalancesheetDataModel(this.balancesheetList);

	}

	private void initDataSources() {
		balancesheetDataSource = new BalancesheetDataSource();

		financialReportDataSource = new FinancialReportDataSource();

	}

	public void refreshDataSource() {
		this.balancesheetList = balancesheetDataSource.getAll();
		lazyModel = new LazyBalancesheetDataModel(this.balancesheetList);

	}

	private void populateData() {
		balancesheetList = balancesheetDataSource.getAll();

		financialReportList = financialReportDataSource.getAll();

	}

	public List<Balancesheet> getBalancesheetList() {
		return balancesheetList;
	}

	public void setBalancesheetList(List<Balancesheet> balancesheetList) {
		this.balancesheetList = balancesheetList;
	}

	public LazyDataModel<Balancesheet> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Balancesheet> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Balancesheet getSelectedBalancesheet() {
		return selectedBalancesheet;
	}

	public void setSelectedBalancesheet(Balancesheet selectedBalancesheet) {
		this.selectedBalancesheet = selectedBalancesheet;
	}

	public List<Balancesheet> getSelectedBalancesheetList() {
		return selectedBalancesheetList;
	}

	public void setSelectedBalancesheetList(
			List<Balancesheet> selectedBalancesheetList) {
		this.selectedBalancesheetList = selectedBalancesheetList;
	}

	public List<Balancesheet> getFilteredBalancesheetList() {
		return filteredBalancesheetList;
	}

	public void setFilteredBalancesheetList(
			List<Balancesheet> filteredBalancesheetList) {
		this.filteredBalancesheetList = filteredBalancesheetList;
	}

	public void newBalancesheet() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBalancesheet",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Balancesheet Selected"
				+ ((Balancesheet) event.getObject()).getBalancesheetId());
		for (Balancesheet cat : selectedBalancesheetList) {
			// System.out.println(cat.getBalancesheetLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Balancesheet) event.getObject()).getBalancesheetId());

	}

	public void deleteSelectedBalancesheet() {
		for (Balancesheet balancesheet : selectedBalancesheetList) {
			// System.out.println(balancesheet.getBalancesheetLabel());
			this.deleteBalancesheet(balancesheet);
		}
	}

	public void deleteBalancesheet(Balancesheet balancesheet) {
		try {
			balancesheetDataSource.delete(balancesheet);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditBalancesheetId() {
		return editBalancesheetId;
	}

	public void setEditBalancesheetId(int editBalancesheetId) {
		this.editBalancesheetId = editBalancesheetId;
	}

	public void editBalancesheet(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBalancesheet",
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
