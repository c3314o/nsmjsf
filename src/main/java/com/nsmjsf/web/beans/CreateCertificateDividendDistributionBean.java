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
import com.nsmjsf.web.datasources.CertificateDividendDistributionDataSource;
import com.nsmjsf.web.datamodels.CertificateDividendDistribution;
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

import com.nsmjsf.web.adapters.IssueManagerAdapter;

import com.nsmjsf.web.datasources.IssueManagerDataSource;

import com.nsmjsf.web.datamodels.IssueManager;

import com.nsmjsf.web.wrappers.IssueManagerWrapper;

@ManagedBean
@ViewScoped
public class CreateCertificateDividendDistributionBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateCertificateDividendDistributionBean.class);

	private CertificateDividendDistribution certificateDividendDistribution;
	private CertificateDividendDistributionDataSource certificateDividendDistributionDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private IssueManagerDataSource issueManagerDataSource;
	private List<IssueManagerWrapper> issueManagerWrapperList;
	private List<IssueManager> issueManagerList;
	private IssueManagerWrapper selectedIssueManagerWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateCertificateDividendDistributionBean() {

		certificateDividendDistribution = new CertificateDividendDistribution();
		/* init datasources */
		certificateDividendDistributionDataSource = new CertificateDividendDistributionDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

		issueManagerDataSource = new IssueManagerDataSource();

		/* init option wrappers */
		issueManagerList = issueManagerDataSource.getAll();
		issueManagerWrapperList = IssueManagerAdapter.wrapAll(issueManagerList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.certificateDividendDistribution = certificateDividendDistributionDataSource
					.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter
					.wrap(certificateDividendDistribution.getFiscalYear());

			this.selectedAnnouncementWrapper = AnnouncementAdapter
					.wrap(certificateDividendDistribution.getAnnouncement());

			this.selectedIssueManagerWrapper = IssueManagerAdapter
					.wrap(certificateDividendDistribution.getIssueManager());

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

	public CertificateDividendDistribution getCertificateDividendDistribution() {
		return certificateDividendDistribution;
	}

	public void setCertificateDividendDistribution(
			CertificateDividendDistribution certificateDividendDistribution) {
		this.certificateDividendDistribution = certificateDividendDistribution;
	}

	/* getters for datasources */
	public CertificateDividendDistributionDataSource getCertificateDividendDistributionDataSource() {
		return certificateDividendDistributionDataSource;
	}

	public void setCertificateDividendDistributionDataSource(
			CertificateDividendDistributionDataSource certificateDividendDistributionDataSource) {
		this.certificateDividendDistributionDataSource = certificateDividendDistributionDataSource;
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

	public List<IssueManager> getIssueManagerList() {
		return issueManagerList;
	}

	public void setIssueManagerList(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}

	public IssueManagerDataSource getIssueManagerDataSource() {
		return issueManagerDataSource;
	}

	public void setIssueManagerDataSource(
			IssueManagerDataSource issueManagerDataSource) {
		this.issueManagerDataSource = issueManagerDataSource;
	}

	public List<IssueManagerWrapper> getIssueManagerWrapperList() {
		return issueManagerWrapperList;
	}

	public void setIssueManagerWrapperList(
			List<IssueManagerWrapper> issueManagerWrapperList) {
		this.issueManagerWrapperList = issueManagerWrapperList;
	}

	public IssueManagerWrapper getSelectedIssueManagerWrapper() {
		return selectedIssueManagerWrapper;
	}

	public void setSelectedIssueManagerWrapper(
			IssueManagerWrapper selectedIssueManagerWrapper) {
		this.selectedIssueManagerWrapper = selectedIssueManagerWrapper;
	}

	public CertificateDividendDistribution saveCertificateDividendDistribution() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			certificateDividendDistribution.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			certificateDividendDistribution.setAnnouncement(announcement);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			certificateDividendDistribution.setIssueManager(issueManager);

			certificateDividendDistributionDataSource.create(
					certificateDividendDistribution, session);
			tx.commit();
			MessageService
					.info("Successfully Saved  CertificateDividendDistribution !");
			this.certificateDividendDistribution = new CertificateDividendDistribution();
			return certificateDividendDistribution;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving CertificateDividendDistribution .Try Again Later!");
			return null;
		}
	}

	public CertificateDividendDistribution updateCertificateDividendDistribution() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			certificateDividendDistribution.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			certificateDividendDistribution.setAnnouncement(announcement);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			certificateDividendDistribution.setIssueManager(issueManager);

			certificateDividendDistributionDataSource.create(
					certificateDividendDistribution, session);
			tx.commit();
			MessageService
					.info("Successfully Saved  CertificateDividendDistribution !");
			this.certificateDividendDistribution = new CertificateDividendDistribution();
			return certificateDividendDistribution;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving CertificateDividendDistribution .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateCertificateDividendDistribution();
		} else {
			log.info("Creating value");
			saveCertificateDividendDistribution();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(
				"createCertificateDividendDistribution");

	}

	public CertificateDividendDistribution saveCertificateDividendDistribution(
			Session session) {

		this.certificateDividendDistribution = certificateDividendDistributionDataSource
				.create(this.certificateDividendDistribution, session);
		return this.certificateDividendDistribution;
	}

}
