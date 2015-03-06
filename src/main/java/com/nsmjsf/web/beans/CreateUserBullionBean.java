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
import com.nsmjsf.web.datasources.UserBullionDataSource;
import com.nsmjsf.web.datamodels.UserBullion;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.BullionTypeAdapter;

import com.nsmjsf.web.datasources.BullionTypeDataSource;

import com.nsmjsf.web.datamodels.BullionType;

import com.nsmjsf.web.wrappers.BullionTypeWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserBullionBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserBullionBean.class);

	private UserBullion userBullion;
	private UserBullionDataSource userBullionDataSource;

	private BullionTypeDataSource bullionTypeDataSource;
	private List<BullionTypeWrapper> bullionTypeWrapperList;
	private List<BullionType> bullionTypeList;
	private BullionTypeWrapper selectedBullionTypeWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserBullionBean() {

		userBullion = new UserBullion();
		/* init datasources */
		userBullionDataSource = new UserBullionDataSource();

		bullionTypeDataSource = new BullionTypeDataSource();

		/* init option wrappers */
		bullionTypeList = bullionTypeDataSource.getAll();
		bullionTypeWrapperList = BullionTypeAdapter.wrapAll(bullionTypeList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userBullion = userBullionDataSource.get(editId);

			this.selectedBullionTypeWrapper = BullionTypeAdapter
					.wrap(userBullion.getBullionType());

			this.selectedUserWrapper = UserAdapter.wrap(userBullion.getUser());

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

	public UserBullion getUserBullion() {
		return userBullion;
	}

	public void setUserBullion(UserBullion userBullion) {
		this.userBullion = userBullion;
	}

	/* getters for datasources */
	public UserBullionDataSource getUserBullionDataSource() {
		return userBullionDataSource;
	}

	public void setUserBullionDataSource(
			UserBullionDataSource userBullionDataSource) {
		this.userBullionDataSource = userBullionDataSource;
	}

	public List<BullionType> getBullionTypeList() {
		return bullionTypeList;
	}

	public void setBullionTypeList(List<BullionType> bullionTypeList) {
		this.bullionTypeList = bullionTypeList;
	}

	public BullionTypeDataSource getBullionTypeDataSource() {
		return bullionTypeDataSource;
	}

	public void setBullionTypeDataSource(
			BullionTypeDataSource bullionTypeDataSource) {
		this.bullionTypeDataSource = bullionTypeDataSource;
	}

	public List<BullionTypeWrapper> getBullionTypeWrapperList() {
		return bullionTypeWrapperList;
	}

	public void setBullionTypeWrapperList(
			List<BullionTypeWrapper> bullionTypeWrapperList) {
		this.bullionTypeWrapperList = bullionTypeWrapperList;
	}

	public BullionTypeWrapper getSelectedBullionTypeWrapper() {
		return selectedBullionTypeWrapper;
	}

	public void setSelectedBullionTypeWrapper(
			BullionTypeWrapper selectedBullionTypeWrapper) {
		this.selectedBullionTypeWrapper = selectedBullionTypeWrapper;
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

	public UserBullion saveUserBullion() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			BullionType bullionType = selectedBullionTypeWrapper
					.getBullionType();

			userBullion.setBullionType(bullionType);

			User user = selectedUserWrapper.getUser();

			userBullion.setUser(user);

			userBullionDataSource.create(userBullion, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserBullion !");
			this.userBullion = new UserBullion();
			return userBullion;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving UserBullion .Try Again Later!");
			return null;
		}
	}

	public UserBullion updateUserBullion() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			BullionType bullionType = selectedBullionTypeWrapper
					.getBullionType();

			userBullion.setBullionType(bullionType);

			User user = selectedUserWrapper.getUser();

			userBullion.setUser(user);

			userBullionDataSource.create(userBullion, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserBullion !");
			this.userBullion = new UserBullion();
			return userBullion;

		} catch (Exception ex) {
			MessageService.error("Failed Saving UserBullion .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserBullion();
		} else {
			log.info("Creating value");
			saveUserBullion();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserBullion");

	}

	public UserBullion saveUserBullion(Session session) {

		this.userBullion = userBullionDataSource.create(this.userBullion,
				session);
		return this.userBullion;
	}

}
