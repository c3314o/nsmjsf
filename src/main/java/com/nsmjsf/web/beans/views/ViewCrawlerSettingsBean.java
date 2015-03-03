
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

import com.nsmjsf.web.datasources.CrawlerSettingsDataSource;
import com.nsmjsf.web.datamodels.CrawlerSettings;
import com.nsmjsf.web.lazymodels.LazyCrawlerSettingsDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewCrawlerSettingsBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewCrawlerSettingsBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<CrawlerSettings> crawlerSettingsList;
    List<CrawlerSettings> selectedCrawlerSettingsList;
	List<CrawlerSettings> filteredCrawlerSettingsList;
	CrawlerSettings selectedCrawlerSettings;
	LazyDataModel<CrawlerSettings> lazyModel;
	CrawlerSettingsDataSource crawlerSettingsDataSource;
	int editCrawlerSettingsId=0;
	
	   
	
	
	public ViewCrawlerSettingsBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyCrawlerSettingsDataModel(this.crawlerSettingsList);
		
	}
	
	
	private void initDataSources()
	{
		crawlerSettingsDataSource=new CrawlerSettingsDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.crawlerSettingsList=crawlerSettingsDataSource.getAll();
		lazyModel=new LazyCrawlerSettingsDataModel(this.crawlerSettingsList);
		
	}
	
	
	private void populateData()
	{
		crawlerSettingsList=crawlerSettingsDataSource.getAll();
		
	   
	
		
			}
	public List<CrawlerSettings> getCrawlerSettingsList() {
		return crawlerSettingsList;
	}
	public void setCrawlerSettingsList(List<CrawlerSettings> crawlerSettingsList) {
		this.crawlerSettingsList = crawlerSettingsList;
	}
	public LazyDataModel<CrawlerSettings> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<CrawlerSettings> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public CrawlerSettings getSelectedCrawlerSettings() {
		return selectedCrawlerSettings;
	}
	public void setSelectedCrawlerSettings(CrawlerSettings selectedCrawlerSettings) {
		this.selectedCrawlerSettings = selectedCrawlerSettings;
	}
	
	public List<CrawlerSettings> getSelectedCrawlerSettingsList() {
		return selectedCrawlerSettingsList;
	}

	public void setSelectedCrawlerSettingsList(
			List<CrawlerSettings> selectedCrawlerSettingsList) {
		this.selectedCrawlerSettingsList = selectedCrawlerSettingsList;
	}

	public List<CrawlerSettings> getFilteredCrawlerSettingsList() {
		return filteredCrawlerSettingsList;
	}

	public void setFilteredCrawlerSettingsList(
			List<CrawlerSettings> filteredCrawlerSettingsList) {
		this.filteredCrawlerSettingsList = filteredCrawlerSettingsList;
	}

	public void newCrawlerSettings() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCrawlerSettings",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("CrawlerSettings Selected"
				+ ((CrawlerSettings) event.getObject()).getCrawlerSettingsId());
		for (CrawlerSettings cat : selectedCrawlerSettingsList) {
			//System.out.println(cat.getCrawlerSettingsLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CrawlerSettings) event.getObject()).getCrawlerSettingsId());

	}

	public void deleteSelectedCrawlerSettings() {
		for (CrawlerSettings crawlerSettings : selectedCrawlerSettingsList) {
			//System.out.println(crawlerSettings.getCrawlerSettingsLabel());
			this.deleteCrawlerSettings(crawlerSettings);
		}
	}
	public void deleteCrawlerSettings(CrawlerSettings crawlerSettings) {
			try{
			crawlerSettingsDataSource.delete(crawlerSettings);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditCrawlerSettingsId() {
		return editCrawlerSettingsId;
	}

	public void setEditCrawlerSettingsId(int editCrawlerSettingsId) {
		this.editCrawlerSettingsId = editCrawlerSettingsId;
	}
	
	public void editCrawlerSettings(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCrawlerSettings",
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



