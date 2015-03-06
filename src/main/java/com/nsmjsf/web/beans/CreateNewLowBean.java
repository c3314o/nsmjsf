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
import com.nsmjsf.web.datasources.NewLowDataSource;
import com.nsmjsf.web.datamodels.NewLow;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateNewLowBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateNewLowBean.class);

	private NewLow newLow;
	private NewLowDataSource newLowDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateNewLowBean() {

		newLow = new NewLow();
		/* init datasources */
		newLowDataSource = new NewLowDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.newLow = newLowDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter.wrap(newLow
					.getCompany());

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

	public NewLow getNewLow() {
		return newLow;
	}

	public void setNewLow(NewLow newLow) {
		this.newLow = newLow;
	}

	/* getters for datasources */
	public NewLowDataSource getNewLowDataSource() {
		return newLowDataSource;
	}

	public void setNewLowDataSource(NewLowDataSource newLowDataSource) {
		this.newLowDataSource = newLowDataSource;
	}

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public CompanyDataSource getCompanyDataSource() {
		return companyDataSource;
	}

	public void setCompanyDataSource(CompanyDataSource companyDataSource) {
		this.companyDataSource = companyDataSource;
	}

	public List<CompanyWrapper> getCompanyWrapperList() {
		return companyWrapperList;
	}

	public void setCompanyWrapperList(List<CompanyWrapper> companyWrapperList) {
		this.companyWrapperList = companyWrapperList;
	}

	public CompanyWrapper getSelectedCompanyWrapper() {
		return selectedCompanyWrapper;
	}

	public void setSelectedCompanyWrapper(CompanyWrapper selectedCompanyWrapper) {
		this.selectedCompanyWrapper = selectedCompanyWrapper;
	}

	public NewLow saveNewLow() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			newLow.setCompany(company);

			newLowDataSource.create(newLow, session);
			tx.commit();
			MessageService.info("Successfully Saved  NewLow !");
			this.newLow = new NewLow();
			return newLow;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving NewLow .Try Again Later!");
			return null;
		}
	}

	public NewLow updateNewLow() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			newLow.setCompany(company);

			newLowDataSource.create(newLow, session);
			tx.commit();
			MessageService.info("Successfully Saved  NewLow !");
			this.newLow = new NewLow();
			return newLow;

		} catch (Exception ex) {
			MessageService.error("Failed Saving NewLow .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateNewLow();
		} else {
			log.info("Creating value");
			saveNewLow();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createNewLow");

	}

	public NewLow saveNewLow(Session session) {

		this.newLow = newLowDataSource.create(this.newLow, session);
		return this.newLow;
	}

}
