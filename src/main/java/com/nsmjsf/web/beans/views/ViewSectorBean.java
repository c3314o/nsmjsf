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

import com.nsmjsf.web.datasources.SectorDataSource;
import com.nsmjsf.web.datamodels.Sector;
import com.nsmjsf.web.lazymodels.LazySectorDataModel;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class ViewSectorBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewSectorBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Sector> sectorList;
	List<Sector> selectedSectorList;
	List<Sector> filteredSectorList;
	Sector selectedSector;
	LazyDataModel<Sector> lazyModel;
	SectorDataSource sectorDataSource;
	int editSectorId = 0;

	public ViewSectorBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazySectorDataModel(this.sectorList);

	}

	private void initDataSources() {
		sectorDataSource = new SectorDataSource();

	}

	public void refreshDataSource() {
		this.sectorList = sectorDataSource.getAll();
		lazyModel = new LazySectorDataModel(this.sectorList);

	}

	private void populateData() {
		sectorList = sectorDataSource.getAll();

	}

	public List<Sector> getSectorList() {
		return sectorList;
	}

	public void setSectorList(List<Sector> sectorList) {
		this.sectorList = sectorList;
	}

	public LazyDataModel<Sector> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Sector> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Sector getSelectedSector() {
		return selectedSector;
	}

	public void setSelectedSector(Sector selectedSector) {
		this.selectedSector = selectedSector;
	}

	public List<Sector> getSelectedSectorList() {
		return selectedSectorList;
	}

	public void setSelectedSectorList(List<Sector> selectedSectorList) {
		this.selectedSectorList = selectedSectorList;
	}

	public List<Sector> getFilteredSectorList() {
		return filteredSectorList;
	}

	public void setFilteredSectorList(List<Sector> filteredSectorList) {
		this.filteredSectorList = filteredSectorList;
	}

	public void newSector() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createSector", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Sector Selected"
				+ ((Sector) event.getObject()).getSectorId());
		for (Sector cat : selectedSectorList) {
			// System.out.println(cat.getSectorLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Sector) event.getObject()).getSectorId());

	}

	public void deleteSelectedSector() {
		for (Sector sector : selectedSectorList) {
			// System.out.println(sector.getSectorLabel());
			this.deleteSector(sector);
		}
	}

	public void deleteSector(Sector sector) {
		try {
			sectorDataSource.delete(sector);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditSectorId() {
		return editSectorId;
	}

	public void setEditSectorId(int editSectorId) {
		this.editSectorId = editSectorId;
	}

	public void editSector(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createSector", options,
				params);
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
