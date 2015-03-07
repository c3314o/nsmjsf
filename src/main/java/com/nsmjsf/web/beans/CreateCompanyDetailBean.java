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

import com.nsmjsf.web.datasources.CompanyDetailDataSource;
import com.nsmjsf.web.datamodels.CompanyDetail;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateCompanyDetailBean implements Serializable {
	
	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;
	
	private static final Log log = LogFactory
			.getLog(CreateCompanyDetailBean.class);

	private CompanyDetail companyDetail;
	private CompanyDetailDataSource companyDetailDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateCompanyDetailBean() {

		companyDetail = new CompanyDetail();
		/* init datasources */
		companyDetailDataSource = new CompanyDetailDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.companyDetail = companyDetailDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter
					.wrap(companyDetail.getPost());

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

	public CompanyDetail getCompanyDetail() {
		return companyDetail;
	}

	public void setCompanyDetail(CompanyDetail companyDetail) {
		this.companyDetail = companyDetail;
	}

	/* getters for datasources */
	public CompanyDetailDataSource getCompanyDetailDataSource() {
		return companyDetailDataSource;
	}

	public void setCompanyDetailDataSource(
			CompanyDetailDataSource companyDetailDataSource) {
		this.companyDetailDataSource = companyDetailDataSource;
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

	public CompanyDetail saveCompanyDetail() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			companyDetail.setPost(post);

			companyDetailDataSource.create(companyDetail, session);
			tx.commit();
			MessageService.info("Successfully Saved  CompanyDetail !");
			this.companyDetail = new CompanyDetail();
			return companyDetail;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving CompanyDetail .Try Again Later!");
			return null;
		}
	}

	public CompanyDetail updateCompanyDetail() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			companyDetail.setPost(post);

			companyDetailDataSource.create(companyDetail, session);
			tx.commit();
			MessageService.info("Successfully Saved  CompanyDetail !");
			this.companyDetail = new CompanyDetail();
			return companyDetail;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving CompanyDetail .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateCompanyDetail();
		} else {
			log.info("Creating value");
			saveCompanyDetail();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createCompanyDetail");

	}

	public CompanyDetail saveCompanyDetail(Session session) {

		this.companyDetail = companyDetailDataSource.create(this.companyDetail,
				session);
		return this.companyDetail;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
