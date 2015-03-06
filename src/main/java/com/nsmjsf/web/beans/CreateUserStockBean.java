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
import com.nsmjsf.web.datasources.UserStockDataSource;
import com.nsmjsf.web.datamodels.UserStock;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserPortfolioAdapter;

import com.nsmjsf.web.datasources.UserPortfolioDataSource;

import com.nsmjsf.web.datamodels.UserPortfolio;

import com.nsmjsf.web.wrappers.UserPortfolioWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserStockBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateUserStockBean.class);

	private UserStock userStock;
	private UserStockDataSource userStockDataSource;

	private UserPortfolioDataSource userPortfolioDataSource;
	private List<UserPortfolioWrapper> userPortfolioWrapperList;
	private List<UserPortfolio> userPortfolioList;
	private UserPortfolioWrapper selectedUserPortfolioWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserStockBean() {

		userStock = new UserStock();
		/* init datasources */
		userStockDataSource = new UserStockDataSource();

		userPortfolioDataSource = new UserPortfolioDataSource();

		/* init option wrappers */
		userPortfolioList = userPortfolioDataSource.getAll();
		userPortfolioWrapperList = UserPortfolioAdapter
				.wrapAll(userPortfolioList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userStock = userStockDataSource.get(editId);

			this.selectedUserPortfolioWrapper = UserPortfolioAdapter
					.wrap(userStock.getUserPortfolio());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(userStock
					.getCompany());

			this.selectedUserWrapper = UserAdapter.wrap(userStock.getUser());

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

	public UserStock getUserStock() {
		return userStock;
	}

	public void setUserStock(UserStock userStock) {
		this.userStock = userStock;
	}

	/* getters for datasources */
	public UserStockDataSource getUserStockDataSource() {
		return userStockDataSource;
	}

	public void setUserStockDataSource(UserStockDataSource userStockDataSource) {
		this.userStockDataSource = userStockDataSource;
	}

	public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	}

	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}

	public UserPortfolioDataSource getUserPortfolioDataSource() {
		return userPortfolioDataSource;
	}

	public void setUserPortfolioDataSource(
			UserPortfolioDataSource userPortfolioDataSource) {
		this.userPortfolioDataSource = userPortfolioDataSource;
	}

	public List<UserPortfolioWrapper> getUserPortfolioWrapperList() {
		return userPortfolioWrapperList;
	}

	public void setUserPortfolioWrapperList(
			List<UserPortfolioWrapper> userPortfolioWrapperList) {
		this.userPortfolioWrapperList = userPortfolioWrapperList;
	}

	public UserPortfolioWrapper getSelectedUserPortfolioWrapper() {
		return selectedUserPortfolioWrapper;
	}

	public void setSelectedUserPortfolioWrapper(
			UserPortfolioWrapper selectedUserPortfolioWrapper) {
		this.selectedUserPortfolioWrapper = selectedUserPortfolioWrapper;
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

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public UserDataSource getUserDataSource() {
		return userDataSource;
	}

	public void setUserDataSource(UserDataSource userDataSource) {
		this.userDataSource = userDataSource;
	}

	public List<UserWrapper> getUserWrapperList() {
		return userWrapperList;
	}

	public void setUserWrapperList(List<UserWrapper> userWrapperList) {
		this.userWrapperList = userWrapperList;
	}

	public UserWrapper getSelectedUserWrapper() {
		return selectedUserWrapper;
	}

	public void setSelectedUserWrapper(UserWrapper selectedUserWrapper) {
		this.selectedUserWrapper = selectedUserWrapper;
	}

	public UserStock saveUserStock() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserPortfolio userPortfolio = selectedUserPortfolioWrapper
					.getUserPortfolio();

			userStock.setUserPortfolio(userPortfolio);

			Company company = selectedCompanyWrapper.getCompany();

			userStock.setCompany(company);

			User user = selectedUserWrapper.getUser();

			userStock.setUser(user);

			userStockDataSource.create(userStock, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserStock !");
			this.userStock = new UserStock();
			return userStock;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving UserStock .Try Again Later!");
			return null;
		}
	}

	public UserStock updateUserStock() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserPortfolio userPortfolio = selectedUserPortfolioWrapper
					.getUserPortfolio();

			userStock.setUserPortfolio(userPortfolio);

			Company company = selectedCompanyWrapper.getCompany();

			userStock.setCompany(company);

			User user = selectedUserWrapper.getUser();

			userStock.setUser(user);

			userStockDataSource.create(userStock, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserStock !");
			this.userStock = new UserStock();
			return userStock;

		} catch (Exception ex) {
			MessageService.error("Failed Saving UserStock .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserStock();
		} else {
			log.info("Creating value");
			saveUserStock();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserStock");

	}

	public UserStock saveUserStock(Session session) {

		this.userStock = userStockDataSource.create(this.userStock, session);
		return this.userStock;
	}

}
