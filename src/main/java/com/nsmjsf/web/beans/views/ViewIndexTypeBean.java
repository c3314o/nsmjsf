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

import com.nsmjsf.web.datasources.IndexTypeDataSource;
import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.lazymodels.LazyIndexTypeDataModel;

import com.nsmjsf.web.adapters.MarketIndexAdapter;

import com.nsmjsf.web.datasources.MarketIndexDataSource;

import com.nsmjsf.web.datamodels.MarketIndex;

import com.nsmjsf.web.wrappers.MarketIndexWrapper;

@ManagedBean
@ViewScoped
public class ViewIndexTypeBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewIndexTypeBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<IndexType> indexTypeList;
	List<IndexType> selectedIndexTypeList;
	List<IndexType> filteredIndexTypeList;
	IndexType selectedIndexType;
	LazyDataModel<IndexType> lazyModel;
	IndexTypeDataSource indexTypeDataSource;
	int editIndexTypeId = 0;

	public ViewIndexTypeBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyIndexTypeDataModel(this.indexTypeList);

	}

	private void initDataSources() {
		indexTypeDataSource = new IndexTypeDataSource();

	}

	public void refreshDataSource() {
		this.indexTypeList = indexTypeDataSource.getAll();
		lazyModel = new LazyIndexTypeDataModel(this.indexTypeList);

	}

	private void populateData() {
		indexTypeList = indexTypeDataSource.getAll();

	}

	public List<IndexType> getIndexTypeList() {
		return indexTypeList;
	}

	public void setIndexTypeList(List<IndexType> indexTypeList) {
		this.indexTypeList = indexTypeList;
	}

	public LazyDataModel<IndexType> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<IndexType> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public IndexType getSelectedIndexType() {
		return selectedIndexType;
	}

	public void setSelectedIndexType(IndexType selectedIndexType) {
		this.selectedIndexType = selectedIndexType;
	}

	public List<IndexType> getSelectedIndexTypeList() {
		return selectedIndexTypeList;
	}

	public void setSelectedIndexTypeList(List<IndexType> selectedIndexTypeList) {
		this.selectedIndexTypeList = selectedIndexTypeList;
	}

	public List<IndexType> getFilteredIndexTypeList() {
		return filteredIndexTypeList;
	}

	public void setFilteredIndexTypeList(List<IndexType> filteredIndexTypeList) {
		this.filteredIndexTypeList = filteredIndexTypeList;
	}

	public void newIndexType() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createIndexType",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("IndexType Selected"
				+ ((IndexType) event.getObject()).getIndexTypeId());
		for (IndexType cat : selectedIndexTypeList) {
			// System.out.println(cat.getIndexTypeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((IndexType) event.getObject()).getIndexTypeId());

	}

	public void deleteSelectedIndexType() {
		for (IndexType indexType : selectedIndexTypeList) {
			// System.out.println(indexType.getIndexTypeLabel());
			this.deleteIndexType(indexType);
		}
	}

	public void deleteIndexType(IndexType indexType) {
		try {
			indexTypeDataSource.delete(indexType);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditIndexTypeId() {
		return editIndexTypeId;
	}

	public void setEditIndexTypeId(int editIndexTypeId) {
		this.editIndexTypeId = editIndexTypeId;
	}

	public void editIndexType(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createIndexType",
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
