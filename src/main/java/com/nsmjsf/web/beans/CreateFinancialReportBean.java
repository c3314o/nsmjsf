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

import com.nsmjsf.web.datasources.FinancialReportDataSource;
import com.nsmjsf.web.datamodels.FinancialReport;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.FiscalYearAdapter;
import com.nsmjsf.web.datasources.FiscalYearDataSource;
import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.wrappers.FiscalYearWrapper;
import com.nsmjsf.web.adapters.AuditStatusAdapter;
import com.nsmjsf.web.datasources.AuditStatusDataSource;
import com.nsmjsf.web.datamodels.AuditStatus;
import com.nsmjsf.web.wrappers.AuditStatusWrapper;
import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;
import com.nsmjsf.web.adapters.CompanyAdapter;
import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.wrappers.CompanyWrapper;
import com.nsmjsf.web.adapters.QuarterAdapter;
import com.nsmjsf.web.datasources.QuarterDataSource;
import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.wrappers.QuarterWrapper;

@ManagedBean
@ViewScoped
public class CreateFinancialReportBean implements Serializable {

	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;
	
	private static final Log log = LogFactory
			.getLog(CreateFinancialReportBean.class);

	private FinancialReport financialReport;
	private FinancialReportDataSource financialReportDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private AuditStatusDataSource auditStatusDataSource;
	private List<AuditStatusWrapper> auditStatusWrapperList;
	private List<AuditStatus> auditStatusList;
	private AuditStatusWrapper selectedAuditStatusWrapper;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private QuarterDataSource quarterDataSource;
	private List<QuarterWrapper> quarterWrapperList;
	private List<Quarter> quarterList;
	private QuarterWrapper selectedQuarterWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateFinancialReportBean() {

		financialReport = new FinancialReport();
		/* init datasources */
		financialReportDataSource = new FinancialReportDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		auditStatusDataSource = new AuditStatusDataSource();

		/* init option wrappers */
		auditStatusList = auditStatusDataSource.getAll();
		auditStatusWrapperList = AuditStatusAdapter.wrapAll(auditStatusList);

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

		quarterDataSource = new QuarterDataSource();

		/* init option wrappers */
		quarterList = quarterDataSource.getAll();
		quarterWrapperList = QuarterAdapter.wrapAll(quarterList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.financialReport = financialReportDataSource.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter
					.wrap(financialReport.getFiscalYear());

			this.selectedAuditStatusWrapper = AuditStatusAdapter
					.wrap(financialReport.getAuditStatus());

			this.selectedPostWrapper = PostAdapter.wrap(financialReport
					.getPost());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(financialReport
					.getCompany());

			this.selectedQuarterWrapper = QuarterAdapter.wrap(financialReport
					.getQuarter());

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

	public FinancialReport getFinancialReport() {
		return financialReport;
	}

	public void setFinancialReport(FinancialReport financialReport) {
		this.financialReport = financialReport;
	}

	/* getters for datasources */
	public FinancialReportDataSource getFinancialReportDataSource() {
		return financialReportDataSource;
	}

	public void setFinancialReportDataSource(
			FinancialReportDataSource financialReportDataSource) {
		this.financialReportDataSource = financialReportDataSource;
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

	public List<AuditStatus> getAuditStatusList() {
		return auditStatusList;
	}

	public void setAuditStatusList(List<AuditStatus> auditStatusList) {
		this.auditStatusList = auditStatusList;
	}

	public AuditStatusDataSource getAuditStatusDataSource() {
		return auditStatusDataSource;
	}

	public void setAuditStatusDataSource(
			AuditStatusDataSource auditStatusDataSource) {
		this.auditStatusDataSource = auditStatusDataSource;
	}

	public List<AuditStatusWrapper> getAuditStatusWrapperList() {
		return auditStatusWrapperList;
	}

	public void setAuditStatusWrapperList(
			List<AuditStatusWrapper> auditStatusWrapperList) {
		this.auditStatusWrapperList = auditStatusWrapperList;
	}

	public AuditStatusWrapper getSelectedAuditStatusWrapper() {
		return selectedAuditStatusWrapper;
	}

	public void setSelectedAuditStatusWrapper(
			AuditStatusWrapper selectedAuditStatusWrapper) {
		this.selectedAuditStatusWrapper = selectedAuditStatusWrapper;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public PostDataSource getPostDataSource() {
		return postDataSource;
	}

	public void setPostDataSource(PostDataSource postDataSource) {
		this.postDataSource = postDataSource;
	}

	public List<PostWrapper> getPostWrapperList() {
		return postWrapperList;
	}

	public void setPostWrapperList(List<PostWrapper> postWrapperList) {
		this.postWrapperList = postWrapperList;
	}

	public PostWrapper getSelectedPostWrapper() {
		return selectedPostWrapper;
	}

	public void setSelectedPostWrapper(PostWrapper selectedPostWrapper) {
		this.selectedPostWrapper = selectedPostWrapper;
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

	public List<Quarter> getQuarterList() {
		return quarterList;
	}

	public void setQuarterList(List<Quarter> quarterList) {
		this.quarterList = quarterList;
	}

	public QuarterDataSource getQuarterDataSource() {
		return quarterDataSource;
	}

	public void setQuarterDataSource(QuarterDataSource quarterDataSource) {
		this.quarterDataSource = quarterDataSource;
	}

	public List<QuarterWrapper> getQuarterWrapperList() {
		return quarterWrapperList;
	}

	public void setQuarterWrapperList(List<QuarterWrapper> quarterWrapperList) {
		this.quarterWrapperList = quarterWrapperList;
	}

	public QuarterWrapper getSelectedQuarterWrapper() {
		return selectedQuarterWrapper;
	}

	public void setSelectedQuarterWrapper(QuarterWrapper selectedQuarterWrapper) {
		this.selectedQuarterWrapper = selectedQuarterWrapper;
	}

	public FinancialReport saveFinancialReport() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			financialReport.setFiscalYear(fiscalYear);

			AuditStatus auditStatus = selectedAuditStatusWrapper
					.getAuditStatus();

			financialReport.setAuditStatus(auditStatus);

			Post post = createPostBean.savePost(session);

			financialReport.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			financialReport.setCompany(company);

			Quarter quarter = selectedQuarterWrapper.getQuarter();

			financialReport.setQuarter(quarter);

			financialReportDataSource.create(financialReport, session);
			tx.commit();
			MessageService.info("Successfully Saved  FinancialReport !");
			this.financialReport = new FinancialReport();
			return financialReport;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving FinancialReport .Try Again Later!");
			return null;
		}
	}

	public FinancialReport updateFinancialReport() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			financialReport.setFiscalYear(fiscalYear);

			AuditStatus auditStatus = selectedAuditStatusWrapper
					.getAuditStatus();

			financialReport.setAuditStatus(auditStatus);

			Post post = createPostBean.savePost(session);

			financialReport.setPost(post);

			Company company = selectedCompanyWrapper.getCompany();

			financialReport.setCompany(company);

			Quarter quarter = selectedQuarterWrapper.getQuarter();

			financialReport.setQuarter(quarter);

			financialReportDataSource.create(financialReport, session);
			tx.commit();
			MessageService.info("Successfully Saved  FinancialReport !");
			this.financialReport = new FinancialReport();
			return financialReport;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving FinancialReport .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateFinancialReport();
		} else {
			log.info("Creating value");
			saveFinancialReport();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createFinancialReport");

	}

	public FinancialReport saveFinancialReport(Session session) {

		this.financialReport = financialReportDataSource.create(
				this.financialReport, session);
		return this.financialReport;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
