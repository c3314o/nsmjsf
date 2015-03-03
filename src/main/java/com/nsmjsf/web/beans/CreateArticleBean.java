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
import com.nsmjsf.web.datasources.ArticleDataSource;
import com.nsmjsf.web.datamodels.Article;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   

@ManagedBean
@ViewScoped

public class CreateArticleBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateArticleBean.class);


	private Article article;
	private ArticleDataSource articleDataSource;
	
	
	
			
    private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
    private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;
	
	
			
			
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateArticleBean() {

		article = new Article();
		/* init datasources */
		articleDataSource = new ArticleDataSource();
		
		
			
postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter
				.wrapAll(postList);
	
			
			
			
userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter
				.wrapAll(userList);
	
			
				
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.article=articleDataSource.get(editId);
			
			

			  
			  this.selectedPostWrapper=PostAdapter.wrap(article.getPost());
	
			
			
			  
			  this.selectedUserWrapper=UserAdapter.wrap(article.getUser());
	
			
				   
			
			
			
			
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

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	/* getters for datasources */
	public ArticleDataSource getArticleDataSource() {
		return articleDataSource;
	}

	public void setArticleDataSource(ArticleDataSource articleDataSource) {
		this.articleDataSource = articleDataSource;
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








			
			
			


public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
  
  
  
	public UserDataSource getUserDataSource() {
		return userDataSource;
	}

	public void setUserDataSource(
			UserDataSource userDataSource) {
		this.userDataSource = userDataSource;
	}

	public List<UserWrapper> getUserWrapperList() {
		return userWrapperList;
	}

	public void setUserWrapperList(
			List<UserWrapper> userWrapperList) {
		this.userWrapperList = userWrapperList;
	}

	

	public UserWrapper getSelectedUserWrapper() {
		return selectedUserWrapper;
	}

	public void setSelectedUserWrapper(
			UserWrapper selectedUserWrapper) {
		this.selectedUserWrapper = selectedUserWrapper;
	}








			
				




	
  
  
  
	public Article saveArticle() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post =selectedPostWrapper.getPost();

			article.setPost(post);
			
			
			
                  User user =selectedUserWrapper.getUser();

			article.setUser(user);
			
				   
			
			
			
			
			articleDataSource.create(article, session);
			tx.commit();
					MessageService.info("Successfully Saved  Article !");
				this.article=new Article();
			return article;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Article .Try Again Later!");
			return null;
		}
	}
	
	public Article updateArticle() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
                  Post post = selectedPostWrapper.getPost();

			      article.setPost(post);
			
			
			
                  User user = selectedUserWrapper.getUser();

			      article.setUser(user);
			
				   
			
			
			
			
			articleDataSource.create(article, session);
			tx.commit();
				MessageService.info("Successfully Saved  Article !");
				this.article=new Article();
			return article;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Article .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateArticle();
		}else{
		log.info("Creating value");
			saveArticle();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createArticle");
		
	}
	public Article saveArticle(Session session){
	
	   this.article= articleDataSource.create(this.article,session);
	   return this.article;
	}
	

}

