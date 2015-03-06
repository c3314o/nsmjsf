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
import com.nsmjsf.web.datasources.CommentSocialDataSource;
import com.nsmjsf.web.datamodels.CommentSocial;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CommentAdapter;

import com.nsmjsf.web.datasources.CommentDataSource;

import com.nsmjsf.web.datamodels.Comment;

import com.nsmjsf.web.wrappers.CommentWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateCommentSocialBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateCommentSocialBean.class);

	private CommentSocial commentSocial;
	private CommentSocialDataSource commentSocialDataSource;

	private CommentDataSource commentDataSource;
	private List<CommentWrapper> commentWrapperList;
	private List<Comment> commentList;
	private CommentWrapper selectedCommentWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateCommentSocialBean() {

		commentSocial = new CommentSocial();
		/* init datasources */
		commentSocialDataSource = new CommentSocialDataSource();

		commentDataSource = new CommentDataSource();

		/* init option wrappers */
		commentList = commentDataSource.getAll();
		commentWrapperList = CommentAdapter.wrapAll(commentList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.commentSocial = commentSocialDataSource.get(editId);

			this.selectedCommentWrapper = CommentAdapter.wrap(commentSocial
					.getComment());

			this.selectedUserWrapper = UserAdapter
					.wrap(commentSocial.getUser());

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

	public CommentSocial getCommentSocial() {
		return commentSocial;
	}

	public void setCommentSocial(CommentSocial commentSocial) {
		this.commentSocial = commentSocial;
	}

	/* getters for datasources */
	public CommentSocialDataSource getCommentSocialDataSource() {
		return commentSocialDataSource;
	}

	public void setCommentSocialDataSource(
			CommentSocialDataSource commentSocialDataSource) {
		this.commentSocialDataSource = commentSocialDataSource;
	}

	public List<Comment> getCommentList() {
		return commentList;
	}

	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}

	public CommentDataSource getCommentDataSource() {
		return commentDataSource;
	}

	public void setCommentDataSource(CommentDataSource commentDataSource) {
		this.commentDataSource = commentDataSource;
	}

	public List<CommentWrapper> getCommentWrapperList() {
		return commentWrapperList;
	}

	public void setCommentWrapperList(List<CommentWrapper> commentWrapperList) {
		this.commentWrapperList = commentWrapperList;
	}

	public CommentWrapper getSelectedCommentWrapper() {
		return selectedCommentWrapper;
	}

	public void setSelectedCommentWrapper(CommentWrapper selectedCommentWrapper) {
		this.selectedCommentWrapper = selectedCommentWrapper;
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

	public CommentSocial saveCommentSocial() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Comment comment = selectedCommentWrapper.getComment();

			commentSocial.setComment(comment);

			User user = selectedUserWrapper.getUser();

			commentSocial.setUser(user);

			commentSocialDataSource.create(commentSocial, session);
			tx.commit();
			MessageService.info("Successfully Saved  CommentSocial !");
			this.commentSocial = new CommentSocial();
			return commentSocial;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving CommentSocial .Try Again Later!");
			return null;
		}
	}

	public CommentSocial updateCommentSocial() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Comment comment = selectedCommentWrapper.getComment();

			commentSocial.setComment(comment);

			User user = selectedUserWrapper.getUser();

			commentSocial.setUser(user);

			commentSocialDataSource.create(commentSocial, session);
			tx.commit();
			MessageService.info("Successfully Saved  CommentSocial !");
			this.commentSocial = new CommentSocial();
			return commentSocial;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving CommentSocial .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateCommentSocial();
		} else {
			log.info("Creating value");
			saveCommentSocial();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createCommentSocial");

	}

	public CommentSocial saveCommentSocial(Session session) {

		this.commentSocial = commentSocialDataSource.create(this.commentSocial,
				session);
		return this.commentSocial;
	}

}
