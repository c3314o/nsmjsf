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

import com.nsmjsf.web.datasources.BrokerDataSource;
import com.nsmjsf.web.datamodels.Broker;
import com.nsmjsf.web.lazymodels.LazyBrokerDataModel;

import com.nsmjsf.web.adapters.PostAdapter;

import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class ViewBrokerBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewBrokerBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Broker> brokerList;
	List<Broker> selectedBrokerList;
	List<Broker> filteredBrokerList;
	Broker selectedBroker;
	LazyDataModel<Broker> lazyModel;
	BrokerDataSource brokerDataSource;
	int editBrokerId = 0;

	List<Post> postList;
	PostDataSource postDataSource;

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public ViewBrokerBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyBrokerDataModel(this.brokerList);

	}

	private void initDataSources() {
		brokerDataSource = new BrokerDataSource();

		postDataSource = new PostDataSource();

	}

	public void refreshDataSource() {
		this.brokerList = brokerDataSource.getAll();
		lazyModel = new LazyBrokerDataModel(this.brokerList);

	}

	private void populateData() {
		brokerList = brokerDataSource.getAll();

		postList = postDataSource.getAll();

	}

	public List<Broker> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<Broker> brokerList) {
		this.brokerList = brokerList;
	}

	public LazyDataModel<Broker> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Broker> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Broker getSelectedBroker() {
		return selectedBroker;
	}

	public void setSelectedBroker(Broker selectedBroker) {
		this.selectedBroker = selectedBroker;
	}

	public List<Broker> getSelectedBrokerList() {
		return selectedBrokerList;
	}

	public void setSelectedBrokerList(List<Broker> selectedBrokerList) {
		this.selectedBrokerList = selectedBrokerList;
	}

	public List<Broker> getFilteredBrokerList() {
		return filteredBrokerList;
	}

	public void setFilteredBrokerList(List<Broker> filteredBrokerList) {
		this.filteredBrokerList = filteredBrokerList;
	}

	public void newBroker() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createBroker", options,
				null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Broker Selected"
				+ ((Broker) event.getObject()).getBrokerId());
		for (Broker cat : selectedBrokerList) {
			// System.out.println(cat.getBrokerLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Broker) event.getObject()).getBrokerId());

	}

	public void deleteSelectedBroker() {
		for (Broker broker : selectedBrokerList) {
			// System.out.println(broker.getBrokerLabel());
			this.deleteBroker(broker);
		}
	}

	public void deleteBroker(Broker broker) {
		try {
			brokerDataSource.delete(broker);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditBrokerId() {
		return editBrokerId;
	}

	public void setEditBrokerId(int editBrokerId) {
		this.editBrokerId = editBrokerId;
	}

	public void editBroker(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createBroker", options,
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
