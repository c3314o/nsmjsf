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
import com.nsmjsf.web.datasources.EnergyTypeDataSource;
import com.nsmjsf.web.datamodels.EnergyType;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateEnergyTypeBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateEnergyTypeBean.class);

	private EnergyType energyType;
	private EnergyTypeDataSource energyTypeDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateEnergyTypeBean() {

		energyType = new EnergyType();
		/* init datasources */
		energyTypeDataSource = new EnergyTypeDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.energyType = energyTypeDataSource.get(editId);

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

	public EnergyType getEnergyType() {
		return energyType;
	}

	public void setEnergyType(EnergyType energyType) {
		this.energyType = energyType;
	}

	/* getters for datasources */
	public EnergyTypeDataSource getEnergyTypeDataSource() {
		return energyTypeDataSource;
	}

	public void setEnergyTypeDataSource(
			EnergyTypeDataSource energyTypeDataSource) {
		this.energyTypeDataSource = energyTypeDataSource;
	}

	public EnergyType saveEnergyType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			energyTypeDataSource.create(energyType, session);
			tx.commit();
			MessageService.info("Successfully Saved  EnergyType !");
			this.energyType = new EnergyType();
			return energyType;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving EnergyType .Try Again Later!");
			return null;
		}
	}

	public EnergyType updateEnergyType() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			energyTypeDataSource.create(energyType, session);
			tx.commit();
			MessageService.info("Successfully Saved  EnergyType !");
			this.energyType = new EnergyType();
			return energyType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving EnergyType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateEnergyType();
		} else {
			log.info("Creating value");
			saveEnergyType();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createEnergyType");

	}

	public EnergyType saveEnergyType(Session session) {

		this.energyType = energyTypeDataSource.create(this.energyType, session);
		return this.energyType;
	}

}
