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

import com.nsmjsf.web.datasources.AllotmentResultDataSource;
import com.nsmjsf.web.datamodels.AllotmentResult;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;
import com.nsmjsf.web.adapters.AnnouncementAdapter;
import com.nsmjsf.web.datasources.AnnouncementDataSource;
import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.wrappers.AnnouncementWrapper;
import com.nsmjsf.web.adapters.CompanyAdapter;
import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateAllotmentResultBean implements Serializable {
	
	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;
	
	private static final Log log = LogFactory
			.getLog(CreateAllotmentResultBean.class);

	private AllotmentResult allotmentResult;
	private AllotmentResultDataSource allotmentResultDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateAllotmentResultBean() {

		allotmentResult = new AllotmentResult();
		/* init datasources */
		allotmentResultDataSource = new AllotmentResultDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.allotmentResult = allotmentResultDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(allotmentResult
					.getPost());

			this.selectedAnnouncementWrapper = AnnouncementAdapter
					.wrap(allotmentResult.getAnnouncement());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(allotmentResult
					.getCompany());

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

	public AllotmentResult getAllotmentResult() {
		return allotmentResult;
	}

	public void setAllotmentResult(AllotmentResult allotmentResult) {
		this.allotmentResult = allotmentResult;
	}

	/* getters for datasources */
	public AllotmentResultDataSource getAllotmentResultDataSource() {
		return allotmentResultDataSource;
	}

	public void setAllotmentResultDataSource(
			AllotmentResultDataSource allotmentResultDataSource) {
		this.allotmentResultDataSource = allotmentResultDataSource;
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

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	public AnnouncementDataSource getAnnouncementDataSource() {
		return announcementDataSource;
	}

	public void setAnnouncementDataSource(
			AnnouncementDataSource announcementDataSource) {
		this.announcementDataSource = announcementDataSource;
	}

	public List<AnnouncementWrapper> getAnnouncementWrapperList() {
		return announcementWrapperList;
	}

	public void setAnnouncementWrapperList(
			List<AnnouncementWrapper> announcementWrapperList) {
		this.announcementWrapperList = announcementWrapperList;
	}

	public AnnouncementWrapper getSelectedAnnouncementWrapper() {
		return selectedAnnouncementWrapper;
	}

	public void setSelectedAnnouncementWrapper(
			AnnouncementWrapper selectedAnnouncementWrapper) {
		this.selectedAnnouncementWrapper = selectedAnnouncementWrapper;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public CompanyDataSource getCompanyDataSource() {
		return companyDataSource;
	}

	public void setCompanyDataSource(CompanyDataSource companyDataSource) {
		this.companyDataSource = companyDataSource;
	}

	public List<CompanyWrapper> getCompanyWrapperList() {
		return companyWrapperList;
	}

	public void setCompanyWrapperList(List<CompanyWrapper> companyWrapperList) {
		this.companyWrapperList = companyWrapperList;
	}

	public CompanyWrapper getSelectedCompanyWrapper() {
		return selectedCompanyWrapper;
	}

	public void setSelectedCompanyWrapper(CompanyWrapper selectedCompanyWrapper) {
		this.selectedCompanyWrapper = selectedCompanyWrapper;
	}

	public AllotmentResult saveAllotmentResult() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			Post post = createPostBean.savePost(session);

			allotmentResult.setPost(post);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			allotmentResult.setAnnouncement(announcement);

			Company company = selectedCompanyWrapper.getCompany();

			allotmentResult.setCompany(company);

			allotmentResultDataSource.create(allotmentResult, session);
			tx.commit();
			MessageService.info("Successfully Saved  AllotmentResult !");
			this.allotmentResult = new AllotmentResult();
			return allotmentResult;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving AllotmentResult .Try Again Later!");
			return null;
		}
	}

	public AllotmentResult updateAllotmentResult() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			allotmentResult.setPost(post);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			allotmentResult.setAnnouncement(announcement);

			Company company = selectedCompanyWrapper.getCompany();

			allotmentResult.setCompany(company);

			allotmentResultDataSource.create(allotmentResult, session);
			tx.commit();
			MessageService.info("Successfully Saved  AllotmentResult !");
			this.allotmentResult = new AllotmentResult();
			return allotmentResult;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving AllotmentResult .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateAllotmentResult();
		} else {
			log.info("Creating value");
			saveAllotmentResult();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createAllotmentResult");

	}

	public AllotmentResult saveAllotmentResult(Session session) {

		this.allotmentResult = allotmentResultDataSource.create(
				this.allotmentResult, session);
		return this.allotmentResult;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
