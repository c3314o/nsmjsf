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
import com.nsmjsf.web.datasources.MapPostPostDataSource;
import com.nsmjsf.web.datamodels.MapPostPost;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateMapPostPostBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateMapPostPostBean.class);

	private MapPostPost mapPostPost;
	private MapPostPostDataSource mapPostPostDataSource;

	private PostDataSource postByToPostIdDataSource;
	private List<PostWrapper> postByToPostIdWrapperList;
	private List<Post> postByToPostIdList;
	private PostWrapper selectedpostByToPostIdWrapper;

	private PostDataSource postByFromPostIdDataSource;
	private List<PostWrapper> postByFromPostIdWrapperList;
	private List<Post> postByFromPostIdList;
	private PostWrapper selectedByFromPostIdWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMapPostPostBean() {

		mapPostPost = new MapPostPost();
		/* init datasources */
		mapPostPostDataSource = new MapPostPostDataSource();

		postByToPostIdDataSource = new PostDataSource();

		/* init option wrappers */
		postByToPostIdList = postByToPostIdDataSource.getAll();
		postByToPostIdWrapperList = PostAdapter.wrapAll(postByToPostIdList);

		postByFromPostIdDataSource = new PostDataSource();

		/* init option wrappers */
		postByFromPostIdList = postByFromPostIdDataSource.getAll();
		postByFromPostIdWrapperList = PostAdapter.wrapAll(postByFromPostIdList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.mapPostPost = mapPostPostDataSource.get(editId);

			this.selectedByFromPostIdWrapper = PostAdapter.wrap(mapPostPost
					.getPostByFromPostId());

			this.selectedpostByToPostIdWrapper = PostAdapter.wrap(mapPostPost
					.getPostByToPostId());

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

	public MapPostPost getMapPostPost() {
		return mapPostPost;
	}

	public void setMapPostPost(MapPostPost mapPostPost) {
		this.mapPostPost = mapPostPost;
	}

	/* getters for datasources */
	public MapPostPostDataSource getMapPostPostDataSource() {
		return mapPostPostDataSource;
	}

	public void setMapPostPostDataSource(
			MapPostPostDataSource mapPostPostDataSource) {
		this.mapPostPostDataSource = mapPostPostDataSource;
	}

	public MapPostPost saveMapPostPost() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post postByToPostId = selectedpostByToPostIdWrapper.getPost();

			Post postByFromPostId = selectedByFromPostIdWrapper.getPost();

			mapPostPost.setPostByToPostId(postByToPostId);
			mapPostPost.setPostByFromPostId(postByFromPostId);

			mapPostPostDataSource.create(mapPostPost, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostPost !");
			this.mapPostPost = new MapPostPost();
			return mapPostPost;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving MapPostPost .Try Again Later!");
			return null;
		}
	}

	public MapPostPost updateMapPostPost() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			Post postByToPostId = selectedpostByToPostIdWrapper.getPost();

			Post postByFromPostId = selectedByFromPostIdWrapper.getPost();

			mapPostPost.setPostByToPostId(postByToPostId);
			mapPostPost.setPostByFromPostId(postByFromPostId);

			mapPostPostDataSource.create(mapPostPost, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostPost !");
			this.mapPostPost = new MapPostPost();
			return mapPostPost;

		} catch (Exception ex) {
			MessageService.error("Failed Saving MapPostPost .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMapPostPost();
		} else {
			log.info("Creating value");
			saveMapPostPost();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createMapPostPost");

	}

	public MapPostPost saveMapPostPost(Session session) {

		this.mapPostPost = mapPostPostDataSource.create(this.mapPostPost,
				session);
		return this.mapPostPost;
	}

	public PostDataSource getPostByToPostIdDataSource() {
		return postByToPostIdDataSource;
	}

	public void setPostByToPostIdDataSource(PostDataSource postByToPostIdDataSource) {
		this.postByToPostIdDataSource = postByToPostIdDataSource;
	}

	public List<PostWrapper> getPostByToPostIdWrapperList() {
		return postByToPostIdWrapperList;
	}

	public void setPostByToPostIdWrapperList(
			List<PostWrapper> postByToPostIdWrapperList) {
		this.postByToPostIdWrapperList = postByToPostIdWrapperList;
	}

	public List<Post> getPostByToPostIdList() {
		return postByToPostIdList;
	}

	public void setPostByToPostIdList(List<Post> postByToPostIdList) {
		this.postByToPostIdList = postByToPostIdList;
	}

	public PostWrapper getSelectedpostByToPostIdWrapper() {
		return selectedpostByToPostIdWrapper;
	}

	public void setSelectedpostByToPostIdWrapper(
			PostWrapper selectedpostByToPostIdWrapper) {
		this.selectedpostByToPostIdWrapper = selectedpostByToPostIdWrapper;
	}

	public PostDataSource getPostByFromPostIdDataSource() {
		return postByFromPostIdDataSource;
	}

	public void setPostByFromPostIdDataSource(
			PostDataSource postByFromPostIdDataSource) {
		this.postByFromPostIdDataSource = postByFromPostIdDataSource;
	}

	public List<PostWrapper> getPostByFromPostIdWrapperList() {
		return postByFromPostIdWrapperList;
	}

	public void setPostByFromPostIdWrapperList(
			List<PostWrapper> postByFromPostIdWrapperList) {
		this.postByFromPostIdWrapperList = postByFromPostIdWrapperList;
	}

	public List<Post> getPostByFromPostIdList() {
		return postByFromPostIdList;
	}

	public void setPostByFromPostIdList(List<Post> postByFromPostIdList) {
		this.postByFromPostIdList = postByFromPostIdList;
	}

	public PostWrapper getSelectedByFromPostIdWrapper() {
		return selectedByFromPostIdWrapper;
	}

	public void setSelectedByFromPostIdWrapper(
			PostWrapper selectedByFromPostIdWrapper) {
		this.selectedByFromPostIdWrapper = selectedByFromPostIdWrapper;
	}

	public boolean isEditMode() {
		return editMode;
	}

	public void setEditMode(boolean editMode) {
		this.editMode = editMode;
	}
	
	

}
