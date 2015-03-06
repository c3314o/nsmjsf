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
import com.nsmjsf.web.datasources.FloorsheetDataSource;
import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateFloorsheetBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateFloorsheetBean.class);

	private Floorsheet floorsheet;
	private FloorsheetDataSource floorsheetDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateFloorsheetBean() {

		floorsheet = new Floorsheet();
		/* init datasources */
		floorsheetDataSource = new FloorsheetDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.floorsheet = floorsheetDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter.wrap(floorsheet
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

	public Floorsheet getFloorsheet() {
		return floorsheet;
	}

	public void setFloorsheet(Floorsheet floorsheet) {
		this.floorsheet = floorsheet;
	}

	/* getters for datasources */
	public FloorsheetDataSource getFloorsheetDataSource() {
		return floorsheetDataSource;
	}

	public void setFloorsheetDataSource(
			FloorsheetDataSource floorsheetDataSource) {
		this.floorsheetDataSource = floorsheetDataSource;
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

	public Floorsheet saveFloorsheet() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			floorsheet.setCompany(company);

			floorsheetDataSource.create(floorsheet, session);
			tx.commit();
			MessageService.info("Successfully Saved  Floorsheet !");
			this.floorsheet = new Floorsheet();
			return floorsheet;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Floorsheet .Try Again Later!");
			return null;
		}
	}

	public Floorsheet updateFloorsheet() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			floorsheet.setCompany(company);

			floorsheetDataSource.create(floorsheet, session);
			tx.commit();
			MessageService.info("Successfully Saved  Floorsheet !");
			this.floorsheet = new Floorsheet();
			return floorsheet;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Floorsheet .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateFloorsheet();
		} else {
			log.info("Creating value");
			saveFloorsheet();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createFloorsheet");

	}

	public Floorsheet saveFloorsheet(Session session) {

		this.floorsheet = floorsheetDataSource.create(this.floorsheet, session);
		return this.floorsheet;
	}

}
