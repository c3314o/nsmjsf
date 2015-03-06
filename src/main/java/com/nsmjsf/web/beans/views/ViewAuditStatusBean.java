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

import com.nsmjsf.web.datasources.AuditStatusDataSource;
import com.nsmjsf.web.datamodels.AuditStatus;
import com.nsmjsf.web.lazymodels.LazyAuditStatusDataModel;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

@ManagedBean
@ViewScoped
public class ViewAuditStatusBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewAuditStatusBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<AuditStatus> auditStatusList;
	List<AuditStatus> selectedAuditStatusList;
	List<AuditStatus> filteredAuditStatusList;
	AuditStatus selectedAuditStatus;
	LazyDataModel<AuditStatus> lazyModel;
	AuditStatusDataSource auditStatusDataSource;
	int editAuditStatusId = 0;

	public ViewAuditStatusBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyAuditStatusDataModel(this.auditStatusList);

	}

	private void initDataSources() {
		auditStatusDataSource = new AuditStatusDataSource();

	}

	public void refreshDataSource() {
		this.auditStatusList = auditStatusDataSource.getAll();
		lazyModel = new LazyAuditStatusDataModel(this.auditStatusList);

	}

	private void populateData() {
		auditStatusList = auditStatusDataSource.getAll();

	}

	public List<AuditStatus> getAuditStatusList() {
		return auditStatusList;
	}

	public void setAuditStatusList(List<AuditStatus> auditStatusList) {
		this.auditStatusList = auditStatusList;
	}

	public LazyDataModel<AuditStatus> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<AuditStatus> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public AuditStatus getSelectedAuditStatus() {
		return selectedAuditStatus;
	}

	public void setSelectedAuditStatus(AuditStatus selectedAuditStatus) {
		this.selectedAuditStatus = selectedAuditStatus;
	}

	public List<AuditStatus> getSelectedAuditStatusList() {
		return selectedAuditStatusList;
	}

	public void setSelectedAuditStatusList(
			List<AuditStatus> selectedAuditStatusList) {
		this.selectedAuditStatusList = selectedAuditStatusList;
	}

	public List<AuditStatus> getFilteredAuditStatusList() {
		return filteredAuditStatusList;
	}

	public void setFilteredAuditStatusList(
			List<AuditStatus> filteredAuditStatusList) {
		this.filteredAuditStatusList = filteredAuditStatusList;
	}

	public void newAuditStatus() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createAuditStatus",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("AuditStatus Selected"
				+ ((AuditStatus) event.getObject()).getAuditStatusId());
		for (AuditStatus cat : selectedAuditStatusList) {
			// System.out.println(cat.getAuditStatusLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((AuditStatus) event.getObject()).getAuditStatusId());

	}

	public void deleteSelectedAuditStatus() {
		for (AuditStatus auditStatus : selectedAuditStatusList) {
			// System.out.println(auditStatus.getAuditStatusLabel());
			this.deleteAuditStatus(auditStatus);
		}
	}

	public void deleteAuditStatus(AuditStatus auditStatus) {
		try {
			auditStatusDataSource.delete(auditStatus);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditAuditStatusId() {
		return editAuditStatusId;
	}

	public void setEditAuditStatusId(int editAuditStatusId) {
		this.editAuditStatusId = editAuditStatusId;
	}

	public void editAuditStatus(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createAuditStatus",
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
