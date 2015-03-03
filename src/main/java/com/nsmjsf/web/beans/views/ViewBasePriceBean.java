
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

import com.nsmjsf.web.datasources.BasePriceDataSource;
import com.nsmjsf.web.datamodels.BasePrice;
import com.nsmjsf.web.lazymodels.LazyBasePriceDataModel;


			
import com.nsmjsf.web.adapters.CompanyAdapter;


import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewBasePriceBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewBasePriceBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<BasePrice> basePriceList;
    List<BasePrice> selectedBasePriceList;
	List<BasePrice> filteredBasePriceList;
	BasePrice selectedBasePrice;
	LazyDataModel<BasePrice> lazyModel;
	BasePriceDataSource basePriceDataSource;
	int editBasePriceId=0;
	

			   List<Company> companyList;
			   CompanyDataSource companyDataSource;
			   public List<Company> getCompanyList() {
		return companyList;
	     }
	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
			
				   
	
	
	public ViewBasePriceBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyBasePriceDataModel(this.basePriceList);
		
	}
	
	
	private void initDataSources()
	{
		basePriceDataSource=new BasePriceDataSource();
		

			  companyDataSource=new CompanyDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.basePriceList=basePriceDataSource.getAll();
		lazyModel=new LazyBasePriceDataModel(this.basePriceList);
		
	}
	
	
	private void populateData()
	{
		basePriceList=basePriceDataSource.getAll();
		

			 companyList=companyDataSource.getAll();
	
				   
	
		
			}
	public List<BasePrice> getBasePriceList() {
		return basePriceList;
	}
	public void setBasePriceList(List<BasePrice> basePriceList) {
		this.basePriceList = basePriceList;
	}
	public LazyDataModel<BasePrice> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<BasePrice> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public BasePrice getSelectedBasePrice() {
		return selectedBasePrice;
	}
	public void setSelectedBasePrice(BasePrice selectedBasePrice) {
		this.selectedBasePrice = selectedBasePrice;
	}
	
	public List<BasePrice> getSelectedBasePriceList() {
		return selectedBasePriceList;
	}

	public void setSelectedBasePriceList(
			List<BasePrice> selectedBasePriceList) {
		this.selectedBasePriceList = selectedBasePriceList;
	}

	public List<BasePrice> getFilteredBasePriceList() {
		return filteredBasePriceList;
	}

	public void setFilteredBasePriceList(
			List<BasePrice> filteredBasePriceList) {
		this.filteredBasePriceList = filteredBasePriceList;
	}

	public void newBasePrice() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBasePrice",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("BasePrice Selected"
				+ ((BasePrice) event.getObject()).getBasePriceId());
		for (BasePrice cat : selectedBasePriceList) {
			//System.out.println(cat.getBasePriceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BasePrice) event.getObject()).getBasePriceId());

	}

	public void deleteSelectedBasePrice() {
		for (BasePrice basePrice : selectedBasePriceList) {
			//System.out.println(basePrice.getBasePriceLabel());
			this.deleteBasePrice(basePrice);
		}
	}
	public void deleteBasePrice(BasePrice basePrice) {
			try{
			basePriceDataSource.delete(basePrice);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditBasePriceId() {
		return editBasePriceId;
	}

	public void setEditBasePriceId(int editBasePriceId) {
		this.editBasePriceId = editBasePriceId;
	}
	
	public void editBasePrice(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBasePrice",
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



