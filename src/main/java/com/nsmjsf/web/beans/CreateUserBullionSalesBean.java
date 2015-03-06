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
import com.nsmjsf.web.datasources.UserBullionSalesDataSource;
import com.nsmjsf.web.datamodels.UserBullionSales;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserBullionAdapter;

import com.nsmjsf.web.datasources.UserBullionDataSource;

import com.nsmjsf.web.datamodels.UserBullion;

import com.nsmjsf.web.wrappers.UserBullionWrapper;

@ManagedBean
@ViewScoped
public class CreateUserBullionSalesBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserBullionSalesBean.class);

	private UserBullionSales userBullionSales;
	private UserBullionSalesDataSource userBullionSalesDataSource;

	private UserBullionDataSource userBullionDataSource;
	private List<UserBullionWrapper> userBullionWrapperList;
	private List<UserBullion> userBullionList;
	private UserBullionWrapper selectedUserBullionWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserBullionSalesBean() {

		userBullionSales = new UserBullionSales();
		/* init datasources */
		userBullionSalesDataSource = new UserBullionSalesDataSource();

		userBullionDataSource = new UserBullionDataSource();

		/* init option wrappers */
		userBullionList = userBullionDataSource.getAll();
		userBullionWrapperList = UserBullionAdapter.wrapAll(userBullionList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userBullionSales = userBullionSalesDataSource.get(editId);

			this.selectedUserBullionWrapper = UserBullionAdapter
					.wrap(userBullionSales.getUserBullion());

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

	public UserBullionSales getUserBullionSales() {
		return userBullionSales;
	}

	public void setUserBullionSales(UserBullionSales userBullionSales) {
		this.userBullionSales = userBullionSales;
	}

	/* getters for datasources */
	public UserBullionSalesDataSource getUserBullionSalesDataSource() {
		return userBullionSalesDataSource;
	}

	public void setUserBullionSalesDataSource(
			UserBullionSalesDataSource userBullionSalesDataSource) {
		this.userBullionSalesDataSource = userBullionSalesDataSource;
	}

	public List<UserBullion> getUserBullionList() {
		return userBullionList;
	}

	public void setUserBullionList(List<UserBullion> userBullionList) {
		this.userBullionList = userBullionList;
	}

	public UserBullionDataSource getUserBullionDataSource() {
		return userBullionDataSource;
	}

	public void setUserBullionDataSource(
			UserBullionDataSource userBullionDataSource) {
		this.userBullionDataSource = userBullionDataSource;
	}

	public List<UserBullionWrapper> getUserBullionWrapperList() {
		return userBullionWrapperList;
	}

	public void setUserBullionWrapperList(
			List<UserBullionWrapper> userBullionWrapperList) {
		this.userBullionWrapperList = userBullionWrapperList;
	}

	public UserBullionWrapper getSelectedUserBullionWrapper() {
		return selectedUserBullionWrapper;
	}

	public void setSelectedUserBullionWrapper(
			UserBullionWrapper selectedUserBullionWrapper) {
		this.selectedUserBullionWrapper = selectedUserBullionWrapper;
	}

	public UserBullionSales saveUserBullionSales() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserBullion userBullion = selectedUserBullionWrapper
					.getUserBullion();

			userBullionSales.setUserBullion(userBullion);

			userBullionSalesDataSource.create(userBullionSales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserBullionSales !");
			this.userBullionSales = new UserBullionSales();
			return userBullionSales;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserBullionSales .Try Again Later!");
			return null;
		}
	}

	public UserBullionSales updateUserBullionSales() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserBullion userBullion = selectedUserBullionWrapper
					.getUserBullion();

			userBullionSales.setUserBullion(userBullion);

			userBullionSalesDataSource.create(userBullionSales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserBullionSales !");
			this.userBullionSales = new UserBullionSales();
			return userBullionSales;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserBullionSales .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserBullionSales();
		} else {
			log.info("Creating value");
			saveUserBullionSales();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(
				"createUserBullionSales");

	}

	public UserBullionSales saveUserBullionSales(Session session) {

		this.userBullionSales = userBullionSalesDataSource.create(
				this.userBullionSales, session);
		return this.userBullionSales;
	}

}
