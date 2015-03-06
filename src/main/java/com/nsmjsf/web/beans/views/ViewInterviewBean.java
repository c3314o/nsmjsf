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

import com.nsmjsf.web.datasources.InterviewDataSource;
import com.nsmjsf.web.datamodels.Interview;
import com.nsmjsf.web.lazymodels.LazyInterviewDataModel;

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
public class ViewInterviewBean implements Serializable {
	private static final Log log = LogFactory.getLog(ViewInterviewBean.class);
	ViewType viewType = ViewType.DATATABLE;

	List<Interview> interviewList;
	List<Interview> selectedInterviewList;
	List<Interview> filteredInterviewList;
	Interview selectedInterview;
	LazyDataModel<Interview> lazyModel;
	InterviewDataSource interviewDataSource;
	int editInterviewId = 0;

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

	public ViewInterviewBean() {
		this.initDataSources();
		this.populateData();

		lazyModel = new LazyInterviewDataModel(this.interviewList);

	}

	private void initDataSources() {
		interviewDataSource = new InterviewDataSource();

		postDataSource = new PostDataSource();

		companyDataSource = new CompanyDataSource();

	}

	public void refreshDataSource() {
		this.interviewList = interviewDataSource.getAll();
		lazyModel = new LazyInterviewDataModel(this.interviewList);

	}

	private void populateData() {
		interviewList = interviewDataSource.getAll();

		postList = postDataSource.getAll();

		companyList = companyDataSource.getAll();

	}

	public List<Interview> getInterviewList() {
		return interviewList;
	}

	public void setInterviewList(List<Interview> interviewList) {
		this.interviewList = interviewList;
	}

	public LazyDataModel<Interview> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<Interview> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public Interview getSelectedInterview() {
		return selectedInterview;
	}

	public void setSelectedInterview(Interview selectedInterview) {
		this.selectedInterview = selectedInterview;
	}

	public List<Interview> getSelectedInterviewList() {
		return selectedInterviewList;
	}

	public void setSelectedInterviewList(List<Interview> selectedInterviewList) {
		this.selectedInterviewList = selectedInterviewList;
	}

	public List<Interview> getFilteredInterviewList() {
		return filteredInterviewList;
	}

	public void setFilteredInterviewList(List<Interview> filteredInterviewList) {
		this.filteredInterviewList = filteredInterviewList;
	}

	public void newInterview() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createInterview",
				options, null);

	}

	public void onRowSelect(SelectEvent event) {
		System.out.println("Interview Selected"
				+ ((Interview) event.getObject()).getInterviewId());
		for (Interview cat : selectedInterviewList) {
			// System.out.println(cat.getInterviewLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Interview) event.getObject()).getInterviewId());

	}

	public void deleteSelectedInterview() {
		for (Interview interview : selectedInterviewList) {
			// System.out.println(interview.getInterviewLabel());
			this.deleteInterview(interview);
		}
	}

	public void deleteInterview(Interview interview) {
		try {
			interviewDataSource.delete(interview);
			this.refreshDataSource();
		} catch (Exception ex) {
			log.info(ex.getMessage());
		}

	}

	/*----------------------------------------*/
	public int getEditInterviewId() {
		return editInterviewId;
	}

	public void setEditInterviewId(int editInterviewId) {
		this.editInterviewId = editInterviewId;
	}

	public void editInterview(int editId) {
		Map<String, List<String>> params = new HashMap<String, List<String>>();
		Map<String, Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId = String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createInterview",
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
