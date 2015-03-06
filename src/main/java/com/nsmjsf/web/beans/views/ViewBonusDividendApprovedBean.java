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

import com.nsmjsf.web.datasources.BonusDividendApprovedDataSource;
import com.nsmjsf.web.datamodels.BonusDividendApproved;
import com.nsmjsf.web.lazymodels.LazyBonusDividendApprovedDataModel;

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
public class ViewBonusDividendApprovedBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewBonusDividendApprovedBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<BonusDividendApproved> bonusDividendApprovedList;
	List<BonusDividendApproved> selectedBonusDividendApprovedList;
	List<BonusDividendApproved> filteredBonusDividendApprovedList;
	BonusDividendApproved selectedBonusDividendApproved;
	LazyDataModel<BonusDividendApproved> lazyModel;
	BonusDividendApprovedDataSource bonusDividendApprovedDataSource;
	int editBonusDividendApprovedId = 0;

	List<FiscalYear> fiscalYearList;
	FiscalYearDataSource fiscalYearDataSource;

	public List<FiscalYear> getFiscalYearList() {
		return fiscalYearList;
	}

	public void setFiscalYearList(List<FiscalYear> fiscalYearList) {
		this.fiscalYearList = fiscalYearList;
	}

	List<Announcement> announcementList;
	AnnouncementDataSource announcementDataSource;

	public List<Announcement> getAnnouncementList() {
		return announcementList;
	}

	public void setAnnouncementList(List<Announcement> announcementList) {
		this.announcementList = announcementList;
	}

	public ViewBonusDividendApprovedBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyBonusDividendApprovedDataModel(
				this.bonusDividendApprovedList);

	}

	private void initDataSources() {
		bonusDividendApprovedDataSource = new BonusDividendApprovedDataSource();

		fiscalYearDataSource = new FiscalYearDataSource();

		announcementDataSource = new AnnouncementDataSource();

	}

	public void refreshDataSource() {
		this.bonusDividendApprovedList = bonusDividendApprovedDataSource
				.getAll();
		lazyModel = new LazyBonusDividendApprovedDataModel(
				this.bonusDividendApprovedList);

	}

	private void populateData() {
		bonusDividendApprovedList = bonusDividendApprovedDataSource.getAll();

		fiscalYearList = fiscalYearDataSource.getAll();

		announcementList = announcementDataSource.getAll();

	}

	public List<BonusDividendApproved> getBonusDividendApprovedList() {
		return bonusDividendApprovedList;
	}

	public void setBonusDividendApprovedList(
			List<BonusDividendApproved> bonusDividendApprovedList) {
		this.bonusDividendApprovedList = bonusDividendApprovedList;
	}

	public LazyDataModel<BonusDividendApproved> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<BonusDividendApproved> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public BonusDividendApproved getSelectedBonusDividendApproved() {
		return selectedBonusDividendApproved;
	}

	public void setSelectedBonusDividendApproved(
			BonusDividendApproved selectedBonusDividendApproved) {
		this.selectedBonusDividendApproved = selectedBonusDividendApproved;
	}

	public List<BonusDividendApproved> getSelectedBonusDividendApprovedList() {
		return selectedBonusDividendApprovedList;
	}

	public void setSelectedBonusDividendApprovedList(
			List<BonusDividendApproved> selectedBonusDividendApprovedList) {
		this.selectedBonusDividendApprovedList = selectedBonusDividendApprovedList;
	}

	public List<BonusDividendApproved> getFilteredBonusDividendApprovedList() {
		return filteredBonusDividendApprovedList;
	}

	public void setFilteredBonusDividendApprovedList(
			List<BonusDividendApproved> filteredBonusDividendApprovedList) {
		this.filteredBonusDividendApprovedList = filteredBonusDividendApprovedList;
	}

	public void newBonusDividendApproved() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog(
				"createBonusDividendApproved", options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("BonusDividendApproved Selected"
				+ ((BonusDividendApproved) event.getObject())
						.getBonusDividendApprovedId());
		for (BonusDividendApproved cat : selectedBonusDividendApprovedList) {
			// System.out.println(cat.getBonusDividendApprovedLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BonusDividendApproved) event.getObject())
						.getBonusDividendApprovedId());

	}

	public void deleteSelectedBonusDividendApproved() {
		for (BonusDividendApproved bonusDividendApproved : selectedBonusDividendApprovedList) {
			// System.out.println(bonusDividendApproved.getBonusDividendApprovedLabel());
			this.deleteBonusDividendApproved(bonusDividendApproved);
		}
	}

	public void deleteBonusDividendApproved(
			BonusDividendApproved bonusDividendApproved) {
		try {
			bonusDividendApprovedDataSource.delete(bonusDividendApproved);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditBonusDividendApprovedId() {
		return editBonusDividendApprovedId;
	}

	public void setEditBonusDividendApprovedId(int editBonusDividendApprovedId) {
		this.editBonusDividendApprovedId = editBonusDividendApprovedId;
	}

	public void editBonusDividendApproved(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog(
				"createBonusDividendApproved", options, params);
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
