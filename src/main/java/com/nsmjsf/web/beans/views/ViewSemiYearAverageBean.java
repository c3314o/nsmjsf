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

import com.nsmjsf.web.datasources.SemiYearAverageDataSource;
import com.nsmjsf.web.datamodels.SemiYearAverage;
import com.nsmjsf.web.lazymodels.LazySemiYearAverageDataModel;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class ViewSemiYearAverageBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewSemiYearAverageBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<SemiYearAverage> semiYearAverageList;
	List<SemiYearAverage> selectedSemiYearAverageList;
	List<SemiYearAverage> filteredSemiYearAverageList;
	SemiYearAverage selectedSemiYearAverage;
	LazyDataModel<SemiYearAverage> lazyModel;
	SemiYearAverageDataSource semiYearAverageDataSource;
	int editSemiYearAverageId = 0;

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public ViewSemiYearAverageBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazySemiYearAverageDataModel(this.semiYearAverageList);

	}

	private void initDataSources() {
		semiYearAverageDataSource = new SemiYearAverageDataSource();

		companyDataSource = new CompanyDataSource();

	}

	public void refreshDataSource() {
		this.semiYearAverageList = semiYearAverageDataSource.getAll();
		lazyModel = new LazySemiYearAverageDataModel(this.semiYearAverageList);

	}

	private void populateData() {
		semiYearAverageList = semiYearAverageDataSource.getAll();

		companyList = companyDataSource.getAll();

	}

	public List<SemiYearAverage> getSemiYearAverageList() {
		return semiYearAverageList;
	}

	public void setSemiYearAverageList(List<SemiYearAverage> semiYearAverageList) {
		this.semiYearAverageList = semiYearAverageList;
	}

	public LazyDataModel<SemiYearAverage> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<SemiYearAverage> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public SemiYearAverage getSelectedSemiYearAverage() {
		return selectedSemiYearAverage;
	}

	public void setSelectedSemiYearAverage(
			SemiYearAverage selectedSemiYearAverage) {
		this.selectedSemiYearAverage = selectedSemiYearAverage;
	}

	public List<SemiYearAverage> getSelectedSemiYearAverageList() {
		return selectedSemiYearAverageList;
	}

	public void setSelectedSemiYearAverageList(
			List<SemiYearAverage> selectedSemiYearAverageList) {
		this.selectedSemiYearAverageList = selectedSemiYearAverageList;
	}

	public List<SemiYearAverage> getFilteredSemiYearAverageList() {
		return filteredSemiYearAverageList;
	}

	public void setFilteredSemiYearAverageList(
			List<SemiYearAverage> filteredSemiYearAverageList) {
		this.filteredSemiYearAverageList = filteredSemiYearAverageList;
	}

	public void newSemiYearAverage() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createSemiYearAverage",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("SemiYearAverage Selected"
				+ ((SemiYearAverage) event.getObject()).getSemiYearAverageId());
		for (SemiYearAverage cat : selectedSemiYearAverageList) {
			// System.out.println(cat.getSemiYearAverageLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((SemiYearAverage) event.getObject()).getSemiYearAverageId());

	}

	public void deleteSelectedSemiYearAverage() {
		for (SemiYearAverage semiYearAverage : selectedSemiYearAverageList) {
			// System.out.println(semiYearAverage.getSemiYearAverageLabel());
			this.deleteSemiYearAverage(semiYearAverage);
		}
	}

	public void deleteSemiYearAverage(SemiYearAverage semiYearAverage) {
		try {
			semiYearAverageDataSource.delete(semiYearAverage);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditSemiYearAverageId() {
		return editSemiYearAverageId;
	}

	public void setEditSemiYearAverageId(int editSemiYearAverageId) {
		this.editSemiYearAverageId = editSemiYearAverageId;
	}

	public void editSemiYearAverage(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createSemiYearAverage",
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
