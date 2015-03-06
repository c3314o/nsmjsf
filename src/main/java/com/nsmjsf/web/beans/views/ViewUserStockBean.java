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

import com.nsmjsf.web.datasources.UserStockDataSource;
import com.nsmjsf.web.datamodels.UserStock;
import com.nsmjsf.web.lazymodels.LazyUserStockDataModel;

import com.nsmjsf.web.adapters.UserStockSalesAdapter;

import com.nsmjsf.web.datasources.UserStockSalesDataSource;

import com.nsmjsf.web.datamodels.UserStockSales;

import com.nsmjsf.web.wrappers.UserStockSalesWrapper;

import com.nsmjsf.web.adapters.UserPortfolioAdapter;

import com.nsmjsf.web.datasources.UserPortfolioDataSource;

import com.nsmjsf.web.datamodels.UserPortfolio;

import com.nsmjsf.web.wrappers.UserPortfolioWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

import com.nsmjsf.web.adapters.UserAdapter;

import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;

@ManagedBean
@ViewScoped
public class ViewUserStockBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewUserStockBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<UserStock> userStockList;
	List<UserStock> selectedUserStockList;
	List<UserStock> filteredUserStockList;
	UserStock selectedUserStock;
	LazyDataModel<UserStock> lazyModel;
	UserStockDataSource userStockDataSource;
	int editUserStockId = 0;

	List<UserPortfolio> userPortfolioList;
	UserPortfolioDataSource userPortfolioDataSource;

	public List<UserPortfolio> getUserPortfolioList() {
		return userPortfolioList;
	}

	public void setUserPortfolioList(List<UserPortfolio> userPortfolioList) {
		this.userPortfolioList = userPortfolioList;
	}

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	List<User> userList;
	UserDataSource userDataSource;

	public List<User> getUserList() {
		return userList;
	}

	public void setUserList(List<User> userList) {
		this.userList = userList;
	}

	public ViewUserStockBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyUserStockDataModel(this.userStockList);

	}

	private void initDataSources() {
		userStockDataSource = new UserStockDataSource();

		userPortfolioDataSource = new UserPortfolioDataSource();

		companyDataSource = new CompanyDataSource();

		userDataSource = new UserDataSource();

	}

	public void refreshDataSource() {
		this.userStockList = userStockDataSource.getAll();
		lazyModel = new LazyUserStockDataModel(this.userStockList);

	}

	private void populateData() {
		userStockList = userStockDataSource.getAll();

		userPortfolioList = userPortfolioDataSource.getAll();

		companyList = companyDataSource.getAll();

		userList = userDataSource.getAll();

	}

	public List<UserStock> getUserStockList() {
		return userStockList;
	}

	public void setUserStockList(List<UserStock> userStockList) {
		this.userStockList = userStockList;
	}

	public LazyDataModel<UserStock> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<UserStock> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public UserStock getSelectedUserStock() {
		return selectedUserStock;
	}

	public void setSelectedUserStock(UserStock selectedUserStock) {
		this.selectedUserStock = selectedUserStock;
	}

	public List<UserStock> getSelectedUserStockList() {
		return selectedUserStockList;
	}

	public void setSelectedUserStockList(List<UserStock> selectedUserStockList) {
		this.selectedUserStockList = selectedUserStockList;
	}

	public List<UserStock> getFilteredUserStockList() {
		return filteredUserStockList;
	}

	public void setFilteredUserStockList(List<UserStock> filteredUserStockList) {
		this.filteredUserStockList = filteredUserStockList;
	}

	public void newUserStock() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createUserStock",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("UserStock Selected"
				+ ((UserStock) event.getObject()).getUserStockId());
		for (UserStock cat : selectedUserStockList) {
			// System.out.println(cat.getUserStockLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((UserStock) event.getObject()).getUserStockId());

	}

	public void deleteSelectedUserStock() {
		for (UserStock userStock : selectedUserStockList) {
			// System.out.println(userStock.getUserStockLabel());
			this.deleteUserStock(userStock);
		}
	}

	public void deleteUserStock(UserStock userStock) {
		try {
			userStockDataSource.delete(userStock);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditUserStockId() {
		return editUserStockId;
	}

	public void setEditUserStockId(int editUserStockId) {
		this.editUserStockId = editUserStockId;
	}

	public void editUserStock(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createUserStock",
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
