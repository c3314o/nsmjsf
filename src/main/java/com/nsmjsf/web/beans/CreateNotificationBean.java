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
import com.nsmjsf.web.datasources.NotificationDataSource;
import com.nsmjsf.web.datamodels.Notification;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateNotificationBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateNotificationBean.class);

	private Notification notification;
	private NotificationDataSource notificationDataSource;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateNotificationBean() {

		notification = new Notification();
		/* init datasources */
		notificationDataSource = new NotificationDataSource();

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.notification = notificationDataSource.get(editId);

			this.selectedUserWrapper = UserAdapter.wrap(notification.getUser());

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

	public Notification getNotification() {
		return notification;
	}

	public void setNotification(Notification notification) {
		this.notification = notification;
	}

	/* getters for datasources */
	public NotificationDataSource getNotificationDataSource() {
		return notificationDataSource;
	}

	public void setNotificationDataSource(
			NotificationDataSource notificationDataSource) {
		this.notificationDataSource = notificationDataSource;
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

	public Notification saveNotification() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			notification.setUser(user);

			notificationDataSource.create(notification, session);
			tx.commit();
			MessageService.info("Successfully Saved  Notification !");
			this.notification = new Notification();
			return notification;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving Notification .Try Again Later!");
			return null;
		}
	}

	public Notification updateNotification() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			User user = selectedUserWrapper.getUser();

			notification.setUser(user);

			notificationDataSource.create(notification, session);
			tx.commit();
			MessageService.info("Successfully Saved  Notification !");
			this.notification = new Notification();
			return notification;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving Notification .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateNotification();
		} else {
			log.info("Creating value");
			saveNotification();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createNotification");

	}

	public Notification saveNotification(Session session) {

		this.notification = notificationDataSource.create(this.notification,
				session);
		return this.notification;
	}

}
