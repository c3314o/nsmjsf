
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

import com.nsmjsf.web.datasources.EnergyPriceDataSource;
import com.nsmjsf.web.datamodels.EnergyPrice;
import com.nsmjsf.web.lazymodels.LazyEnergyPriceDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewEnergyPriceBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewEnergyPriceBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<EnergyPrice> energyPriceList;
    List<EnergyPrice> selectedEnergyPriceList;
	List<EnergyPrice> filteredEnergyPriceList;
	EnergyPrice selectedEnergyPrice;
	LazyDataModel<EnergyPrice> lazyModel;
	EnergyPriceDataSource energyPriceDataSource;
	int editEnergyPriceId=0;
	
	   
	
	
	public ViewEnergyPriceBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyEnergyPriceDataModel(this.energyPriceList);
		
	}
	
	
	private void initDataSources()
	{
		energyPriceDataSource=new EnergyPriceDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.energyPriceList=energyPriceDataSource.getAll();
		lazyModel=new LazyEnergyPriceDataModel(this.energyPriceList);
		
	}
	
	
	private void populateData()
	{
		energyPriceList=energyPriceDataSource.getAll();
		
	   
	
		
			}
	public List<EnergyPrice> getEnergyPriceList() {
		return energyPriceList;
	}
	public void setEnergyPriceList(List<EnergyPrice> energyPriceList) {
		this.energyPriceList = energyPriceList;
	}
	public LazyDataModel<EnergyPrice> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<EnergyPrice> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public EnergyPrice getSelectedEnergyPrice() {
		return selectedEnergyPrice;
	}
	public void setSelectedEnergyPrice(EnergyPrice selectedEnergyPrice) {
		this.selectedEnergyPrice = selectedEnergyPrice;
	}
	
	public List<EnergyPrice> getSelectedEnergyPriceList() {
		return selectedEnergyPriceList;
	}

	public void setSelectedEnergyPriceList(
			List<EnergyPrice> selectedEnergyPriceList) {
		this.selectedEnergyPriceList = selectedEnergyPriceList;
	}

	public List<EnergyPrice> getFilteredEnergyPriceList() {
		return filteredEnergyPriceList;
	}

	public void setFilteredEnergyPriceList(
			List<EnergyPrice> filteredEnergyPriceList) {
		this.filteredEnergyPriceList = filteredEnergyPriceList;
	}

	public void newEnergyPrice() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createEnergyPrice",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("EnergyPrice Selected"
				+ ((EnergyPrice) event.getObject()).getEnergyPriceId());
		for (EnergyPrice cat : selectedEnergyPriceList) {
			//System.out.println(cat.getEnergyPriceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((EnergyPrice) event.getObject()).getEnergyPriceId());

	}

	public void deleteSelectedEnergyPrice() {
		for (EnergyPrice energyPrice : selectedEnergyPriceList) {
			//System.out.println(energyPrice.getEnergyPriceLabel());
			this.deleteEnergyPrice(energyPrice);
		}
	}
	public void deleteEnergyPrice(EnergyPrice energyPrice) {
			try{
			energyPriceDataSource.delete(energyPrice);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditEnergyPriceId() {
		return editEnergyPriceId;
	}

	public void setEditEnergyPriceId(int editEnergyPriceId) {
		this.editEnergyPriceId = editEnergyPriceId;
	}
	
	public void editEnergyPrice(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createEnergyPrice",
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



