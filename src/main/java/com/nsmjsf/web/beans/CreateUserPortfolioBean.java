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
import com.nsmjsf.web.datasources.UserPortfolioDataSource;
import com.nsmjsf.web.datamodels.UserPortfolio;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserPortfolioBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserPortfolioBean.class);

	private UserPortfolio userPortfolio;
	private UserPortfolioDataSource userPortfolioDataSource;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserPortfolioBean() {

		userPortfolio = new UserPortfolio();
		/* init datasources */
		userPortfolioDataSource = new UserPortfolioDataSource();

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userPortfolio = userPortfolioDataSource.get(editId);

			this.selectedUserWrapper = UserAdapter
					.wrap(userPortfolio.getUser());

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

	public UserPortfolio getUserPortfolio() {
		return userPortfolio;
	}

	public void setUserPortfolio(UserPortfolio userPortfolio) {
		this.userPortfolio = userPortfolio;
	}

	/* getters for datasources */
	public UserPortfolioDataSource getUserPortfolioDataSource() {
		return userPortfolioDataSource;
	}

	public void setUserPortfolioDataSource(
			UserPortfolioDataSource userPortfolioDataSource) {
		this.userPortfolioDataSource = userPortfolioDataSource;
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

	public UserPortfolio saveUserPortfolio() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			userPortfolio.setUser(user);

			userPortfolioDataSource.create(userPortfolio, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserPortfolio !");
			this.userPortfolio = new UserPortfolio();
			return userPortfolio;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserPortfolio .Try Again Later!");
			return null;
		}
	}

	public UserPortfolio updateUserPortfolio() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			userPortfolio.setUser(user);

			userPortfolioDataSource.create(userPortfolio, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserPortfolio !");
			this.userPortfolio = new UserPortfolio();
			return userPortfolio;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserPortfolio .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserPortfolio();
		} else {
			log.info("Creating value");
			saveUserPortfolio();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserPortfolio");

	}

	public UserPortfolio saveUserPortfolio(Session session) {

		this.userPortfolio = userPortfolioDataSource.create(this.userPortfolio,
				session);
		return this.userPortfolio;
	}

}
