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

import com.nsmjsf.web.datasources.UserEnergySalesDataSource;
import com.nsmjsf.web.datamodels.UserEnergySales;
import com.nsmjsf.web.lazymodels.LazyUserEnergySalesDataModel;

import com.nsmjsf.web.adapters.UserEnergyAdapter;

import com.nsmjsf.web.datasources.UserEnergyDataSource;

import com.nsmjsf.web.datamodels.UserEnergy;

import com.nsmjsf.web.wrappers.UserEnergyWrapper;

@ManagedBean
@ViewScoped
public class ViewUserEnergySalesBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewUserEnergySalesBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<UserEnergySales> userEnergySalesList;
	List<UserEnergySales> selectedUserEnergySalesList;
	List<UserEnergySales> filteredUserEnergySalesList;
	UserEnergySales selectedUserEnergySales;
	LazyDataModel<UserEnergySales> lazyModel;
	UserEnergySalesDataSource userEnergySalesDataSource;
	int editUserEnergySalesId = 0;

	List<UserEnergy> userEnergyList;
	UserEnergyDataSource userEnergyDataSource;

	public List<UserEnergy> getUserEnergyList() {
		return userEnergyList;
	}

	public void setUserEnergyList(List<UserEnergy> userEnergyList) {
		this.userEnergyList = userEnergyList;
	}

	public ViewUserEnergySalesBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserEnergySalesDataModel(this.userEnergySalesList);

	}

	private void initDataSources() {
		userEnergySalesDataSource = new UserEnergySalesDataSource();

		userEnergyDataSource = new UserEnergyDataSource();

	}

	public void refreshDataSource() {
		this.userEnergySalesList = userEnergySalesDataSource.getAll();
		lazyModel = new LazyUserEnergySalesDataModel(this.userEnergySalesList);

	}

	private void populateData() {
		userEnergySalesList = userEnergySalesDataSource.getAll();

		userEnergyList = userEnergyDataSource.getAll();

	}

	public List<UserEnergySales> getUserEnergySalesList() {
		return userEnergySalesList;
	}

	public void setUserEnergySalesList(List<UserEnergySales> userEnergySalesList) {
		this.userEnergySalesList = userEnergySalesList;
	}

	public LazyDataModel<UserEnergySales> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UserEnergySales> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserEnergySales getSelectedUserEnergySales() {
		return selectedUserEnergySales;
	}

	public void setSelectedUserEnergySales(
			UserEnergySales selectedUserEnergySales) {
		this.selectedUserEnergySales = selectedUserEnergySales;
	}

	public List<UserEnergySales> getSelectedUserEnergySalesList() {
		return selectedUserEnergySalesList;
	}

	public void setSelectedUserEnergySalesList(
			List<UserEnergySales> selectedUserEnergySalesList) {
		this.selectedUserEnergySalesList = selectedUserEnergySalesList;
	}

	public List<UserEnergySales> getFilteredUserEnergySalesList() {
		return filteredUserEnergySalesList;
	}

	public void setFilteredUserEnergySalesList(
			List<UserEnergySales> filteredUserEnergySalesList) {
		this.filteredUserEnergySalesList = filteredUserEnergySalesList;
	}

	public void newUserEnergySales() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserEnergySales",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("UserEnergySales Selected"
				+ ((UserEnergySales) event.getObject()).getUserEnergySalesId());
		for (UserEnergySales cat : selectedUserEnergySalesList) {
			// System.out.println(cat.getUserEnergySalesLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserEnergySales) event.getObject()).getUserEnergySalesId());

	}

	public void deleteSelectedUserEnergySales() {
		for (UserEnergySales userEnergySales : selectedUserEnergySalesList) {
			// System.out.println(userEnergySales.getUserEnergySalesLabel());
			this.deleteUserEnergySales(userEnergySales);
		}
	}

	public void deleteUserEnergySales(UserEnergySales userEnergySales) {
		try {
			userEnergySalesDataSource.delete(userEnergySales);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserEnergySalesId() {
		return editUserEnergySalesId;
	}

	public void setEditUserEnergySalesId(int editUserEnergySalesId) {
		this.editUserEnergySalesId = editUserEnergySalesId;
	}

	public void editUserEnergySales(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserEnergySales",
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
