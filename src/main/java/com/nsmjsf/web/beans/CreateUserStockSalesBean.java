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
import com.nsmjsf.web.datasources.UserStockSalesDataSource;
import com.nsmjsf.web.datamodels.UserStockSales;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserStockAdapter;

import com.nsmjsf.web.datasources.UserStockDataSource;

import com.nsmjsf.web.datamodels.UserStock;

import com.nsmjsf.web.wrappers.UserStockWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserStockSalesBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserStockSalesBean.class);

	private UserStockSales userStockSales;
	private UserStockSalesDataSource userStockSalesDataSource;

	private UserStockDataSource userStockDataSource;
	private List<UserStockWrapper> userStockWrapperList;
	private List<UserStock> userStockList;
	private UserStockWrapper selectedUserStockWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserStockSalesBean() {

		userStockSales = new UserStockSales();
		/* init datasources */
		userStockSalesDataSource = new UserStockSalesDataSource();

		userStockDataSource = new UserStockDataSource();

		/* init option wrappers */
		userStockList = userStockDataSource.getAll();
		userStockWrapperList = UserStockAdapter.wrapAll(userStockList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userStockSales = userStockSalesDataSource.get(editId);

			this.selectedUserStockWrapper = UserStockAdapter
					.wrap(userStockSales.getUserStock());

			this.selectedUserWrapper = UserAdapter.wrap(userStockSales
					.getUser());

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

	public UserStockSales getUserStockSales() {
		return userStockSales;
	}

	public void setUserStockSales(UserStockSales userStockSales) {
		this.userStockSales = userStockSales;
	}

	/* getters for datasources */
	public UserStockSalesDataSource getUserStockSalesDataSource() {
		return userStockSalesDataSource;
	}

	public void setUserStockSalesDataSource(
			UserStockSalesDataSource userStockSalesDataSource) {
		this.userStockSalesDataSource = userStockSalesDataSource;
	}

	public List<UserStock> getUserStockList() {
		return userStockList;
	}

	public void setUserStockList(List<UserStock> userStockList) {
		this.userStockList = userStockList;
	}

	public UserStockDataSource getUserStockDataSource() {
		return userStockDataSource;
	}

	public void setUserStockDataSource(UserStockDataSource userStockDataSource) {
		this.userStockDataSource = userStockDataSource;
	}

	public List<UserStockWrapper> getUserStockWrapperList() {
		return userStockWrapperList;
	}

	public void setUserStockWrapperList(
			List<UserStockWrapper> userStockWrapperList) {
		this.userStockWrapperList = userStockWrapperList;
	}

	public UserStockWrapper getSelectedUserStockWrapper() {
		return selectedUserStockWrapper;
	}

	public void setSelectedUserStockWrapper(
			UserStockWrapper selectedUserStockWrapper) {
		this.selectedUserStockWrapper = selectedUserStockWrapper;
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

	public UserStockSales saveUserStockSales() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserStock userStock = selectedUserStockWrapper.getUserStock();

			userStockSales.setUserStock(userStock);

			User user = selectedUserWrapper.getUser();

			userStockSales.setUser(user);

			userStockSalesDataSource.create(userStockSales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserStockSales !");
			this.userStockSales = new UserStockSales();
			return userStockSales;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserStockSales .Try Again Later!");
			return null;
		}
	}

	public UserStockSales updateUserStockSales() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserStock userStock = selectedUserStockWrapper.getUserStock();

			userStockSales.setUserStock(userStock);

			User user = selectedUserWrapper.getUser();

			userStockSales.setUser(user);

			userStockSalesDataSource.create(userStockSales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserStockSales !");
			this.userStockSales = new UserStockSales();
			return userStockSales;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserStockSales .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserStockSales();
		} else {
			log.info("Creating value");
			saveUserStockSales();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserStockSales");

	}

	public UserStockSales saveUserStockSales(Session session) {

		this.userStockSales = userStockSalesDataSource.create(
				this.userStockSales, session);
		return this.userStockSales;
	}

}
