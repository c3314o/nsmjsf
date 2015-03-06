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
import com.nsmjsf.web.datasources.BasePriceDataSource;
import com.nsmjsf.web.datamodels.BasePrice;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class CreateBasePriceBean implements Serializable {

	private static final Log log = LogFactory.getLog(CreateBasePriceBean.class);

	private BasePrice basePrice;
	private BasePriceDataSource basePriceDataSource;

	private CompanyDataSource companyDataSource;
	private List<CompanyWrapper> companyWrapperList;
	private List<Company> companyList;
	private CompanyWrapper selectedCompanyWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateBasePriceBean() {

		basePrice = new BasePrice();
		/* init datasources */
		basePriceDataSource = new BasePriceDataSource();

		companyDataSource = new CompanyDataSource();

		/* init option wrappers */
		companyList = companyDataSource.getAll();
		companyWrapperList = CompanyAdapter.wrapAll(companyList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.basePrice = basePriceDataSource.get(editId);

			this.selectedCompanyWrapper = CompanyAdapter.wrap(basePrice
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

	public BasePrice getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BasePrice basePrice) {
		this.basePrice = basePrice;
	}

	/* getters for datasources */
	public BasePriceDataSource getBasePriceDataSource() {
		return basePriceDataSource;
	}

	public void setBasePriceDataSource(BasePriceDataSource basePriceDataSource) {
		this.basePriceDataSource = basePriceDataSource;
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

	public BasePrice saveBasePrice() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			basePrice.setCompany(company);

			basePriceDataSource.create(basePrice, session);
			tx.commit();
			MessageService.info("Successfully Saved  BasePrice !");
			this.basePrice = new BasePrice();
			return basePrice;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving BasePrice .Try Again Later!");
			return null;
		}
	}

	public BasePrice updateBasePrice() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Company company = selectedCompanyWrapper.getCompany();

			basePrice.setCompany(company);

			basePriceDataSource.create(basePrice, session);
			tx.commit();
			MessageService.info("Successfully Saved  BasePrice !");
			this.basePrice = new BasePrice();
			return basePrice;

		} catch (Exception ex) {
			MessageService.error("Failed Saving BasePrice .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateBasePrice();
		} else {
			log.info("Creating value");
			saveBasePrice();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createBasePrice");

	}

	public BasePrice saveBasePrice(Session session) {

		this.basePrice = basePriceDataSource.create(this.basePrice, session);
		return this.basePrice;
	}

}
