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
import com.nsmjsf.web.datasources.MapPostCompanyDataSource;
import com.nsmjsf.web.datamodels.MapPostCompany;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateMapPostCompanyBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateMapPostCompanyBean.class);

	private MapPostCompany mapPostCompany;
	private MapPostCompanyDataSource mapPostCompanyDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMapPostCompanyBean() {

		mapPostCompany = new MapPostCompany();
		/* init datasources */
		mapPostCompanyDataSource = new MapPostCompanyDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.mapPostCompany = mapPostCompanyDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(mapPostCompany
					.getPost());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(mapPostCompany
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

	public MapPostCompany getMapPostCompany() {
		return mapPostCompany;
	}

	public void setMapPostCompany(MapPostCompany mapPostCompany) {
		this.mapPostCompany = mapPostCompany;
	}

	/* getters for datasources */
	public MapPostCompanyDataSource getMapPostCompanyDataSource() {
		return mapPostCompanyDataSource;
	}

	public void setMapPostCompanyDataSource(
			MapPostCompanyDataSource mapPostCompanyDataSource) {
		this.mapPostCompanyDataSource = mapPostCompanyDataSource;
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

	public MapPostCompany saveMapPostCompany() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			mapPostCompany.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			mapPostCompany.setCompany(company);

			mapPostCompanyDataSource.create(mapPostCompany, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostCompany !");
			this.mapPostCompany = new MapPostCompany();
			return mapPostCompany;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving MapPostCompany .Try Again Later!");
			return null;
		}
	}

	public MapPostCompany updateMapPostCompany() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = selectedPostWrapper.getPost();

			mapPostCompany.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			mapPostCompany.setCompany(company);

			mapPostCompanyDataSource.create(mapPostCompany, session);
			tx.commit();
			MessageService.info("Successfully Saved  MapPostCompany !");
			this.mapPostCompany = new MapPostCompany();
			return mapPostCompany;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving MapPostCompany .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMapPostCompany();
		} else {
			log.info("Creating value");
			saveMapPostCompany();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createMapPostCompany");

	}

	public MapPostCompany saveMapPostCompany(Session session) {

		this.mapPostCompany = mapPostCompanyDataSource.create(
				this.mapPostCompany, session);
		return this.mapPostCompany;
	}

}
