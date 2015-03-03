
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

import com.nsmjsf.web.datasources.ProxyDataSource;
import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.lazymodels.LazyProxyDataModel;

	   
@ManagedBean
@ViewScoped
public class ViewProxyBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewProxyBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Proxy> proxyList;
    List<Proxy> selectedProxyList;
	List<Proxy> filteredProxyList;
	Proxy selectedProxy;
	LazyDataModel<Proxy> lazyModel;
	ProxyDataSource proxyDataSource;
	int editProxyId=0;
	
	   
	
	
	public ViewProxyBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyProxyDataModel(this.proxyList);
		
	}
	
	
	private void initDataSources()
	{
		proxyDataSource=new ProxyDataSource();
		
	   
	
		
	}
	
	public void refreshDataSource(){
		this.proxyList=proxyDataSource.getAll();
		lazyModel=new LazyProxyDataModel(this.proxyList);
		
	}
	
	
	private void populateData()
	{
		proxyList=proxyDataSource.getAll();
		
	   
	
		
			}
	public List<Proxy> getProxyList() {
		return proxyList;
	}
	public void setProxyList(List<Proxy> proxyList) {
		this.proxyList = proxyList;
	}
	public LazyDataModel<Proxy> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Proxy> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Proxy getSelectedProxy() {
		return selectedProxy;
	}
	public void setSelectedProxy(Proxy selectedProxy) {
		this.selectedProxy = selectedProxy;
	}
	
	public List<Proxy> getSelectedProxyList() {
		return selectedProxyList;
	}

	public void setSelectedProxyList(
			List<Proxy> selectedProxyList) {
		this.selectedProxyList = selectedProxyList;
	}

	public List<Proxy> getFilteredProxyList() {
		return filteredProxyList;
	}

	public void setFilteredProxyList(
			List<Proxy> filteredProxyList) {
		this.filteredProxyList = filteredProxyList;
	}

	public void newProxy() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createProxy",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Proxy Selected"
				+ ((Proxy) event.getObject()).getProxyId());
		for (Proxy cat : selectedProxyList) {
			//System.out.println(cat.getProxyLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Proxy) event.getObject()).getProxyId());

	}

	public void deleteSelectedProxy() {
		for (Proxy proxy : selectedProxyList) {
			//System.out.println(proxy.getProxyLabel());
			this.deleteProxy(proxy);
		}
	}
	public void deleteProxy(Proxy proxy) {
			try{
			proxyDataSource.delete(proxy);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditProxyId() {
		return editProxyId;
	}

	public void setEditProxyId(int editProxyId) {
		this.editProxyId = editProxyId;
	}
	
	public void editProxy(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createProxy",
				options,params);
	}

	public ViewType getViewType() {
		return viewType;
	}

	public void setViewType(ViewType viewType) {
		this.viewType = viewType;
	}

	public boolean isDataGrid()
	{
		return this.viewType==ViewType.DATAGRID;
	}
	public boolean isDataTable()
	{
		return this.viewType==ViewType.DATATABLE;
	}
	public boolean isDataScroller()
	{
		return this.viewType==ViewType.DATASCROLLER;
	}
	public boolean isDataTableLive()
	{
		return this.viewType==ViewType.DATATABLELIVE;
	}
	
	public void toDataTable()
	{
		this.viewType=ViewType.DATATABLE;
	}
	public void toDataGrid()
	{
		this.viewType=ViewType.DATAGRID;
	}
	public void toDataScroll()
	{
		this.viewType=ViewType.DATASCROLLER;
	}
	

}



