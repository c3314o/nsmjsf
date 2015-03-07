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

import com.nsmjsf.web.datasources.AnnouncementDataSource;
import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;
import com.nsmjsf.web.adapters.AnnouncementTypeAdapter;
import com.nsmjsf.web.datasources.AnnouncementTypeDataSource;
import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.wrappers.AnnouncementTypeWrapper;

@ManagedBean
@ViewScoped
public class CreateAnnouncementBean implements Serializable {
	
	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;
	
	private static final Log log = LogFactory
			.getLog(CreateAnnouncementBean.class);

	private Announcement announcement;
	private AnnouncementDataSource announcementDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private AnnouncementTypeDataSource announcementTypeDataSource;
	private List<AnnouncementTypeWrapper> announcementTypeWrapperList;
	private List<AnnouncementType> announcementTypeList;
	private AnnouncementTypeWrapper selectedAnnouncementTypeWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateAnnouncementBean() {

		announcement = new Announcement();
		/* init datasources */
		announcementDataSource = new AnnouncementDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

		announcementTypeDataSource = new AnnouncementTypeDataSource();

		/* init option wrappers */
		announcementTypeList = announcementTypeDataSource.getAll();
		announcementTypeWrapperList = AnnouncementTypeAdapter
				.wrapAll(announcementTypeList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.announcement = announcementDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(announcement.getPost());

			this.selectedAnnouncementTypeWrapper = AnnouncementTypeAdapter
					.wrap(announcement.getAnnouncementType());

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

	public Announcement getAnnouncement() {
		return announcement;
	}

	public void setAnnouncement(Announcement announcement) {
		this.announcement = announcement;
	}

	/* getters for datasources */
	public AnnouncementDataSource getAnnouncementDataSource() {
		return announcementDataSource;
	}

	public void setAnnouncementDataSource(
			AnnouncementDataSource announcementDataSource) {
		this.announcementDataSource = announcementDataSource;
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

	public List<AnnouncementType> getAnnouncementTypeList() {
		return announcementTypeList;
	}

	public void setAnnouncementTypeList(
			List<AnnouncementType> announcementTypeList) {
		this.announcementTypeList = announcementTypeList;
	}

	public AnnouncementTypeDataSource getAnnouncementTypeDataSource() {
		return announcementTypeDataSource;
	}

	public void setAnnouncementTypeDataSource(
			AnnouncementTypeDataSource announcementTypeDataSource) {
		this.announcementTypeDataSource = announcementTypeDataSource;
	}

	public List<AnnouncementTypeWrapper> getAnnouncementTypeWrapperList() {
		return announcementTypeWrapperList;
	}

	public void setAnnouncementTypeWrapperList(
			List<AnnouncementTypeWrapper> announcementTypeWrapperList) {
		this.announcementTypeWrapperList = announcementTypeWrapperList;
	}

	public AnnouncementTypeWrapper getSelectedAnnouncementTypeWrapper() {
		return selectedAnnouncementTypeWrapper;
	}

	public void setSelectedAnnouncementTypeWrapper(
			AnnouncementTypeWrapper selectedAnnouncementTypeWrapper) {
		this.selectedAnnouncementTypeWrapper = selectedAnnouncementTypeWrapper;
	}

	public Announcement saveAnnouncement() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			announcement.setPost(post);

			AnnouncementType announcementType = selectedAnnouncementTypeWrapper
					.getAnnouncementType();

			announcement.setAnnouncementType(announcementType);

			announcementDataSource.create(announcement, session);
			tx.commit();
			MessageService.info("Successfully Saved  Announcement !");
			this.announcement = new Announcement();
			return announcement;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving Announcement .Try Again Later!");
			return null;
		}
	}

	public Announcement updateAnnouncement() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			announcement.setPost(post);

			AnnouncementType announcementType = selectedAnnouncementTypeWrapper
					.getAnnouncementType();

			announcement.setAnnouncementType(announcementType);

			announcementDataSource.create(announcement, session);
			tx.commit();
			MessageService.info("Successfully Saved  Announcement !");
			this.announcement = new Announcement();
			return announcement;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving Announcement .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateAnnouncement();
		} else {
			log.info("Creating value");
			saveAnnouncement();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createAnnouncement");

	}

	public Announcement saveAnnouncement(Session session) {

		this.announcement = announcementDataSource.create(this.announcement,
				session);
		return this.announcement;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
