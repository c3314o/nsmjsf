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

import com.nsmjsf.web.datasources.LatestPriceDataSource;
import com.nsmjsf.web.datamodels.LatestPrice;
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
public class CreateLatestPriceBean implements Serializable {

	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;

	private static final Log log = LogFactory
			.getLog(CreateLatestPriceBean.class);

	private LatestPrice latestPrice;
	private LatestPriceDataSource latestPriceDataSource;

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

	public CreateLatestPriceBean() {

		latestPrice = new LatestPrice();
		/* init datasources */
		latestPriceDataSource = new LatestPriceDataSource();

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
			this.latestPrice = latestPriceDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(latestPrice.getPost());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(latestPrice
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

	public LatestPrice getLatestPrice() {
		return latestPrice;
	}

	public void setLatestPrice(LatestPrice latestPrice) {
		this.latestPrice = latestPrice;
	}

	/* getters for datasources */
	public LatestPriceDataSource getLatestPriceDataSource() {
		return latestPriceDataSource;
	}

	public void setLatestPriceDataSource(
			LatestPriceDataSource latestPriceDataSource) {
		this.latestPriceDataSource = latestPriceDataSource;
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

	public LatestPrice saveLatestPrice() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			latestPrice.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			latestPrice.setCompany(company);

			latestPriceDataSource.create(latestPrice, session);
			tx.commit();
			MessageService.info("Successfully Saved  LatestPrice !");
			this.latestPrice = new LatestPrice();
			return latestPrice;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving LatestPrice .Try Again Later!");
			return null;
		}
	}

	public LatestPrice updateLatestPrice() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			latestPrice.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			latestPrice.setCompany(company);

			latestPriceDataSource.create(latestPrice, session);
			tx.commit();
			MessageService.info("Successfully Saved  LatestPrice !");
			this.latestPrice = new LatestPrice();
			return latestPrice;

		} catch (Exception ex) {
			MessageService.error("Failed Saving LatestPrice .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateLatestPrice();
		} else {
			log.info("Creating value");
			saveLatestPrice();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createLatestPrice");

	}

	public LatestPrice saveLatestPrice(Session session) {

		this.latestPrice = latestPriceDataSource.create(this.latestPrice,
				session);
		return this.latestPrice;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
