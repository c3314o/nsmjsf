
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

import com.nsmjsf.web.datasources.UserBullionDataSource;
import com.nsmjsf.web.datamodels.UserBullion;
import com.nsmjsf.web.lazymodels.LazyUserBullionDataModel;


			
import com.nsmjsf.web.adapters.BullionTypeAdapter;


import com.nsmjsf.web.datasources.BullionTypeDataSource;

import com.nsmjsf.web.datamodels.BullionType;

import com.nsmjsf.web.wrappers.BullionTypeWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserBullionSalesAdapter;


import com.nsmjsf.web.datasources.UserBullionSalesDataSource;

import com.nsmjsf.web.datamodels.UserBullionSales;

import com.nsmjsf.web.wrappers.UserBullionSalesWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserBullionBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserBullionBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserBullion> userBullionList;
    List<UserBullion> selectedUserBullionList;
	List<UserBullion> filteredUserBullionList;
	UserBullion selectedUserBullion;
	LazyDataModel<UserBullion> lazyModel;
	UserBullionDataSource userBullionDataSource;
	int editUserBullionId=0;
	

			   List<BullionType> bullionTypeList;
			   BullionTypeDataSource bullionTypeDataSource;
			   public List<BullionType> getBullionTypeList() {
		return bullionTypeList;
	     }
	public void setBullionTypeList(List<BullionType> bullionTypeList) {
		this.bullionTypeList = bullionTypeList;
	}
			
			
			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewUserBullionBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserBullionDataModel(this.userBullionList);
		
	}
	
	
	private void initDataSources()
	{
		userBullionDataSource=new UserBullionDataSource();
		

			  bullionTypeDataSource=new BullionTypeDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.userBullionList=userBullionDataSource.getAll();
		lazyModel=new LazyUserBullionDataModel(this.userBullionList);
		
	}
	
	
	private void populateData()
	{
		userBullionList=userBullionDataSource.getAll();
		

			 bullionTypeList=bullionTypeDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<UserBullion> getUserBullionList() {
		return userBullionList;
	}
	public void setUserBullionList(List<UserBullion> userBullionList) {
		this.userBullionList = userBullionList;
	}
	public LazyDataModel<UserBullion> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserBullion> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserBullion getSelectedUserBullion() {
		return selectedUserBullion;
	}
	public void setSelectedUserBullion(UserBullion selectedUserBullion) {
		this.selectedUserBullion = selectedUserBullion;
	}
	
	public List<UserBullion> getSelectedUserBullionList() {
		return selectedUserBullionList;
	}

	public void setSelectedUserBullionList(
			List<UserBullion> selectedUserBullionList) {
		this.selectedUserBullionList = selectedUserBullionList;
	}

	public List<UserBullion> getFilteredUserBullionList() {
		return filteredUserBullionList;
	}

	public void setFilteredUserBullionList(
			List<UserBullion> filteredUserBullionList) {
		this.filteredUserBullionList = filteredUserBullionList;
	}

	public void newUserBullion() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserBullion",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserBullion Selected"
				+ ((UserBullion) event.getObject()).getUserBullionId());
		for (UserBullion cat : selectedUserBullionList) {
			//System.out.println(cat.getUserBullionLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserBullion) event.getObject()).getUserBullionId());

	}

	public void deleteSelectedUserBullion() {
		for (UserBullion userBullion : selectedUserBullionList) {
			//System.out.println(userBullion.getUserBullionLabel());
			this.deleteUserBullion(userBullion);
		}
	}
	public void deleteUserBullion(UserBullion userBullion) {
			try{
			userBullionDataSource.delete(userBullion);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserBullionId() {
		return editUserBullionId;
	}

	public void setEditUserBullionId(int editUserBullionId) {
		this.editUserBullionId = editUserBullionId;
	}
	
	public void editUserBullion(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserBullion",
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



