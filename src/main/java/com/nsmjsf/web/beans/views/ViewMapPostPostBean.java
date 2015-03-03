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

import com.nsmjsf.web.datasources.MapPostPostDataSource;
import com.nsmjsf.web.datamodels.MapPostPost;
import com.nsmjsf.web.lazymodels.LazyMapPostPostDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class ViewMapPostPostBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewMapPostPostBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<MapPostPost> mapPostPostList;
	List<MapPostPost> selectedMapPostPostList;
	List<MapPostPost> filteredMapPostPostList;
	MapPostPost selectedMapPostPost;
	LazyDataModel<MapPostPost> lazyModel;
	MapPostPostDataSource mapPostPostDataSource;
	int editMapPostPostId = 0;

	List<Post> postByToPostIdList;
	PostDataSource postByToPostIdDataSource;

	List<Post> postByFromPostIdList;
	PostDataSource postByFromPostIdDataSource;

	public ViewMapPostPostBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyMapPostPostDataModel(this.mapPostPostList);

	}

	private void initDataSources() {
		mapPostPostDataSource = new MapPostPostDataSource();

		postByToPostIdDataSource = new PostDataSource();

		postByFromPostIdDataSource = new PostDataSource();

	}

	public void refreshDataSource() {
		this.mapPostPostList = mapPostPostDataSource.getAll();
		lazyModel = new LazyMapPostPostDataModel(this.mapPostPostList);

	}

	private void populateData() {
		mapPostPostList = mapPostPostDataSource.getAll();

		postByToPostIdList = postByToPostIdDataSource.getAll();

		postByFromPostIdList = postByFromPostIdDataSource.getAll();

	}

	public List<MapPostPost> getMapPostPostList() {
		return mapPostPostList;
	}

	public void setMapPostPostList(List<MapPostPost> mapPostPostList) {
		this.mapPostPostList = mapPostPostList;
	}

	public LazyDataModel<MapPostPost> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<MapPostPost> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public MapPostPost getSelectedMapPostPost() {
		return selectedMapPostPost;
	}

	public void setSelectedMapPostPost(MapPostPost selectedMapPostPost) {
		this.selectedMapPostPost = selectedMapPostPost;
	}

	public List<MapPostPost> getSelectedMapPostPostList() {
		return selectedMapPostPostList;
	}

	public void setSelectedMapPostPostList(
			List<MapPostPost> selectedMapPostPostList) {
		this.selectedMapPostPostList = selectedMapPostPostList;
	}

	public List<MapPostPost> getFilteredMapPostPostList() {
		return filteredMapPostPostList;
	}

	public void setFilteredMapPostPostList(
			List<MapPostPost> filteredMapPostPostList) {
		this.filteredMapPostPostList = filteredMapPostPostList;
	}

	public void newMapPostPost() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createMapPostPost",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("MapPostPost Selected"
				+ ((MapPostPost) event.getObject()).getMapPostPostId());
		for (MapPostPost cat : selectedMapPostPostList) {
			// System.out.println(cat.getMapPostPostLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((MapPostPost) event.getObject()).getMapPostPostId());

	}

	public void deleteSelectedMapPostPost() {
		for (MapPostPost mapPostPost : selectedMapPostPostList) {
			// System.out.println(mapPostPost.getMapPostPostLabel());
			this.deleteMapPostPost(mapPostPost);
		}
	}

	public void deleteMapPostPost(MapPostPost mapPostPost) {
		try {
			mapPostPostDataSource.delete(mapPostPost);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditMapPostPostId() {
		return editMapPostPostId;
	}

	public void setEditMapPostPostId(int editMapPostPostId) {
		this.editMapPostPostId = editMapPostPostId;
	}

	public void editMapPostPost(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createMapPostPost",
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

	public List<Post> getPostByToPostIdList() {
		return postByToPostIdList;
	}

	public void setPostByToPostIdList(List<Post> postByToPostIdList) {
		this.postByToPostIdList = postByToPostIdList;
	}

	public List<Post> getPostByFromPostIdList() {
		return postByFromPostIdList;
	}

	public void setPostByFromPostIdList(List<Post> postByFromPostIdList) {
		this.postByFromPostIdList = postByFromPostIdList;
	}
	
	
	

}
