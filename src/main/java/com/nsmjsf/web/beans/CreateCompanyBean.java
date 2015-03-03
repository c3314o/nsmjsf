package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;
import org.primefaces.context.RequestContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.SectorAdapter;


import com.nsmjsf.web.datasources.SectorDataSource;

import com.nsmjsf.web.datamodels.Sector;

import com.nsmjsf.web.wrappers.SectorWrapper;



			
			
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.CompanyDetailAdapter;


import com.nsmjsf.web.datasources.CompanyDetailDataSource;

import com.nsmjsf.web.datamodels.CompanyDetail;

import com.nsmjsf.web.wrappers.CompanyDetailWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateCompanyBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateCompanyBean.class);


	private Company company;
	private CompanyDataSource companyDataSource;
	
	
	
			
    private SectorDataSource sectorDataSource;
	private List<SectorWrapper> sectorWrapperList;
	private List<Sector> sectorList;
	private SectorWrapper selectedSectorWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private CompanyDetailDataSource companyDetailDataSource;
	private List<CompanyDetailWrapper> companyDetailWrapperList;
	private List<CompanyDetail> companyDetailList;
	private CompanyDetailWrapper selectedCompanyDetailWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateCompanyBean() {

		company = new Company();
		/* init datasources */
		companyDataSource = new CompanyDataSource();
		
		
			
sectorDataSource = new SectorDataSource();

		/* init option wrappers */
		sectorList = sectorDataSource.getAll();
		sectorWrapperList = SectorAdapter
				.wrapAll(sectorList);
	
			
			
			
postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter
				.wrapAll(postList);
	
			
			
			
companyDetailDataSource = new CompanyDetailDataSource();

		/* init option wrappers */
		companyDetailList = companyDetailDataSource.getAll();
		companyDetailWrapperList = CompanyDetailAdapter
				.wrapAll(companyDetailList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.company=companyDataSource.get(editId);
			
			

			  
			  this.selectedSectorWrapper=SectorAdapter.wrap(company.getSector());
	
			
			
			  
			  this.selectedPostWrapper=PostAdapter.wrap(company.getPost());
	
			
			
			  
			  this.selectedCompanyDetailWrapper=CompanyDetailAdapter.wrap(company.getCompanyDetail());
	
			
				   
			
			
			
			
		}
	}
	private void extractParams()
	{
		int editId = ParameterManager.getInt("editId");
		if(editId!=0)
		{
			this.editId=editId;
			this.editMode=true;
			System.out.println("EditId"+editId);
		}
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	/* getters for datasources */
	public CompanyDataSource getCompanyDataSource() {
		return companyDataSource;
	}

	public void setCompanyDataSource(CompanyDataSource companyDataSource) {
		this.companyDataSource = companyDataSource;
	}
	
	
	
	
	
	
	
			


public List<Sector> getSectorList() {
		return sectorList;
	}

	public void setSectorList(List<Sector> sectorList) {
		this.sectorList = sectorList;
	}
  
  
  
	public SectorDataSource getSectorDataSource() {
		return sectorDataSource;
	}

	public void setSectorDataSource(
			SectorDataSource sectorDataSource) {
		this.sectorDataSource = sectorDataSource;
	}

	public List<SectorWrapper> getSectorWrapperList() {
		return sectorWrapperList;
	}

	public void setSectorWrapperList(
			List<SectorWrapper> sectorWrapperList) {
		this.sectorWrapperList = sectorWrapperList;
	}

	

	public SectorWrapper getSelectedSectorWrapper() {
		return selectedSectorWrapper;
	}

	public void setSelectedSectorWrapper(
			SectorWrapper selectedSectorWrapper) {
		this.selectedSectorWrapper = selectedSectorWrapper;
	}








			
			
			


public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
  
  
  
	public PostDataSource getPostDataSource() {
		return postDataSource;
	}

	public void setPostDataSource(
			PostDataSource postDataSource) {
		this.postDataSource = postDataSource;
	}

	public List<PostWrapper> getPostWrapperList() {
		return postWrapperList;
	}

	public void setPostWrapperList(
			List<PostWrapper> postWrapperList) {
		this.postWrapperList = postWrapperList;
	}

	

	public PostWrapper getSelectedPostWrapper() {
		return selectedPostWrapper;
	}

	public void setSelectedPostWrapper(
			PostWrapper selectedPostWrapper) {
		this.selectedPostWrapper = selectedPostWrapper;
	}








			
			
			


public List<CompanyDetail> getCompanyDetailList() {
		return companyDetailList;
	}

	public void setCompanyDetailList(List<CompanyDetail> companyDetailList) {
		this.companyDetailList = companyDetailList;
	}
  
  
  
	public CompanyDetailDataSource getCompanyDetailDataSource() {
		return companyDetailDataSource;
	}

	public void setCompanyDetailDataSource(
			CompanyDetailDataSource companyDetailDataSource) {
		this.companyDetailDataSource = companyDetailDataSource;
	}

	public List<CompanyDetailWrapper> getCompanyDetailWrapperList() {
		return companyDetailWrapperList;
	}

	public void setCompanyDetailWrapperList(
			List<CompanyDetailWrapper> companyDetailWrapperList) {
		this.companyDetailWrapperList = companyDetailWrapperList;
	}

	

	public CompanyDetailWrapper getSelectedCompanyDetailWrapper() {
		return selectedCompanyDetailWrapper;
	}

	public void setSelectedCompanyDetailWrapper(
			CompanyDetailWrapper selectedCompanyDetailWrapper) {
		this.selectedCompanyDetailWrapper = selectedCompanyDetailWrapper;
	}








			
				




	
  
  
  
	public Company saveCompany() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Sector sector =selectedSectorWrapper.getSector();

			company.setSector(sector);
			
			
			
                  Post post =selectedPostWrapper.getPost();

			company.setPost(post);
			
			
			
                  CompanyDetail companyDetail =selectedCompanyDetailWrapper.getCompanyDetail();

			company.setCompanyDetail(companyDetail);
			
				   
			
			
			
			
			companyDataSource.create(company, session);
			tx.commit();
					MessageService.info("Successfully Saved  Company !");
				this.company=new Company();
			return company;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Company .Try Again Later!");
			return null;
		}
	}
	
	public Company updateCompany() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Sector sector = selectedSectorWrapper.getSector();

			      company.setSector(sector);
			
			
			
                  Post post = selectedPostWrapper.getPost();

			      company.setPost(post);
			
			
			
                  CompanyDetail companyDetail = selectedCompanyDetailWrapper.getCompanyDetail();

			      company.setCompanyDetail(companyDetail);
			
				   
			
			
			
			
			companyDataSource.create(company, session);
			tx.commit();
				MessageService.info("Successfully Saved  Company !");
				this.company=new Company();
			return company;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Company .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateCompany();
		}else{
		log.info("Creating value");
			saveCompany();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createCompany");
		
	}
	public Company saveCompany(Session session){
	
	   this.company= companyDataSource.create(this.company,session);
	   return this.company;
	}
	

}

