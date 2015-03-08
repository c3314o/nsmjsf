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

import com.nsmjsf.web.datasources.BrokerDataSource;
import com.nsmjsf.web.datamodels.Broker;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */

import com.nsmjsf.web.adapters.PostAdapter;
import com.nsmjsf.web.datasources.PostDataSource;
import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

@ManagedBean
@ViewScoped
public class CreateBrokerBean implements Serializable {
	
	@ManagedProperty(value = "#{createPostBean}")
	private CreatePostBean createPostBean;

	private static final Log log = LogFactory.getLog(CreateBrokerBean.class);

	private Broker broker;
	private BrokerDataSource brokerDataSource;

	private PostDataSource postDataSource;
	private List<PostWrapper> postWrapperList;
	private List<Post> postList;
	private PostWrapper selectedPostWrapper;

	private int editId = 0;
	private boolean editMode = false;

	public CreateBrokerBean() {

		broker = new Broker();
		/* init datasources */
		brokerDataSource = new BrokerDataSource();

		postDataSource = new PostDataSource();

		/* init option wrappers */
		postList = postDataSource.getAll();
		postWrapperList = PostAdapter.wrapAll(postList);

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.broker = brokerDataSource.get(editId);

			this.selectedPostWrapper = PostAdapter.wrap(broker.getPost());

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

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	/* getters for datasources */
	public BrokerDataSource getBrokerDataSource() {
		return brokerDataSource;
	}

	public void setBrokerDataSource(BrokerDataSource brokerDataSource) {
		this.brokerDataSource = brokerDataSource;
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

	public Broker saveBroker() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			broker.setPost(post);

			brokerDataSource.create(broker, session);
			tx.commit();
			MessageService.info("Successfully Saved  Broker !");
			this.broker = new Broker();
			return broker;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService.error("Failed Saving Broker .Try Again Later!");
			return null;
		}
	}

	public Broker updateBroker() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			Post post = createPostBean.savePost(session);

			broker.setPost(post);

			brokerDataSource.create(broker, session);
			tx.commit();
			MessageService.info("Successfully Saved  Broker !");
			this.broker = new Broker();
			return broker;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Broker .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateBroker();
		} else {
			log.info("Creating value");
			saveBroker();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance().closeDialog("createBroker");

	}

	public Broker saveBroker(Session session) {

		this.broker = brokerDataSource.create(this.broker, session);
		return this.broker;
	}

	public CreatePostBean getCreatePostBean() {
		return createPostBean;
	}

	public void setCreatePostBean(CreatePostBean createPostBean) {
		this.createPostBean = createPostBean;
	}

}
