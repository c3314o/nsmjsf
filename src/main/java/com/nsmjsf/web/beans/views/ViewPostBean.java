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

import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.lazymodels.LazyPostDataModel;

import com.nsmjsf.web.adapters.EventAdapter;

import com.nsmjsf.web.datasources.EventDataSource;

import com.nsmjsf.web.datamodels.Event;

import com.nsmjsf.web.wrappers.EventWrapper;

import com.nsmjsf.web.adapters.InterviewAdapter;

import com.nsmjsf.web.datasources.InterviewDataSource;

import com.nsmjsf.web.datamodels.Interview;

import com.nsmjsf.web.wrappers.InterviewWrapper;

import com.nsmjsf.web.adapters.PostLikeAdapter;

import com.nsmjsf.web.datasources.PostLikeDataSource;

import com.nsmjsf.web.datamodels.PostLike;

import com.nsmjsf.web.wrappers.PostLikeWrapper;

import com.nsmjsf.web.adapters.TodaysPriceAdapter;

import com.nsmjsf.web.datasources.TodaysPriceDataSource;

import com.nsmjsf.web.datamodels.TodaysPrice;

import com.nsmjsf.web.wrappers.TodaysPriceWrapper;

import com.nsmjsf.web.adapters.AnnouncementAdapter;

import com.nsmjsf.web.datasources.AnnouncementDataSource;

import com.nsmjsf.web.datamodels.Announcement;

import com.nsmjsf.web.wrappers.AnnouncementWrapper;

import com.nsmjsf.web.adapters.FinancialReportAdapter;

import com.nsmjsf.web.datasources.FinancialReportDataSource;

import com.nsmjsf.web.datamodels.FinancialReport;

import com.nsmjsf.web.wrappers.FinancialReportWrapper;

import com.nsmjsf.web.adapters.BullionPriceAdapter;

import com.nsmjsf.web.datasources.BullionPriceDataSource;

import com.nsmjsf.web.datamodels.BullionPrice;

import com.nsmjsf.web.wrappers.BullionPriceWrapper;

import com.nsmjsf.web.adapters.MapPostCategoryAdapter;

import com.nsmjsf.web.datasources.MapPostCategoryDataSource;

import com.nsmjsf.web.datamodels.MapPostCategory;

import com.nsmjsf.web.wrappers.MapPostCategoryWrapper;

import com.nsmjsf.web.adapters.LatestPriceAdapter;

import com.nsmjsf.web.datasources.LatestPriceDataSource;

import com.nsmjsf.web.datamodels.LatestPrice;

import com.nsmjsf.web.wrappers.LatestPriceWrapper;

import com.nsmjsf.web.adapters.MapPostCompanyAdapter;

import com.nsmjsf.web.datasources.MapPostCompanyDataSource;

import com.nsmjsf.web.datamodels.MapPostCompany;

import com.nsmjsf.web.wrappers.MapPostCompanyWrapper;

import com.nsmjsf.web.adapters.ArticleAdapter;

import com.nsmjsf.web.datasources.ArticleDataSource;

import com.nsmjsf.web.datamodels.Article;

import com.nsmjsf.web.wrappers.ArticleWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.MarketIndexAdapter;

import com.nsmjsf.web.datasources.MarketIndexDataSource;

import com.nsmjsf.web.datamodels.MarketIndex;

import com.nsmjsf.web.wrappers.MarketIndexWrapper;

import com.nsmjsf.web.adapters.NewsAdapter;

import com.nsmjsf.web.datasources.NewsDataSource;

import com.nsmjsf.web.datamodels.News;

import com.nsmjsf.web.wrappers.NewsWrapper;

import com.nsmjsf.web.adapters.ForumThreadAdapter;

import com.nsmjsf.web.datasources.ForumThreadDataSource;

import com.nsmjsf.web.datamodels.ForumThread;

import com.nsmjsf.web.wrappers.ForumThreadWrapper;

import com.nsmjsf.web.adapters.PostImageAdapter;

import com.nsmjsf.web.datasources.PostImageDataSource;

import com.nsmjsf.web.datamodels.PostImage;

import com.nsmjsf.web.wrappers.PostImageWrapper;

import com.nsmjsf.web.adapters.MarketIndexAdapter;

import com.nsmjsf.web.datasources.MarketIndexDataSource;

import com.nsmjsf.web.datamodels.MarketIndex;

import com.nsmjsf.web.wrappers.MarketIndexWrapper;

import com.nsmjsf.web.adapters.CompanyDetailAdapter;

import com.nsmjsf.web.datasources.CompanyDetailDataSource;

import com.nsmjsf.web.datamodels.CompanyDetail;

import com.nsmjsf.web.wrappers.CompanyDetailWrapper;

import com.nsmjsf.web.adapters.ContentSourceAdapter;

import com.nsmjsf.web.datasources.ContentSourceDataSource;

import com.nsmjsf.web.datamodels.ContentSource;

import com.nsmjsf.web.wrappers.ContentSourceWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

import com.nsmjsf.web.adapters.CommentAdapter;

import com.nsmjsf.web.datasources.CommentDataSource;

import com.nsmjsf.web.datamodels.Comment;

import com.nsmjsf.web.wrappers.CommentWrapper;

import com.nsmjsf.web.adapters.BrokerAdapter;

import com.nsmjsf.web.datasources.BrokerDataSource;

import com.nsmjsf.web.datamodels.Broker;

import com.nsmjsf.web.wrappers.BrokerWrapper;

import com.nsmjsf.web.adapters.AllotmentResultAdapter;

import com.nsmjsf.web.datasources.AllotmentResultDataSource;

import com.nsmjsf.web.datamodels.AllotmentResult;

import com.nsmjsf.web.wrappers.AllotmentResultWrapper;

@ManagedBean
@ViewScoped
public class ViewPostBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewPostBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Post> postList;
	List<Post> selectedPostList;
	List<Post> filteredPostList;
	Post selectedPost;
	LazyDataModel<Post> lazyModel;
	PostDataSource postDataSource;
	int editPostId = 0;

	List<User> userList;
	UserDataSource userDataSource;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public ViewPostBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyPostDataModel(this.postList);

	}

	private void initDataSources() {
		postDataSource = new PostDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.postList = postDataSource.getAll();
		lazyModel = new LazyPostDataModel(this.postList);

	}

	private void populateData() {
		postList = postDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public LazyDataModel<Post> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Post> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Post getSelectedPost() {
		return selectedPost;
	}

	public void setSelectedPost(Post selectedPost) {
		this.selectedPost = selectedPost;
	}

	public List<Post> getSelectedPostList() {
		return selectedPostList;
	}

	public void setSelectedPostList(List<Post> selectedPostList) {
		this.selectedPostList = selectedPostList;
	}

	public List<Post> getFilteredPostList() {
		return filteredPostList;
	}

	public void setFilteredPostList(List<Post> filteredPostList) {
		this.filteredPostList = filteredPostList;
	}

	public void newPost() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createPost", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Post Selected"
				+ ((Post) event.getObject()).getPostId());
		for (Post cat : selectedPostList) {
			// System.out.println(cat.getPostLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Post) event.getObject()).getPostId());

	}

	public void deleteSelectedPost() {
		for (Post post : selectedPostList) {
			// System.out.println(post.getPostLabel());
			this.deletePost(post);
		}
	}

	public void deletePost(Post post) {
		try {
			postDataSource.delete(post);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditPostId() {
		return editPostId;
	}

	public void setEditPostId(int editPostId) {
		this.editPostId = editPostId;
	}

	public void editPost(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createPost", options,
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
