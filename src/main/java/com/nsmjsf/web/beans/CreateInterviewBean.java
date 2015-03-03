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
import com.nsmjsf.web.datasources.InterviewDataSource;
import com.nsmjsf.web.datamodels.Interview;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
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

public class CreateInterviewBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateInterviewBean.class);


	private Interview interview;
	private InterviewDataSource interviewDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateInterviewBean() {

		interview = new Interview();
		/* init datasources */
		interviewDataSource = new InterviewDataSource();
		
		
			
postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter
				.wrapAll(postList);
	
			
			
			
companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter
				.wrapAll(companyList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.interview=interviewDataSource.get(editId);
			
			

			  
			  this.selectedPostWrapper=PostAdapter.wrap(interview.getPost());
	
			
			
			  
			  this.selectedCompanyWrapper=CompanyAdapter.wrap(interview.getCompany());
	
			
				   
			
			
			
			
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

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	/* getters for datasources */
	public InterviewDataSource getInterviewDataSource() {
		return interviewDataSource;
	}

	public void setInterviewDataSource(InterviewDataSource interviewDataSource) {
		this.interviewDataSource = interviewDataSource;
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








			
			
			


public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}
  
  
  
	public CompanyDataSource getCompanyDataSource() {
		return companyDataSource;
	}

	public void setCompanyDataSource(
			CompanyDataSource companyDataSource) {
		this.companyDataSource = companyDataSource;
	}

	public List<CompanyWrapper> getCompanyWrapperList() {
		return companyWrapperList;
	}

	public void setCompanyWrapperList(
			List<CompanyWrapper> companyWrapperList) {
		this.companyWrapperList = companyWrapperList;
	}

	

	public CompanyWrapper getSelectedCompanyWrapper() {
		return selectedCompanyWrapper;
	}

	public void setSelectedCompanyWrapper(
			CompanyWrapper selectedCompanyWrapper) {
		this.selectedCompanyWrapper = selectedCompanyWrapper;
	}








			
				




	
  
  
  
	public Interview saveInterview() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post =selectedPostWrapper.getPost();

			interview.setPost(post);
			
			
			
                  Company company =selectedCompanyWrapper.getCompany();

			interview.setCompany(company);
			
				   
			
			
			
			
			interviewDataSource.create(interview, session);
			tx.commit();
					MessageService.info("Successfully Saved  Interview !");
				this.interview=new Interview();
			return interview;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Interview .Try Again Later!");
			return null;
		}
	}
	
	public Interview updateInterview() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post = selectedPostWrapper.getPost();

			      interview.setPost(post);
			
			
			
                  Company company = selectedCompanyWrapper.getCompany();

			      interview.setCompany(company);
			
				   
			
			
			
			
			interviewDataSource.create(interview, session);
			tx.commit();
				MessageService.info("Successfully Saved  Interview !");
				this.interview=new Interview();
			return interview;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Interview .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateInterview();
		}else{
		log.info("Creating value");
			saveInterview();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createInterview");
		
	}
	public Interview saveInterview(Session session){
	
	   this.interview= interviewDataSource.create(this.interview,session);
	   return this.interview;
	}
	

}

