
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

import com.nsmjsf.web.datasources.UserWatchListStockDataSource;
import com.nsmjsf.web.datamodels.UserWatchListStock;
import com.nsmjsf.web.lazymodels.LazyUserWatchListStockDataModel;


			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserWatchListAdapter;


import com.nsmjsf.web.datasources.UserWatchListDataSource;

import com.nsmjsf.web.datamodels.UserWatchList;

import com.nsmjsf.web.wrappers.UserWatchListWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserWatchListStockBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserWatchListStockBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserWatchListStock> userWatchListStockList;
    List<UserWatchListStock> selectedUserWatchListStockList;
	List<UserWatchListStock> filteredUserWatchListStockList;
	UserWatchListStock selectedUserWatchListStock;
	LazyDataModel<UserWatchListStock> lazyModel;
	UserWatchListStockDataSource userWatchListStockDataSource;
	int editUserWatchListStockId=0;
	

			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
			
			   List<UserWatchList> userWatchListList;
			   UserWatchListDataSource userWatchListDataSource;
			   public List<UserWatchList> getUserWatchListList() {
		return userWatchListList;
	     }
	public void setUserWatchListList(List<UserWatchList> userWatchListList) {
		this.userWatchListList = userWatchListList;
	}
			
				   
	
	
	public ViewUserWatchListStockBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserWatchListStockDataModel(this.userWatchListStockList);
		
	}
	
	
	private void initDataSources()
	{
		userWatchListStockDataSource=new UserWatchListStockDataSource();
		

			  companyDataSource=new CompanyDataSource();
			
			
			  userWatchListDataSource=new UserWatchListDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.userWatchListStockList=userWatchListStockDataSource.getAll();
		lazyModel=new LazyUserWatchListStockDataModel(this.userWatchListStockList);
		
	}
	
	
	private void populateData()
	{
		userWatchListStockList=userWatchListStockDataSource.getAll();
		

			 companyList=companyDataSource.getAll();
	
			
			 userWatchListList=userWatchListDataSource.getAll();
	
				   
	
		
			}
	public List<UserWatchListStock> getUserWatchListStockList() {
		return userWatchListStockList;
	}
	public void setUserWatchListStockList(List<UserWatchListStock> userWatchListStockList) {
		this.userWatchListStockList = userWatchListStockList;
	}
	public LazyDataModel<UserWatchListStock> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserWatchListStock> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserWatchListStock getSelectedUserWatchListStock() {
		return selectedUserWatchListStock;
	}
	public void setSelectedUserWatchListStock(UserWatchListStock selectedUserWatchListStock) {
		this.selectedUserWatchListStock = selectedUserWatchListStock;
	}
	
	public List<UserWatchListStock> getSelectedUserWatchListStockList() {
		return selectedUserWatchListStockList;
	}

	public void setSelectedUserWatchListStockList(
			List<UserWatchListStock> selectedUserWatchListStockList) {
		this.selectedUserWatchListStockList = selectedUserWatchListStockList;
	}

	public List<UserWatchListStock> getFilteredUserWatchListStockList() {
		return filteredUserWatchListStockList;
	}

	public void setFilteredUserWatchListStockList(
			List<UserWatchListStock> filteredUserWatchListStockList) {
		this.filteredUserWatchListStockList = filteredUserWatchListStockList;
	}

	public void newUserWatchListStock() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserWatchListStock",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserWatchListStock Selected"
				+ ((UserWatchListStock) event.getObject()).getUserWatchListStockId());
		for (UserWatchListStock cat : selectedUserWatchListStockList) {
			//System.out.println(cat.getUserWatchListStockLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserWatchListStock) event.getObject()).getUserWatchListStockId());

	}

	public void deleteSelectedUserWatchListStock() {
		for (UserWatchListStock userWatchListStock : selectedUserWatchListStockList) {
			//System.out.println(userWatchListStock.getUserWatchListStockLabel());
			this.deleteUserWatchListStock(userWatchListStock);
		}
	}
	public void deleteUserWatchListStock(UserWatchListStock userWatchListStock) {
			try{
			userWatchListStockDataSource.delete(userWatchListStock);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserWatchListStockId() {
		return editUserWatchListStockId;
	}

	public void setEditUserWatchListStockId(int editUserWatchListStockId) {
		this.editUserWatchListStockId = editUserWatchListStockId;
	}
	
	public void editUserWatchListStock(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserWatchListStock",
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



