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

import com.nsmjsf.web.datasources.UserBullionSalesDataSource;
import com.nsmjsf.web.datamodels.UserBullionSales;
import com.nsmjsf.web.lazymodels.LazyUserBullionSalesDataModel;

import com.nsmjsf.web.adapters.UserBullionAdapter;

import com.nsmjsf.web.datasources.UserBullionDataSource;

import com.nsmjsf.web.datamodels.UserBullion;

import com.nsmjsf.web.wrappers.UserBullionWrapper;

@ManagedBean
@ViewScoped
public class ViewUserBullionSalesBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewUserBullionSalesBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<UserBullionSales> userBullionSalesList;
	List<UserBullionSales> selectedUserBullionSalesList;
	List<UserBullionSales> filteredUserBullionSalesList;
	UserBullionSales selectedUserBullionSales;
	LazyDataModel<UserBullionSales> lazyModel;
	UserBullionSalesDataSource userBullionSalesDataSource;
	int editUserBullionSalesId = 0;

	List<UserBullion> userBullionList;
	UserBullionDataSource userBullionDataSource;

	public List<UserBullion> getUserBullionList() {
		return userBullionList;
	}

	public void setUserBullionList(List<UserBullion> userBullionList) {
		this.userBullionList = userBullionList;
	}

	public ViewUserBullionSalesBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserBullionSalesDataModel(this.userBullionSalesList);

	}

	private void initDataSources() {
		userBullionSalesDataSource = new UserBullionSalesDataSource();

		userBullionDataSource = new UserBullionDataSource();

	}

	public void refreshDataSource() {
		this.userBullionSalesList = userBullionSalesDataSource.getAll();
		lazyModel = new LazyUserBullionSalesDataModel(this.userBullionSalesList);

	}

	private void populateData() {
		userBullionSalesList = userBullionSalesDataSource.getAll();

		userBullionList = userBullionDataSource.getAll();

	}

	public List<UserBullionSales> getUserBullionSalesList() {
		return userBullionSalesList;
	}

	public void setUserBullionSalesList(
			List<UserBullionSales> userBullionSalesList) {
		this.userBullionSalesList = userBullionSalesList;
	}

	public LazyDataModel<UserBullionSales> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UserBullionSales> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserBullionSales getSelectedUserBullionSales() {
		return selectedUserBullionSales;
	}

	public void setSelectedUserBullionSales(
			UserBullionSales selectedUserBullionSales) {
		this.selectedUserBullionSales = selectedUserBullionSales;
	}

	public List<UserBullionSales> getSelectedUserBullionSalesList() {
		return selectedUserBullionSalesList;
	}

	public void setSelectedUserBullionSalesList(
			List<UserBullionSales> selectedUserBullionSalesList) {
		this.selectedUserBullionSalesList = selectedUserBullionSalesList;
	}

	public List<UserBullionSales> getFilteredUserBullionSalesList() {
		return filteredUserBullionSalesList;
	}

	public void setFilteredUserBullionSalesList(
			List<UserBullionSales> filteredUserBullionSalesList) {
		this.filteredUserBullionSalesList = filteredUserBullionSalesList;
	}

	public void newUserBullionSales() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog(
				"createUserBullionSales", options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("UserBullionSales Selected"
				+ ((UserBullionSales) event.getObject())
						.getUserBullionSalesId());
		for (UserBullionSales cat : selectedUserBullionSalesList) {
			// System.out.println(cat.getUserBullionSalesLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserBullionSales) event.getObject())
						.getUserBullionSalesId());

	}

	public void deleteSelectedUserBullionSales() {
		for (UserBullionSales userBullionSales : selectedUserBullionSalesList) {
			// System.out.println(userBullionSales.getUserBullionSalesLabel());
			this.deleteUserBullionSales(userBullionSales);
		}
	}

	public void deleteUserBullionSales(UserBullionSales userBullionSales) {
		try {
			userBullionSalesDataSource.delete(userBullionSales);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserBullionSalesId() {
		return editUserBullionSalesId;
	}

	public void setEditUserBullionSalesId(int editUserBullionSalesId) {
		this.editUserBullionSalesId = editUserBullionSalesId;
	}

	public void editUserBullionSales(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog(
				"createUserBullionSales", options, params);
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
