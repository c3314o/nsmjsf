
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

import com.nsmjsf.web.datasources.ArticleDataSource;
import com.nsmjsf.web.datamodels.Article;
import com.nsmjsf.web.lazymodels.LazyArticleDataModel;


			
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
public class ViewArticleBean implements Serializable {
private static final Log log = LogFactory.getLog(ViewArticleBean.class);
 ViewType viewType=ViewType.DATATABLE;
 

	List<Article> articleList;
    List<Article> selectedArticleList;
	List<Article> filteredArticleList;
	Article selectedArticle;
	LazyDataModel<Article> lazyModel;
	ArticleDataSource articleDataSource;
	int editArticleId=0;
	

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
			
				   
	
	
	public ViewArticleBean()
	{
		this.initDataSources();
		this.populateData();
		
		lazyModel=new LazyArticleDataModel(this.articleList);
		
	}
	
	
	private void initDataSources()
	{
		articleDataSource=new ArticleDataSource();
		

			  postDataSource=new PostDataSource();
			
			
			  userDataSource=new UserDataSource();
			
				   
	
		
	}
	
	public void refreshDataSource(){
		this.articleList=articleDataSource.getAll();
		lazyModel=new LazyArticleDataModel(this.articleList);
		
	}
	
	
	private void populateData()
	{
		articleList=articleDataSource.getAll();
		

			 postList=postDataSource.getAll();
	
			
			 userList=userDataSource.getAll();
	
				   
	
		
			}
	public List<Article> getArticleList() {
		return articleList;
	}
	public void setArticleList(List<Article> articleList) {
		this.articleList = articleList;
	}
	public LazyDataModel<Article> getLazyModel() {
		return lazyModel;
	}
	public void setLazyModel(LazyDataModel<Article> lazyModel) {
		this.lazyModel = lazyModel;
	}
	public Article getSelectedArticle() {
		return selectedArticle;
	}
	public void setSelectedArticle(Article selectedArticle) {
		this.selectedArticle = selectedArticle;
	}
	
	public List<Article> getSelectedArticleList() {
		return selectedArticleList;
	}

	public void setSelectedArticleList(
			List<Article> selectedArticleList) {
		this.selectedArticleList = selectedArticleList;
	}

	public List<Article> getFilteredArticleList() {
		return filteredArticleList;
	}

	public void setFilteredArticleList(
			List<Article> filteredArticleList) {
		this.filteredArticleList = filteredArticleList;
	}

	public void newArticle() {
		System.out.println("Opening dialogue");
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);

		RequestContext.getCurrentInstance().openDialog("createArticle",
				options, null);

	}
	
	public void onRowSelect(SelectEvent event) {
		System.out.println("Article Selected"
				+ ((Article) event.getObject()).getArticleId());
		for (Article cat : selectedArticleList) {
			//System.out.println(cat.getArticleLabel());
		}

	}

	public void onRowUnselect(UnselectEvent event) {
		System.out.println("Car unSelected"
				+ ((Article) event.getObject()).getArticleId());

	}

	public void deleteSelectedArticle() {
		for (Article article : selectedArticleList) {
			//System.out.println(article.getArticleLabel());
			this.deleteArticle(article);
		}
	}
	public void deleteArticle(Article article) {
			try{
			articleDataSource.delete(article);
			this.refreshDataSource();
			}catch(Exception ex)
			{
				log.info(ex.getMessage());
			}
		
	}
/*----------------------------------------*/
	public int getEditArticleId() {
		return editArticleId;
	}

	public void setEditArticleId(int editArticleId) {
		this.editArticleId = editArticleId;
	}
	
	public void editArticle(int editId)
	{
		Map<String,List<String>> params = new HashMap<String,List<String>>();
		Map<String,Object> options = new HashMap<String, Object>();
		List<String> list = new ArrayList<String>();
		String seditId=String.valueOf(editId);
		options.put("modal", true);
		list.add(seditId);
		params.put("editId", list);
		RequestContext.getCurrentInstance().openDialog("createArticle",
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



