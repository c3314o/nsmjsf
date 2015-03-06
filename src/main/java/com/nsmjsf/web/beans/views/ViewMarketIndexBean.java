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

import com.nsmjsf.web.datasources.MarketIndexDataSource;
import com.nsmjsf.web.datamodels.MarketIndex;
import com.nsmjsf.web.lazymodels.LazyMarketIndexDataModel;

import com.nsmjsf.web.adapters.IndexTypeAdapter;

import com.nsmjsf.web.datasources.IndexTypeDataSource;

import com.nsmjsf.web.datamodels.IndexType;

import com.nsmjsf.web.wrappers.IndexTypeWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class ViewMarketIndexBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewMarketIndexBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<MarketIndex> marketIndexList;
	List<MarketIndex> selectedMarketIndexList;
	List<MarketIndex> filteredMarketIndexList;
	MarketIndex selectedMarketIndex;
	LazyDataModel<MarketIndex> lazyModel;
	MarketIndexDataSource marketIndexDataSource;
	int editMarketIndexId = 0;

	List<IndexType> indexTypeList;
	IndexTypeDataSource indexTypeDataSource;

	public List<IndexType> getIndexTypeList() {
		return indexTypeList;
	}

	public void setIndexTypeList(List<IndexType> indexTypeList) {
		this.indexTypeList = indexTypeList;
	}

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public ViewMarketIndexBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyMarketIndexDataModel(this.marketIndexList);

	}

	private void initDataSources() {
		marketIndexDataSource = new MarketIndexDataSource();

		indexTypeDataSource = new IndexTypeDataSource();

		postDataSource = new PostDataSource();

	}

	public void refreshDataSource() {
		this.marketIndexList = marketIndexDataSource.getAll();
		lazyModel = new LazyMarketIndexDataModel(this.marketIndexList);

	}

	private void populateData() {
		marketIndexList = marketIndexDataSource.getAll();

		indexTypeList = indexTypeDataSource.getAll();

		postList = postDataSource.getAll();

	}

	public List<MarketIndex> getMarketIndexList() {
		return marketIndexList;
	}

	public void setMarketIndexList(List<MarketIndex> marketIndexList) {
		this.marketIndexList = marketIndexList;
	}

	public LazyDataModel<MarketIndex> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<MarketIndex> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public MarketIndex getSelectedMarketIndex() {
		return selectedMarketIndex;
	}

	public void setSelectedMarketIndex(MarketIndex selectedMarketIndex) {
		this.selectedMarketIndex = selectedMarketIndex;
	}

	public List<MarketIndex> getSelectedMarketIndexList() {
		return selectedMarketIndexList;
	}

	public void setSelectedMarketIndexList(
			List<MarketIndex> selectedMarketIndexList) {
		this.selectedMarketIndexList = selectedMarketIndexList;
	}

	public List<MarketIndex> getFilteredMarketIndexList() {
		return filteredMarketIndexList;
	}

	public void setFilteredMarketIndexList(
			List<MarketIndex> filteredMarketIndexList) {
		this.filteredMarketIndexList = filteredMarketIndexList;
	}

	public void newMarketIndex() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createMarketIndex",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("MarketIndex Selected"
				+ ((MarketIndex) event.getObject()).getMarketIndexId());
		for (MarketIndex cat : selectedMarketIndexList) {
			// System.out.println(cat.getMarketIndexLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((MarketIndex) event.getObject()).getMarketIndexId());

	}

	public void deleteSelectedMarketIndex() {
		for (MarketIndex marketIndex : selectedMarketIndexList) {
			// System.out.println(marketIndex.getMarketIndexLabel());
			this.deleteMarketIndex(marketIndex);
		}
	}

	public void deleteMarketIndex(MarketIndex marketIndex) {
		try {
			marketIndexDataSource.delete(marketIndex);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditMarketIndexId() {
		return editMarketIndexId;
	}

	public void setEditMarketIndexId(int editMarketIndexId) {
		this.editMarketIndexId = editMarketIndexId;
	}

	public void editMarketIndex(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createMarketIndex",
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
