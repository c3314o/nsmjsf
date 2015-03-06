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

import com.nsmjsf.web.datasources.CurrencyRateDataSource;
import com.nsmjsf.web.datamodels.CurrencyRate;
import com.nsmjsf.web.lazymodels.LazyCurrencyRateDataModel;

import com.nsmjsf.web.adapters.CurrencyTypeAdapter;

import com.nsmjsf.web.datasources.CurrencyTypeDataSource;

import com.nsmjsf.web.datamodels.CurrencyType;

import com.nsmjsf.web.wrappers.CurrencyTypeWrapper;

@ManagedBean
@ViewScoped
public class ViewCurrencyRateBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewCurrencyRateBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<CurrencyRate> currencyRateList;
	List<CurrencyRate> selectedCurrencyRateList;
	List<CurrencyRate> filteredCurrencyRateList;
	CurrencyRate selectedCurrencyRate;
	LazyDataModel<CurrencyRate> lazyModel;
	CurrencyRateDataSource currencyRateDataSource;
	int editCurrencyRateId = 0;

	List<CurrencyType> currencyTypeList;
	CurrencyTypeDataSource currencyTypeDataSource;

	public List<CurrencyType> getCurrencyTypeList() {
		return currencyTypeList;
	}

	public void setCurrencyTypeList(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	public ViewCurrencyRateBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyCurrencyRateDataModel(this.currencyRateList);

	}

	private void initDataSources() {
		currencyRateDataSource = new CurrencyRateDataSource();

		currencyTypeDataSource = new CurrencyTypeDataSource();

	}

	public void refreshDataSource() {
		this.currencyRateList = currencyRateDataSource.getAll();
		lazyModel = new LazyCurrencyRateDataModel(this.currencyRateList);

	}

	private void populateData() {
		currencyRateList = currencyRateDataSource.getAll();

		currencyTypeList = currencyTypeDataSource.getAll();

	}

	public List<CurrencyRate> getCurrencyRateList() {
		return currencyRateList;
	}

	public void setCurrencyRateList(List<CurrencyRate> currencyRateList) {
		this.currencyRateList = currencyRateList;
	}

	public LazyDataModel<CurrencyRate> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<CurrencyRate> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public CurrencyRate getSelectedCurrencyRate() {
		return selectedCurrencyRate;
	}

	public void setSelectedCurrencyRate(CurrencyRate selectedCurrencyRate) {
		this.selectedCurrencyRate = selectedCurrencyRate;
	}

	public List<CurrencyRate> getSelectedCurrencyRateList() {
		return selectedCurrencyRateList;
	}

	public void setSelectedCurrencyRateList(
			List<CurrencyRate> selectedCurrencyRateList) {
		this.selectedCurrencyRateList = selectedCurrencyRateList;
	}

	public List<CurrencyRate> getFilteredCurrencyRateList() {
		return filteredCurrencyRateList;
	}

	public void setFilteredCurrencyRateList(
			List<CurrencyRate> filteredCurrencyRateList) {
		this.filteredCurrencyRateList = filteredCurrencyRateList;
	}

	public void newCurrencyRate() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCurrencyRate",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("CurrencyRate Selected"
				+ ((CurrencyRate) event.getObject()).getCurrencyRateId());
		for (CurrencyRate cat : selectedCurrencyRateList) {
			// System.out.println(cat.getCurrencyRateLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CurrencyRate) event.getObject()).getCurrencyRateId());

	}

	public void deleteSelectedCurrencyRate() {
		for (CurrencyRate currencyRate : selectedCurrencyRateList) {
			// System.out.println(currencyRate.getCurrencyRateLabel());
			this.deleteCurrencyRate(currencyRate);
		}
	}

	public void deleteCurrencyRate(CurrencyRate currencyRate) {
		try {
			currencyRateDataSource.delete(currencyRate);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditCurrencyRateId() {
		return editCurrencyRateId;
	}

	public void setEditCurrencyRateId(int editCurrencyRateId) {
		this.editCurrencyRateId = editCurrencyRateId;
	}

	public void editCurrencyRate(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCurrencyRate",
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
