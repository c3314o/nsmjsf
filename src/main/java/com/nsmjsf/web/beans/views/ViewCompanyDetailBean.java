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

import com.nsmjsf.web.datasources.CompanyDetailDataSource;
import com.nsmjsf.web.datamodels.CompanyDetail;
import com.nsmjsf.web.lazymodels.LazyCompanyDetailDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

import com.nsmjsf.web.adapters.CompanyAdapter;

import com.nsmjsf.web.datasources.CompanyDataSource;

import com.nsmjsf.web.datamodels.Company;

import com.nsmjsf.web.wrappers.CompanyWrapper;

@ManagedBean
@ViewScoped
public class ViewCompanyDetailBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewCompanyDetailBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<CompanyDetail> companyDetailList;
	List<CompanyDetail> selectedCompanyDetailList;
	List<CompanyDetail> filteredCompanyDetailList;
	CompanyDetail selectedCompanyDetail;
	LazyDataModel<CompanyDetail> lazyModel;
	CompanyDetailDataSource companyDetailDataSource;
	int editCompanyDetailId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public ViewCompanyDetailBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyCompanyDetailDataModel(this.companyDetailList);

	}

	private void initDataSources() {
		companyDetailDataSource = new CompanyDetailDataSource();

		postDataSource = new PostDataSource();

	}

	public void refreshDataSource() {
		this.companyDetailList = companyDetailDataSource.getAll();
		lazyModel = new LazyCompanyDetailDataModel(this.companyDetailList);

	}

	private void populateData() {
		companyDetailList = companyDetailDataSource.getAll();

		postList = postDataSource.getAll();

	}

	public List<CompanyDetail> getCompanyDetailList() {
		return companyDetailList;
	}

	public void setCompanyDetailList(List<CompanyDetail> companyDetailList) {
		this.companyDetailList = companyDetailList;
	}

	public LazyDataModel<CompanyDetail> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<CompanyDetail> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public CompanyDetail getSelectedCompanyDetail() {
		return selectedCompanyDetail;
	}

	public void setSelectedCompanyDetail(CompanyDetail selectedCompanyDetail) {
		this.selectedCompanyDetail = selectedCompanyDetail;
	}

	public List<CompanyDetail> getSelectedCompanyDetailList() {
		return selectedCompanyDetailList;
	}

	public void setSelectedCompanyDetailList(
			List<CompanyDetail> selectedCompanyDetailList) {
		this.selectedCompanyDetailList = selectedCompanyDetailList;
	}

	public List<CompanyDetail> getFilteredCompanyDetailList() {
		return filteredCompanyDetailList;
	}

	public void setFilteredCompanyDetailList(
			List<CompanyDetail> filteredCompanyDetailList) {
		this.filteredCompanyDetailList = filteredCompanyDetailList;
	}

	public void newCompanyDetail() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCompanyDetail",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("CompanyDetail Selected"
				+ ((CompanyDetail) event.getObject()).getCompanyDetailId());
		for (CompanyDetail cat : selectedCompanyDetailList) {
			// System.out.println(cat.getCompanyDetailLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CompanyDetail) event.getObject()).getCompanyDetailId());

	}

	public void deleteSelectedCompanyDetail() {
		for (CompanyDetail companyDetail : selectedCompanyDetailList) {
			// System.out.println(companyDetail.getCompanyDetailLabel());
			this.deleteCompanyDetail(companyDetail);
		}
	}

	public void deleteCompanyDetail(CompanyDetail companyDetail) {
		try {
			companyDetailDataSource.delete(companyDetail);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditCompanyDetailId() {
		return editCompanyDetailId;
	}

	public void setEditCompanyDetailId(int editCompanyDetailId) {
		this.editCompanyDetailId = editCompanyDetailId;
	}

	public void editCompanyDetail(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCompanyDetail",
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
