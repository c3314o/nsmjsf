package com.nsmjsf.web.pages;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;

import com.nsmjsf.web.beans.views.ViewFloorsheetBean;
import com.nsmjsf.web.beans.views.ViewFloorsheetDumpBean;
import com.nsmjsf.web.beans.views.ViewType;
import com.nsmjsf.web.datamodels.FloorsheetDump;
import com.nsmjsf.web.datasources.FloorsheetDumpDataSource;
import com.nsmjsf.web.lazymodels.LazyFloorsheetDumpDataModel;
import com.nsmjsf.web.livedata.FloorsheetLive;

@ManagedBean
@ViewScoped
public class FloorSheetPage implements Serializable{
	private static final Log log = LogFactory
			.getLog(ViewFloorsheetDumpBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<FloorsheetDump> floorsheetDumpList;
	List<FloorsheetDump> selectedFloorsheetDumpList;
	List<FloorsheetDump> filteredFloorsheetDumpList;
	FloorsheetDump selectedFloorsheetDump;
	LazyDataModel<FloorsheetDump> lazyModel;
	FloorsheetDumpDataSource floorsheetDumpDataSource;
	private boolean editable=false;
	int editFloorsheetDumpId = 0;

	public FloorSheetPage() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyFloorsheetDumpDataModel(this.floorsheetDumpList);

	}

	private void initDataSources() {
		floorsheetDumpDataSource = new FloorsheetDumpDataSource();

	}

	public void refreshDataSource() {
		this.floorsheetDumpList = floorsheetDumpDataSource.getAll();
		lazyModel = new LazyFloorsheetDumpDataModel(this.floorsheetDumpList);

	}

	private void populateData() {
		floorsheetDumpList = FloorsheetLive.getInstance().getFloorsheetList();
	}

	public List<FloorsheetDump> getFloorsheetDumpList() {
		return floorsheetDumpList;
	}

	public void setFloorsheetDumpList(List<FloorsheetDump> floorsheetDumpList) {
		this.floorsheetDumpList = floorsheetDumpList;
	}

	public LazyDataModel<FloorsheetDump> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<FloorsheetDump> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public FloorsheetDump getSelectedFloorsheetDump() {
		return selectedFloorsheetDump;
	}

	public void setSelectedFloorsheetDump(FloorsheetDump selectedFloorsheetDump) {
		this.selectedFloorsheetDump = selectedFloorsheetDump;
	}

	public List<FloorsheetDump> getSelectedFloorsheetDumpList() {
		return selectedFloorsheetDumpList;
	}

	public void setSelectedFloorsheetDumpList(
			List<FloorsheetDump> selectedFloorsheetDumpList) {
		this.selectedFloorsheetDumpList = selectedFloorsheetDumpList;
	}

	public List<FloorsheetDump> getFilteredFloorsheetDumpList() {
		return filteredFloorsheetDumpList;
	}

	public void setFilteredFloorsheetDumpList(
			List<FloorsheetDump> filteredFloorsheetDumpList) {
		this.filteredFloorsheetDumpList = filteredFloorsheetDumpList;
	}

	public void newFloorsheetDump() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createFloorsheetDump",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("FloorsheetDump Selected"
				+ ((FloorsheetDump) event.getObject()).getFloorsheetDumpId());
		for (FloorsheetDump cat : selectedFloorsheetDumpList) {
			// System.out.println(cat.getFloorsheetDumpLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((FloorsheetDump) event.getObject()).getFloorsheetDumpId());

	}

	public void deleteSelectedFloorsheetDump() {
		for (FloorsheetDump floorsheetDump : selectedFloorsheetDumpList) {
			// System.out.println(floorsheetDump.getFloorsheetDumpLabel());
			this.deleteFloorsheetDump(floorsheetDump);
		}
	}

	public void deleteFloorsheetDump(FloorsheetDump floorsheetDump) {
		try {
			floorsheetDumpDataSource.delete(floorsheetDump);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditFloorsheetDumpId() {
		return editFloorsheetDumpId;
	}

	public void setEditFloorsheetDumpId(int editFloorsheetDumpId) {
		this.editFloorsheetDumpId = editFloorsheetDumpId;
	}

	public void editFloorsheetDump(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createFloorsheetDump",
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
