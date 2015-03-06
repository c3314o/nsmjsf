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

import com.nsmjsf.web.datasources.UserInfoDataSource;
import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.lazymodels.LazyUserInfoDataModel;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class ViewUserInfoBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewUserInfoBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<UserInfo> userInfoList;
	List<UserInfo> selectedUserInfoList;
	List<UserInfo> filteredUserInfoList;
	UserInfo selectedUserInfo;
	LazyDataModel<UserInfo> lazyModel;
	UserInfoDataSource userInfoDataSource;
	int editUserInfoId = 0;

	public ViewUserInfoBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserInfoDataModel(this.userInfoList);

	}

	private void initDataSources() {
		userInfoDataSource = new UserInfoDataSource();

	}

	public void refreshDataSource() {
		this.userInfoList = userInfoDataSource.getAll();
		lazyModel = new LazyUserInfoDataModel(this.userInfoList);

	}

	private void populateData() {
		userInfoList = userInfoDataSource.getAll();

	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public LazyDataModel<UserInfo> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UserInfo> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserInfo getSelectedUserInfo() {
		return selectedUserInfo;
	}

	public void setSelectedUserInfo(UserInfo selectedUserInfo) {
		this.selectedUserInfo = selectedUserInfo;
	}

	public List<UserInfo> getSelectedUserInfoList() {
		return selectedUserInfoList;
	}

	public void setSelectedUserInfoList(List<UserInfo> selectedUserInfoList) {
		this.selectedUserInfoList = selectedUserInfoList;
	}

	public List<UserInfo> getFilteredUserInfoList() {
		return filteredUserInfoList;
	}

	public void setFilteredUserInfoList(List<UserInfo> filteredUserInfoList) {
		this.filteredUserInfoList = filteredUserInfoList;
	}

	public void newUserInfo() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserInfo",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("UserInfo Selected"
				+ ((UserInfo) event.getObject()).getUserInfoId());
		for (UserInfo cat : selectedUserInfoList) {
			// System.out.println(cat.getUserInfoLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserInfo) event.getObject()).getUserInfoId());

	}

	public void deleteSelectedUserInfo() {
		for (UserInfo userInfo : selectedUserInfoList) {
			// System.out.println(userInfo.getUserInfoLabel());
			this.deleteUserInfo(userInfo);
		}
	}

	public void deleteUserInfo(UserInfo userInfo) {
		try {
			userInfoDataSource.delete(userInfo);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserInfoId() {
		return editUserInfoId;
	}

	public void setEditUserInfoId(int editUserInfoId) {
		this.editUserInfoId = editUserInfoId;
	}

	public void editUserInfo(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserInfo",
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
