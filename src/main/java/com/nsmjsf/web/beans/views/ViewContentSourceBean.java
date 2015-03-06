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

import com.nsmjsf.web.datasources.ContentSourceDataSource;
import com.nsmjsf.web.datamodels.ContentSource;
import com.nsmjsf.web.lazymodels.LazyContentSourceDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class ViewContentSourceBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewContentSourceBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<ContentSource> contentSourceList;
	List<ContentSource> selectedContentSourceList;
	List<ContentSource> filteredContentSourceList;
	ContentSource selectedContentSource;
	LazyDataModel<ContentSource> lazyModel;
	ContentSourceDataSource contentSourceDataSource;
	int editContentSourceId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public ViewContentSourceBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyContentSourceDataModel(this.contentSourceList);

	}

	private void initDataSources() {
		contentSourceDataSource = new ContentSourceDataSource();

		postDataSource = new PostDataSource();

	}

	public void refreshDataSource() {
		this.contentSourceList = contentSourceDataSource.getAll();
		lazyModel = new LazyContentSourceDataModel(this.contentSourceList);

	}

	private void populateData() {
		contentSourceList = contentSourceDataSource.getAll();

		postList = postDataSource.getAll();

	}

	public List<ContentSource> getContentSourceList() {
		return contentSourceList;
	}

	public void setContentSourceList(List<ContentSource> contentSourceList) {
		this.contentSourceList = contentSourceList;
	}

	public LazyDataModel<ContentSource> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<ContentSource> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public ContentSource getSelectedContentSource() {
		return selectedContentSource;
	}

	public void setSelectedContentSource(ContentSource selectedContentSource) {
		this.selectedContentSource = selectedContentSource;
	}

	public List<ContentSource> getSelectedContentSourceList() {
		return selectedContentSourceList;
	}

	public void setSelectedContentSourceList(
			List<ContentSource> selectedContentSourceList) {
		this.selectedContentSourceList = selectedContentSourceList;
	}

	public List<ContentSource> getFilteredContentSourceList() {
		return filteredContentSourceList;
	}

	public void setFilteredContentSourceList(
			List<ContentSource> filteredContentSourceList) {
		this.filteredContentSourceList = filteredContentSourceList;
	}

	public void newContentSource() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createContentSource",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("ContentSource Selected"
				+ ((ContentSource) event.getObject()).getContentSourceId());
		for (ContentSource cat : selectedContentSourceList) {
			// System.out.println(cat.getContentSourceLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((ContentSource) event.getObject()).getContentSourceId());

	}

	public void deleteSelectedContentSource() {
		for (ContentSource contentSource : selectedContentSourceList) {
			// System.out.println(contentSource.getContentSourceLabel());
			this.deleteContentSource(contentSource);
		}
	}

	public void deleteContentSource(ContentSource contentSource) {
		try {
			contentSourceDataSource.delete(contentSource);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditContentSourceId() {
		return editContentSourceId;
	}

	public void setEditContentSourceId(int editContentSourceId) {
		this.editContentSourceId = editContentSourceId;
	}

	public void editContentSource(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createContentSource",
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
