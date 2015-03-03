
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

import com.nsmjsf.web.datasources.ProfitLossDataSource;
import com.nsmjsf.web.datamodels.ProfitLoss;
import com.nsmjsf.web.lazymodels.LazyProfitLossDataModel;


			
import com.nsmjsf.web.adapters.FinancialReportAdapter;


import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewProfitLossBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewProfitLossBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<ProfitLoss> profitLossList;
    List<ProfitLoss> selectedProfitLossList;
	List<ProfitLoss> filteredProfitLossList;
	ProfitLoss selectedProfitLoss;
	LazyDataModel<ProfitLoss> lazyModel;
	ProfitLossDataSource profitLossDataSource;
	int editProfitLossId=0;
	

			   List<FinancialReport> financialReportList;
			   FinancialReportDataSource financialReportDataSource;
			   public List<FinancialReport> getFinancialReportList() {
		return financialReportList;
	     }
	public void setFinancialReportList(List<FinancialReport> financialReportList) {
		this.financialReportList = financialReportList;
	}
			
				   
	
	
	public ViewProfitLossBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyProfitLossDataModel(this.profitLossList);
		
	}
	
	
	private void initDataSources()
	{
		profitLossDataSource=new ProfitLossDataSource();
		

			  financialReportDataSource=new FinancialReportDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.profitLossList=profitLossDataSource.getAll();
		lazyModel=new LazyProfitLossDataModel(this.profitLossList);
		
	}
	
	
	private void populateData()
	{
		profitLossList=profitLossDataSource.getAll();
		

			 financialReportList=financialReportDataSource.getAll();
	
				   
	
		
			}
	public List<ProfitLoss> getProfitLossList() {
		return profitLossList;
	}
	public void setProfitLossList(List<ProfitLoss> profitLossList) {
		this.profitLossList = profitLossList;
	}
	public LazyDataModel<ProfitLoss> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<ProfitLoss> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public ProfitLoss getSelectedProfitLoss() {
		return selectedProfitLoss;
	}
	public void setSelectedProfitLoss(ProfitLoss selectedProfitLoss) {
		this.selectedProfitLoss = selectedProfitLoss;
	}
	
	public List<ProfitLoss> getSelectedProfitLossList() {
		return selectedProfitLossList;
	}

	public void setSelectedProfitLossList(
			List<ProfitLoss> selectedProfitLossList) {
		this.selectedProfitLossList = selectedProfitLossList;
	}

	public List<ProfitLoss> getFilteredProfitLossList() {
		return filteredProfitLossList;
	}

	public void setFilteredProfitLossList(
			List<ProfitLoss> filteredProfitLossList) {
		this.filteredProfitLossList = filteredProfitLossList;
	}

	public void newProfitLoss() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createProfitLoss",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("ProfitLoss Selected"
				+ ((ProfitLoss) event.getObject()).getProfitLossId());
		for (ProfitLoss cat : selectedProfitLossList) {
			//System.out.println(cat.getProfitLossLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((ProfitLoss) event.getObject()).getProfitLossId());

	}

	public void deleteSelectedProfitLoss() {
		for (ProfitLoss profitLoss : selectedProfitLossList) {
			//System.out.println(profitLoss.getProfitLossLabel());
			this.deleteProfitLoss(profitLoss);
		}
	}
	public void deleteProfitLoss(ProfitLoss profitLoss) {
			try{
			profitLossDataSource.delete(profitLoss);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditProfitLossId() {
		return editProfitLossId;
	}

	public void setEditProfitLossId(int editProfitLossId) {
		this.editProfitLossId = editProfitLossId;
	}
	
	public void editProfitLoss(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createProfitLoss",
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



