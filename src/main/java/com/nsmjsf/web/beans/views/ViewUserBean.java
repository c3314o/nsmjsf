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

import com.nsmjsf.web.datasources.UserDataSource;
import com.nsmjsf.web.datamodels.User;
import com.nsmjsf.web.lazymodels.LazyUserDataModel;

import com.nsmjsf.web.adapters.CommentSocialAdapter;

import com.nsmjsf.web.datasources.CommentSocialDataSource;

import com.nsmjsf.web.datamodels.CommentSocial;

import com.nsmjsf.web.wrappers.CommentSocialWrapper;

import com.nsmjsf.web.adapters.UserStockSalesAdapter;

import com.nsmjsf.web.datasources.UserStockSalesDataSource;

import com.nsmjsf.web.datamodels.UserStockSales;

import com.nsmjsf.web.wrappers.UserStockSalesWrapper;

import com.nsmjsf.web.adapters.ArticleAdapter;

import com.nsmjsf.web.datasources.ArticleDataSource;

import com.nsmjsf.web.datamodels.Article;

import com.nsmjsf.web.wrappers.ArticleWrapper;

import com.nsmjsf.web.adapters.UserEnergyAdapter;

import com.nsmjsf.web.datasources.UserEnergyDataSource;

import com.nsmjsf.web.datamodels.UserEnergy;

import com.nsmjsf.web.wrappers.UserEnergyWrapper;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.PostLikeAdapter;

import com.nsmjsf.web.datasources.PostLikeDataSource;

import com.nsmjsf.web.datamodels.PostLike;

import com.nsmjsf.web.wrappers.PostLikeWrapper;

import com.nsmjsf.web.adapters.NewsAdapter;

import com.nsmjsf.web.datasources.NewsDataSource;

import com.nsmjsf.web.datamodels.News;

import com.nsmjsf.web.wrappers.NewsWrapper;

import com.nsmjsf.web.adapters.UserTypeAdapter;

import com.nsmjsf.web.datasources.UserTypeDataSource;

import com.nsmjsf.web.datamodels.UserType;

import com.nsmjsf.web.wrappers.UserTypeWrapper;

import com.nsmjsf.web.adapters.UserStockAdapter;

import com.nsmjsf.web.datasources.UserStockDataSource;

import com.nsmjsf.web.datamodels.UserStock;

import com.nsmjsf.web.wrappers.UserStockWrapper;

import com.nsmjsf.web.adapters.NotificationAdapter;

import com.nsmjsf.web.datasources.NotificationDataSource;

import com.nsmjsf.web.datamodels.Notification;

import com.nsmjsf.web.wrappers.NotificationWrapper;

import com.nsmjsf.web.adapters.UserWatchListAdapter;

import com.nsmjsf.web.datasources.UserWatchListDataSource;

import com.nsmjsf.web.datamodels.UserWatchList;

import com.nsmjsf.web.wrappers.UserWatchListWrapper;

import com.nsmjsf.web.adapters.UserBullionAdapter;

import com.nsmjsf.web.datasources.UserBullionDataSource;

import com.nsmjsf.web.datamodels.UserBullion;

import com.nsmjsf.web.wrappers.UserBullionWrapper;

import com.nsmjsf.web.adapters.UserInfoAdapter;

import com.nsmjsf.web.datasources.UserInfoDataSource;

import com.nsmjsf.web.datamodels.UserInfo;

import com.nsmjsf.web.wrappers.UserInfoWrapper;

import com.nsmjsf.web.adapters.UserPortfolioAdapter;

import com.nsmjsf.web.datasources.UserPortfolioDataSource;

import com.nsmjsf.web.datamodels.UserPortfolio;

import com.nsmjsf.web.wrappers.UserPortfolioWrapper;

import com.nsmjsf.web.adapters.CommentAdapter;

import com.nsmjsf.web.datasources.CommentDataSource;

import com.nsmjsf.web.datamodels.Comment;

import com.nsmjsf.web.wrappers.CommentWrapper;

@ManagedBean
@ViewScoped
public class ViewUserBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewUserBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<User> userList;
	List<User> selectedUserList;
	List<User> filteredUserList;
	User selectedUser;
	LazyDataModel<User> lazyModel;
	UserDataSource userDataSource;
	int editUserId = 0;

	List<UserType> userTypeList;
	UserTypeDataSource userTypeDataSource;

	public List<UserType> getUserTypeList() {
		return userTypeList;
	}

	public void setUserTypeList(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	List<UserInfo> userInfoList;
	UserInfoDataSource userInfoDataSource;

	public List<UserInfo> getUserInfoList() {
		return userInfoList;
	}

	public void setUserInfoList(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	public ViewUserBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserDataModel(this.userList);

	}

	private void initDataSources() {
		userDataSource = new UserDataSource();

		userTypeDataSource = new UserTypeDataSource();

		userInfoDataSource = new UserInfoDataSource();

	}

	public void refreshDataSource() {
		this.userList = userDataSource.getAll();
		lazyModel = new LazyUserDataModel(this.userList);

	}

	private void populateData() {
		userList = userDataSource.getAll();

		userTypeList = userTypeDataSource.getAll();

		userInfoList = userInfoDataSource.getAll();

	}

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public LazyDataModel<User> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<User> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public List<User> getSelectedUserList() {
		return selectedUserList;
	}

	public void setSelectedUserList(List<User> selectedUserList) {
		this.selectedUserList = selectedUserList;
	}

	public List<User> getFilteredUserList() {
		return filteredUserList;
	}

	public void setFilteredUserList(List<User> filteredUserList) {
		this.filteredUserList = filteredUserList;
	}

	public void newUser() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUser", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("User Selected"
				+ ((User) event.getObject()).getUserId());
		for (User cat : selectedUserList) {
			// System.out.println(cat.getUserLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((User) event.getObject()).getUserId());

	}

	public void deleteSelectedUser() {
		for (User user : selectedUserList) {
			// System.out.println(user.getUserLabel());
			this.deleteUser(user);
		}
	}

	public void deleteUser(User user) {
		try {
			userDataSource.delete(user);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserId() {
		return editUserId;
	}

	public void setEditUserId(int editUserId) {
		this.editUserId = editUserId;
	}

	public void editUser(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUser", options,
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
