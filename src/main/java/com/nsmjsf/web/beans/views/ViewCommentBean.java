
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

import com.nsmjsf.web.datasources.CommentDataSource;
import com.nsmjsf.web.datamodels.Comment;
import com.nsmjsf.web.lazymodels.LazyCommentDataModel;


			
import com.nsmjsf.web.adapters.CommentSocialAdapter;


import com.nsmjsf.web.datasources.CommentSocialDataSource;

import com.nsmjsf.web.datamodels.CommentSocial;

import com.nsmjsf.web.wrappers.CommentSocialWrapper;



			
			
			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
			
			
import com.nsmjsf.web.adapters.UserAdapter;


import com.nsmjsf.web.datasources.UserDataSource;

import com.nsmjsf.web.datamodels.User;

import com.nsmjsf.web.wrappers.UserWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewCommentBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewCommentBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Comment> commentList;
    List<Comment> selectedCommentList;
	List<Comment> filteredCommentList;
	Comment selectedComment;
	LazyDataModel<Comment> lazyModel;
	CommentDataSource commentDataSource;
	int editCommentId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
			
			   List<User> userList;
			   UserDataSource userDataSource;
			   public List<User> getUserList() {
		return userList;
	     }
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
			
				   
	
	
	public ViewCommentBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyCommentDataModel(this.commentList);
		
	}
	
	
	private void initDataSources()
	{
		commentDataSource=new CommentDataSource();
		

			  postDataSource=new PostDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.commentList=commentDataSource.getAll();
		lazyModel=new LazyCommentDataModel(this.commentList);
		
	}
	
	
	private void populateData()
	{
		commentList=commentDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<Comment> getCommentList() {
		return commentList;
	}
	public void setCommentList(List<Comment> commentList) {
		this.commentList = commentList;
	}
	public LazyDataModel<Comment> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Comment> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Comment getSelectedComment() {
		return selectedComment;
	}
	public void setSelectedComment(Comment selectedComment) {
		this.selectedComment = selectedComment;
	}
	
	public List<Comment> getSelectedCommentList() {
		return selectedCommentList;
	}

	public void setSelectedCommentList(
			List<Comment> selectedCommentList) {
		this.selectedCommentList = selectedCommentList;
	}

	public List<Comment> getFilteredCommentList() {
		return filteredCommentList;
	}

	public void setFilteredCommentList(
			List<Comment> filteredCommentList) {
		this.filteredCommentList = filteredCommentList;
	}

	public void newComment() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createComment",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Comment Selected"
				+ ((Comment) event.getObject()).getCommentId());
		for (Comment cat : selectedCommentList) {
			//System.out.println(cat.getCommentLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Comment) event.getObject()).getCommentId());

	}

	public void deleteSelectedComment() {
		for (Comment comment : selectedCommentList) {
			//System.out.println(comment.getCommentLabel());
			this.deleteComment(comment);
		}
	}
	public void deleteComment(Comment comment) {
			try{
			commentDataSource.delete(comment);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditCommentId() {
		return editCommentId;
	}

	public void setEditCommentId(int editCommentId) {
		this.editCommentId = editCommentId;
	}
	
	public void editComment(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createComment",
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



