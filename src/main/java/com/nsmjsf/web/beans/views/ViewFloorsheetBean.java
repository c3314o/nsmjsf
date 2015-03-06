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

import com.nsmjsf.web.datasources.FloorsheetDataSource;
import com.nsmjsf.web.datamodels.Floorsheet;
import com.nsmjsf.web.lazymodels.LazyFloorsheetDataModel;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class ViewFloorsheetBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewFloorsheetBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Floorsheet> floorsheetList;
	List<Floorsheet> selectedFloorsheetList;
	List<Floorsheet> filteredFloorsheetList;
	Floorsheet selectedFloorsheet;
	LazyDataModel<Floorsheet> lazyModel;
	FloorsheetDataSource floorsheetDataSource;
	int editFloorsheetId = 0;

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public ViewFloorsheetBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyFloorsheetDataModel(this.floorsheetList);

	}

	private void initDataSources() {
		floorsheetDataSource = new FloorsheetDataSource();

		companyDataSource = new CompanyDataSource();

	}

	public void refreshDataSource() {
		this.floorsheetList = floorsheetDataSource.getAll();
		lazyModel = new LazyFloorsheetDataModel(this.floorsheetList);

	}

	private void populateData() {
		floorsheetList = floorsheetDataSource.getAll();

		companyList = companyDataSource.getAll();

	}

	public List<Floorsheet> getFloorsheetList() {
		return floorsheetList;
	}

	public void setFloorsheetList(List<Floorsheet> floorsheetList) {
		this.floorsheetList = floorsheetList;
	}

	public LazyDataModel<Floorsheet> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Floorsheet> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Floorsheet getSelectedFloorsheet() {
		return selectedFloorsheet;
	}

	public void setSelectedFloorsheet(Floorsheet selectedFloorsheet) {
		this.selectedFloorsheet = selectedFloorsheet;
	}

	public List<Floorsheet> getSelectedFloorsheetList() {
		return selectedFloorsheetList;
	}

	public void setSelectedFloorsheetList(
			List<Floorsheet> selectedFloorsheetList) {
		this.selectedFloorsheetList = selectedFloorsheetList;
	}

	public List<Floorsheet> getFilteredFloorsheetList() {
		return filteredFloorsheetList;
	}

	public void setFilteredFloorsheetList(
			List<Floorsheet> filteredFloorsheetList) {
		this.filteredFloorsheetList = filteredFloorsheetList;
	}

	public void newFloorsheet() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFloorsheet",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Floorsheet Selected"
				+ ((Floorsheet) event.getObject()).getFloorsheetId());
		for (Floorsheet cat : selectedFloorsheetList) {
			// System.out.println(cat.getFloorsheetLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Floorsheet) event.getObject()).getFloorsheetId());

	}

	public void deleteSelectedFloorsheet() {
		for (Floorsheet floorsheet : selectedFloorsheetList) {
			// System.out.println(floorsheet.getFloorsheetLabel());
			this.deleteFloorsheet(floorsheet);
		}
	}

	public void deleteFloorsheet(Floorsheet floorsheet) {
		try {
			floorsheetDataSource.delete(floorsheet);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditFloorsheetId() {
		return editFloorsheetId;
	}

	public void setEditFloorsheetId(int editFloorsheetId) {
		this.editFloorsheetId = editFloorsheetId;
	}

	public void editFloorsheet(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFloorsheet",
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
