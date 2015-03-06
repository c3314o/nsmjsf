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
import com.nsmjsf.web.datasources.UserEnergyDataSource;
import com.nsmjsf.web.datamodels.UserEnergy;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.EnergyTypeAdapter;

import com.nsmjsf.web.datasources.EnergyTypeDataSource;

import com.nsmjsf.web.datamodels.EnergyType;

import com.nsmjsf.web.wrappers.EnergyTypeWrapper;

import com.nsmjsf.web.adapters.UserPortfolioAdapter;

import com.nsmjsf.web.datasources.UserPortfolioDataSource;

import com.nsmjsf.web.datamodels.UserPortfolio;

import com.nsmjsf.web.wrappers.UserPortfolioWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class CreateUserEnergyBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserEnergyBean.class);

	private UserEnergy userEnergy;
	private UserEnergyDataSource userEnergyDataSource;

	private EnergyTypeDataSource energyTypeDataSource;
	private List<EnergyTypeWrapper> energyTypeWrapperList;
	private List<EnergyType> energyTypeList;
	private EnergyTypeWrapper selectedEnergyTypeWrapper;

	private UserPortfolioDataSource userPortfolioDataSource;
	private List<UserPortfolioWrapper> userPortfolioWrapperList;
	private List<UserPortfolio> userPortfolioList;
	private UserPortfolioWrapper selectedUserPortfolioWrapper;

	private UserDataSource userDataSource;
	private List<UserWrapper> userWrapperList;
	private List<User> userList;
	private UserWrapper selectedUserWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserEnergyBean() {

		userEnergy = new UserEnergy();
		/* init datasources */
		userEnergyDataSource = new UserEnergyDataSource();

		energyTypeDataSource = new EnergyTypeDataSource();

		/* init option wrappers */
		energyTypeList = energyTypeDataSource.getAll();
		energyTypeWrapperList = EnergyTypeAdapter.wrapAll(energyTypeList);

		userPortfolioDataSource = new UserPortfolioDataSource();

		/* init option wrappers */
		userPortfolioList = userPortfolioDataSource.getAll();
		userPortfolioWrapperList = UserPortfolioAdapter
				.wrapAll(userPortfolioList);

		userDataSource = new UserDataSource();

		/* init option wrappers */
		userList = userDataSource.getAll();
		userWrapperList = UserAdapter.wrapAll(userList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userEnergy = userEnergyDataSource.get(editId);

			this.selectedEnergyTypeWrapper = EnergyTypeAdapter.wrap(userEnergy
					.getEnergyType());

			this.selectedUserPortfolioWrapper = UserPortfolioAdapter
					.wrap(userEnergy.getUserPortfolio());

			this.selectedUserWrapper = UserAdapter.wrap(userEnergy.getUser());

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

	public UserEnergy getUserEnergy() {
		return userEnergy;
	}

	public void setUserEnergy(UserEnergy userEnergy) {
		this.userEnergy = userEnergy;
	}

	/* getters for datasources */
	public UserEnergyDataSource getUserEnergyDataSource() {
		return userEnergyDataSource;
	}

	public void setUserEnergyDataSource(
			UserEnergyDataSource userEnergyDataSource) {
		this.userEnergyDataSource = userEnergyDataSource;
	}

	public List<EnergyType> getEnergyTypeList() {
		return energyTypeList;
	}

	public void setEnergyTypeList(List<EnergyType> energyTypeList) {
		this.energyTypeList = energyTypeList;
	}

	public EnergyTypeDataSource getEnergyTypeDataSource() {
		return energyTypeDataSource;
	}

	public void setEnergyTypeDataSource(
			EnergyTypeDataSource energyTypeDataSource) {
		this.energyTypeDataSource = energyTypeDataSource;
	}

	public List<EnergyTypeWrapper> getEnergyTypeWrapperList() {
		return energyTypeWrapperList;
	}

	public void setEnergyTypeWrapperList(
			List<EnergyTypeWrapper> energyTypeWrapperList) {
		this.energyTypeWrapperList = energyTypeWrapperList;
	}

	public EnergyTypeWrapper getSelectedEnergyTypeWrapper() {
		return selectedEnergyTypeWrapper;
	}

	public void setSelectedEnergyTypeWrapper(
			EnergyTypeWrapper selectedEnergyTypeWrapper) {
		this.selectedEnergyTypeWrapper = selectedEnergyTypeWrapper;
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

	public UserEnergy saveUserEnergy() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			EnergyType energyType = selectedEnergyTypeWrapper.getEnergyType();

			userEnergy.setEnergyType(energyType);

			UserPortfolio userPortfolio = selectedUserPortfolioWrapper
					.getUserPortfolio();

			userEnergy.setUserPortfolio(userPortfolio);

			User user = selectedUserWrapper.getUser();

			userEnergy.setUser(user);

			userEnergyDataSource.create(userEnergy, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserEnergy !");
			this.userEnergy = new UserEnergy();
			return userEnergy;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving UserEnergy .Try Again Later!");
			return null;
		}
	}

	public UserEnergy updateUserEnergy() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			EnergyType energyType = selectedEnergyTypeWrapper.getEnergyType();

			userEnergy.setEnergyType(energyType);

			UserPortfolio userPortfolio = selectedUserPortfolioWrapper
					.getUserPortfolio();

			userEnergy.setUserPortfolio(userPortfolio);

			User user = selectedUserWrapper.getUser();

			userEnergy.setUser(user);

			userEnergyDataSource.create(userEnergy, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserEnergy !");
			this.userEnergy = new UserEnergy();
			return userEnergy;

		} catch (Exception ex) {
			MessageService.error("Failed Saving UserEnergy .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserEnergy();
		} else {
			log.info("Creating value");
			saveUserEnergy();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createUserEnergy");

	}

	public UserEnergy saveUserEnergy(Session session) {

		this.userEnergy = userEnergyDataSource.create(this.userEnergy, session);
		return this.userEnergy;
	}

}
