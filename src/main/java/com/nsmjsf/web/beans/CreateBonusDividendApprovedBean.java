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
import com.nsmjsf.web.datasources.BonusDividendApprovedDataSource;
import com.nsmjsf.web.datamodels.BonusDividendApproved;
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

@ManagedBean
@ViewScoped
public class CreateBonusDividendApprovedBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateBonusDividendApprovedBean.class);

	private BonusDividendApproved bonusDividendApproved;
	private BonusDividendApprovedDataSource bonusDividendApprovedDataSource;

	private FiscalYearDataSource fiscalYearDataSource;
	private List<FiscalYearWrapper> fiscalYearWrapperList;
	private List<FiscalYear> fiscalYearList;
	private FiscalYearWrapper selectedFiscalYearWrapper;

	private AnnouncementDataSource announcementDataSource;
	private List<AnnouncementWrapper> announcementWrapperList;
	private List<Announcement> announcementList;
	private AnnouncementWrapper selectedAnnouncementWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateBonusDividendApprovedBean() {

		bonusDividendApproved = new BonusDividendApproved();
		/* init datasources */
		bonusDividendApprovedDataSource = new BonusDividendApprovedDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		/* init option wrappers */
		fiscalYearList = fiscalYearDataSource.getAll();
		fiscalYearWrapperList = FiscalYearAdapter.wrapAll(fiscalYearList);

		announcementDataSource = new AnnouncementDataSource();

		/* init option wrappers */
		announcementList = announcementDataSource.getAll();
		announcementWrapperList = AnnouncementAdapter.wrapAll(announcementList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.bonusDividendApproved = bonusDividendApprovedDataSource
					.get(editId);

			this.selectedFiscalYearWrapper = FiscalYearAdapter
					.wrap(bonusDividendApproved.getFiscalYear());

			this.selectedAnnouncementWrapper = AnnouncementAdapter
					.wrap(bonusDividendApproved.getAnnouncement());

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

	public BonusDividendApproved getBonusDividendApproved() {
		return bonusDividendApproved;
	}

	public void setBonusDividendApproved(
			BonusDividendApproved bonusDividendApproved) {
		this.bonusDividendApproved = bonusDividendApproved;
	}

	/* getters for datasources */
	public BonusDividendApprovedDataSource getBonusDividendApprovedDataSource() {
		return bonusDividendApprovedDataSource;
	}

	public void setBonusDividendApprovedDataSource(
			BonusDividendApprovedDataSource bonusDividendApprovedDataSource) {
		this.bonusDividendApprovedDataSource = bonusDividendApprovedDataSource;
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

	public BonusDividendApproved saveBonusDividendApproved() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			bonusDividendApproved.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			bonusDividendApproved.setAnnouncement(announcement);

			bonusDividendApprovedDataSource.create(bonusDividendApproved,
					session);
			tx.commit();
			MessageService.info("Successfully Saved  BonusDividendApproved !");
			this.bonusDividendApproved = new BonusDividendApproved();
			return bonusDividendApproved;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving BonusDividendApproved .Try Again Later!");
			return null;
		}
	}

	public BonusDividendApproved updateBonusDividendApproved() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			FiscalYear fiscalYear = selectedFiscalYearWrapper.getFiscalYear();

			bonusDividendApproved.setFiscalYear(fiscalYear);

			Announcement announcement = selectedAnnouncementWrapper
					.getAnnouncement();

			bonusDividendApproved.setAnnouncement(announcement);

			bonusDividendApprovedDataSource.create(bonusDividendApproved,
					session);
			tx.commit();
			MessageService.info("Successfully Saved  BonusDividendApproved !");
			this.bonusDividendApproved = new BonusDividendApproved();
			return bonusDividendApproved;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving BonusDividendApproved .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateBonusDividendApproved();
		} else {
			log.info("Creating value");
			saveBonusDividendApproved();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog(
				"createBonusDividendApproved");

	}

	public BonusDividendApproved saveBonusDividendApproved(Session session) {

		this.bonusDividendApproved = bonusDividendApprovedDataSource.create(
				this.bonusDividendApproved, session);
		return this.bonusDividendApproved;
	}

}
