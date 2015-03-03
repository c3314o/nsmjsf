
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

import com.nsmjsf.web.datasources.CommentSocialDataSource;
import com.nsmjsf.web.datamodels.CommentSocial;
import com.nsmjsf.web.lazymodels.LazyCommentSocialDataModel;


			
import com.nsmjsf.web.adapters.CommentAdapter;


import com.nsmjsf.web.datasources.CommentDataSource;

import com.nsmjsf.web.datamodels.Comment;

import com.nsmjsf.web.wrappers.CommentWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewCommentSocialBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewCommentSocialBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<CommentSocial> commentSocialList;
    List<CommentSocial> selectedCommentSocialList;
	List<CommentSocial> filteredCommentSocialList;
	CommentSocial selectedCommentSocial;
	LazyDataModel<CommentSocial> lazyModel;
	CommentSocialDataSource commentSocialDataSource;
	int editCommentSocialId=0;
	

			   List<Comment> commentList;
			   CommentDataSource commentDataSource;
			   public List<Comment> getCommentList() {
		return commentList;
	     }
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
			
			
			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewCommentSocialBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyCommentSocialDataModel(this.commentSocialList);
		
	}
	
	
	private void initDataSources()
	{
		commentSocialDataSource=new CommentSocialDataSource();
		

			  commentDataSource=new CommentDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.commentSocialList=commentSocialDataSource.getAll();
		lazyModel=new LazyCommentSocialDataModel(this.commentSocialList);
		
	}
	
	
	private void populateData()
	{
		commentSocialList=commentSocialDataSource.getAll();
		

			 commentList=commentDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<CommentSocial> getCommentSocialList() {
		return commentSocialList;
	}
	public void setCommentSocialList(List<CommentSocial> commentSocialList) {
		this.commentSocialList = commentSocialList;
	}
	public LazyDataModel<CommentSocial> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<CommentSocial> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public CommentSocial getSelectedCommentSocial() {
		return selectedCommentSocial;
	}
	public void setSelectedCommentSocial(CommentSocial selectedCommentSocial) {
		this.selectedCommentSocial = selectedCommentSocial;
	}
	
	public List<CommentSocial> getSelectedCommentSocialList() {
		return selectedCommentSocialList;
	}

	public void setSelectedCommentSocialList(
			List<CommentSocial> selectedCommentSocialList) {
		this.selectedCommentSocialList = selectedCommentSocialList;
	}

	public List<CommentSocial> getFilteredCommentSocialList() {
		return filteredCommentSocialList;
	}

	public void setFilteredCommentSocialList(
			List<CommentSocial> filteredCommentSocialList) {
		this.filteredCommentSocialList = filteredCommentSocialList;
	}

	public void newCommentSocial() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createCommentSocial",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("CommentSocial Selected"
				+ ((CommentSocial) event.getObject()).getCommentSocialId());
		for (CommentSocial cat : selectedCommentSocialList) {
			//System.out.println(cat.getCommentSocialLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((CommentSocial) event.getObject()).getCommentSocialId());

	}

	public void deleteSelectedCommentSocial() {
		for (CommentSocial commentSocial : selectedCommentSocialList) {
			//System.out.println(commentSocial.getCommentSocialLabel());
			this.deleteCommentSocial(commentSocial);
		}
	}
	public void deleteCommentSocial(CommentSocial commentSocial) {
			try{
			commentSocialDataSource.delete(commentSocial);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditCommentSocialId() {
		return editCommentSocialId;
	}

	public void setEditCommentSocialId(int editCommentSocialId) {
		this.editCommentSocialId = editCommentSocialId;
	}
	
	public void editCommentSocial(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createCommentSocial",
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



