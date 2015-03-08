package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;

import org.primefaces.context.RequestContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datasources.PostLikeDataSource;
import com.nsmjsf.web.datamodels.PostLike;
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
public class CreatePostLikeBean implements Serializable {
	
	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;
	
	private static final Log log = LogFactory.getLog(CreatePostLikeBean.class);

	private PostLike postLike;
	private PostLikeDataSource postLikeDataSource;

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

	public CreatePostLikeBean() {

		postLike = new PostLike();
		/* init datasources */
		postLikeDataSource = new PostLikeDataSource();

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
			this.postLike = postLikeDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(postLike.getPost());

			this.selectedUserWrapper = UserAdapter.wrap(postLike.getUser());

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

	public PostLike getPostLike() {
		return postLike;
	}

	public void setPostLike(PostLike postLike) {
		this.postLike = postLike;
	}

	/* getters for datasources */
	public PostLikeDataSource getPostLikeDataSource() {
		return postLikeDataSource;
	}

	public void setPostLikeDataSource(PostLikeDataSource postLikeDataSource) {
		this.postLikeDataSource = postLikeDataSource;
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

	public PostLike savePostLike() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			postLike.setPost(post);

			User user = selectedUserWrapper.getUser();

			postLike.setUser(user);

			postLikeDataSource.create(postLike, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostLike !");
			this.postLike = new PostLike();
			return postLike;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving PostLike .Try Again Later!");
			return null;
		}
	}

	public PostLike updatePostLike() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			postLike.setPost(post);

			User user = selectedUserWrapper.getUser();

			postLike.setUser(user);

			postLikeDataSource.create(postLike, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostLike !");
			this.postLike = new PostLike();
			return postLike;

		} catch (Exception ex) {
			MessageService.error("Failed Saving PostLike .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updatePostLike();
		} else {
			log.info("Creating value");
			savePostLike();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createPostLike");

	}

	public PostLike savePostLike(Session session) {

		this.postLike = postLikeDataSource.create(this.postLike, session);
		return this.postLike;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
