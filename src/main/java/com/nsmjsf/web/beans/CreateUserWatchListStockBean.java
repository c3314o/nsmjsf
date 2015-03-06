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
import com.nsmjsf.web.datasources.UserWatchListStockDataSource;
import com.nsmjsf.web.datamodels.UserWatchListStock;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.UserWatchListAdapter;

import com.nsmjsf.web.datasources.UserWatchListDataSource;

import com.nsmjsf.web.datamodels.UserWatchList;

import com.nsmjsf.web.wrappers.UserWatchListWrapper;

@ManagedBean
@ViewScoped
public class CreateUserWatchListStockBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserWatchListStockBean.class);

	private UserWatchListStock userWatchListStock;
	private UserWatchListStockDataSource userWatchListStockDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private UserWatchListDataSource userWatchListDataSource;
	private List<UserWatchListWrapper> userWatchListWrapperList;
	private List<UserWatchList> userWatchListList;
	private UserWatchListWrapper selectedUserWatchListWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserWatchListStockBean() {

		userWatchListStock = new UserWatchListStock();
		/* init datasources */
		userWatchListStockDataSource = new UserWatchListStockDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

		userWatchListDataSource = new UserWatchListDataSource();

		/* init option wrappers */
		userWatchListList = userWatchListDataSource.getAll();
		userWatchListWrapperList = UserWatchListAdapter
				.wrapAll(userWatchListList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userWatchListStock = userWatchListStockDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter
					.wrap(userWatchListStock.getCompany());

			this.selectedUserWatchListWrapper = UserWatchListAdapter
					.wrap(userWatchListStock.getUserWatchList());

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

	public UserWatchListStock getUserWatchListStock() {
		return userWatchListStock;
	}

	public void setUserWatchListStock(UserWatchListStock userWatchListStock) {
		this.userWatchListStock = userWatchListStock;
	}

	/* getters for datasources */
	public UserWatchListStockDataSource getUserWatchListStockDataSource() {
		return userWatchListStockDataSource;
	}

	public void setUserWatchListStockDataSource(
			UserWatchListStockDataSource userWatchListStockDataSource) {
		this.userWatchListStockDataSource = userWatchListStockDataSource;
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

	public List<UserWatchList> getUserWatchListList() {
		return userWatchListList;
	}

	public void setUserWatchListList(List<UserWatchList> userWatchListList) {
		this.userWatchListList = userWatchListList;
	}

	public UserWatchListDataSource getUserWatchListDataSource() {
		return userWatchListDataSource;
	}

	public void setUserWatchListDataSource(
			UserWatchListDataSource userWatchListDataSource) {
		this.userWatchListDataSource = userWatchListDataSource;
	}

	public List<UserWatchListWrapper> getUserWatchListWrapperList() {
		return userWatchListWrapperList;
	}

	public void setUserWatchListWrapperList(
			List<UserWatchListWrapper> userWatchListWrapperList) {
		this.userWatchListWrapperList = userWatchListWrapperList;
	}

	public UserWatchListWrapper getSelectedUserWatchListWrapper() {
		return selectedUserWatchListWrapper;
	}

	public void setSelectedUserWatchListWrapper(
			UserWatchListWrapper selectedUserWatchListWrapper) {
		this.selectedUserWatchListWrapper = selectedUserWatchListWrapper;
	}

	public UserWatchListStock saveUserWatchListStock() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			userWatchListStock.setCompany(company);

			UserWatchList userWatchList = selectedUserWatchListWrapper
					.getUserWatchList();

			userWatchListStock.setUserWatchList(userWatchList);

			userWatchListStockDataSource.create(userWatchListStock, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserWatchListStock !");
			this.userWatchListStock = new UserWatchListStock();
			return userWatchListStock;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserWatchListStock .Try Again Later!");
			return null;
		}
	}

	public UserWatchListStock updateUserWatchListStock() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			userWatchListStock.setCompany(company);

			UserWatchList userWatchList = selectedUserWatchListWrapper
					.getUserWatchList();

			userWatchListStock.setUserWatchList(userWatchList);

			userWatchListStockDataSource.create(userWatchListStock, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserWatchListStock !");
			this.userWatchListStock = new UserWatchListStock();
			return userWatchListStock;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserWatchListStock .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserWatchListStock();
		} else {
			log.info("Creating value");
			saveUserWatchListStock();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(
				"createUserWatchListStock");

	}

	public UserWatchListStock saveUserWatchListStock(Session session) {

		this.userWatchListStock = userWatchListStockDataSource.create(
				this.userWatchListStock, session);
		return this.userWatchListStock;
	}

}
