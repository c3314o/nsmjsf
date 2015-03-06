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

import com.nsmjsf.web.datasources.NewsDataSource;
import com.nsmjsf.web.datamodels.News;
import com.nsmjsf.web.lazymodels.LazyNewsDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class ViewNewsBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewNewsBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<News> newsList;
	List<News> selectedNewsList;
	List<News> filteredNewsList;
	News selectedNews;
	LazyDataModel<News> lazyModel;
	NewsDataSource newsDataSource;
	int editNewsId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	List<User> userList;
	UserDataSource userDataSource;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public ViewNewsBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyNewsDataModel(this.newsList);

	}

	private void initDataSources() {
		newsDataSource = new NewsDataSource();

		postDataSource = new PostDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.newsList = newsDataSource.getAll();
		lazyModel = new LazyNewsDataModel(this.newsList);

	}

	private void populateData() {
		newsList = newsDataSource.getAll();

		postList = postDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<News> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<News> newsList) {
		this.newsList = newsList;
	}

	public LazyDataModel<News> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<News> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public News getSelectedNews() {
		return selectedNews;
	}

	public void setSelectedNews(News selectedNews) {
		this.selectedNews = selectedNews;
	}

	public List<News> getSelectedNewsList() {
		return selectedNewsList;
	}

	public void setSelectedNewsList(List<News> selectedNewsList) {
		this.selectedNewsList = selectedNewsList;
	}

	public List<News> getFilteredNewsList() {
		return filteredNewsList;
	}

	public void setFilteredNewsList(List<News> filteredNewsList) {
		this.filteredNewsList = filteredNewsList;
	}

	public void newNews() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createNews", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("News Selected"
				+ ((News) event.getObject()).getNewsId());
		for (News cat : selectedNewsList) {
			// System.out.println(cat.getNewsLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((News) event.getObject()).getNewsId());

	}

	public void deleteSelectedNews() {
		for (News news : selectedNewsList) {
			// System.out.println(news.getNewsLabel());
			this.deleteNews(news);
		}
	}

	public void deleteNews(News news) {
		try {
			newsDataSource.delete(news);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditNewsId() {
		return editNewsId;
	}

	public void setEditNewsId(int editNewsId) {
		this.editNewsId = editNewsId;
	}

	public void editNews(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createNews", options,
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
