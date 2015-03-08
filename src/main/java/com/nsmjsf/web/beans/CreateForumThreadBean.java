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

import com.nsmjsf.web.datasources.ForumThreadDataSource;
import com.nsmjsf.web.datamodels.ForumThread;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateForumThreadBean implements Serializable {

	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;

	private static final Log log = LogFactory
			.getLog(CreateForumThreadBean.class);

	private ForumThread forumThread;
	private ForumThreadDataSource forumThreadDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateForumThreadBean() {

		forumThread = new ForumThread();
		/* init datasources */
		forumThreadDataSource = new ForumThreadDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.forumThread = forumThreadDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(forumThread.getPost());

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

	public ForumThread getForumThread() {
		return forumThread;
	}

	public void setForumThread(ForumThread forumThread) {
		this.forumThread = forumThread;
	}

	/* getters for datasources */
	public ForumThreadDataSource getForumThreadDataSource() {
		return forumThreadDataSource;
	}

	public void setForumThreadDataSource(
			ForumThreadDataSource forumThreadDataSource) {
		this.forumThreadDataSource = forumThreadDataSource;
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

	public ForumThread saveForumThread() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			forumThread.setPost(post);

			forumThreadDataSource.create(forumThread, session);
			tx.commit();
			MessageService.info("Successfully Saved  ForumThread !");
			this.forumThread = new ForumThread();
			return forumThread;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving ForumThread .Try Again Later!");
			return null;
		}
	}

	public ForumThread updateForumThread() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			forumThread.setPost(post);

			forumThreadDataSource.create(forumThread, session);
			tx.commit();
			MessageService.info("Successfully Saved  ForumThread !");
			this.forumThread = new ForumThread();
			return forumThread;

		} catch (Exception ex) {
			MessageService.error("Failed Saving ForumThread .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateForumThread();
		} else {
			log.info("Creating value");
			saveForumThread();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createForumThread");

	}

	public ForumThread saveForumThread(Session session) {

		this.forumThread = forumThreadDataSource.create(this.forumThread,
				session);
		return this.forumThread;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
