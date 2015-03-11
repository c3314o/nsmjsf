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
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreatePostBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreatePostBean.class);

	private Post post;
	private PostDataSource postDataSource;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreatePostBean() {

		post = new Post();
		/* init datasources */
		postDataSource = new PostDataSource();

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.post = postDataSource.get(editId);

			this.selectedUserWrapper = UserAdapter.wrap(post.getUser());

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

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	/* getters for datasources */
	public PostDataSource getPostDataSource() {
		return postDataSource;
	}

	public void setPostDataSource(PostDataSource postDataSource) {
		this.postDataSource = postDataSource;
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

	public Post savePost() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			post.setUser(user);

			postDataSource.create(post, session);
			tx.commit();
			MessageService.info("Successfully Saved  Post !");
			this.post = new Post();
			return post;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Post .Try Again Later!");
			return null;
		}
	}

	public Post updatePost() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = new User();

			post.setUser(user);

			postDataSource.create(post, session);
			tx.commit();
			MessageService.info("Successfully Saved  Post !");
			this.post = new Post();
			return post;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Post .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updatePost();
		} else {
			log.info("Creating value");
			savePost();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createPost");

	}

	public Post savePost(Session session) {

		this.post = postDataSource.create(this.post, session);
		return this.post;
	}

}
