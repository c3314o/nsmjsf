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

import com.nsmjsf.web.datasources.PostImageDataSource;
import com.nsmjsf.web.datamodels.PostImage;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreatePostImageBean implements Serializable {

	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;

	private static final Log log = LogFactory.getLog(CreatePostImageBean.class);

	private PostImage postImage;
	private PostImageDataSource postImageDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreatePostImageBean() {

		postImage = new PostImage();
		/* init datasources */
		postImageDataSource = new PostImageDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.postImage = postImageDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(postImage.getPost());

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

	public PostImage getPostImage() {
		return postImage;
	}

	public void setPostImage(PostImage postImage) {
		this.postImage = postImage;
	}

	/* getters for datasources */
	public PostImageDataSource getPostImageDataSource() {
		return postImageDataSource;
	}

	public void setPostImageDataSource(PostImageDataSource postImageDataSource) {
		this.postImageDataSource = postImageDataSource;
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

	public PostImage savePostImage() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			postImage.setPost(post);

			postImageDataSource.create(postImage, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostImage !");
			this.postImage = new PostImage();
			return postImage;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving PostImage .Try Again Later!");
			return null;
		}
	}

	public PostImage updatePostImage() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			postImage.setPost(post);

			postImageDataSource.create(postImage, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostImage !");
			this.postImage = new PostImage();
			return postImage;

		} catch (Exception ex) {
			MessageService.error("Failed Saving PostImage .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updatePostImage();
		} else {
			log.info("Creating value");
			savePostImage();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createPostImage");

	}

	public PostImage savePostImage(Session session) {

		this.postImage = postImageDataSource.create(this.postImage, session);
		return this.postImage;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
