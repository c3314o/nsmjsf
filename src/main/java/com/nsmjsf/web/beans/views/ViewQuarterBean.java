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

import com.nsmjsf.web.datasources.QuarterDataSource;
import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.lazymodels.LazyQuarterDataModel;

import com.nsmjsf.web.adapters.FinancialHighlightAdapter;

import com.nsmjsf.web.datasources.FinancialHighlightDataSource;

import com.nsmjsf.web.datamodels.FinancialHighlight;

import com.nsmjsf.web.wrappers.FinancialHighlightWrapper;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

@ManagedBean
@ViewScoped
public class ViewQuarterBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewQuarterBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Quarter> quarterList;
	List<Quarter> selectedQuarterList;
	List<Quarter> filteredQuarterList;
	Quarter selectedQuarter;
	LazyDataModel<Quarter> lazyModel;
	QuarterDataSource quarterDataSource;
	int editQuarterId = 0;

	public ViewQuarterBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyQuarterDataModel(this.quarterList);

	}

	private void initDataSources() {
		quarterDataSource = new QuarterDataSource();

	}

	public void refreshDataSource() {
		this.quarterList = quarterDataSource.getAll();
		lazyModel = new LazyQuarterDataModel(this.quarterList);

	}

	private void populateData() {
		quarterList = quarterDataSource.getAll();

	}

	public List<Quarter> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(List<Quarter> quarterList) {
		this.quarterList = quarterList;
	}

	public LazyDataModel<Quarter> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Quarter> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Quarter getSelectedQuarter() {
		return selectedQuarter;
	}

	public void setSelectedQuarter(Quarter selectedQuarter) {
		this.selectedQuarter = selectedQuarter;
	}

	public List<Quarter> getSelectedQuarterList() {
		return selectedQuarterList;
	}

	public void setSelectedQuarterList(List<Quarter> selectedQuarterList) {
		this.selectedQuarterList = selectedQuarterList;
	}

	public List<Quarter> getFilteredQuarterList() {
		return filteredQuarterList;
	}

	public void setFilteredQuarterList(List<Quarter> filteredQuarterList) {
		this.filteredQuarterList = filteredQuarterList;
	}

	public void newQuarter() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createQuarter",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Quarter Selected"
				+ ((Quarter) event.getObject()).getQuarterId());
		for (Quarter cat : selectedQuarterList) {
			// System.out.println(cat.getQuarterLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Quarter) event.getObject()).getQuarterId());

	}

	public void deleteSelectedQuarter() {
		for (Quarter quarter : selectedQuarterList) {
			// System.out.println(quarter.getQuarterLabel());
			this.deleteQuarter(quarter);
		}
	}

	public void deleteQuarter(Quarter quarter) {
		try {
			quarterDataSource.delete(quarter);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditQuarterId() {
		return editQuarterId;
	}

	public void setEditQuarterId(int editQuarterId) {
		this.editQuarterId = editQuarterId;
	}

	public void editQuarter(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createQuarter",
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
