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
import com.nsmjsf.web.datasources.CommentDataSource;
import com.nsmjsf.web.datamodels.Comment;
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
public class CreateCommentBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateCommentBean.class);

	private Comment comment;
	private CommentDataSource commentDataSource;

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

	public CreateCommentBean() {

		comment = new Comment();
		/* init datasources */
		commentDataSource = new CommentDataSource();

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
			this.comment = commentDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(comment.getPost());

			this.selectedUserWrapper = UserAdapter.wrap(comment.getUser());

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

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	/* getters for datasources */
	public CommentDataSource getCommentDataSource() {
		return commentDataSource;
	}

	public void setCommentDataSource(CommentDataSource commentDataSource) {
		this.commentDataSource = commentDataSource;
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

	public Comment saveComment() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			comment.setPost(post);

			User user = selectedUserWrapper.getUser();

			comment.setUser(user);

			commentDataSource.create(comment, session);
			tx.commit();
			MessageService.info("Successfully Saved  Comment !");
			this.comment = new Comment();
			return comment;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Comment .Try Again Later!");
			return null;
		}
	}

	public Comment updateComment() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			comment.setPost(post);

			User user = selectedUserWrapper.getUser();

			comment.setUser(user);

			commentDataSource.create(comment, session);
			tx.commit();
			MessageService.info("Successfully Saved  Comment !");
			this.comment = new Comment();
			return comment;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Comment .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateComment();
		} else {
			log.info("Creating value");
			saveComment();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createComment");

	}

	public Comment saveComment(Session session) {

		this.comment = commentDataSource.create(this.comment, session);
		return this.comment;
	}

}
