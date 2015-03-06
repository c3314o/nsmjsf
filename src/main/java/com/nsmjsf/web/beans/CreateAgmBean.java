package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;

import org.primefaces.context.RequestContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datasources.AgmDataSource;
import com.nsmjsf.web.datamodels.Agm;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.FiscalYearAdapter;
import com.nsmjsf.web.datasources.FiscalYearDataSource;
import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.wrappers.FiscalYearWrapper;
import com.nsmjsf.web.adapters.AnnouncementAdapter;
import com.nsmjsf.web.datasources.AnnouncementDataSource;
import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.wrappers.AnnouncementWrapper;
import com.nsmjsf.web.adapters.CompanyAdapter;
import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateAgmBean implements Serializable {
	
	
	private static final Log log = LogFactory.getLog(CreateAgmBean.class);

	private Agm agm;
	private AgmDataSource agmDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateAgmBean() {

		agm = new Agm();
		/* init datasources */
		agmDataSource = new AgmDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.agm = agmDataSource.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter.wrap(agm
					.getFiscalYear());

			this.selectedAnnouncementWrapper = AnnouncementAdapter.wrap(agm
					.getAnnouncement());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(agm.getCompany());

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

	public Agm getAgm() {
		return agm;
	}

	public void setAgm(Agm agm) {
		this.agm = agm;
	}

	/* getters for datasources */
	public AgmDataSource getAgmDataSource() {
		return agmDataSource;
	}

	public void setAgmDataSource(AgmDataSource agmDataSource) {
		this.agmDataSource = agmDataSource;
	}

	public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	public FiscalYearDataSource getFiscalYearDataSource() {
		return fiscalYearDataSource;
	}

	public void setFiscalYearDataSource(
			FiscalYearDataSource fiscalYearDataSource) {
		this.fiscalYearDataSource = fiscalYearDataSource;
	}

	public List<FiscalYearWrapper> getFiscalYearWrapperList() {
		return fiscalYearWrapperList;
	}

	public void setFiscalYearWrapperList(
			List<FiscalYearWrapper> fiscalYearWrapperList) {
		this.fiscalYearWrapperList = fiscalYearWrapperList;
	}

	public FiscalYearWrapper getSelectedFiscalYearWrapper() {
		return selectedFiscalYearWrapper;
	}

	public void setSelectedFiscalYearWrapper(
			FiscalYearWrapper selectedFiscalYearWrapper) {
		this.selectedFiscalYearWrapper = selectedFiscalYearWrapper;
	}

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	public AnnouncementDataSource getAnnouncementDataSource() {
		return announcementDataSource;
	}

	public void setAnnouncementDataSource(
			AnnouncementDataSource announcementDataSource) {
		this.announcementDataSource = announcementDataSource;
	}

	public List<AnnouncementWrapper> getAnnouncementWrapperList() {
		return announcementWrapperList;
	}

	public void setAnnouncementWrapperList(
			List<AnnouncementWrapper> announcementWrapperList) {
		this.announcementWrapperList = announcementWrapperList;
	}

	public AnnouncementWrapper getSelectedAnnouncementWrapper() {
		return selectedAnnouncementWrapper;
	}

	public void setSelectedAnnouncementWrapper(
			AnnouncementWrapper selectedAnnouncementWrapper) {
		this.selectedAnnouncementWrapper = selectedAnnouncementWrapper;
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

	public Agm saveAgm() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
			
			
			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			agm.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			agm.setAnnouncement(announcement);

			Company company = selectedCompanyWrapper.getCompany();

			agm.setCompany(company);

			agmDataSource.create(agm, session);
			tx.commit();
			MessageService.info("Successfully Saved  Agm !");
			this.agm = new Agm();
			return agm;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Agm .Try Again Later!");
			return null;
		}
	}

	public Agm updateAgm() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			agm.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			agm.setAnnouncement(announcement);

			Company company = selectedCompanyWrapper.getCompany();

			agm.setCompany(company);

			agmDataSource.create(agm, session);
			tx.commit();
			MessageService.info("Successfully Saved  Agm !");
			this.agm = new Agm();
			return agm;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Agm .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateAgm();
		} else {
			log.info("Creating value");
			saveAgm();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createAgm");

	}

	public Agm saveAgm(Session session) {

		this.agm = agmDataSource.create(this.agm, session);
		return this.agm;
	}

}
