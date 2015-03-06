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
import com.nsmjsf.web.datasources.CurrencyTypeDataSource;
import com.nsmjsf.web.datamodels.CurrencyType;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateCurrencyTypeBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateCurrencyTypeBean.class);

	private CurrencyType currencyType;
	private CurrencyTypeDataSource currencyTypeDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateCurrencyTypeBean() {

		currencyType = new CurrencyType();
		/* init datasources */
		currencyTypeDataSource = new CurrencyTypeDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.currencyType = currencyTypeDataSource.get(editId);

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

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	/* getters for datasources */
	public CurrencyTypeDataSource getCurrencyTypeDataSource() {
		return currencyTypeDataSource;
	}

	public void setCurrencyTypeDataSource(
			CurrencyTypeDataSource currencyTypeDataSource) {
		this.currencyTypeDataSource = currencyTypeDataSource;
	}

	public CurrencyType saveCurrencyType() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			currencyTypeDataSource.create(currencyType, session);
			tx.commit();
			MessageService.info("Successfully Saved  CurrencyType !");
			this.currencyType = new CurrencyType();
			return currencyType;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving CurrencyType .Try Again Later!");
			return null;
		}
	}

	public CurrencyType updateCurrencyType() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			currencyTypeDataSource.create(currencyType, session);
			tx.commit();
			MessageService.info("Successfully Saved  CurrencyType !");
			this.currencyType = new CurrencyType();
			return currencyType;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving CurrencyType .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateCurrencyType();
		} else {
			log.info("Creating value");
			saveCurrencyType();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createCurrencyType");

	}

	public CurrencyType saveCurrencyType(Session session) {

		this.currencyType = currencyTypeDataSource.create(this.currencyType,
				session);
		return this.currencyType;
	}

}
