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

import com.nsmjsf.web.datasources.PostLikeDataSource;
import com.nsmjsf.web.datamodels.PostLike;
import com.nsmjsf.web.lazymodels.LazyPostLikeDataModel;

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
public class ViewPostLikeBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewPostLikeBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<PostLike> postLikeList;
	List<PostLike> selectedPostLikeList;
	List<PostLike> filteredPostLikeList;
	PostLike selectedPostLike;
	LazyDataModel<PostLike> lazyModel;
	PostLikeDataSource postLikeDataSource;
	int editPostLikeId = 0;

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

	public ViewPostLikeBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyPostLikeDataModel(this.postLikeList);

	}

	private void initDataSources() {
		postLikeDataSource = new PostLikeDataSource();

		postDataSource = new PostDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.postLikeList = postLikeDataSource.getAll();
		lazyModel = new LazyPostLikeDataModel(this.postLikeList);

	}

	private void populateData() {
		postLikeList = postLikeDataSource.getAll();

		postList = postDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<PostLike> getPostLikeList() {
		return postLikeList;
	}

	public void setPostLikeList(List<PostLike> postLikeList) {
		this.postLikeList = postLikeList;
	}

	public LazyDataModel<PostLike> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PostLike> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public PostLike getSelectedPostLike() {
		return selectedPostLike;
	}

	public void setSelectedPostLike(PostLike selectedPostLike) {
		this.selectedPostLike = selectedPostLike;
	}

	public List<PostLike> getSelectedPostLikeList() {
		return selectedPostLikeList;
	}

	public void setSelectedPostLikeList(List<PostLike> selectedPostLikeList) {
		this.selectedPostLikeList = selectedPostLikeList;
	}

	public List<PostLike> getFilteredPostLikeList() {
		return filteredPostLikeList;
	}

	public void setFilteredPostLikeList(List<PostLike> filteredPostLikeList) {
		this.filteredPostLikeList = filteredPostLikeList;
	}

	public void newPostLike() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createPostLike",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("PostLike Selected"
				+ ((PostLike) event.getObject()).getPostLikeId());
		for (PostLike cat : selectedPostLikeList) {
			// System.out.println(cat.getPostLikeLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((PostLike) event.getObject()).getPostLikeId());

	}

	public void deleteSelectedPostLike() {
		for (PostLike postLike : selectedPostLikeList) {
			// System.out.println(postLike.getPostLikeLabel());
			this.deletePostLike(postLike);
		}
	}

	public void deletePostLike(PostLike postLike) {
		try {
			postLikeDataSource.delete(postLike);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditPostLikeId() {
		return editPostLikeId;
	}

	public void setEditPostLikeId(int editPostLikeId) {
		this.editPostLikeId = editPostLikeId;
	}

	public void editPostLike(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createPostLike",
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
