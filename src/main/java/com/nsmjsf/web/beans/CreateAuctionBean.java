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

import com.nsmjsf.web.datasources.AuctionDataSource;
import com.nsmjsf.web.datamodels.Auction;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

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
public class CreateAuctionBean implements Serializable {

	@ManagedProperty(value = "#{createAnnouncementBean}")
	private CreateAnnouncementBean createAnnouncementBean;

	private static final Log log = LogFactory.getLog(CreateAuctionBean.class);

	private Auction auction;
	private AuctionDataSource auctionDataSource;

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

	public CreateAuctionBean() {

		auction = new Auction();
		/* init datasources */
		auctionDataSource = new AuctionDataSource();

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
			this.auction = auctionDataSource.get(editId);

			this.selectedAnnouncementWrapper = AnnouncementAdapter.wrap(auction
					.getAnnouncement());

			this.selectedIssueManagerWrapper = IssueManagerAdapter.wrap(auction
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

	public Auction getAuction() {
		return auction;
	}

	public void setAuction(Auction auction) {
		this.auction = auction;
	}

	/* getters for datasources */
	public AuctionDataSource getAuctionDataSource() {
		return auctionDataSource;
	}

	public void setAuctionDataSource(AuctionDataSource auctionDataSource) {
		this.auctionDataSource = auctionDataSource;
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

	public Auction saveAuction() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Announcement announcement = createAnnouncementBean
					.saveAnnouncement(session);

			auction.setAnnouncement(announcement);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			auction.setIssueManager(issueManager);

			auctionDataSource.create(auction, session);
			tx.commit();
			MessageService.info("Successfully Saved  Auction !");
			this.auction = new Auction();
			return auction;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Auction .Try Again Later!");
			return null;
		}
	}

	public Auction updateAuction() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Announcement announcement = createAnnouncementBean
					.saveAnnouncement(session);

			auction.setAnnouncement(announcement);

			IssueManager issueManager = selectedIssueManagerWrapper
					.getIssueManager();

			auction.setIssueManager(issueManager);

			auctionDataSource.create(auction, session);
			tx.commit();
			MessageService.info("Successfully Saved  Auction !");
			this.auction = new Auction();
			return auction;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Auction .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateAuction();
		} else {
			log.info("Creating value");
			saveAuction();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createAuction");

	}

	public Auction saveAuction(Session session) {

		this.auction = auctionDataSource.create(this.auction, session);
		return this.auction;
	}

	public CreateAnnouncementBean getCreateAnnouncementBean() {
		return createAnnouncementBean;
	}

	public void setCreateAnnouncementBean(
			CreateAnnouncementBean createAnnouncementBean) {
		this.createAnnouncementBean = createAnnouncementBean;
	}

}
