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

import com.nsmjsf.web.datasources.BodDataSource;
import com.nsmjsf.web.datamodels.Bod;
import com.nsmjsf.web.lazymodels.LazyBodDataModel;

import com.nsmjsf.web.adapters.FiscalYearAdapter;

import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.BodTypeAdapter;

import com.nsmjsf.web.datasources.BodTypeDataSource;

import com.nsmjsf.web.datamodels.BodType;

import com.nsmjsf.web.wrappers.BodTypeWrapper;

@ManagedBean
@ViewScoped
public class ViewBodBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewBodBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Bod> bodList;
	List<Bod> selectedBodList;
	List<Bod> filteredBodList;
	Bod selectedBod;
	LazyDataModel<Bod> lazyModel;
	BodDataSource bodDataSource;
	int editBodId = 0;

	List<FiscalYear> fiscalYearList;
	FiscalYearDataSource fiscalYearDataSource;

	public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	List<BodType> bodTypeList;
	BodTypeDataSource bodTypeDataSource;

	public List<BodType> getBodTypeList() {
		return bodTypeList;
	}

	public void setBodTypeList(List<BodType> bodTypeList) {
		this.bodTypeList = bodTypeList;
	}

	public ViewBodBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyBodDataModel(this.bodList);

	}

	private void initDataSources() {
		bodDataSource = new BodDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		companyDataSource = new CompanyDataSource();

		bodTypeDataSource = new BodTypeDataSource();

	}

	public void refreshDataSource() {
		this.bodList = bodDataSource.getAll();
		lazyModel = new LazyBodDataModel(this.bodList);

	}

	private void populateData() {
		bodList = bodDataSource.getAll();

		fiscalYearList = fiscalYearDataSource.getAll();

		companyList = companyDataSource.getAll();

		bodTypeList = bodTypeDataSource.getAll();

	}

	public List<Bod> getBodList() {
		return bodList;
	}

	public void setBodList(List<Bod> bodList) {
		this.bodList = bodList;
	}

	public LazyDataModel<Bod> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Bod> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Bod getSelectedBod() {
		return selectedBod;
	}

	public void setSelectedBod(Bod selectedBod) {
		this.selectedBod = selectedBod;
	}

	public List<Bod> getSelectedBodList() {
		return selectedBodList;
	}

	public void setSelectedBodList(List<Bod> selectedBodList) {
		this.selectedBodList = selectedBodList;
	}

	public List<Bod> getFilteredBodList() {
		return filteredBodList;
	}

	public void setFilteredBodList(List<Bod> filteredBodList) {
		this.filteredBodList = filteredBodList;
	}

	public void newBod() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBod", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Bod Selected"
				+ ((Bod) event.getObject()).getBodId());
		for (Bod cat : selectedBodList) {
			// System.out.println(cat.getBodLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Bod) event.getObject()).getBodId());

	}

	public void deleteSelectedBod() {
		for (Bod bod : selectedBodList) {
			// System.out.println(bod.getBodLabel());
			this.deleteBod(bod);
		}
	}

	public void deleteBod(Bod bod) {
		try {
			bodDataSource.delete(bod);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditBodId() {
		return editBodId;
	}

	public void setEditBodId(int editBodId) {
		this.editBodId = editBodId;
	}

	public void editBod(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBod", options,
				params);
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
