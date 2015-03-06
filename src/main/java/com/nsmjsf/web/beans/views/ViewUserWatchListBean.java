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

import com.nsmjsf.web.datasources.UserWatchListDataSource;
import com.nsmjsf.web.datamodels.UserWatchList;
import com.nsmjsf.web.lazymodels.LazyUserWatchListDataModel;

import com.nsmjsf.web.adapters.UserWatchListStockAdapter;

import com.nsmjsf.web.datasources.UserWatchListStockDataSource;

import com.nsmjsf.web.datamodels.UserWatchListStock;

import com.nsmjsf.web.wrappers.UserWatchListStockWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class ViewUserWatchListBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewUserWatchListBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<UserWatchList> userWatchListList;
	List<UserWatchList> selectedUserWatchListList;
	List<UserWatchList> filteredUserWatchListList;
	UserWatchList selectedUserWatchList;
	LazyDataModel<UserWatchList> lazyModel;
	UserWatchListDataSource userWatchListDataSource;
	int editUserWatchListId = 0;

	List<User> userList;
	UserDataSource userDataSource;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public ViewUserWatchListBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserWatchListDataModel(this.userWatchListList);

	}

	private void initDataSources() {
		userWatchListDataSource = new UserWatchListDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.userWatchListList = userWatchListDataSource.getAll();
		lazyModel = new LazyUserWatchListDataModel(this.userWatchListList);

	}

	private void populateData() {
		userWatchListList = userWatchListDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<UserWatchList> getUserWatchListList() {
		return userWatchListList;
	}

	public void setUserWatchListList(List<UserWatchList> userWatchListList) {
		this.userWatchListList = userWatchListList;
	}

	public LazyDataModel<UserWatchList> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UserWatchList> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserWatchList getSelectedUserWatchList() {
		return selectedUserWatchList;
	}

	public void setSelectedUserWatchList(UserWatchList selectedUserWatchList) {
		this.selectedUserWatchList = selectedUserWatchList;
	}

	public List<UserWatchList> getSelectedUserWatchListList() {
		return selectedUserWatchListList;
	}

	public void setSelectedUserWatchListList(
			List<UserWatchList> selectedUserWatchListList) {
		this.selectedUserWatchListList = selectedUserWatchListList;
	}

	public List<UserWatchList> getFilteredUserWatchListList() {
		return filteredUserWatchListList;
	}

	public void setFilteredUserWatchListList(
			List<UserWatchList> filteredUserWatchListList) {
		this.filteredUserWatchListList = filteredUserWatchListList;
	}

	public void newUserWatchList() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserWatchList",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("UserWatchList Selected"
				+ ((UserWatchList) event.getObject()).getUserWatchListId());
		for (UserWatchList cat : selectedUserWatchListList) {
			// System.out.println(cat.getUserWatchListLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserWatchList) event.getObject()).getUserWatchListId());

	}

	public void deleteSelectedUserWatchList() {
		for (UserWatchList userWatchList : selectedUserWatchListList) {
			// System.out.println(userWatchList.getUserWatchListLabel());
			this.deleteUserWatchList(userWatchList);
		}
	}

	public void deleteUserWatchList(UserWatchList userWatchList) {
		try {
			userWatchListDataSource.delete(userWatchList);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserWatchListId() {
		return editUserWatchListId;
	}

	public void setEditUserWatchListId(int editUserWatchListId) {
		this.editUserWatchListId = editUserWatchListId;
	}

	public void editUserWatchList(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserWatchList",
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
