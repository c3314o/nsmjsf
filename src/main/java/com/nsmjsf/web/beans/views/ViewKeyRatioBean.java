
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

import com.nsmjsf.web.datasources.KeyRatioDataSource;
import com.nsmjsf.web.datamodels.KeyRatio;
import com.nsmjsf.web.lazymodels.LazyKeyRatioDataModel;


			
import com.nsmjsf.web.adapters.FinancialReportAdapter;


import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewKeyRatioBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewKeyRatioBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<KeyRatio> keyRatioList;
    List<KeyRatio> selectedKeyRatioList;
	List<KeyRatio> filteredKeyRatioList;
	KeyRatio selectedKeyRatio;
	LazyDataModel<KeyRatio> lazyModel;
	KeyRatioDataSource keyRatioDataSource;
	int editKeyRatioId=0;
	

			   List<FinancialReport> financialReportList;
			   FinancialReportDataSource financialReportDataSource;
			   public List<FinancialReport> getFinancialReportList() {
		return financialReportList;
	     }
	public void setFinancialReportList(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}
			
				   
	
	
	public ViewKeyRatioBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyKeyRatioDataModel(this.keyRatioList);
		
	}
	
	
	private void initDataSources()
	{
		keyRatioDataSource=new KeyRatioDataSource();
		

			  financialReportDataSource=new FinancialReportDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.keyRatioList=keyRatioDataSource.getAll();
		lazyModel=new LazyKeyRatioDataModel(this.keyRatioList);
		
	}
	
	
	private void populateData()
	{
		keyRatioList=keyRatioDataSource.getAll();
		

			 financialReportList=financialReportDataSource.getAll();
	
				   
	
		
			}
	public List<KeyRatio> getKeyRatioList() {
		return keyRatioList;
	}
	public void setKeyRatioList(List<KeyRatio> keyRatioList) {
		this.keyRatioList = keyRatioList;
	}
	public LazyDataModel<KeyRatio> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<KeyRatio> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public KeyRatio getSelectedKeyRatio() {
		return selectedKeyRatio;
	}
	public void setSelectedKeyRatio(KeyRatio selectedKeyRatio) {
		this.selectedKeyRatio = selectedKeyRatio;
	}
	
	public List<KeyRatio> getSelectedKeyRatioList() {
		return selectedKeyRatioList;
	}

	public void setSelectedKeyRatioList(
			List<KeyRatio> selectedKeyRatioList) {
		this.selectedKeyRatioList = selectedKeyRatioList;
	}

	public List<KeyRatio> getFilteredKeyRatioList() {
		return filteredKeyRatioList;
	}

	public void setFilteredKeyRatioList(
			List<KeyRatio> filteredKeyRatioList) {
		this.filteredKeyRatioList = filteredKeyRatioList;
	}

	public void newKeyRatio() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createKeyRatio",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("KeyRatio Selected"
				+ ((KeyRatio) event.getObject()).getKeyRatioId());
		for (KeyRatio cat : selectedKeyRatioList) {
			//System.out.println(cat.getKeyRatioLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((KeyRatio) event.getObject()).getKeyRatioId());

	}

	public void deleteSelectedKeyRatio() {
		for (KeyRatio keyRatio : selectedKeyRatioList) {
			//System.out.println(keyRatio.getKeyRatioLabel());
			this.deleteKeyRatio(keyRatio);
		}
	}
	public void deleteKeyRatio(KeyRatio keyRatio) {
			try{
			keyRatioDataSource.delete(keyRatio);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditKeyRatioId() {
		return editKeyRatioId;
	}

	public void setEditKeyRatioId(int editKeyRatioId) {
		this.editKeyRatioId = editKeyRatioId;
	}
	
	public void editKeyRatio(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createKeyRatio",
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



