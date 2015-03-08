package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;

import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;

import org.primefaces.context.RequestContext;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datasources.MarketIndexDataSource;
import com.nsmjsf.web.datamodels.MarketIndex;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.IndexTypeAdapter;
import com.nsmjsf.web.datasources.IndexTypeDataSource;
import com.nsmjsf.web.datamodels.IndexType;
import com.nsmjsf.web.wrappers.IndexTypeWrapper;
import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateMarketIndexBean implements Serializable {

	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;

	private static final Log log = LogFactory
			.getLog(CreateMarketIndexBean.class);

	private MarketIndex marketIndex;
	private MarketIndexDataSource marketIndexDataSource;

	private IndexTypeDataSource indexTypeDataSource;
	private List<IndexTypeWrapper> indexTypeWrapperList;
	private List<IndexType> indexTypeList;
	private IndexTypeWrapper selectedIndexTypeWrapper;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateMarketIndexBean() {

		marketIndex = new MarketIndex();
		/* init datasources */
		marketIndexDataSource = new MarketIndexDataSource();

		indexTypeDataSource = new IndexTypeDataSource();

		/* init option wrappers */
		indexTypeList = indexTypeDataSource.getAll();
		indexTypeWrapperList = IndexTypeAdapter.wrapAll(indexTypeList);

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.marketIndex = marketIndexDataSource.get(editId);

			this.selectedIndexTypeWrapper = IndexTypeAdapter.wrap(marketIndex
					.getIndexType());

			this.selectedPostWrapper = PostAdapter.wrap(marketIndex.getPost());

		}
	}

	private void extractParams() {
		int editId = ParameterManager.getInt("editId");
		if (editId != 0) {
			this.editId = editId;
			this.editMode = true;
			System.out.println("EditId" + editId);
		}
	}

	public MarketIndex getMarketIndex() {
		return marketIndex;
	}

	public void setMarketIndex(MarketIndex marketIndex) {
		this.marketIndex = marketIndex;
	}

	/* getters for datasources */
	public MarketIndexDataSource getMarketIndexDataSource() {
		return marketIndexDataSource;
	}

	public void setMarketIndexDataSource(
			MarketIndexDataSource marketIndexDataSource) {
		this.marketIndexDataSource = marketIndexDataSource;
	}

	public List<IndexType> getIndexTypeList() {
		return indexTypeList;
	}

	public void setIndexTypeList(List<IndexType> indexTypeList) {
		this.indexTypeList = indexTypeList;
	}

	public IndexTypeDataSource getIndexTypeDataSource() {
		return indexTypeDataSource;
	}

	public void setIndexTypeDataSource(IndexTypeDataSource indexTypeDataSource) {
		this.indexTypeDataSource = indexTypeDataSource;
	}

	public List<IndexTypeWrapper> getIndexTypeWrapperList() {
		return indexTypeWrapperList;
	}

	public void setIndexTypeWrapperList(
			List<IndexTypeWrapper> indexTypeWrapperList) {
		this.indexTypeWrapperList = indexTypeWrapperList;
	}

	public IndexTypeWrapper getSelectedIndexTypeWrapper() {
		return selectedIndexTypeWrapper;
	}

	public void setSelectedIndexTypeWrapper(
			IndexTypeWrapper selectedIndexTypeWrapper) {
		this.selectedIndexTypeWrapper = selectedIndexTypeWrapper;
	}

	public List<Post> getPostList() {
		return postList;
	}

	public void setPostList(List<Post> postList) {
		this.postList = postList;
	}

	public PostDataSource getPostDataSource() {
		return postDataSource;
	}

	public void setPostDataSource(PostDataSource postDataSource) {
		this.postDataSource = postDataSource;
	}

	public List<PostWrapper> getPostWrapperList() {
		return postWrapperList;
	}

	public void setPostWrapperList(List<PostWrapper> postWrapperList) {
		this.postWrapperList = postWrapperList;
	}

	public PostWrapper getSelectedPostWrapper() {
		return selectedPostWrapper;
	}

	public void setSelectedPostWrapper(PostWrapper selectedPostWrapper) {
		this.selectedPostWrapper = selectedPostWrapper;
	}

	public MarketIndex saveMarketIndex() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			IndexType indexType = selectedIndexTypeWrapper.getIndexType();

			marketIndex.setIndexType(indexType);

			Post post = createPostBean.savePost(session);

			marketIndex.setPost(post);

			marketIndexDataSource.create(marketIndex, session);
			tx.commit();
			MessageService.info("Successfully Saved  MarketIndex !");
			this.marketIndex = new MarketIndex();
			return marketIndex;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving MarketIndex .Try Again Later!");
			return null;
		}
	}

	public MarketIndex updateMarketIndex() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			IndexType indexType = selectedIndexTypeWrapper.getIndexType();

			marketIndex.setIndexType(indexType);

			Post post = createPostBean.savePost(session);

			marketIndex.setPost(post);

			marketIndexDataSource.create(marketIndex, session);
			tx.commit();
			MessageService.info("Successfully Saved  MarketIndex !");
			this.marketIndex = new MarketIndex();
			return marketIndex;

		} catch (Exception ex) {
			MessageService.error("Failed Saving MarketIndex .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateMarketIndex();
		} else {
			log.info("Creating value");
			saveMarketIndex();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createMarketIndex");

	}

	public MarketIndex saveMarketIndex(Session session) {

		this.marketIndex = marketIndexDataSource.create(this.marketIndex,
				session);
		return this.marketIndex;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}
}
