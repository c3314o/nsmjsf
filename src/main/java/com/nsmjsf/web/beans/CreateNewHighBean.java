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
import com.nsmjsf.web.datasources.NewHighDataSource;
import com.nsmjsf.web.datamodels.NewHigh;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateNewHighBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateNewHighBean.class);

	private NewHigh newHigh;
	private NewHighDataSource newHighDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateNewHighBean() {

		newHigh = new NewHigh();
		/* init datasources */
		newHighDataSource = new NewHighDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.newHigh = newHighDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter.wrap(newHigh
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

	public NewHigh getNewHigh() {
		return newHigh;
	}

	public void setNewHigh(NewHigh newHigh) {
		this.newHigh = newHigh;
	}

	/* getters for datasources */
	public NewHighDataSource getNewHighDataSource() {
		return newHighDataSource;
	}

	public void setNewHighDataSource(NewHighDataSource newHighDataSource) {
		this.newHighDataSource = newHighDataSource;
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

	public NewHigh saveNewHigh() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			newHigh.setCompany(company);

			newHighDataSource.create(newHigh, session);
			tx.commit();
			MessageService.info("Successfully Saved  NewHigh !");
			this.newHigh = new NewHigh();
			return newHigh;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving NewHigh .Try Again Later!");
			return null;
		}
	}

	public NewHigh updateNewHigh() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			newHigh.setCompany(company);

			newHighDataSource.create(newHigh, session);
			tx.commit();
			MessageService.info("Successfully Saved  NewHigh !");
			this.newHigh = new NewHigh();
			return newHigh;

		} catch (Exception ex) {
			MessageService.error("Failed Saving NewHigh .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateNewHigh();
		} else {
			log.info("Creating value");
			saveNewHigh();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createNewHigh");

	}

	public NewHigh saveNewHigh(Session session) {

		this.newHigh = newHighDataSource.create(this.newHigh, session);
		return this.newHigh;
	}

}
