package com.nsmjsf.web.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import com.nsmjsf.web.datalayer.DbSessionManager;
import com.nsmjsf.web.messaging.MessageService;
import org.primefaces.context.RequestContext;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.nsmjsf.web.datasources.ProxyDataSource;
import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.utils.ParameterManager;
/*imports  */	   

@ManagedBean
@ViewScoped

public class CreateProxyBean implements Serializable {

private static final Log log = LogFactory
			.getLog(CreateProxyBean.class);


	private Proxy proxy;
	private ProxyDataSource proxyDataSource;
	
	
	
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  
			
		
			
			
			
	  	   
	
	
	private int editId=0;
	private boolean editMode=false;	
	
	
	
	
	
	

	public CreateProxyBean() {

		proxy = new Proxy();
		/* init datasources */
		proxyDataSource = new ProxyDataSource();
		
			
		
		

	}
	
	@PostConstruct
	private void init()
	{
		extractParams();
		if(this.editMode)
		{
			this.proxy=proxyDataSource.get(editId);
			
			
	   
			
			
			
			
		}
	}
	private void extractParams()
	{
		int editId = ParameterManager.getInt("editId");
		if(editId!=0)
		{
			this.editId=editId;
			this.editMode=true;
			System.out.println("EditId"+editId);
		}
	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	/* getters for datasources */
	public ProxyDataSource getProxyDataSource() {
		return proxyDataSource;
	}

	public void setProxyDataSource(ProxyDataSource proxyDataSource) {
		this.proxyDataSource = proxyDataSource;
	}
	
	
	
	
	
	
		




	
  
  
  
	public Proxy saveProxy() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			proxyDataSource.create(proxy, session);
			tx.commit();
					MessageService.info("Successfully Saved  Proxy !");
				this.proxy=new Proxy();
			return proxy;

		} catch (Exception ex) {
		log.error(ex.getMessage());
			MessageService.error("Failed Saving Proxy .Try Again Later!");
			return null;
		}
	}
	
	public Proxy updateProxy() {
		try {
		log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();
			
				   
			
			
			
			
			proxyDataSource.create(proxy, session);
			tx.commit();
				MessageService.info("Successfully Saved  Proxy !");
				this.proxy=new Proxy();
			return proxy;

		} catch (Exception ex) {
			MessageService.error("Failed Saving Proxy .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}
	
	public void saveOrUpdate(){
	
	if(this.editMode)
		{
		log.info("Updating value");
			updateProxy();
		}else{
		log.info("Creating value");
			saveProxy();
		}
	}
	public void cancel()
	{
	    RequestContext.getCurrentInstance().closeDialog("createProxy");
		
	}
	public Proxy saveProxy(Session session){
	
	   this.proxy= proxyDataSource.create(this.proxy,session);
	   return this.proxy;
	}
	

}

