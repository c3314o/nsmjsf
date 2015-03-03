
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

import com.nsmjsf.web.datasources.UserTypeDataSource;
import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.lazymodels.LazyUserTypeDataModel;


			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserTypeBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserTypeBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserType> userTypeList;
    List<UserType> selectedUserTypeList;
	List<UserType> filteredUserTypeList;
	UserType selectedUserType;
	LazyDataModel<UserType> lazyModel;
	UserTypeDataSource userTypeDataSource;
	int editUserTypeId=0;
	
	   
	
	
	public ViewUserTypeBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserTypeDataModel(this.userTypeList);
		
	}
	
	
	private void initDataSources()
	{
		userTypeDataSource=new UserTypeDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.userTypeList=userTypeDataSource.getAll();
		lazyModel=new LazyUserTypeDataModel(this.userTypeList);
		
	}
	
	
	private void populateData()
	{
		userTypeList=userTypeDataSource.getAll();
		
	   
	
		
			}
	public List<UserType> getUserTypeList() {
		return userTypeList;
	}
	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}
	public LazyDataModel<UserType> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserType> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserType getSelectedUserType() {
		return selectedUserType;
	}
	public void setSelectedUserType(UserType selectedUserType) {
		this.selectedUserType = selectedUserType;
	}
	
	public List<UserType> getSelectedUserTypeList() {
		return selectedUserTypeList;
	}

	public void setSelectedUserTypeList(
			List<UserType> selectedUserTypeList) {
		this.selectedUserTypeList = selectedUserTypeList;
	}

	public List<UserType> getFilteredUserTypeList() {
		return filteredUserTypeList;
	}

	public void setFilteredUserTypeList(
			List<UserType> filteredUserTypeList) {
		this.filteredUserTypeList = filteredUserTypeList;
	}

	public void newUserType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserType",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserType Selected"
				+ ((UserType) event.getObject()).getUserTypeId());
		for (UserType cat : selectedUserTypeList) {
			//System.out.println(cat.getUserTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserType) event.getObject()).getUserTypeId());

	}

	public void deleteSelectedUserType() {
		for (UserType userType : selectedUserTypeList) {
			//System.out.println(userType.getUserTypeLabel());
			this.deleteUserType(userType);
		}
	}
	public void deleteUserType(UserType userType) {
			try{
			userTypeDataSource.delete(userType);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserTypeId() {
		return editUserTypeId;
	}

	public void setEditUserTypeId(int editUserTypeId) {
		this.editUserTypeId = editUserTypeId;
	}
	
	public void editUserType(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserType",
				options,params);
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public boolean isDataGrid()
	{
		return this.viewType==ViewType.DATAGRID;
	}
	public boolean isDataTable()
	{
		return this.viewType==ViewType.DATATABLE;
	}
	public boolean isDataScroller()
	{
		return this.viewType==ViewType.DATASCROLLER;
	}
	public boolean isDataTableLive()
	{
		return this.viewType==ViewType.DATATABLELIVE;
	}
	
	public void toDataTable()
	{
		this.viewType=ViewType.DATATABLE;
	}
	public void toDataGrid()
	{
		this.viewType=ViewType.DATAGRID;
	}
	public void toDataScroll()
	{
		this.viewType=ViewType.DATASCROLLER;
	}
	

}



