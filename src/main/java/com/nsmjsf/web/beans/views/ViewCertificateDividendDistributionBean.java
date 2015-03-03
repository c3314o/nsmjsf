
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

import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;
import com.nsmjsf.web.datamodels.CertificateDividendDistribution;
import com.nsmjsf.web.lazymodels.LazyCertificateDividendDistributionDataModel;


			
import com.nsmjsf.web.adapters.FiscalYearAdapter;


import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;



			
			
			
import com.nsmjsf.web.adapters.AnnouncementAdapter;


import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;



			
			
			
import com.nsmjsf.web.adapters.IssueManagerAdapter;


import com.nsmjsf.web.datasources.IssueManagerDataSource;

import com.nsmjsf.web.datamodels.IssueManager;

import com.nsmjsf.web.wrappers.IssueManagerWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewCertificateDividendDistributionBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewCertificateDividendDistributionBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<CertificateDividendDistribution> certificateDividendDistributionList;
    List<CertificateDividendDistribution> selectedCertificateDividendDistributionList;
	List<CertificateDividendDistribution> filteredCertificateDividendDistributionList;
	CertificateDividendDistribution selectedCertificateDividendDistribution;
	LazyDataModel<CertificateDividendDistribution> lazyModel;
	CertificateDividendDistributionDataSource certificateDividendDistributionDataSource;
	int editCertificateDividendDistributionId=0;
	

			   List<FiscalYear> fiscalYearList;
			   FiscalYearDataSource fiscalYearDataSource;
			   public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	     }
	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}
			
			
			   List<Announcement> announcementList;
			   AnnouncementDataSource announcementDataSource;
			   public List<Announcement> getAnnouncementList() {
		return announcementList;
	     }
	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}
			
			
			   List<IssueManager> issueManagerList;
			   IssueManagerDataSource issueManagerDataSource;
			   public List<IssueManager> getIssueManagerList() {
		return issueManagerList;
	     }
	public void setIssueManagerList(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}
			
				   
	
	
	public ViewCertificateDividendDistributionBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyCertificateDividendDistributionDataModel(this.certificateDividendDistributionList);
		
	}
	
	
	private void initDataSources()
	{
		certificateDividendDistributionDataSource=new CertificateDividendDistributionDataSource();
		

			  fiscalYearDataSource=new FiscalYearDataSource();
			
			
			  announcementDataSource=new AnnouncementDataSource();
			
			
			  issueManagerDataSource=new IssueManagerDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.certificateDividendDistributionList=certificateDividendDistributionDataSource.getAll();
		lazyModel=new LazyCertificateDividendDistributionDataModel(this.certificateDividendDistributionList);
		
	}
	
	
	private void populateData()
	{
		certificateDividendDistributionList=certificateDividendDistributionDataSource.getAll();
		

			 fiscalYearList=fiscalYearDataSource.getAll();
	
			
			 announcementList=announcementDataSource.getAll();
	
			
			 issueManagerList=issueManagerDataSource.getAll();
	
				   
	
		
			}
	public List<CertificateDividendDistribution> getCertificateDividendDistributionList() {
		return certificateDividendDistributionList;
	}
	public void setCertificateDividendDistributionList(List<CertificateDividendDistribution> certificateDividendDistributionList) {
		this.certificateDividendDistributionList = certificateDividendDistributionList;
	}
	public LazyDataModel<CertificateDividendDistribution> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<CertificateDividendDistribution> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public CertificateDividendDistribution getSelectedCertificateDividendDistribution() {
		return selectedCertificateDividendDistribution;
	}
	public void setSelectedCertificateDividendDistribution(CertificateDividendDistribution selectedCertificateDividendDistribution) {
		this.selectedCertificateDividendDistribution = selectedCertificateDividendDistribution;
	}
	
	public List<CertificateDividendDistribution> getSelectedCertificateDividendDistributionList() {
		return selectedCertificateDividendDistributionList;
	}

	public void setSelectedCertificateDividendDistributionList(
			List<CertificateDividendDistribution> selectedCertificateDividendDistributionList) {
		this.selectedCertificateDividendDistributionList = selectedCertificateDividendDistributionList;
	}

	public List<CertificateDividendDistribution> getFilteredCertificateDividendDistributionList() {
		return filteredCertificateDividendDistributionList;
	}

	public void setFilteredCertificateDividendDistributionList(
			List<CertificateDividendDistribution> filteredCertificateDividendDistributionList) {
		this.filteredCertificateDividendDistributionList = filteredCertificateDividendDistributionList;
	}

	public void newCertificateDividendDistribution() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCertificateDividendDistribution",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("CertificateDividendDistribution Selected"
				+ ((CertificateDividendDistribution) event.getObject()).getCertificateDividendDistributionId());
		for (CertificateDividendDistribution cat : selectedCertificateDividendDistributionList) {
			//System.out.println(cat.getCertificateDividendDistributionLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CertificateDividendDistribution) event.getObject()).getCertificateDividendDistributionId());

	}

	public void deleteSelectedCertificateDividendDistribution() {
		for (CertificateDividendDistribution certificateDividendDistribution : selectedCertificateDividendDistributionList) {
			//System.out.println(certificateDividendDistribution.getCertificateDividendDistributionLabel());
			this.deleteCertificateDividendDistribution(certificateDividendDistribution);
		}
	}
	public void deleteCertificateDividendDistribution(CertificateDividendDistribution certificateDividendDistribution) {
			try{
			certificateDividendDistributionDataSource.delete(certificateDividendDistribution);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditCertificateDividendDistributionId() {
		return editCertificateDividendDistributionId;
	}

	public void setEditCertificateDividendDistributionId(int editCertificateDividendDistributionId) {
		this.editCertificateDividendDistributionId = editCertificateDividendDistributionId;
	}
	
	public void editCertificateDividendDistribution(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCertificateDividendDistribution",
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



