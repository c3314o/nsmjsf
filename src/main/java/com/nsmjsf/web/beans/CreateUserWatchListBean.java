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
import com.nsmjsf.web.datasources.UserWatchListDataSource;
import com.nsmjsf.web.datamodels.UserWatchList;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserWatchListBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserWatchListBean.class);

	private UserWatchList userWatchList;
	private UserWatchListDataSource userWatchListDataSource;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserWatchListBean() {

		userWatchList = new UserWatchList();
		/* init datasources */
		userWatchListDataSource = new UserWatchListDataSource();

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userWatchList = userWatchListDataSource.get(editId);

			this.selectedUserWrapper = UserAdapter
					.wrap(userWatchList.getUser());

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

	public UserWatchList getUserWatchList() {
		return userWatchList;
	}

	public void setUserWatchList(UserWatchList userWatchList) {
		this.userWatchList = userWatchList;
	}

	/* getters for datasources */
	public UserWatchListDataSource getUserWatchListDataSource() {
		return userWatchListDataSource;
	}

	public void setUserWatchListDataSource(
			UserWatchListDataSource userWatchListDataSource) {
		this.userWatchListDataSource = userWatchListDataSource;
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

	public UserWatchList saveUserWatchList() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			userWatchList.setUser(user);

			userWatchListDataSource.create(userWatchList, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserWatchList !");
			this.userWatchList = new UserWatchList();
			return userWatchList;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserWatchList .Try Again Later!");
			return null;
		}
	}

	public UserWatchList updateUserWatchList() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			userWatchList.setUser(user);

			userWatchListDataSource.create(userWatchList, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserWatchList !");
			this.userWatchList = new UserWatchList();
			return userWatchList;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserWatchList .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserWatchList();
		} else {
			log.info("Creating value");
			saveUserWatchList();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserWatchList");

	}

	public UserWatchList saveUserWatchList(Session session) {

		this.userWatchList = userWatchListDataSource.create(this.userWatchList,
				session);
		return this.userWatchList;
	}

}
