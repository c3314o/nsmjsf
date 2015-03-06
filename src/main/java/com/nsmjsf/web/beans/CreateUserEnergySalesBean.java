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
import com.nsmjsf.web.datasources.UserEnergySalesDataSource;
import com.nsmjsf.web.datamodels.UserEnergySales;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.UserEnergyAdapter;

import com.nsmjsf.web.datasources.UserEnergyDataSource;

import com.nsmjsf.web.datamodels.UserEnergy;

import com.nsmjsf.web.wrappers.UserEnergyWrapper;

@ManagedBean
@ViewScoped
public class CreateUserEnergySalesBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateUserEnergySalesBean.class);

	private UserEnergySales userEnergySales;
	private UserEnergySalesDataSource userEnergySalesDataSource;

	private UserEnergyDataSource userEnergyDataSource;
	private List<UserEnergyWrapper> userEnergyWrapperList;
	private List<UserEnergy> userEnergyList;
	private UserEnergyWrapper selectedUserEnergyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateUserEnergySalesBean() {

		userEnergySales = new UserEnergySales();
		/* init datasources */
		userEnergySalesDataSource = new UserEnergySalesDataSource();

		userEnergyDataSource = new UserEnergyDataSource();

		/* init option wrappers */
		userEnergyList = userEnergyDataSource.getAll();
		userEnergyWrapperList = UserEnergyAdapter.wrapAll(userEnergyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.userEnergySales = userEnergySalesDataSource.get(editId);

			this.selectedUserEnergyWrapper = UserEnergyAdapter
					.wrap(userEnergySales.getUserEnergy());

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

	public UserEnergySales getUserEnergySales() {
		return userEnergySales;
	}

	public void setUserEnergySales(UserEnergySales userEnergySales) {
		this.userEnergySales = userEnergySales;
	}

	/* getters for datasources */
	public UserEnergySalesDataSource getUserEnergySalesDataSource() {
		return userEnergySalesDataSource;
	}

	public void setUserEnergySalesDataSource(
			UserEnergySalesDataSource userEnergySalesDataSource) {
		this.userEnergySalesDataSource = userEnergySalesDataSource;
	}

	public List<UserEnergy> getUserEnergyList() {
		return userEnergyList;
	}

	public void setUserEnergyList(List<UserEnergy> userEnergyList) {
		this.userEnergyList = userEnergyList;
	}

	public UserEnergyDataSource getUserEnergyDataSource() {
		return userEnergyDataSource;
	}

	public void setUserEnergyDataSource(
			UserEnergyDataSource userEnergyDataSource) {
		this.userEnergyDataSource = userEnergyDataSource;
	}

	public List<UserEnergyWrapper> getUserEnergyWrapperList() {
		return userEnergyWrapperList;
	}

	public void setUserEnergyWrapperList(
			List<UserEnergyWrapper> userEnergyWrapperList) {
		this.userEnergyWrapperList = userEnergyWrapperList;
	}

	public UserEnergyWrapper getSelectedUserEnergyWrapper() {
		return selectedUserEnergyWrapper;
	}

	public void setSelectedUserEnergyWrapper(
			UserEnergyWrapper selectedUserEnergyWrapper) {
		this.selectedUserEnergyWrapper = selectedUserEnergyWrapper;
	}

	public UserEnergySales saveUserEnergySales() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserEnergy userEnergy = selectedUserEnergyWrapper.getUserEnergy();

			userEnergySales.setUserEnergy(userEnergy);

			userEnergySalesDataSource.create(userEnergySales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserEnergySales !");
			this.userEnergySales = new UserEnergySales();
			return userEnergySales;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving UserEnergySales .Try Again Later!");
			return null;
		}
	}

	public UserEnergySales updateUserEnergySales() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			UserEnergy userEnergy = selectedUserEnergyWrapper.getUserEnergy();

			userEnergySales.setUserEnergy(userEnergy);

			userEnergySalesDataSource.create(userEnergySales, session);
			tx.commit();
			MessageService.info("Successfully Saved  UserEnergySales !");
			this.userEnergySales = new UserEnergySales();
			return userEnergySales;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving UserEnergySales .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateUserEnergySales();
		} else {
			log.info("Creating value");
			saveUserEnergySales();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createUserEnergySales");

	}

	public UserEnergySales saveUserEnergySales(Session session) {

		this.userEnergySales = userEnergySalesDataSource.create(
				this.userEnergySales, session);
		return this.userEnergySales;
	}

}
