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
import com.nsmjsf.web.datasources.PostCategoryDataSource;
import com.nsmjsf.web.datamodels.PostCategory;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreatePostCategoryBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreatePostCategoryBean.class);

	private PostCategory postCategory;
	private PostCategoryDataSource postCategoryDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreatePostCategoryBean() {

		postCategory = new PostCategory();
		/* init datasources */
		postCategoryDataSource = new PostCategoryDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.postCategory = postCategoryDataSource.get(editId);

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

	public PostCategory getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	/* getters for datasources */
	public PostCategoryDataSource getPostCategoryDataSource() {
		return postCategoryDataSource;
	}

	public void setPostCategoryDataSource(
			PostCategoryDataSource postCategoryDataSource) {
		this.postCategoryDataSource = postCategoryDataSource;
	}

	public PostCategory savePostCategory() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			postCategoryDataSource.create(postCategory, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostCategory !");
			this.postCategory = new PostCategory();
			return postCategory;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving PostCategory .Try Again Later!");
			return null;
		}
	}

	public PostCategory updatePostCategory() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			postCategoryDataSource.create(postCategory, session);
			tx.commit();
			MessageService.info("Successfully Saved  PostCategory !");
			this.postCategory = new PostCategory();
			return postCategory;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving PostCategory .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updatePostCategory();
		} else {
			log.info("Creating value");
			savePostCategory();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createPostCategory");

	}

	public PostCategory savePostCategory(Session session) {

		this.postCategory = postCategoryDataSource.create(this.postCategory,
				session);
		return this.postCategory;
	}

}
