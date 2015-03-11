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

import com.nsmjsf.web.datasources.UserDataSource;
import com.nsmjsf.web.datamodels.User;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserTypeAdapter;
import com.nsmjsf.web.datasources.UserTypeDataSource;
import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.wrappers.UserTypeWrapper;
import com.nsmjsf.web.adapters.UserInfoAdapter;
import com.nsmjsf.web.datasources.UserInfoDataSource;
import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.wrappers.UserInfoWrapper;

@ManagedBean
@ViewScoped
public class CreateUserBean implements Serializable {

	@ManagedProperty(value = "#{createUserInfoBean}")
	private CreateUserInfoBean createUserInfoBean;
	
	private static final Log log = LogFactory.getLog(CreateUserBean.class);

	private User user;
	private UserDataSource userDataSource;

	private UserTypeDataSource userTypeDataSource;
	private List<UserTypeWrapper> userTypeWrapperList;
	private List<UserType> userTypeList;
	private UserTypeWrapper selectedUserTypeWrapper;

	private UserInfoDataSource userInfoDataSource;
	private List<UserInfoWrapper> userInfoWrapperList;
	private List<UserInfo> userInfoList;
	private UserInfoWrapper selectedUserInfoWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserBean() {

		user = new User();
		/* init datasources */
		userDataSource = new UserDataSource();

		userTypeDataSource = new UserTypeDataSource();

		/* init option wrappers */
		userTypeList = userTypeDataSource.getAll();
		userTypeWrapperList = UserTypeAdapter.wrapAll(userTypeList);

		userInfoDataSource = new UserInfoDataSource();

		/* init option wrappers */
		userInfoList = userInfoDataSource.getAll();
		userInfoWrapperList = UserInfoAdapter.wrapAll(userInfoList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.user = userDataSource.get(editId);

			this.selectedUserTypeWrapper = UserTypeAdapter.wrap(user
					.getUserType());

			this.selectedUserInfoWrapper = UserInfoAdapter.wrap(user
					.getUserInfo());

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	/* getters for datasources */
	public UserDataSource getUserDataSource() {
		return userDataSource;
	}

	public void setUserDataSource(UserDataSource userDataSource) {
		this.userDataSource = userDataSource;
	}

	public List<UserType> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	public UserTypeDataSource getUserTypeDataSource() {
		return userTypeDataSource;
	}

	public void setUserTypeDataSource(UserTypeDataSource userTypeDataSource) {
		this.userTypeDataSource = userTypeDataSource;
	}

	public List<UserTypeWrapper> getUserTypeWrapperList() {
		return userTypeWrapperList;
	}

	public void setUserTypeWrapperList(List<UserTypeWrapper> userTypeWrapperList) {
		this.userTypeWrapperList = userTypeWrapperList;
	}

	public UserTypeWrapper getSelectedUserTypeWrapper() {
		return selectedUserTypeWrapper;
	}

	public void setSelectedUserTypeWrapper(
			UserTypeWrapper selectedUserTypeWrapper) {
		this.selectedUserTypeWrapper = selectedUserTypeWrapper;
	}

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public UserInfoDataSource getUserInfoDataSource() {
		return userInfoDataSource;
	}

	public void setUserInfoDataSource(UserInfoDataSource userInfoDataSource) {
		this.userInfoDataSource = userInfoDataSource;
	}

	public List<UserInfoWrapper> getUserInfoWrapperList() {
		return userInfoWrapperList;
	}

	public void setUserInfoWrapperList(List<UserInfoWrapper> userInfoWrapperList) {
		this.userInfoWrapperList = userInfoWrapperList;
	}

	public UserInfoWrapper getSelectedUserInfoWrapper() {
		return selectedUserInfoWrapper;
	}

	public void setSelectedUserInfoWrapper(
			UserInfoWrapper selectedUserInfoWrapper) {
		this.selectedUserInfoWrapper = selectedUserInfoWrapper;
	}

	public User saveUser() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserType userType = selectedUserTypeWrapper.getUserType();

			user.setUserType(userType);

			UserInfo userInfo = createUserInfoBean.saveUserInfo(session);

			user.setUserInfo(userInfo);

			userDataSource.create(user, session);
			tx.commit();
			MessageService.info("Successfully Saved  User !");
			this.user = new User();
			return user;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving User .Try Again Later!");
			return null;
		}
	}

	public User updateUser() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserType userType = selectedUserTypeWrapper.getUserType();

			user.setUserType(userType);

			UserInfo userInfo = createUserInfoBean.saveUserInfo(session);

			user.setUserInfo(userInfo);

			userDataSource.create(user, session);
			tx.commit();
			MessageService.info("Successfully Saved  User !");
			this.user = new User();
			return user;

		} catch (Exception ex) {
			MessageService.error("Failed Saving User .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUser();
		} else {
			log.info("Creating value");
			saveUser();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUser");

	}

	public User saveUser(Session session) {

		this.user = userDataSource.create(this.user, session);
		return this.user;
	}

	public CreateUserInfoBean getCreateUserInfoBean() {
		return createUserInfoBean;
	}

	public void setCreateUserInfoBean(CreateUserInfoBean createUserInfoBean) {
		this.createUserInfoBean = createUserInfoBean;
	}
	
}
