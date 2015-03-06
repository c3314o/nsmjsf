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

import com.nsmjsf.web.datasources.BullionTypeDataSource;
import com.nsmjsf.web.datamodels.BullionType;
import com.nsmjsf.web.lazymodels.LazyBullionTypeDataModel;

import com.nsmjsf.web.adapters.UserBullionAdapter;

import com.nsmjsf.web.datasources.UserBullionDataSource;

import com.nsmjsf.web.datamodels.UserBullion;

import com.nsmjsf.web.wrappers.UserBullionWrapper;

@ManagedBean
@ViewScoped
public class ViewBullionTypeBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewBullionTypeBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<BullionType> bullionTypeList;
	List<BullionType> selectedBullionTypeList;
	List<BullionType> filteredBullionTypeList;
	BullionType selectedBullionType;
	LazyDataModel<BullionType> lazyModel;
	BullionTypeDataSource bullionTypeDataSource;
	int editBullionTypeId = 0;

	public ViewBullionTypeBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyBullionTypeDataModel(this.bullionTypeList);

	}

	private void initDataSources() {
		bullionTypeDataSource = new BullionTypeDataSource();

	}

	public void refreshDataSource() {
		this.bullionTypeList = bullionTypeDataSource.getAll();
		lazyModel = new LazyBullionTypeDataModel(this.bullionTypeList);

	}

	private void populateData() {
		bullionTypeList = bullionTypeDataSource.getAll();

	}

	public List<BullionType> getBullionTypeList() {
		return bullionTypeList;
	}

	public void setBullionTypeList(List<BullionType> bullionTypeList) {
		this.bullionTypeList = bullionTypeList;
	}

	public LazyDataModel<BullionType> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<BullionType> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public BullionType getSelectedBullionType() {
		return selectedBullionType;
	}

	public void setSelectedBullionType(BullionType selectedBullionType) {
		this.selectedBullionType = selectedBullionType;
	}

	public List<BullionType> getSelectedBullionTypeList() {
		return selectedBullionTypeList;
	}

	public void setSelectedBullionTypeList(
			List<BullionType> selectedBullionTypeList) {
		this.selectedBullionTypeList = selectedBullionTypeList;
	}

	public List<BullionType> getFilteredBullionTypeList() {
		return filteredBullionTypeList;
	}

	public void setFilteredBullionTypeList(
			List<BullionType> filteredBullionTypeList) {
		this.filteredBullionTypeList = filteredBullionTypeList;
	}

	public void newBullionType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBullionType",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("BullionType Selected"
				+ ((BullionType) event.getObject()).getBullionTypeId());
		for (BullionType cat : selectedBullionTypeList) {
			// System.out.println(cat.getBullionTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((BullionType) event.getObject()).getBullionTypeId());

	}

	public void deleteSelectedBullionType() {
		for (BullionType bullionType : selectedBullionTypeList) {
			// System.out.println(bullionType.getBullionTypeLabel());
			this.deleteBullionType(bullionType);
		}
	}

	public void deleteBullionType(BullionType bullionType) {
		try {
			bullionTypeDataSource.delete(bullionType);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditBullionTypeId() {
		return editBullionTypeId;
	}

	public void setEditBullionTypeId(int editBullionTypeId) {
		this.editBullionTypeId = editBullionTypeId;
	}

	public void editBullionType(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBullionType",
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
