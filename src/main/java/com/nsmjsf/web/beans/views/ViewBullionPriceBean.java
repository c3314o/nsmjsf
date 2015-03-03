
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

import com.nsmjsf.web.datasources.BullionPriceDataSource;
import com.nsmjsf.web.datamodels.BullionPrice;
import com.nsmjsf.web.lazymodels.LazyBullionPriceDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewBullionPriceBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewBullionPriceBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<BullionPrice> bullionPriceList;
    List<BullionPrice> selectedBullionPriceList;
	List<BullionPrice> filteredBullionPriceList;
	BullionPrice selectedBullionPrice;
	LazyDataModel<BullionPrice> lazyModel;
	BullionPriceDataSource bullionPriceDataSource;
	int editBullionPriceId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
				   
	
	
	public ViewBullionPriceBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyBullionPriceDataModel(this.bullionPriceList);
		
	}
	
	
	private void initDataSources()
	{
		bullionPriceDataSource=new BullionPriceDataSource();
		

			  postDataSource=new PostDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.bullionPriceList=bullionPriceDataSource.getAll();
		lazyModel=new LazyBullionPriceDataModel(this.bullionPriceList);
		
	}
	
	
	private void populateData()
	{
		bullionPriceList=bullionPriceDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
				   
	
		
			}
	public List<BullionPrice> getBullionPriceList() {
		return bullionPriceList;
	}
	public void setBullionPriceList(List<BullionPrice> bullionPriceList) {
		this.bullionPriceList = bullionPriceList;
	}
	public LazyDataModel<BullionPrice> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<BullionPrice> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public BullionPrice getSelectedBullionPrice() {
		return selectedBullionPrice;
	}
	public void setSelectedBullionPrice(BullionPrice selectedBullionPrice) {
		this.selectedBullionPrice = selectedBullionPrice;
	}
	
	public List<BullionPrice> getSelectedBullionPriceList() {
		return selectedBullionPriceList;
	}

	public void setSelectedBullionPriceList(
			List<BullionPrice> selectedBullionPriceList) {
		this.selectedBullionPriceList = selectedBullionPriceList;
	}

	public List<BullionPrice> getFilteredBullionPriceList() {
		return filteredBullionPriceList;
	}

	public void setFilteredBullionPriceList(
			List<BullionPrice> filteredBullionPriceList) {
		this.filteredBullionPriceList = filteredBullionPriceList;
	}

	public void newBullionPrice() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBullionPrice",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("BullionPrice Selected"
				+ ((BullionPrice) event.getObject()).getBullionPriceId());
		for (BullionPrice cat : selectedBullionPriceList) {
			//System.out.println(cat.getBullionPriceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BullionPrice) event.getObject()).getBullionPriceId());

	}

	public void deleteSelectedBullionPrice() {
		for (BullionPrice bullionPrice : selectedBullionPriceList) {
			//System.out.println(bullionPrice.getBullionPriceLabel());
			this.deleteBullionPrice(bullionPrice);
		}
	}
	public void deleteBullionPrice(BullionPrice bullionPrice) {
			try{
			bullionPriceDataSource.delete(bullionPrice);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditBullionPriceId() {
		return editBullionPriceId;
	}

	public void setEditBullionPriceId(int editBullionPriceId) {
		this.editBullionPriceId = editBullionPriceId;
	}
	
	public void editBullionPrice(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBullionPrice",
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



