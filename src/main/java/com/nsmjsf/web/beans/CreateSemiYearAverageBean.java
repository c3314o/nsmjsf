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
import com.nsmjsf.web.datasources.SemiYearAverageDataSource;
import com.nsmjsf.web.datamodels.SemiYearAverage;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateSemiYearAverageBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateSemiYearAverageBean.class);

	private SemiYearAverage semiYearAverage;
	private SemiYearAverageDataSource semiYearAverageDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateSemiYearAverageBean() {

		semiYearAverage = new SemiYearAverage();
		/* init datasources */
		semiYearAverageDataSource = new SemiYearAverageDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.semiYearAverage = semiYearAverageDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter.wrap(semiYearAverage
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

	public SemiYearAverage getSemiYearAverage() {
		return semiYearAverage;
	}

	public void setSemiYearAverage(SemiYearAverage semiYearAverage) {
		this.semiYearAverage = semiYearAverage;
	}

	/* getters for datasources */
	public SemiYearAverageDataSource getSemiYearAverageDataSource() {
		return semiYearAverageDataSource;
	}

	public void setSemiYearAverageDataSource(
			SemiYearAverageDataSource semiYearAverageDataSource) {
		this.semiYearAverageDataSource = semiYearAverageDataSource;
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

	public SemiYearAverage saveSemiYearAverage() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			semiYearAverage.setCompany(company);

			semiYearAverageDataSource.create(semiYearAverage, session);
			tx.commit();
			MessageService.info("Successfully Saved  SemiYearAverage !");
			this.semiYearAverage = new SemiYearAverage();
			return semiYearAverage;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving SemiYearAverage .Try Again Later!");
			return null;
		}
	}

	public SemiYearAverage updateSemiYearAverage() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			semiYearAverage.setCompany(company);

			semiYearAverageDataSource.create(semiYearAverage, session);
			tx.commit();
			MessageService.info("Successfully Saved  SemiYearAverage !");
			this.semiYearAverage = new SemiYearAverage();
			return semiYearAverage;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving SemiYearAverage .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateSemiYearAverage();
		} else {
			log.info("Creating value");
			saveSemiYearAverage();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createSemiYearAverage");

	}

	public SemiYearAverage saveSemiYearAverage(Session session) {

		this.semiYearAverage = semiYearAverageDataSource.create(
				this.semiYearAverage, session);
		return this.semiYearAverage;
	}

}
