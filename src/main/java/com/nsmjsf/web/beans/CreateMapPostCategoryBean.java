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
import com.nsmjsf.web.datasources.MapPostCategoryDataSource;
import com.nsmjsf.web.datamodels.MapPostCategory;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostCategoryAdapter;

import com.nsmjsf.web.datasources.PostCategoryDataSource;

import com.nsmjsf.web.datamodels.PostCategory;

import com.nsmjsf.web.wrappers.PostCategoryWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateMapPostCategoryBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateMapPostCategoryBean.class);

	private MapPostCategory mapPostCategory;
	private MapPostCategoryDataSource mapPostCategoryDataSource;

	private PostCategoryDataSource postCategoryDataSource;
	private List<PostCategoryWrapper> postCategoryWrapperList;
	private List<PostCategory> postCategoryList;
	private PostCategoryWrapper selectedPostCategoryWrapper;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMapPostCategoryBean() {

		mapPostCategory = new MapPostCategory();
		/* init datasources */
		mapPostCategoryDataSource = new MapPostCategoryDataSource();

		postCategoryDataSource = new PostCategoryDataSource();

		/* init option wrappers */
		postCategoryList = postCategoryDataSource.getAll();
		postCategoryWrapperList = PostCategoryAdapter.wrapAll(postCategoryList);

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.mapPostCategory = mapPostCategoryDataSource.get(editId);

			this.selectedPostCategoryWrapper = PostCategoryAdapter
					.wrap(mapPostCategory.getPostCategory());

			this.selectedPostWrapper = PostAdapter.wrap(mapPostCategory
					.getPost());

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

	public MapPostCategory getMapPostCategory() {
		return mapPostCategory;
	}

	public void setMapPostCategory(MapPostCategory mapPostCategory) {
		this.mapPostCategory = mapPostCategory;
	}

	/* getters for datasources */
	public MapPostCategoryDataSource getMapPostCategoryDataSource() {
		return mapPostCategoryDataSource;
	}

	public void setMapPostCategoryDataSource(
			MapPostCategoryDataSource mapPostCategoryDataSource) {
		this.mapPostCategoryDataSource = mapPostCategoryDataSource;
	}

	public List<PostCategory> getPostCategoryList() {
		return postCategoryList;
	}

	public void setPostCategoryList(List<PostCategory> postCategoryList) {
		this.postCategoryList = postCategoryList;
	}

	public PostCategoryDataSource getPostCategoryDataSource() {
		return postCategoryDataSource;
	}

	public void setPostCategoryDataSource(
			PostCategoryDataSource postCategoryDataSource) {
		this.postCategoryDataSource = postCategoryDataSource;
	}

	public List<PostCategoryWrapper> getPostCategoryWrapperList() {
		return postCategoryWrapperList;
	}

	public void setPostCategoryWrapperList(
			List<PostCategoryWrapper> postCategoryWrapperList) {
		this.postCategoryWrapperList = postCategoryWrapperList;
	}

	public PostCategoryWrapper getSelectedPostCategoryWrapper() {
		return selectedPostCategoryWrapper;
	}

	public void setSelectedPostCategoryWrapper(
			PostCategoryWrapper selectedPostCategoryWrapper) {
		this.selectedPostCategoryWrapper = selectedPostCategoryWrapper;
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

	public MapPostCategory saveMapPostCategory() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			PostCategory postCategory = selectedPostCategoryWrapper
					.getPostCategory();

			mapPostCategory.setPostCategory(postCategory);

			Post post = selectedPostWrapper.getPost();

			mapPostCategory.setPost(post);

			mapPostCategoryDataSource.create(mapPostCategory, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostCategory !");
			this.mapPostCategory = new MapPostCategory();
			return mapPostCategory;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving MapPostCategory .Try Again Later!");
			return null;
		}
	}

	public MapPostCategory updateMapPostCategory() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			PostCategory postCategory = selectedPostCategoryWrapper
					.getPostCategory();

			mapPostCategory.setPostCategory(postCategory);

			Post post = selectedPostWrapper.getPost();

			mapPostCategory.setPost(post);

			mapPostCategoryDataSource.create(mapPostCategory, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostCategory !");
			this.mapPostCategory = new MapPostCategory();
			return mapPostCategory;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving MapPostCategory .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMapPostCategory();
		} else {
			log.info("Creating value");
			saveMapPostCategory();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createMapPostCategory");

	}

	public MapPostCategory saveMapPostCategory(Session session) {

		this.mapPostCategory = mapPostCategoryDataSource.create(
				this.mapPostCategory, session);
		return this.mapPostCategory;
	}

}
