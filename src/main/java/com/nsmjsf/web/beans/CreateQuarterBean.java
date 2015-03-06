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
import com.nsmjsf.web.datasources.QuarterDataSource;
import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateQuarterBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateQuarterBean.class);

	private Quarter quarter;
	private QuarterDataSource quarterDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateQuarterBean() {

		quarter = new Quarter();
		/* init datasources */
		quarterDataSource = new QuarterDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.quarter = quarterDataSource.get(editId);

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

	public Quarter getQuarter() {
		return quarter;
	}

	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}

	/* getters for datasources */
	public QuarterDataSource getQuarterDataSource() {
		return quarterDataSource;
	}

	public void setQuarterDataSource(QuarterDataSource quarterDataSource) {
		this.quarterDataSource = quarterDataSource;
	}

	public Quarter saveQuarter() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			quarterDataSource.create(quarter, session);
			tx.commit();
			MessageService.info("Successfully Saved  Quarter !");
			this.quarter = new Quarter();
			return quarter;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Quarter .Try Again Later!");
			return null;
		}
	}

	public Quarter updateQuarter() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			quarterDataSource.create(quarter, session);
			tx.commit();
			MessageService.info("Successfully Saved  Quarter !");
			this.quarter = new Quarter();
			return quarter;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Quarter .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateQuarter();
		} else {
			log.info("Creating value");
			saveQuarter();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createQuarter");

	}

	public Quarter saveQuarter(Session session) {

		this.quarter = quarterDataSource.create(this.quarter, session);
		return this.quarter;
	}

}
