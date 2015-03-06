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
import com.nsmjsf.web.datasources.FiscalYearDataSource;
import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateFiscalYearBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateFiscalYearBean.class);

	private FiscalYear fiscalYear;
	private FiscalYearDataSource fiscalYearDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateFiscalYearBean() {

		fiscalYear = new FiscalYear();
		/* init datasources */
		fiscalYearDataSource = new FiscalYearDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.fiscalYear = fiscalYearDataSource.get(editId);

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

	public FiscalYear getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYear fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	/* getters for datasources */
	public FiscalYearDataSource getFiscalYearDataSource() {
		return fiscalYearDataSource;
	}

	public void setFiscalYearDataSource(
			FiscalYearDataSource fiscalYearDataSource) {
		this.fiscalYearDataSource = fiscalYearDataSource;
	}

	public FiscalYear saveFiscalYear() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			fiscalYearDataSource.create(fiscalYear, session);
			tx.commit();
			MessageService.info("Successfully Saved  FiscalYear !");
			this.fiscalYear = new FiscalYear();
			return fiscalYear;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving FiscalYear .Try Again Later!");
			return null;
		}
	}

	public FiscalYear updateFiscalYear() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			fiscalYearDataSource.create(fiscalYear, session);
			tx.commit();
			MessageService.info("Successfully Saved  FiscalYear !");
			this.fiscalYear = new FiscalYear();
			return fiscalYear;

		} catch (Exception ex) {
			MessageService.error("Failed Saving FiscalYear .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateFiscalYear();
		} else {
			log.info("Creating value");
			saveFiscalYear();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createFiscalYear");

	}

	public FiscalYear saveFiscalYear(Session session) {

		this.fiscalYear = fiscalYearDataSource.create(this.fiscalYear, session);
		return this.fiscalYear;
	}

}
