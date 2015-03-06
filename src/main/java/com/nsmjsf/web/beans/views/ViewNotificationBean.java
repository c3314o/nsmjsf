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

import com.nsmjsf.web.datasources.NotificationDataSource;
import com.nsmjsf.web.datamodels.Notification;
import com.nsmjsf.web.lazymodels.LazyNotificationDataModel;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class ViewNotificationBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewNotificationBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Notification> notificationList;
	List<Notification> selectedNotificationList;
	List<Notification> filteredNotificationList;
	Notification selectedNotification;
	LazyDataModel<Notification> lazyModel;
	NotificationDataSource notificationDataSource;
	int editNotificationId = 0;

	List<User> userList;
	UserDataSource userDataSource;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public ViewNotificationBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyNotificationDataModel(this.notificationList);

	}

	private void initDataSources() {
		notificationDataSource = new NotificationDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.notificationList = notificationDataSource.getAll();
		lazyModel = new LazyNotificationDataModel(this.notificationList);

	}

	private void populateData() {
		notificationList = notificationDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<Notification> getNotificationList() {
		return notificationList;
	}

	public void setNotificationList(List<Notification> notificationList) {
		this.notificationList = notificationList;
	}

	public LazyDataModel<Notification> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Notification> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Notification getSelectedNotification() {
		return selectedNotification;
	}

	public void setSelectedNotification(Notification selectedNotification) {
		this.selectedNotification = selectedNotification;
	}

	public List<Notification> getSelectedNotificationList() {
		return selectedNotificationList;
	}

	public void setSelectedNotificationList(
			List<Notification> selectedNotificationList) {
		this.selectedNotificationList = selectedNotificationList;
	}

	public List<Notification> getFilteredNotificationList() {
		return filteredNotificationList;
	}

	public void setFilteredNotificationList(
			List<Notification> filteredNotificationList) {
		this.filteredNotificationList = filteredNotificationList;
	}

	public void newNotification() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createNotification",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Notification Selected"
				+ ((Notification) event.getObject()).getNotificationId());
		for (Notification cat : selectedNotificationList) {
			// System.out.println(cat.getNotificationLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Notification) event.getObject()).getNotificationId());

	}

	public void deleteSelectedNotification() {
		for (Notification notification : selectedNotificationList) {
			// System.out.println(notification.getNotificationLabel());
			this.deleteNotification(notification);
		}
	}

	public void deleteNotification(Notification notification) {
		try {
			notificationDataSource.delete(notification);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditNotificationId() {
		return editNotificationId;
	}

	public void setEditNotificationId(int editNotificationId) {
		this.editNotificationId = editNotificationId;
	}

	public void editNotification(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createNotification",
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
