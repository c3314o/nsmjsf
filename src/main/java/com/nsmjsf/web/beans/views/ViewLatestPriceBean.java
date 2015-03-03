
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

import com.nsmjsf.web.datasources.LatestPriceDataSource;
import com.nsmjsf.web.datamodels.LatestPrice;
import com.nsmjsf.web.lazymodels.LazyLatestPriceDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewLatestPriceBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewLatestPriceBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<LatestPrice> latestPriceList;
    List<LatestPrice> selectedLatestPriceList;
	List<LatestPrice> filteredLatestPriceList;
	LatestPrice selectedLatestPrice;
	LazyDataModel<LatestPrice> lazyModel;
	LatestPriceDataSource latestPriceDataSource;
	int editLatestPriceId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
			
			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
				   
	
	
	public ViewLatestPriceBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyLatestPriceDataModel(this.latestPriceList);
		
	}
	
	
	private void initDataSources()
	{
		latestPriceDataSource=new LatestPriceDataSource();
		

			  postDataSource=new PostDataSource();
			
			
			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.latestPriceList=latestPriceDataSource.getAll();
		lazyModel=new LazyLatestPriceDataModel(this.latestPriceList);
		
	}
	
	
	private void populateData()
	{
		latestPriceList=latestPriceDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
			
			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<LatestPrice> getLatestPriceList() {
		return latestPriceList;
	}
	public void setLatestPriceList(List<LatestPrice> latestPriceList) {
		this.latestPriceList = latestPriceList;
	}
	public LazyDataModel<LatestPrice> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<LatestPrice> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public LatestPrice getSelectedLatestPrice() {
		return selectedLatestPrice;
	}
	public void setSelectedLatestPrice(LatestPrice selectedLatestPrice) {
		this.selectedLatestPrice = selectedLatestPrice;
	}
	
	public List<LatestPrice> getSelectedLatestPriceList() {
		return selectedLatestPriceList;
	}

	public void setSelectedLatestPriceList(
			List<LatestPrice> selectedLatestPriceList) {
		this.selectedLatestPriceList = selectedLatestPriceList;
	}

	public List<LatestPrice> getFilteredLatestPriceList() {
		return filteredLatestPriceList;
	}

	public void setFilteredLatestPriceList(
			List<LatestPrice> filteredLatestPriceList) {
		this.filteredLatestPriceList = filteredLatestPriceList;
	}

	public void newLatestPrice() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createLatestPrice",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("LatestPrice Selected"
				+ ((LatestPrice) event.getObject()).getLatestPriceId());
		for (LatestPrice cat : selectedLatestPriceList) {
			//System.out.println(cat.getLatestPriceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((LatestPrice) event.getObject()).getLatestPriceId());

	}

	public void deleteSelectedLatestPrice() {
		for (LatestPrice latestPrice : selectedLatestPriceList) {
			//System.out.println(latestPrice.getLatestPriceLabel());
			this.deleteLatestPrice(latestPrice);
		}
	}
	public void deleteLatestPrice(LatestPrice latestPrice) {
			try{
			latestPriceDataSource.delete(latestPrice);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditLatestPriceId() {
		return editLatestPriceId;
	}

	public void setEditLatestPriceId(int editLatestPriceId) {
		this.editLatestPriceId = editLatestPriceId;
	}
	
	public void editLatestPrice(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createLatestPrice",
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



