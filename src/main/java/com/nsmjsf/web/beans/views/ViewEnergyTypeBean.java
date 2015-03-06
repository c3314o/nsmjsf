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

import com.nsmjsf.web.datasources.EnergyTypeDataSource;
import com.nsmjsf.web.datamodels.EnergyType;
import com.nsmjsf.web.lazymodels.LazyEnergyTypeDataModel;

import com.nsmjsf.web.adapters.UserEnergyAdapter;

import com.nsmjsf.web.datasources.UserEnergyDataSource;

import com.nsmjsf.web.datamodels.UserEnergy;

import com.nsmjsf.web.wrappers.UserEnergyWrapper;

@ManagedBean
@ViewScoped
public class ViewEnergyTypeBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewEnergyTypeBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<EnergyType> energyTypeList;
	List<EnergyType> selectedEnergyTypeList;
	List<EnergyType> filteredEnergyTypeList;
	EnergyType selectedEnergyType;
	LazyDataModel<EnergyType> lazyModel;
	EnergyTypeDataSource energyTypeDataSource;
	int editEnergyTypeId = 0;

	public ViewEnergyTypeBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyEnergyTypeDataModel(this.energyTypeList);

	}

	private void initDataSources() {
		energyTypeDataSource = new EnergyTypeDataSource();

	}

	public void refreshDataSource() {
		this.energyTypeList = energyTypeDataSource.getAll();
		lazyModel = new LazyEnergyTypeDataModel(this.energyTypeList);

	}

	private void populateData() {
		energyTypeList = energyTypeDataSource.getAll();

	}

	public List<EnergyType> getEnergyTypeList() {
		return energyTypeList;
	}

	public void setEnergyTypeList(List<EnergyType> energyTypeList) {
		this.energyTypeList = energyTypeList;
	}

	public LazyDataModel<EnergyType> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<EnergyType> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public EnergyType getSelectedEnergyType() {
		return selectedEnergyType;
	}

	public void setSelectedEnergyType(EnergyType selectedEnergyType) {
		this.selectedEnergyType = selectedEnergyType;
	}

	public List<EnergyType> getSelectedEnergyTypeList() {
		return selectedEnergyTypeList;
	}

	public void setSelectedEnergyTypeList(
			List<EnergyType> selectedEnergyTypeList) {
		this.selectedEnergyTypeList = selectedEnergyTypeList;
	}

	public List<EnergyType> getFilteredEnergyTypeList() {
		return filteredEnergyTypeList;
	}

	public void setFilteredEnergyTypeList(
			List<EnergyType> filteredEnergyTypeList) {
		this.filteredEnergyTypeList = filteredEnergyTypeList;
	}

	public void newEnergyType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createEnergyType",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("EnergyType Selected"
				+ ((EnergyType) event.getObject()).getEnergyTypeId());
		for (EnergyType cat : selectedEnergyTypeList) {
			// System.out.println(cat.getEnergyTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((EnergyType) event.getObject()).getEnergyTypeId());

	}

	public void deleteSelectedEnergyType() {
		for (EnergyType energyType : selectedEnergyTypeList) {
			// System.out.println(energyType.getEnergyTypeLabel());
			this.deleteEnergyType(energyType);
		}
	}

	public void deleteEnergyType(EnergyType energyType) {
		try {
			energyTypeDataSource.delete(energyType);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditEnergyTypeId() {
		return editEnergyTypeId;
	}

	public void setEditEnergyTypeId(int editEnergyTypeId) {
		this.editEnergyTypeId = editEnergyTypeId;
	}

	public void editEnergyType(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createEnergyType",
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
