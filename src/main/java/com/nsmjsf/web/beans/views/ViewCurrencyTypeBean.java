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

import com.nsmjsf.web.datasources.CurrencyTypeDataSource;
import com.nsmjsf.web.datamodels.CurrencyType;
import com.nsmjsf.web.lazymodels.LazyCurrencyTypeDataModel;

import com.nsmjsf.web.adapters.CurrencyRateAdapter;

import com.nsmjsf.web.datasources.CurrencyRateDataSource;

import com.nsmjsf.web.datamodels.CurrencyRate;

import com.nsmjsf.web.wrappers.CurrencyRateWrapper;

@ManagedBean
@ViewScoped
public class ViewCurrencyTypeBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewCurrencyTypeBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<CurrencyType> currencyTypeList;
	List<CurrencyType> selectedCurrencyTypeList;
	List<CurrencyType> filteredCurrencyTypeList;
	CurrencyType selectedCurrencyType;
	LazyDataModel<CurrencyType> lazyModel;
	CurrencyTypeDataSource currencyTypeDataSource;
	int editCurrencyTypeId = 0;

	public ViewCurrencyTypeBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyCurrencyTypeDataModel(this.currencyTypeList);

	}

	private void initDataSources() {
		currencyTypeDataSource = new CurrencyTypeDataSource();

	}

	public void refreshDataSource() {
		this.currencyTypeList = currencyTypeDataSource.getAll();
		lazyModel = new LazyCurrencyTypeDataModel(this.currencyTypeList);

	}

	private void populateData() {
		currencyTypeList = currencyTypeDataSource.getAll();

	}

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public LazyDataModel<CurrencyType> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<CurrencyType> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public CurrencyType getSelectedCurrencyType() {
		return selectedCurrencyType;
	}

	public void setSelectedCurrencyType(CurrencyType selectedCurrencyType) {
		this.selectedCurrencyType = selectedCurrencyType;
	}

	public List<CurrencyType> getSelectedCurrencyTypeList() {
		return selectedCurrencyTypeList;
	}

	public void setSelectedCurrencyTypeList(
			List<CurrencyType> selectedCurrencyTypeList) {
		this.selectedCurrencyTypeList = selectedCurrencyTypeList;
	}

	public List<CurrencyType> getFilteredCurrencyTypeList() {
		return filteredCurrencyTypeList;
	}

	public void setFilteredCurrencyTypeList(
			List<CurrencyType> filteredCurrencyTypeList) {
		this.filteredCurrencyTypeList = filteredCurrencyTypeList;
	}

	public void newCurrencyType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCurrencyType",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("CurrencyType Selected"
				+ ((CurrencyType) event.getObject()).getCurrencyTypeId());
		for (CurrencyType cat : selectedCurrencyTypeList) {
			// System.out.println(cat.getCurrencyTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CurrencyType) event.getObject()).getCurrencyTypeId());

	}

	public void deleteSelectedCurrencyType() {
		for (CurrencyType currencyType : selectedCurrencyTypeList) {
			// System.out.println(currencyType.getCurrencyTypeLabel());
			this.deleteCurrencyType(currencyType);
		}
	}

	public void deleteCurrencyType(CurrencyType currencyType) {
		try {
			currencyTypeDataSource.delete(currencyType);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditCurrencyTypeId() {
		return editCurrencyTypeId;
	}

	public void setEditCurrencyTypeId(int editCurrencyTypeId) {
		this.editCurrencyTypeId = editCurrencyTypeId;
	}

	public void editCurrencyType(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCurrencyType",
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
