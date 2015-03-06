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

import com.nsmjsf.web.datasources.MapPostCompanyDataSource;
import com.nsmjsf.web.datamodels.MapPostCompany;
import com.nsmjsf.web.lazymodels.LazyMapPostCompanyDataModel;

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
public class ViewMapPostCompanyBean implements Serializable {
	private static final Log log = LogFactory
			.getLog(ViewMapPostCompanyBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<MapPostCompany> mapPostCompanyList;
	List<MapPostCompany> selectedMapPostCompanyList;
	List<MapPostCompany> filteredMapPostCompanyList;
	MapPostCompany selectedMapPostCompany;
	LazyDataModel<MapPostCompany> lazyModel;
	MapPostCompanyDataSource mapPostCompanyDataSource;
	int editMapPostCompanyId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	List<Company> companyList;
	CompanyDataSource companyDataSource;

	public List<Company> getCompanyList() {
		return companyList;
	}

	public void setCompanyList(List<Company> companyList) {
		this.companyList = companyList;
	}

	public ViewMapPostCompanyBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyMapPostCompanyDataModel(this.mapPostCompanyList);

	}

	private void initDataSources() {
		mapPostCompanyDataSource = new MapPostCompanyDataSource();

		postDataSource = new PostDataSource();

		companyDataSource = new CompanyDataSource();

	}

	public void refreshDataSource() {
		this.mapPostCompanyList = mapPostCompanyDataSource.getAll();
		lazyModel = new LazyMapPostCompanyDataModel(this.mapPostCompanyList);

	}

	private void populateData() {
		mapPostCompanyList = mapPostCompanyDataSource.getAll();

		postList = postDataSource.getAll();

		companyList = companyDataSource.getAll();

	}

	public List<MapPostCompany> getMapPostCompanyList() {
		return mapPostCompanyList;
	}

	public void setMapPostCompanyList(List<MapPostCompany> mapPostCompanyList) {
		this.mapPostCompanyList = mapPostCompanyList;
	}

	public LazyDataModel<MapPostCompany> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<MapPostCompany> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public MapPostCompany getSelectedMapPostCompany() {
		return selectedMapPostCompany;
	}

	public void setSelectedMapPostCompany(MapPostCompany selectedMapPostCompany) {
		this.selectedMapPostCompany = selectedMapPostCompany;
	}

	public List<MapPostCompany> getSelectedMapPostCompanyList() {
		return selectedMapPostCompanyList;
	}

	public void setSelectedMapPostCompanyList(
			List<MapPostCompany> selectedMapPostCompanyList) {
		this.selectedMapPostCompanyList = selectedMapPostCompanyList;
	}

	public List<MapPostCompany> getFilteredMapPostCompanyList() {
		return filteredMapPostCompanyList;
	}

	public void setFilteredMapPostCompanyList(
			List<MapPostCompany> filteredMapPostCompanyList) {
		this.filteredMapPostCompanyList = filteredMapPostCompanyList;
	}

	public void newMapPostCompany() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createMapPostCompany",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("MapPostCompany Selected"
				+ ((MapPostCompany) event.getObject()).getMapPostCompanyId());
		for (MapPostCompany cat : selectedMapPostCompanyList) {
			// System.out.println(cat.getMapPostCompanyLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((MapPostCompany) event.getObject()).getMapPostCompanyId());

	}

	public void deleteSelectedMapPostCompany() {
		for (MapPostCompany mapPostCompany : selectedMapPostCompanyList) {
			// System.out.println(mapPostCompany.getMapPostCompanyLabel());
			this.deleteMapPostCompany(mapPostCompany);
		}
	}

	public void deleteMapPostCompany(MapPostCompany mapPostCompany) {
		try {
			mapPostCompanyDataSource.delete(mapPostCompany);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditMapPostCompanyId() {
		return editMapPostCompanyId;
	}

	public void setEditMapPostCompanyId(int editMapPostCompanyId) {
		this.editMapPostCompanyId = editMapPostCompanyId;
	}

	public void editMapPostCompany(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createMapPostCompany",
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
