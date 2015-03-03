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
import com.nsmjsf.web.datasources.EventDataSource;
import com.nsmjsf.web.datamodels.Event;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateEventBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateEventBean.class);


	private Event event;
	private EventDataSource eventDataSource;
	
	
	
			
    private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateEventBean() {

		event = new Event();
		/* init datasources */
		eventDataSource = new EventDataSource();
		
		
			
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
			this.event=eventDataSource.get(editId);
			
			

			  
			  this.selectedPostWrapper=PostAdapter.wrap(event.getPost());
	
			
				   
			
			
			
			
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

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	/* getters for datasources */
	public EventDataSource getEventDataSource() {
		return eventDataSource;
	}

	public void setEventDataSource(EventDataSource eventDataSource) {
		this.eventDataSource = eventDataSource;
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








			
				




	
  
  
  
	public Event saveEvent() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post =selectedPostWrapper.getPost();

			event.setPost(post);
			
				   
			
			
			
			
			eventDataSource.create(event, session);
			tx.commit();
					MessageService.info("Successfully Saved  Event !");
				this.event=new Event();
			return event;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Event .Try Again Later!");
			return null;
		}
	}
	
	public Event updateEvent() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post = selectedPostWrapper.getPost();

			      event.setPost(post);
			
				   
			
			
			
			
			eventDataSource.create(event, session);
			tx.commit();
				MessageService.info("Successfully Saved  Event !");
				this.event=new Event();
			return event;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Event .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateEvent();
		}else{
		log.info("Creating value");
			saveEvent();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createEvent");
		
	}
	public Event saveEvent(Session session){
	
	   this.event= eventDataSource.create(this.event,session);
	   return this.event;
	}
	

}

