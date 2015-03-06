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

import com.nsmjsf.web.datasources.TodaysPriceDataSource;
import com.nsmjsf.web.datamodels.TodaysPrice;
import com.nsmjsf.web.lazymodels.LazyTodaysPriceDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class ViewTodaysPriceBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewTodaysPriceBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<TodaysPrice> todaysPriceList;
	List<TodaysPrice> selectedTodaysPriceList;
	List<TodaysPrice> filteredTodaysPriceList;
	TodaysPrice selectedTodaysPrice;
	LazyDataModel<TodaysPrice> lazyModel;
	TodaysPriceDataSource todaysPriceDataSource;
	int editTodaysPriceId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public ViewTodaysPriceBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyTodaysPriceDataModel(this.todaysPriceList);

	}

	private void initDataSources() {
		todaysPriceDataSource = new TodaysPriceDataSource();

		postDataSource = new PostDataSource();

		companyDataSource = new CompanyDataSource();

	}

	public void refreshDataSource() {
		this.todaysPriceList = todaysPriceDataSource.getAll();
		lazyModel = new LazyTodaysPriceDataModel(this.todaysPriceList);

	}

	private void populateData() {
		todaysPriceList = todaysPriceDataSource.getAll();

		postList = postDataSource.getAll();

		companyList = companyDataSource.getAll();

	}

	public List<TodaysPrice> getTodaysPriceList() {
		return todaysPriceList;
	}

	public void setTodaysPriceList(List<TodaysPrice> todaysPriceList) {
		this.todaysPriceList = todaysPriceList;
	}

	public LazyDataModel<TodaysPrice> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<TodaysPrice> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public TodaysPrice getSelectedTodaysPrice() {
		return selectedTodaysPrice;
	}

	public void setSelectedTodaysPrice(TodaysPrice selectedTodaysPrice) {
		this.selectedTodaysPrice = selectedTodaysPrice;
	}

	public List<TodaysPrice> getSelectedTodaysPriceList() {
		return selectedTodaysPriceList;
	}

	public void setSelectedTodaysPriceList(
			List<TodaysPrice> selectedTodaysPriceList) {
		this.selectedTodaysPriceList = selectedTodaysPriceList;
	}

	public List<TodaysPrice> getFilteredTodaysPriceList() {
		return filteredTodaysPriceList;
	}

	public void setFilteredTodaysPriceList(
			List<TodaysPrice> filteredTodaysPriceList) {
		this.filteredTodaysPriceList = filteredTodaysPriceList;
	}

	public void newTodaysPrice() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createTodaysPrice",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("TodaysPrice Selected"
				+ ((TodaysPrice) event.getObject()).getTodaysPriceId());
		for (TodaysPrice cat : selectedTodaysPriceList) {
			// System.out.println(cat.getTodaysPriceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((TodaysPrice) event.getObject()).getTodaysPriceId());

	}

	public void deleteSelectedTodaysPrice() {
		for (TodaysPrice todaysPrice : selectedTodaysPriceList) {
			// System.out.println(todaysPrice.getTodaysPriceLabel());
			this.deleteTodaysPrice(todaysPrice);
		}
	}

	public void deleteTodaysPrice(TodaysPrice todaysPrice) {
		try {
			todaysPriceDataSource.delete(todaysPrice);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditTodaysPriceId() {
		return editTodaysPriceId;
	}

	public void setEditTodaysPriceId(int editTodaysPriceId) {
		this.editTodaysPriceId = editTodaysPriceId;
	}

	public void editTodaysPrice(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createTodaysPrice",
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
