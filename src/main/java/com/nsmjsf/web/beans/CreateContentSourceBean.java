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
import com.nsmjsf.web.datasources.ContentSourceDataSource;
import com.nsmjsf.web.datamodels.ContentSource;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateContentSourceBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateContentSourceBean.class);

	private ContentSource contentSource;
	private ContentSourceDataSource contentSourceDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateContentSourceBean() {

		contentSource = new ContentSource();
		/* init datasources */
		contentSourceDataSource = new ContentSourceDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.contentSource = contentSourceDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter
					.wrap(contentSource.getPost());

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

	public ContentSource getContentSource() {
		return contentSource;
	}

	public void setContentSource(ContentSource contentSource) {
		this.contentSource = contentSource;
	}

	/* getters for datasources */
	public ContentSourceDataSource getContentSourceDataSource() {
		return contentSourceDataSource;
	}

	public void setContentSourceDataSource(
			ContentSourceDataSource contentSourceDataSource) {
		this.contentSourceDataSource = contentSourceDataSource;
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

	public ContentSource saveContentSource() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			contentSource.setPost(post);

			contentSourceDataSource.create(contentSource, session);
			tx.commit();
			MessageService.info("Successfully Saved  ContentSource !");
			this.contentSource = new ContentSource();
			return contentSource;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving ContentSource .Try Again Later!");
			return null;
		}
	}

	public ContentSource updateContentSource() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			contentSource.setPost(post);

			contentSourceDataSource.create(contentSource, session);
			tx.commit();
			MessageService.info("Successfully Saved  ContentSource !");
			this.contentSource = new ContentSource();
			return contentSource;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving ContentSource .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateContentSource();
		} else {
			log.info("Creating value");
			saveContentSource();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createContentSource");

	}

	public ContentSource saveContentSource(Session session) {

		this.contentSource = contentSourceDataSource.create(this.contentSource,
				session);
		return this.contentSource;
	}

}
