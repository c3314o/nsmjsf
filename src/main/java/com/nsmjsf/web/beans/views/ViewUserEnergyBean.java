
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

import com.nsmjsf.web.datasources.UserEnergyDataSource;
import com.nsmjsf.web.datamodels.UserEnergy;
import com.nsmjsf.web.lazymodels.LazyUserEnergyDataModel;


			
import com.nsmjsf.web.adapters.UserEnergySalesAdapter;


import com.nsmjsf.web.datasources.UserEnergySalesDataSource;

import com.nsmjsf.web.datamodels.UserEnergySales;

import com.nsmjsf.web.wrappers.UserEnergySalesWrapper;



			
			
			
import com.nsmjsf.web.adapters.EnergyTypeAdapter;


import com.nsmjsf.web.datasources.EnergyTypeDataSource;

import com.nsmjsf.web.datamodels.EnergyType;

import com.nsmjsf.web.wrappers.EnergyTypeWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserPortfolioAdapter;


import com.nsmjsf.web.datasources.UserPortfolioDataSource;

import com.nsmjsf.web.datamodels.UserPortfolio;

import com.nsmjsf.web.wrappers.UserPortfolioWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewUserEnergyBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewUserEnergyBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<UserEnergy> userEnergyList;
    List<UserEnergy> selectedUserEnergyList;
	List<UserEnergy> filteredUserEnergyList;
	UserEnergy selectedUserEnergy;
	LazyDataModel<UserEnergy> lazyModel;
	UserEnergyDataSource userEnergyDataSource;
	int editUserEnergyId=0;
	

			   List<EnergyType> energyTypeList;
			   EnergyTypeDataSource energyTypeDataSource;
			   public List<EnergyType> getEnergyTypeList() {
		return energyTypeList;
	     }
	public void setEnergyTypeList(List<EnergyType> energyTypeList) {
		this.energyTypeList = energyTypeList;
	}
			
			
			   List<UserPortfolio> userPortfolioList;
			   UserPortfolioDataSource userPortfolioDataSource;
			   public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	     }
	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}
			
			
			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewUserEnergyBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyUserEnergyDataModel(this.userEnergyList);
		
	}
	
	
	private void initDataSources()
	{
		userEnergyDataSource=new UserEnergyDataSource();
		

			  energyTypeDataSource=new EnergyTypeDataSource();
			
			
			  userPortfolioDataSource=new UserPortfolioDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.userEnergyList=userEnergyDataSource.getAll();
		lazyModel=new LazyUserEnergyDataModel(this.userEnergyList);
		
	}
	
	
	private void populateData()
	{
		userEnergyList=userEnergyDataSource.getAll();
		

			 energyTypeList=energyTypeDataSource.getAll();
	
			
			 userPortfolioList=userPortfolioDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<UserEnergy> getUserEnergyList() {
		return userEnergyList;
	}
	public void setUserEnergyList(List<UserEnergy> userEnergyList) {
		this.userEnergyList = userEnergyList;
	}
	public LazyDataModel<UserEnergy> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<UserEnergy> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public UserEnergy getSelectedUserEnergy() {
		return selectedUserEnergy;
	}
	public void setSelectedUserEnergy(UserEnergy selectedUserEnergy) {
		this.selectedUserEnergy = selectedUserEnergy;
	}
	
	public List<UserEnergy> getSelectedUserEnergyList() {
		return selectedUserEnergyList;
	}

	public void setSelectedUserEnergyList(
			List<UserEnergy> selectedUserEnergyList) {
		this.selectedUserEnergyList = selectedUserEnergyList;
	}

	public List<UserEnergy> getFilteredUserEnergyList() {
		return filteredUserEnergyList;
	}

	public void setFilteredUserEnergyList(
			List<UserEnergy> filteredUserEnergyList) {
		this.filteredUserEnergyList = filteredUserEnergyList;
	}

	public void newUserEnergy() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserEnergy",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("UserEnergy Selected"
				+ ((UserEnergy) event.getObject()).getUserEnergyId());
		for (UserEnergy cat : selectedUserEnergyList) {
			//System.out.println(cat.getUserEnergyLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserEnergy) event.getObject()).getUserEnergyId());

	}

	public void deleteSelectedUserEnergy() {
		for (UserEnergy userEnergy : selectedUserEnergyList) {
			//System.out.println(userEnergy.getUserEnergyLabel());
			this.deleteUserEnergy(userEnergy);
		}
	}
	public void deleteUserEnergy(UserEnergy userEnergy) {
			try{
			userEnergyDataSource.delete(userEnergy);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditUserEnergyId() {
		return editUserEnergyId;
	}

	public void setEditUserEnergyId(int editUserEnergyId) {
		this.editUserEnergyId = editUserEnergyId;
	}
	
	public void editUserEnergy(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserEnergy",
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



