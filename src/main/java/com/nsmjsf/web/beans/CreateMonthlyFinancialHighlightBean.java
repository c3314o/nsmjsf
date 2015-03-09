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

import com.nsmjsf.web.datasources.MonthlyFinancialHighlightDataSource;
import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;
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
import com.nsmjsf.web.adapters.MonthAdapter;
import com.nsmjsf.web.datasources.MonthDataSource;
import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.wrappers.MonthWrapper;

@ManagedBean
@ViewScoped
public class CreateMonthlyFinancialHighlightBean implements Serializable {

	@ManagedProperty(value = "#{createAnnouncementBean}")
	private CreateAnnouncementBean createAnnouncementBean;

	private static final Log log = LogFactory
			.getLog(CreateMonthlyFinancialHighlightBean.class);

	private MonthlyFinancialHighlight monthlyFinancialHighlight;
	private MonthlyFinancialHighlightDataSource monthlyFinancialHighlightDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private MonthDataSource monthDataSource;
	private List<MonthWrapper> monthWrapperList;
	private List<Month> monthList;
	private MonthWrapper selectedMonthWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMonthlyFinancialHighlightBean() {

		monthlyFinancialHighlight = new MonthlyFinancialHighlight();
		/* init datasources */
		monthlyFinancialHighlightDataSource = new MonthlyFinancialHighlightDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

		monthDataSource = new MonthDataSource();

		/* init option wrappers */
		monthList = monthDataSource.getAll();
		monthWrapperList = MonthAdapter.wrapAll(monthList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.monthlyFinancialHighlight = monthlyFinancialHighlightDataSource
					.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter
					.wrap(monthlyFinancialHighlight.getFiscalYear());

			this.selectedAnnouncementWrapper = AnnouncementAdapter
					.wrap(monthlyFinancialHighlight.getAnnouncement());

			this.selectedMonthWrapper = MonthAdapter
					.wrap(monthlyFinancialHighlight.getMonth());

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

	public MonthlyFinancialHighlight getMonthlyFinancialHighlight() {
		return monthlyFinancialHighlight;
	}

	public void setMonthlyFinancialHighlight(
			MonthlyFinancialHighlight monthlyFinancialHighlight) {
		this.monthlyFinancialHighlight = monthlyFinancialHighlight;
	}

	/* getters for datasources */
	public MonthlyFinancialHighlightDataSource getMonthlyFinancialHighlightDataSource() {
		return monthlyFinancialHighlightDataSource;
	}

	public void setMonthlyFinancialHighlightDataSource(
			MonthlyFinancialHighlightDataSource monthlyFinancialHighlightDataSource) {
		this.monthlyFinancialHighlightDataSource = monthlyFinancialHighlightDataSource;
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

	public List<Month> getMonthList() {
		return monthList;
	}

	public void setMonthList(List<Month> monthList) {
		this.monthList = monthList;
	}

	public MonthDataSource getMonthDataSource() {
		return monthDataSource;
	}

	public void setMonthDataSource(MonthDataSource monthDataSource) {
		this.monthDataSource = monthDataSource;
	}

	public List<MonthWrapper> getMonthWrapperList() {
		return monthWrapperList;
	}

	public void setMonthWrapperList(List<MonthWrapper> monthWrapperList) {
		this.monthWrapperList = monthWrapperList;
	}

	public MonthWrapper getSelectedMonthWrapper() {
		return selectedMonthWrapper;
	}

	public void setSelectedMonthWrapper(MonthWrapper selectedMonthWrapper) {
		this.selectedMonthWrapper = selectedMonthWrapper;
	}

	public MonthlyFinancialHighlight saveMonthlyFinancialHighlight() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			monthlyFinancialHighlight.setFiscalYear(fiscalYear);

			Announcement announcement = createAnnouncementBean.saveAnnouncement(session);

			monthlyFinancialHighlight.setAnnouncement(announcement);

			Month month = selectedMonthWrapper.getMonth();

			monthlyFinancialHighlight.setMonth(month);

			monthlyFinancialHighlightDataSource.create(
					monthlyFinancialHighlight, session);
			tx.commit();
			MessageService
					.info("Successfully Saved  MonthlyFinancialHighlight !");
			this.monthlyFinancialHighlight = new MonthlyFinancialHighlight();
			return monthlyFinancialHighlight;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving MonthlyFinancialHighlight .Try Again Later!");
			return null;
		}
	}

	public MonthlyFinancialHighlight updateMonthlyFinancialHighlight() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			monthlyFinancialHighlight.setFiscalYear(fiscalYear);

			Announcement announcement = createAnnouncementBean.saveAnnouncement(session);

			monthlyFinancialHighlight.setAnnouncement(announcement);

			Month month = selectedMonthWrapper.getMonth();

			monthlyFinancialHighlight.setMonth(month);

			monthlyFinancialHighlightDataSource.create(
					monthlyFinancialHighlight, session);
			tx.commit();
			MessageService
					.info("Successfully Saved  MonthlyFinancialHighlight !");
			this.monthlyFinancialHighlight = new MonthlyFinancialHighlight();
			return monthlyFinancialHighlight;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving MonthlyFinancialHighlight .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMonthlyFinancialHighlight();
		} else {
			log.info("Creating value");
			saveMonthlyFinancialHighlight();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(
				"createMonthlyFinancialHighlight");

	}

	public MonthlyFinancialHighlight saveMonthlyFinancialHighlight(
			Session session) {

		this.monthlyFinancialHighlight = monthlyFinancialHighlightDataSource
				.create(this.monthlyFinancialHighlight, session);
		return this.monthlyFinancialHighlight;
	}

	public CreateAnnouncementBean getCreateAnnouncementBean() {
		return createAnnouncementBean;
	}

	public void setCreateAnnouncementBean(
			CreateAnnouncementBean createAnnouncementBean) {
		this.createAnnouncementBean = createAnnouncementBean;
	}
	
}
