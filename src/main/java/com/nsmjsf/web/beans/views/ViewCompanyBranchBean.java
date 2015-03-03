
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

import com.nsmjsf.web.datasources.CompanyBranchDataSource;
import com.nsmjsf.web.datamodels.CompanyBranch;
import com.nsmjsf.web.lazymodels.LazyCompanyBranchDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewCompanyBranchBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewCompanyBranchBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<CompanyBranch> companyBranchList;
    List<CompanyBranch> selectedCompanyBranchList;
	List<CompanyBranch> filteredCompanyBranchList;
	CompanyBranch selectedCompanyBranch;
	LazyDataModel<CompanyBranch> lazyModel;
	CompanyBranchDataSource companyBranchDataSource;
	int editCompanyBranchId=0;
	
	   
	
	
	public ViewCompanyBranchBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyCompanyBranchDataModel(this.companyBranchList);
		
	}
	
	
	private void initDataSources()
	{
		companyBranchDataSource=new CompanyBranchDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.companyBranchList=companyBranchDataSource.getAll();
		lazyModel=new LazyCompanyBranchDataModel(this.companyBranchList);
		
	}
	
	
	private void populateData()
	{
		companyBranchList=companyBranchDataSource.getAll();
		
	   
	
		
			}
	public List<CompanyBranch> getCompanyBranchList() {
		return companyBranchList;
	}
	public void setCompanyBranchList(List<CompanyBranch> companyBranchList) {
		this.companyBranchList = companyBranchList;
	}
	public LazyDataModel<CompanyBranch> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<CompanyBranch> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public CompanyBranch getSelectedCompanyBranch() {
		return selectedCompanyBranch;
	}
	public void setSelectedCompanyBranch(CompanyBranch selectedCompanyBranch) {
		this.selectedCompanyBranch = selectedCompanyBranch;
	}
	
	public List<CompanyBranch> getSelectedCompanyBranchList() {
		return selectedCompanyBranchList;
	}

	public void setSelectedCompanyBranchList(
			List<CompanyBranch> selectedCompanyBranchList) {
		this.selectedCompanyBranchList = selectedCompanyBranchList;
	}

	public List<CompanyBranch> getFilteredCompanyBranchList() {
		return filteredCompanyBranchList;
	}

	public void setFilteredCompanyBranchList(
			List<CompanyBranch> filteredCompanyBranchList) {
		this.filteredCompanyBranchList = filteredCompanyBranchList;
	}

	public void newCompanyBranch() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCompanyBranch",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("CompanyBranch Selected"
				+ ((CompanyBranch) event.getObject()).getCompanyBranchId());
		for (CompanyBranch cat : selectedCompanyBranchList) {
			//System.out.println(cat.getCompanyBranchLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CompanyBranch) event.getObject()).getCompanyBranchId());

	}

	public void deleteSelectedCompanyBranch() {
		for (CompanyBranch companyBranch : selectedCompanyBranchList) {
			//System.out.println(companyBranch.getCompanyBranchLabel());
			this.deleteCompanyBranch(companyBranch);
		}
	}
	public void deleteCompanyBranch(CompanyBranch companyBranch) {
			try{
			companyBranchDataSource.delete(companyBranch);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditCompanyBranchId() {
		return editCompanyBranchId;
	}

	public void setEditCompanyBranchId(int editCompanyBranchId) {
		this.editCompanyBranchId = editCompanyBranchId;
	}
	
	public void editCompanyBranch(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCompanyBranch",
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



