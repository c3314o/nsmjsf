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
import com.nsmjsf.web.datasources.CrawlerSettingsDataSource;
import com.nsmjsf.web.datamodels.CrawlerSettings;
import com.nsmjsf.web.utils.ParameterManager;

/*imports  */

@ManagedBean
@ViewScoped
public class CreateCrawlerSettingsBean implements Serializable {

	private static final Log log = LogFactory
			.getLog(CreateCrawlerSettingsBean.class);

	private CrawlerSettings crawlerSettings;
	private CrawlerSettingsDataSource crawlerSettingsDataSource;

	private int editId = 0;
	private boolean editMode = false;

	public CreateCrawlerSettingsBean() {

		crawlerSettings = new CrawlerSettings();
		/* init datasources */
		crawlerSettingsDataSource = new CrawlerSettingsDataSource();

	}

	@PostConstruct
	private void init() {
		extractParams();
		if (this.editMode) {
			this.crawlerSettings = crawlerSettingsDataSource.get(editId);

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

	public CrawlerSettings getCrawlerSettings() {
		return crawlerSettings;
	}

	public void setCrawlerSettings(CrawlerSettings crawlerSettings) {
		this.crawlerSettings = crawlerSettings;
	}

	/* getters for datasources */
	public CrawlerSettingsDataSource getCrawlerSettingsDataSource() {
		return crawlerSettingsDataSource;
	}

	public void setCrawlerSettingsDataSource(
			CrawlerSettingsDataSource crawlerSettingsDataSource) {
		this.crawlerSettingsDataSource = crawlerSettingsDataSource;
	}

	public CrawlerSettings saveCrawlerSettings() {
		try {

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			crawlerSettingsDataSource.create(crawlerSettings, session);
			tx.commit();
			MessageService.info("Successfully Saved  CrawlerSettings !");
			this.crawlerSettings = new CrawlerSettings();
			return crawlerSettings;

		} catch (Exception ex) {
			log.error(ex.getMessage());
			MessageService
					.error("Failed Saving CrawlerSettings .Try Again Later!");
			return null;
		}
	}

	public CrawlerSettings updateCrawlerSettings() {
		try {
			log.info("Starting to update....");

			Session session = DbSessionManager.getUserDbsession().getSession();
			Transaction tx = session.beginTransaction();

			crawlerSettingsDataSource.create(crawlerSettings, session);
			tx.commit();
			MessageService.info("Successfully Saved  CrawlerSettings !");
			this.crawlerSettings = new CrawlerSettings();
			return crawlerSettings;

		} catch (Exception ex) {
			MessageService
					.error("Failed Saving CrawlerSettings .Try Again Later!");
			log.error(ex.getMessage());
			return null;
		}
	}

	public void saveOrUpdate() {

		if (this.editMode) {
			log.info("Updating value");
			updateCrawlerSettings();
		} else {
			log.info("Creating value");
			saveCrawlerSettings();
		}
	}

	public void cancel() {
		RequestContext.getCurrentInstance()
				.closeDialog("createCrawlerSettings");

	}

	public CrawlerSettings saveCrawlerSettings(Session session) {

		this.crawlerSettings = crawlerSettingsDataSource.create(
				this.crawlerSettings, session);
		return this.crawlerSettings;
	}

}
