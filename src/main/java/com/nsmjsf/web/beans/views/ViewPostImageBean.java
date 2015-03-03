
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

import com.nsmjsf.web.datasources.PostImageDataSource;
import com.nsmjsf.web.datamodels.PostImage;
import com.nsmjsf.web.lazymodels.LazyPostImageDataModel;


			
import com.nsmjsf.web.adapters.PostAdapter;


import com.nsmjsf.web.datasources.PostDataSource;

import com.nsmjsf.web.datamodels.Post;

import com.nsmjsf.web.wrappers.PostWrapper;



			
				   
@ManagedBean
@ViewScoped
public class ViewPostImageBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewPostImageBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<PostImage> postImageList;
    List<PostImage> selectedPostImageList;
	List<PostImage> filteredPostImageList;
	PostImage selectedPostImage;
	LazyDataModel<PostImage> lazyModel;
	PostImageDataSource postImageDataSource;
	int editPostImageId=0;
	

			   List<Post> postList;
			   PostDataSource postDataSource;
			   public List<Post> getPostList() {
		return postList;
	     }
	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}
			
				   
	
	
	public ViewPostImageBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyPostImageDataModel(this.postImageList);
		
	}
	
	
	private void initDataSources()
	{
		postImageDataSource=new PostImageDataSource();
		

			  postDataSource=new PostDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.postImageList=postImageDataSource.getAll();
		lazyModel=new LazyPostImageDataModel(this.postImageList);
		
	}
	
	
	private void populateData()
	{
		postImageList=postImageDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
				   
	
		
			}
	public List<PostImage> getPostImageList() {
		return postImageList;
	}
	public void setPostImageList(List<PostImage> postImageList) {
		this.postImageList = postImageList;
	}
	public LazyDataModel<PostImage> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<PostImage> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public PostImage getSelectedPostImage() {
		return selectedPostImage;
	}
	public void setSelectedPostImage(PostImage selectedPostImage) {
		this.selectedPostImage = selectedPostImage;
	}
	
	public List<PostImage> getSelectedPostImageList() {
		return selectedPostImageList;
	}

	public void setSelectedPostImageList(
			List<PostImage> selectedPostImageList) {
		this.selectedPostImageList = selectedPostImageList;
	}

	public List<PostImage> getFilteredPostImageList() {
		return filteredPostImageList;
	}

	public void setFilteredPostImageList(
			List<PostImage> filteredPostImageList) {
		this.filteredPostImageList = filteredPostImageList;
	}

	public void newPostImage() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createPostImage",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("PostImage Selected"
				+ ((PostImage) event.getObject()).getPostImageId());
		for (PostImage cat : selectedPostImageList) {
			//System.out.println(cat.getPostImageLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((PostImage) event.getObject()).getPostImageId());

	}

	public void deleteSelectedPostImage() {
		for (PostImage postImage : selectedPostImageList) {
			//System.out.println(postImage.getPostImageLabel());
			this.deletePostImage(postImage);
		}
	}
	public void deletePostImage(PostImage postImage) {
			try{
			postImageDataSource.delete(postImage);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditPostImageId() {
		return editPostImageId;
	}

	public void setEditPostImageId(int editPostImageId) {
		this.editPostImageId = editPostImageId;
	}
	
	public void editPostImage(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createPostImage",
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



