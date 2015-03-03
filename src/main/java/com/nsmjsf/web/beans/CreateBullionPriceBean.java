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
import com.nsmjsf.web.datasources.BullionPriceDataSource;
import com.nsmjsf.web.datamodels.BullionPrice;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateBullionPriceBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateBullionPriceBean.class);


	private BullionPrice bullionPrice;
	private BullionPriceDataSource bullionPriceDataSource;
	
	
	
			
    private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateBullionPriceBean() {

		bullionPrice = new BullionPrice();
		/* init datasources */
		bullionPriceDataSource = new BullionPriceDataSource();
		
		
			
postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter
				.wrapAll(postList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.bullionPrice=bullionPriceDataSource.get(editId);
			
			

			  
			  this.selectedPostWrapper=PostAdapter.wrap(bullionPrice.getPost());
	
			
				   
			
			
			
			
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

	public BullionPrice getBullionPrice() {
		return bullionPrice;
	}

	public void setBullionPrice(BullionPrice bullionPrice) {
		this.bullionPrice = bullionPrice;
	}

	/* getters for datasources */
	public BullionPriceDataSource getBullionPriceDataSource() {
		return bullionPriceDataSource;
	}

	public void setBullionPriceDataSource(BullionPriceDataSource bullionPriceDataSource) {
		this.bullionPriceDataSource = bullionPriceDataSource;
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








			
				




	
  
  
  
	public BullionPrice saveBullionPrice() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post =selectedPostWrapper.getPost();

			bullionPrice.setPost(post);
			
				   
			
			
			
			
			bullionPriceDataSource.create(bullionPrice, session);
			tx.commit();
					MessageService.info("Successfully Saved  BullionPrice !");
				this.bullionPrice=new BullionPrice();
			return bullionPrice;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving BullionPrice .Try Again Later!");
			return null;
		}
	}
	
	public BullionPrice updateBullionPrice() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post = selectedPostWrapper.getPost();

			      bullionPrice.setPost(post);
			
				   
			
			
			
			
			bullionPriceDataSource.create(bullionPrice, session);
			tx.commit();
				MessageService.info("Successfully Saved  BullionPrice !");
				this.bullionPrice=new BullionPrice();
			return bullionPrice;

		} catch (Exception ex) {
			MessageService.error("Failed Saving BullionPrice .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateBullionPrice();
		}else{
		log.info("Creating value");
			saveBullionPrice();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createBullionPrice");
		
	}
	public BullionPrice saveBullionPrice(Session session){
	
	   this.bullionPrice= bullionPriceDataSource.create(this.bullionPrice,session);
	   return this.bullionPrice;
	}
	

}

