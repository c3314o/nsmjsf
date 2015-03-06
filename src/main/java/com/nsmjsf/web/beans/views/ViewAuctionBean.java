package com.nsmjsf.web.beans.views;

import java.io.Serializable;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.context.RequestContext;

import com.nsmjsf.web.datasources.AuctionDataSource;
import com.nsmjsf.web.datamodels.Auction;
import com.nsmjsf.web.lazymodels.LazyAuctionDataModel;

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
public class ViewAuctionBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewAuctionBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Auction> auctionList;
	List<Auction> selectedAuctionList;
	List<Auction> filteredAuctionList;
	Auction selectedAuction;
	LazyDataModel<Auction> lazyModel;
	AuctionDataSource auctionDataSource;
	int editAuctionId = 0;

	List<Announcement> announcementList;
	AnnouncementDataSource announcementDataSource;

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	List<IssueManager> issueManagerList;
	IssueManagerDataSource issueManagerDataSource;

	public List<IssueManager> getIssueManagerList() {
		return issueManagerList;
	}

	public void setIssueManagerList(List<IssueManager> issueManagerList) {
		this.issueManagerList = issueManagerList;
	}

	public ViewAuctionBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyAuctionDataModel(this.auctionList);

	}

	private void initDataSources() {
		auctionDataSource = new AuctionDataSource();

		announcementDataSource = new AnnouncementDataSource();

		issueManagerDataSource = new IssueManagerDataSource();

	}

	public void refreshDataSource() {
		this.auctionList = auctionDataSource.getAll();
		lazyModel = new LazyAuctionDataModel(this.auctionList);

	}

	private void populateData() {
		auctionList = auctionDataSource.getAll();

		announcementList = announcementDataSource.getAll();

		issueManagerList = issueManagerDataSource.getAll();

	}

	public List<Auction> getAuctionList() {
		return auctionList;
	}

	public void setAuctionList(List<Auction> auctionList) {
		this.auctionList = auctionList;
	}

	public LazyDataModel<Auction> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Auction> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Auction getSelectedAuction() {
		return selectedAuction;
	}

	public void setSelectedAuction(Auction selectedAuction) {
		this.selectedAuction = selectedAuction;
	}

	public List<Auction> getSelectedAuctionList() {
		return selectedAuctionList;
	}

	public void setSelectedAuctionList(List<Auction> selectedAuctionList) {
		this.selectedAuctionList = selectedAuctionList;
	}

	public List<Auction> getFilteredAuctionList() {
		return filteredAuctionList;
	}

	public void setFilteredAuctionList(List<Auction> filteredAuctionList) {
		this.filteredAuctionList = filteredAuctionList;
	}

	public void newAuction() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAuction",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Auction Selected"
				+ ((Auction) event.getObject()).getAuctionId());
		for (Auction cat : selectedAuctionList) {
			// System.out.println(cat.getAuctionLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Auction) event.getObject()).getAuctionId());

	}

	public void deleteSelectedAuction() {
		for (Auction auction : selectedAuctionList) {
			// System.out.println(auction.getAuctionLabel());
			this.deleteAuction(auction);
		}
	}

	public void deleteAuction(Auction auction) {
		try {
			auctionDataSource.delete(auction);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditAuctionId() {
		return editAuctionId;
	}

	public void setEditAuctionId(int editAuctionId) {
		this.editAuctionId = editAuctionId;
	}

	public void editAuction(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAuction",
				options, params);
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public boolean isDataGrid() {
		return this.viewType == ViewType.DATAGRID;
	}

	public boolean isDataTable() {
		return this.viewType == ViewType.DATATABLE;
	}

	public boolean isDataScroller() {
		return this.viewType == ViewType.DATASCROLLER;
	}

	public boolean isDataTableLive() {
		return this.viewType == ViewType.DATATABLELIVE;
	}

	public void toDataTable() {
		this.viewType = ViewType.DATATABLE;
	}

	public void toDataGrid() {
		this.viewType = ViewType.DATAGRID;
	}

	public void toDataScroll() {
		this.viewType = ViewType.DATASCROLLER;
	}

}
