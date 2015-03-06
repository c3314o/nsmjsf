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
import com.nsmjsf.web.datasources.MonthDataSource;
import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateMonthBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateMonthBean.class);

	private Month month;
	private MonthDataSource monthDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMonthBean() {

		month = new Month();
		/* init datasources */
		monthDataSource = new MonthDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.month = monthDataSource.get(editId);

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

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	/* getters for datasources */
	public MonthDataSource getMonthDataSource() {
		return monthDataSource;
	}

	public void setMonthDataSource(MonthDataSource monthDataSource) {
		this.monthDataSource = monthDataSource;
	}

	public Month saveMonth() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			monthDataSource.create(month, session);
			tx.commit();
			MessageService.info("Successfully Saved  Month !");
			this.month = new Month();
			return month;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Month .Try Again Later!");
			return null;
		}
	}

	public Month updateMonth() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			monthDataSource.create(month, session);
			tx.commit();
			MessageService.info("Successfully Saved  Month !");
			this.month = new Month();
			return month;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Month .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMonth();
		} else {
			log.info("Creating value");
			saveMonth();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createMonth");

	}

	public Month saveMonth(Session session) {

		this.month = monthDataSource.create(this.month, session);
		return this.month;
	}

}
