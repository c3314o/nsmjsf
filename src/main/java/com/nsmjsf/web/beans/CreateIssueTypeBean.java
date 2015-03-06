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
import com.nsmjsf.web.datasources.IssueTypeDataSource;
import com.nsmjsf.web.datamodels.IssueType;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateIssueTypeBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateIssueTypeBean.class);

	private IssueType issueType;
	private IssueTypeDataSource issueTypeDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateIssueTypeBean() {

		issueType = new IssueType();
		/* init datasources */
		issueTypeDataSource = new IssueTypeDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.issueType = issueTypeDataSource.get(editId);

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

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	/* getters for datasources */
	public IssueTypeDataSource getIssueTypeDataSource() {
		return issueTypeDataSource;
	}

	public void setIssueTypeDataSource(IssueTypeDataSource issueTypeDataSource) {
		this.issueTypeDataSource = issueTypeDataSource;
	}

	public IssueType saveIssueType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			issueTypeDataSource.create(issueType, session);
			tx.commit();
			MessageService.info("Successfully Saved  IssueType !");
			this.issueType = new IssueType();
			return issueType;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving IssueType .Try Again Later!");
			return null;
		}
	}

	public IssueType updateIssueType() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			issueTypeDataSource.create(issueType, session);
			tx.commit();
			MessageService.info("Successfully Saved  IssueType !");
			this.issueType = new IssueType();
			return issueType;

		} catch (Exception ex) {
			MessageService.error("Failed Saving IssueType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateIssueType();
		} else {
			log.info("Creating value");
			saveIssueType();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createIssueType");

	}

	public IssueType saveIssueType(Session session) {

		this.issueType = issueTypeDataSource.create(this.issueType, session);
		return this.issueType;
	}

}
