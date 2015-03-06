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
import com.nsmjsf.web.datasources.SectorDataSource;
import com.nsmjsf.web.datamodels.Sector;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateSectorBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateSectorBean.class);

	private Sector sector;
	private SectorDataSource sectorDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateSectorBean() {

		sector = new Sector();
		/* init datasources */
		sectorDataSource = new SectorDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.sector = sectorDataSource.get(editId);

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

	public Sector getSector() {
		return sector;
	}

	public void setSector(Sector sector) {
		this.sector = sector;
	}

	/* getters for datasources */
	public SectorDataSource getSectorDataSource() {
		return sectorDataSource;
	}

	public void setSectorDataSource(SectorDataSource sectorDataSource) {
		this.sectorDataSource = sectorDataSource;
	}

	public Sector saveSector() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			sectorDataSource.create(sector, session);
			tx.commit();
			MessageService.info("Successfully Saved  Sector !");
			this.sector = new Sector();
			return sector;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Sector .Try Again Later!");
			return null;
		}
	}

	public Sector updateSector() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			sectorDataSource.create(sector, session);
			tx.commit();
			MessageService.info("Successfully Saved  Sector !");
			this.sector = new Sector();
			return sector;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Sector .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateSector();
		} else {
			log.info("Creating value");
			saveSector();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createSector");

	}

	public Sector saveSector(Session session) {

		this.sector = sectorDataSource.create(this.sector, session);
		return this.sector;
	}

}
