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

import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;
import com.nsmjsf.web.lazymodels.LazyMonthlyFinancialHighlightDataModel;

import com.nsmjsf.web.adapters.FiscalYearAdapter;

import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;

import com.nsmjsf.web.adapters.AnnouncementAdapter;

import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;

import com.nsmjsf.web.adapters.MonthAdapter;

import com.nsmjsf.web.datasources.MonthDataSource;

import com.nsmjsf.web.datamodels.Month;

import com.nsmjsf.web.wrappers.MonthWrapper;

@ManagedBean
@ViewScoped
public class ViewMonthlyFinancialHighlightBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewMonthlyFinancialHighlightBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<MonthlyFinancialHighlight> monthlyFinancialHighlightList;
	List<MonthlyFinancialHighlight> selectedMonthlyFinancialHighlightList;
	List<MonthlyFinancialHighlight> filteredMonthlyFinancialHighlightList;
	MonthlyFinancialHighlight selectedMonthlyFinancialHighlight;
	LazyDataModel<MonthlyFinancialHighlight> lazyModel;
	MonthlyFinancialHighlightDataSource monthlyFinancialHighlightDataSource;
	int editMonthlyFinancialHighlightId = 0;

	List<FiscalYear> fiscalYearList;
	FiscalYearDataSource fiscalYearDataSource;

	public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	List<Announcement> announcementList;
	AnnouncementDataSource announcementDataSource;

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	List<Month> monthList;
	MonthDataSource monthDataSource;

	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

	public ViewMonthlyFinancialHighlightBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyMonthlyFinancialHighlightDataModel(
				this.monthlyFinancialHighlightList);

	}

	private void initDataSources() {
		monthlyFinancialHighlightDataSource = new MonthlyFinancialHighlightDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		announcementDataSource = new AnnouncementDataSource();

		monthDataSource = new MonthDataSource();

	}

	public void refreshDataSource() {
		this.monthlyFinancialHighlightList = monthlyFinancialHighlightDataSource
				.getAll();
		lazyModel = new LazyMonthlyFinancialHighlightDataModel(
				this.monthlyFinancialHighlightList);

	}

	private void populateData() {
		monthlyFinancialHighlightList = monthlyFinancialHighlightDataSource
				.getAll();

		fiscalYearList = fiscalYearDataSource.getAll();

		announcementList = announcementDataSource.getAll();

		monthList = monthDataSource.getAll();

	}

	public List<MonthlyFinancialHighlight> getMonthlyFinancialHighlightList() {
		return monthlyFinancialHighlightList;
	}

	public void setMonthlyFinancialHighlightList(
			List<MonthlyFinancialHighlight> monthlyFinancialHighlightList) {
		this.monthlyFinancialHighlightList = monthlyFinancialHighlightList;
	}

	public LazyDataModel<MonthlyFinancialHighlight> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<MonthlyFinancialHighlight> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public MonthlyFinancialHighlight getSelectedMonthlyFinancialHighlight() {
		return selectedMonthlyFinancialHighlight;
	}

	public void setSelectedMonthlyFinancialHighlight(
			MonthlyFinancialHighlight selectedMonthlyFinancialHighlight) {
		this.selectedMonthlyFinancialHighlight = selectedMonthlyFinancialHighlight;
	}

	public List<MonthlyFinancialHighlight> getSelectedMonthlyFinancialHighlightList() {
		return selectedMonthlyFinancialHighlightList;
	}

	public void setSelectedMonthlyFinancialHighlightList(
			List<MonthlyFinancialHighlight> selectedMonthlyFinancialHighlightList) {
		this.selectedMonthlyFinancialHighlightList = selectedMonthlyFinancialHighlightList;
	}

	public List<MonthlyFinancialHighlight> getFilteredMonthlyFinancialHighlightList() {
		return filteredMonthlyFinancialHighlightList;
	}

	public void setFilteredMonthlyFinancialHighlightList(
			List<MonthlyFinancialHighlight> filteredMonthlyFinancialHighlightList) {
		this.filteredMonthlyFinancialHighlightList = filteredMonthlyFinancialHighlightList;
	}

	public void newMonthlyFinancialHighlight() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog(
				"createMonthlyFinancialHighlight", options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("MonthlyFinancialHighlight Selected"
				+ ((MonthlyFinancialHighlight) event.getObject())
						.getMonthlyFinancialHighlightId());
		for (MonthlyFinancialHighlight cat : selectedMonthlyFinancialHighlightList) {
			// System.out.println(cat.getMonthlyFinancialHighlightLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((MonthlyFinancialHighlight) event.getObject())
						.getMonthlyFinancialHighlightId());

	}

	public void deleteSelectedMonthlyFinancialHighlight() {
		for (MonthlyFinancialHighlight monthlyFinancialHighlight : selectedMonthlyFinancialHighlightList) {
			// System.out.println(monthlyFinancialHighlight.getMonthlyFinancialHighlightLabel());
			this.deleteMonthlyFinancialHighlight(monthlyFinancialHighlight);
		}
	}

	public void deleteMonthlyFinancialHighlight(
			MonthlyFinancialHighlight monthlyFinancialHighlight) {
		try {
			monthlyFinancialHighlightDataSource
					.delete(monthlyFinancialHighlight);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditMonthlyFinancialHighlightId() {
		return editMonthlyFinancialHighlightId;
	}

	public void setEditMonthlyFinancialHighlightId(
			int editMonthlyFinancialHighlightId) {
		this.editMonthlyFinancialHighlightId = editMonthlyFinancialHighlightId;
	}

	public void editMonthlyFinancialHighlight(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog(
				"createMonthlyFinancialHighlight", options, params);
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
