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
import com.nsmjsf.web.datasources.NewsDataSource;
import com.nsmjsf.web.datamodels.News;
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
public class CreateNewsBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateNewsBean.class);

	private News news;
	private NewsDataSource newsDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateNewsBean() {

		news = new News();
		/* init datasources */
		newsDataSource = new NewsDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.news = newsDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(news.getPost());

			this.selectedUserWrapper = UserAdapter.wrap(news.getUser());

		}
	}

	private void extractParams() {
		int editId = ParameterManager.getInt("editId");
		if (editId != 0) {
			this.editId = editId;
			this.editMode = true;
			System.out.println("EditId" + editId);
		}
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	/* getters for datasources */
	public NewsDataSource getNewsDataSource() {
		return newsDataSource;
	}

	public void setNewsDataSource(NewsDataSource newsDataSource) {
		this.newsDataSource = newsDataSource;
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

	public void setPostDataSource(PostDataSource postDataSource) {
		this.postDataSource = postDataSource;
	}

	public List<PostWrapper> getPostWrapperList() {
		return postWrapperList;
	}

	public void setPostWrapperList(List<PostWrapper> postWrapperList) {
		this.postWrapperList = postWrapperList;
	}

	public PostWrapper getSelectedPostWrapper() {
		return selectedPostWrapper;
	}

	public void setSelectedPostWrapper(PostWrapper selectedPostWrapper) {
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

	public void setUserDataSource(UserDataSource userDataSource) {
		this.userDataSource = userDataSource;
	}

	public List<UserWrapper> getUserWrapperList() {
		return userWrapperList;
	}

	public void setUserWrapperList(List<UserWrapper> userWrapperList) {
		this.userWrapperList = userWrapperList;
	}

	public UserWrapper getSelectedUserWrapper() {
		return selectedUserWrapper;
	}

	public void setSelectedUserWrapper(UserWrapper selectedUserWrapper) {
		this.selectedUserWrapper = selectedUserWrapper;
	}

	public News saveNews() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			news.setPost(post);

			User user = selectedUserWrapper.getUser();

			news.setUser(user);

			newsDataSource.create(news, session);
			tx.commit();
			MessageService.info("Successfully Saved  News !");
			this.news = new News();
			return news;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving News .Try Again Later!");
			return null;
		}
	}

	public News updateNews() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			news.setPost(post);

			User user = selectedUserWrapper.getUser();

			news.setUser(user);

			newsDataSource.create(news, session);
			tx.commit();
			MessageService.info("Successfully Saved  News !");
			this.news = new News();
			return news;

		} catch (Exception ex) {
			MessageService.error("Failed Saving News .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateNews();
		} else {
			log.info("Creating value");
			saveNews();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createNews");

	}

	public News saveNews(Session session) {

		this.news = newsDataSource.create(this.news, session);
		return this.news;
	}

}
