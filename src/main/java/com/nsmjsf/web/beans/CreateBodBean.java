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
import com.nsmjsf.web.datasources.BodDataSource;
import com.nsmjsf.web.datamodels.Bod;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.FiscalYearAdapter;

import com.nsmjsf.web.datasources.FiscalYearDataSource;

import com.nsmjsf.web.datamodels.FiscalYear;

import com.nsmjsf.web.wrappers.FiscalYearWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.BodTypeAdapter;

import com.nsmjsf.web.datasources.BodTypeDataSource;

import com.nsmjsf.web.datamodels.BodType;

import com.nsmjsf.web.wrappers.BodTypeWrapper;

@ManagedBean
@ViewScoped
public class CreateBodBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateBodBean.class);

	private Bod bod;
	private BodDataSource bodDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private BodTypeDataSource bodTypeDataSource;
	private List<BodTypeWrapper> bodTypeWrapperList;
	private List<BodType> bodTypeList;
	private BodTypeWrapper selectedBodTypeWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateBodBean() {

		bod = new Bod();
		/* init datasources */
		bodDataSource = new BodDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

		bodTypeDataSource = new BodTypeDataSource();

		/* init option wrappers */
		bodTypeList = bodTypeDataSource.getAll();
		bodTypeWrapperList = BodTypeAdapter.wrapAll(bodTypeList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.bod = bodDataSource.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter.wrap(bod
					.getFiscalYear());

			this.selectedCompanyWrapper = CompanyAdapter.wrap(bod.getCompany());

			this.selectedBodTypeWrapper = BodTypeAdapter.wrap(bod.getBodType());

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

	public Bod getBod() {
		return bod;
	}

	public void setBod(Bod bod) {
		this.bod = bod;
	}

	/* getters for datasources */
	public BodDataSource getBodDataSource() {
		return bodDataSource;
	}

	public void setBodDataSource(BodDataSource bodDataSource) {
		this.bodDataSource = bodDataSource;
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

	public List<BodType> getBodTypeList() {
		return bodTypeList;
	}

	public void setBodTypeList(List<BodType> bodTypeList) {
		this.bodTypeList = bodTypeList;
	}

	public BodTypeDataSource getBodTypeDataSource() {
		return bodTypeDataSource;
	}

	public void setBodTypeDataSource(BodTypeDataSource bodTypeDataSource) {
		this.bodTypeDataSource = bodTypeDataSource;
	}

	public List<BodTypeWrapper> getBodTypeWrapperList() {
		return bodTypeWrapperList;
	}

	public void setBodTypeWrapperList(List<BodTypeWrapper> bodTypeWrapperList) {
		this.bodTypeWrapperList = bodTypeWrapperList;
	}

	public BodTypeWrapper getSelectedBodTypeWrapper() {
		return selectedBodTypeWrapper;
	}

	public void setSelectedBodTypeWrapper(BodTypeWrapper selectedBodTypeWrapper) {
		this.selectedBodTypeWrapper = selectedBodTypeWrapper;
	}

	public Bod saveBod() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			bod.setFiscalYear(fiscalYear);

			Company company = selectedCompanyWrapper.getCompany();

			bod.setCompany(company);

			BodType bodType = selectedBodTypeWrapper.getBodType();

			bod.setBodType(bodType);

			bodDataSource.create(bod, session);
			tx.commit();
			MessageService.info("Successfully Saved  Bod !");
			this.bod = new Bod();
			return bod;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Bod .Try Again Later!");
			return null;
		}
	}

	public Bod updateBod() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			bod.setFiscalYear(fiscalYear);

			Company company = selectedCompanyWrapper.getCompany();

			bod.setCompany(company);

			BodType bodType = selectedBodTypeWrapper.getBodType();

			bod.setBodType(bodType);

			bodDataSource.create(bod, session);
			tx.commit();
			MessageService.info("Successfully Saved  Bod !");
			this.bod = new Bod();
			return bod;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Bod .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateBod();
		} else {
			log.info("Creating value");
			saveBod();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createBod");

	}

	public Bod saveBod(Session session) {

		this.bod = bodDataSource.create(this.bod, session);
		return this.bod;
	}

}
