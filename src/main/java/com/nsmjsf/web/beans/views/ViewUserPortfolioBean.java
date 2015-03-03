
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

import com.nsmjsf.web.datasources.UserPortfolioDataSource;
import com.nsmjsf.web.datamodels.UserPortfolio;
import com.nsmjsf.web.lazymodels.LazyUserPortfolioDataModel;


			
import com.nsmjsf.web.adapters.UserEnergyAdapter;


import com.nsmjsf.web.datasources.UserEnergyDataSource;

import com.nsmjsf.web.datamodels.UserEnergy;

import com.nsmjsf.web.wrappers.UserEnergyWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserStockAdapter;


import com.nsmjsf.web.datasources.UserStockDataSource;

import com.nsmjsf.web.datamodels.UserStock;

import com.nsmjsf.web.wrappers.UserStockWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserPortfolioBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserPortfolioBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserPortfolio> userPortfolioList;
    List<UserPortfolio> selectedUserPortfolioList;
	List<UserPortfolio> filteredUserPortfolioList;
	UserPortfolio selectedUserPortfolio;
	LazyDataModel<UserPortfolio> lazyModel;
	UserPortfolioDataSource userPortfolioDataSource;
	int editUserPortfolioId=0;
	

			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewUserPortfolioBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserPortfolioDataModel(this.userPortfolioList);
		
	}
	
	
	private void initDataSources()
	{
		userPortfolioDataSource=new UserPortfolioDataSource();
		

			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.userPortfolioList=userPortfolioDataSource.getAll();
		lazyModel=new LazyUserPortfolioDataModel(this.userPortfolioList);
		
	}
	
	
	private void populateData()
	{
		userPortfolioList=userPortfolioDataSource.getAll();
		

			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	}
	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}
	public LazyDataModel<UserPortfolio> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserPortfolio> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserPortfolio getSelectedUserPortfolio() {
		return selectedUserPortfolio;
	}
	public void setSelectedUserPortfolio(UserPortfolio selectedUserPortfolio) {
		this.selectedUserPortfolio = selectedUserPortfolio;
	}
	
	public List<UserPortfolio> getSelectedUserPortfolioList() {
		return selectedUserPortfolioList;
	}

	public void setSelectedUserPortfolioList(
			List<UserPortfolio> selectedUserPortfolioList) {
		this.selectedUserPortfolioList = selectedUserPortfolioList;
	}

	public List<UserPortfolio> getFilteredUserPortfolioList() {
		return filteredUserPortfolioList;
	}

	public void setFilteredUserPortfolioList(
			List<UserPortfolio> filteredUserPortfolioList) {
		this.filteredUserPortfolioList = filteredUserPortfolioList;
	}

	public void newUserPortfolio() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserPortfolio",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserPortfolio Selected"
				+ ((UserPortfolio) event.getObject()).getUserPortfolioId());
		for (UserPortfolio cat : selectedUserPortfolioList) {
			//System.out.println(cat.getUserPortfolioLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserPortfolio) event.getObject()).getUserPortfolioId());

	}

	public void deleteSelectedUserPortfolio() {
		for (UserPortfolio userPortfolio : selectedUserPortfolioList) {
			//System.out.println(userPortfolio.getUserPortfolioLabel());
			this.deleteUserPortfolio(userPortfolio);
		}
	}
	public void deleteUserPortfolio(UserPortfolio userPortfolio) {
			try{
			userPortfolioDataSource.delete(userPortfolio);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserPortfolioId() {
		return editUserPortfolioId;
	}

	public void setEditUserPortfolioId(int editUserPortfolioId) {
		this.editUserPortfolioId = editUserPortfolioId;
	}
	
	public void editUserPortfolio(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserPortfolio",
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



