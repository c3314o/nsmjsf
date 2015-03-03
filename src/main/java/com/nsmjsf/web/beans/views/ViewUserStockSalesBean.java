
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

import com.nsmjsf.web.datasources.UserStockSalesDataSource;
import com.nsmjsf.web.datamodels.UserStockSales;
import com.nsmjsf.web.lazymodels.LazyUserStockSalesDataModel;


			
import com.nsmjsf.web.adapters.UserStockAdapter;


import com.nsmjsf.web.datasources.UserStockDataSource;

import com.nsmjsf.web.datamodels.UserStock;

import com.nsmjsf.web.wrappers.UserStockWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserStockSalesBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserStockSalesBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserStockSales> userStockSalesList;
    List<UserStockSales> selectedUserStockSalesList;
	List<UserStockSales> filteredUserStockSalesList;
	UserStockSales selectedUserStockSales;
	LazyDataModel<UserStockSales> lazyModel;
	UserStockSalesDataSource userStockSalesDataSource;
	int editUserStockSalesId=0;
	

			   List<UserStock> userStockList;
			   UserStockDataSource userStockDataSource;
			   public List<UserStock> getUserStockList() {
		return userStockList;
	     }
	public void setUserStockList(List<UserStock> userStockList) {
		this.userStockList = userStockList;
	}
			
			
			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewUserStockSalesBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserStockSalesDataModel(this.userStockSalesList);
		
	}
	
	
	private void initDataSources()
	{
		userStockSalesDataSource=new UserStockSalesDataSource();
		

			  userStockDataSource=new UserStockDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.userStockSalesList=userStockSalesDataSource.getAll();
		lazyModel=new LazyUserStockSalesDataModel(this.userStockSalesList);
		
	}
	
	
	private void populateData()
	{
		userStockSalesList=userStockSalesDataSource.getAll();
		

			 userStockList=userStockDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<UserStockSales> getUserStockSalesList() {
		return userStockSalesList;
	}
	public void setUserStockSalesList(List<UserStockSales> userStockSalesList) {
		this.userStockSalesList = userStockSalesList;
	}
	public LazyDataModel<UserStockSales> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserStockSales> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserStockSales getSelectedUserStockSales() {
		return selectedUserStockSales;
	}
	public void setSelectedUserStockSales(UserStockSales selectedUserStockSales) {
		this.selectedUserStockSales = selectedUserStockSales;
	}
	
	public List<UserStockSales> getSelectedUserStockSalesList() {
		return selectedUserStockSalesList;
	}

	public void setSelectedUserStockSalesList(
			List<UserStockSales> selectedUserStockSalesList) {
		this.selectedUserStockSalesList = selectedUserStockSalesList;
	}

	public List<UserStockSales> getFilteredUserStockSalesList() {
		return filteredUserStockSalesList;
	}

	public void setFilteredUserStockSalesList(
			List<UserStockSales> filteredUserStockSalesList) {
		this.filteredUserStockSalesList = filteredUserStockSalesList;
	}

	public void newUserStockSales() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserStockSales",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserStockSales Selected"
				+ ((UserStockSales) event.getObject()).getUserStockSalesId());
		for (UserStockSales cat : selectedUserStockSalesList) {
			//System.out.println(cat.getUserStockSalesLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserStockSales) event.getObject()).getUserStockSalesId());

	}

	public void deleteSelectedUserStockSales() {
		for (UserStockSales userStockSales : selectedUserStockSalesList) {
			//System.out.println(userStockSales.getUserStockSalesLabel());
			this.deleteUserStockSales(userStockSales);
		}
	}
	public void deleteUserStockSales(UserStockSales userStockSales) {
			try{
			userStockSalesDataSource.delete(userStockSales);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserStockSalesId() {
		return editUserStockSalesId;
	}

	public void setEditUserStockSalesId(int editUserStockSalesId) {
		this.editUserStockSalesId = editUserStockSalesId;
	}
	
	public void editUserStockSales(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserStockSales",
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



