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
import com.nsmjsf.web.datasources.IssueDataSource;
import com.nsmjsf.web.datamodels.Issue;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.AnnouncementAdapter;

import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;

import com.nsmjsf.web.adapters.IssueTypeAdapter;

import com.nsmjsf.web.datasources.IssueTypeDataSource;

import com.nsmjsf.web.datamodels.IssueType;

import com.nsmjsf.web.wrappers.IssueTypeWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.IssueManagerAdapter;

import com.nsmjsf.web.datasources.IssueManagerDataSource;

import com.nsmjsf.web.datamodels.IssueManager;

import com.nsmjsf.web.wrappers.IssueManagerWrapper;

@ManagedBean
@ViewScoped
public class CreateIssueBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateIssueBean.class);

	private Issue issue;
	private IssueDataSource issueDataSource;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private IssueTypeDataSource issueTypeDataSource;
	private List<IssueTypeWrapper> issueTypeWrapperList;
	private List<IssueType> issueTypeList;
	private IssueTypeWrapper selectedIssueTypeWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private IssueManagerDataSource issueManagerDataSource;
	private List<IssueManagerWrapper> issueManagerWrapperList;
	private List<IssueManager> issueManagerList;
	private IssueManagerWrapper selectedIssueManagerWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateIssueBean() {

		issue = new Issue();
		/* init datasources */
		issueDataSource = new IssueDataSource();

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

		issueTypeDataSource = new IssueTypeDataSource();

		/* init option wrappers */
		issueTypeList = issueTypeDataSource.getAll();
		issueTypeWrapperList = IssueTypeAdapter.wrapAll(issueTypeList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

		issueManagerDataSource = new IssueManagerDataSource();

		/* init option wrappers */
		issueManagerList = issueManagerDataSource.getAll();
		issueManagerWrapperList = IssueManagerAdapter.wrapAll(issueManagerList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.issue = issueDataSource.get(editId);

			this.selectedAnnouncementWrapper = AnnouncementAdapter.wrap(issue
					.getAnnouncement());

			this.selectedIssueTypeWrapper = IssueTypeAdapter.wrap(issue
					.getIssueType());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(issue
					.getCompany());

			this.selectedIssueManagerWrapper = IssueManagerAdapter.wrap(issue
					.getIssueManager());

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

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	/* getters for datasources */
	public IssueDataSource getIssueDataSource() {
		return issueDataSource;
	}

	public void setIssueDataSource(IssueDataSource issueDataSource) {
		this.issueDataSource = issueDataSource;
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

	public List<IssueType> getIssueTypeList() {
		return issueTypeList;
	}

	public void setIssueTypeList(List<IssueType> issueTypeList) {
		this.issueTypeList = issueTypeList;
	}

	public IssueTypeDataSource getIssueTypeDataSource() {
		return issueTypeDataSource;
	}

	public void setIssueTypeDataSource(IssueTypeDataSource issueTypeDataSource) {
		this.issueTypeDataSource = issueTypeDataSource;
	}

	public List<IssueTypeWrapper> getIssueTypeWrapperList() {
		return issueTypeWrapperList;
	}

	public void setIssueTypeWrapperList(
			List<IssueTypeWrapper> issueTypeWrapperList) {
		this.issueTypeWrapperList = issueTypeWrapperList;
	}

	public IssueTypeWrapper getSelectedIssueTypeWrapper() {
		return selectedIssueTypeWrapper;
	}

	public void setSelectedIssueTypeWrapper(
			IssueTypeWrapper selectedIssueTypeWrapper) {
		this.selectedIssueTypeWrapper = selectedIssueTypeWrapper;
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

	public Issue saveIssue() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			issue.setAnnouncement(announcement);

			IssueType issueType = selectedIssueTypeWrapper.getIssueType();

			issue.setIssueType(issueType);

			Company company = selectedCompanyWrapper.getCompany();

			issue.setCompany(company);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			issue.setIssueManager(issueManager);

			issueDataSource.create(issue, session);
			tx.commit();
			MessageService.info("Successfully Saved  Issue !");
			this.issue = new Issue();
			return issue;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Issue .Try Again Later!");
			return null;
		}
	}

	public Issue updateIssue() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			issue.setAnnouncement(announcement);

			IssueType issueType = selectedIssueTypeWrapper.getIssueType();

			issue.setIssueType(issueType);

			Company company = selectedCompanyWrapper.getCompany();

			issue.setCompany(company);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			issue.setIssueManager(issueManager);

			issueDataSource.create(issue, session);
			tx.commit();
			MessageService.info("Successfully Saved  Issue !");
			this.issue = new Issue();
			return issue;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Issue .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateIssue();
		} else {
			log.info("Creating value");
			saveIssue();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createIssue");

	}

	public Issue saveIssue(Session session) {

		this.issue = issueDataSource.create(this.issue, session);
		return this.issue;
	}

}
