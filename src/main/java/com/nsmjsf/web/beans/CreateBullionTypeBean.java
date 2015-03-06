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
import com.nsmjsf.web.datasources.BullionTypeDataSource;
import com.nsmjsf.web.datamodels.BullionType;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateBullionTypeBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateBullionTypeBean.class);

	private BullionType bullionType;
	private BullionTypeDataSource bullionTypeDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateBullionTypeBean() {

		bullionType = new BullionType();
		/* init datasources */
		bullionTypeDataSource = new BullionTypeDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.bullionType = bullionTypeDataSource.get(editId);

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

	public BullionType getBullionType() {
		return bullionType;
	}

	public void setBullionType(BullionType bullionType) {
		this.bullionType = bullionType;
	}

	/* getters for datasources */
	public BullionTypeDataSource getBullionTypeDataSource() {
		return bullionTypeDataSource;
	}

	public void setBullionTypeDataSource(
			BullionTypeDataSource bullionTypeDataSource) {
		this.bullionTypeDataSource = bullionTypeDataSource;
	}

	public BullionType saveBullionType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			bullionTypeDataSource.create(bullionType, session);
			tx.commit();
			MessageService.info("Successfully Saved  BullionType !");
			this.bullionType = new BullionType();
			return bullionType;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving BullionType .Try Again Later!");
			return null;
		}
	}

	public BullionType updateBullionType() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			bullionTypeDataSource.create(bullionType, session);
			tx.commit();
			MessageService.info("Successfully Saved  BullionType !");
			this.bullionType = new BullionType();
			return bullionType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving BullionType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateBullionType();
		} else {
			log.info("Creating value");
			saveBullionType();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createBullionType");

	}

	public BullionType saveBullionType(Session session) {

		this.bullionType = bullionTypeDataSource.create(this.bullionType,
				session);
		return this.bullionType;
	}

}
