package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CrawlerSettings generated by hbm2java
 */
@Entity
@Table(name = "crawler_settings", catalog = "admin_nsmjsf")
public class CrawlerSettings implements java.io.Serializable {

	private Integer crawlerSettingsId;
	private String crawlerSettingsFor;
	private String crawlerSettingsUrl;
	private String crawlerSettingsTableSelector;
	private String crawlerSettingsHeaderSelector;
	private String crawlerSettingsDataSelector;

	public CrawlerSettings() {
	}

	public CrawlerSettings(String crawlerSettingsFor,
			String crawlerSettingsUrl, String crawlerSettingsTableSelector,
			String crawlerSettingsHeaderSelector,
			String crawlerSettingsDataSelector) {
		this.crawlerSettingsFor = crawlerSettingsFor;
		this.crawlerSettingsUrl = crawlerSettingsUrl;
		this.crawlerSettingsTableSelector = crawlerSettingsTableSelector;
		this.crawlerSettingsHeaderSelector = crawlerSettingsHeaderSelector;
		this.crawlerSettingsDataSelector = crawlerSettingsDataSelector;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "crawler_settings_id", unique = true, nullable = false)
	public Integer getCrawlerSettingsId() {
		return this.crawlerSettingsId;
	}

	public void setCrawlerSettingsId(Integer crawlerSettingsId) {
		this.crawlerSettingsId = crawlerSettingsId;
	}

	@Column(name = "crawler_settings_for", nullable = false)
	public String getCrawlerSettingsFor() {
		return this.crawlerSettingsFor;
	}

	public void setCrawlerSettingsFor(String crawlerSettingsFor) {
		this.crawlerSettingsFor = crawlerSettingsFor;
	}

	@Column(name = "crawler_settings_url", nullable = false)
	public String getCrawlerSettingsUrl() {
		return this.crawlerSettingsUrl;
	}

	public void setCrawlerSettingsUrl(String crawlerSettingsUrl) {
		this.crawlerSettingsUrl = crawlerSettingsUrl;
	}

	@Column(name = "crawler_settings_table_selector", nullable = false)
	public String getCrawlerSettingsTableSelector() {
		return this.crawlerSettingsTableSelector;
	}

	public void setCrawlerSettingsTableSelector(
			String crawlerSettingsTableSelector) {
		this.crawlerSettingsTableSelector = crawlerSettingsTableSelector;
	}

	@Column(name = "crawler_settings_header_selector", nullable = false)
	public String getCrawlerSettingsHeaderSelector() {
		return this.crawlerSettingsHeaderSelector;
	}

	public void setCrawlerSettingsHeaderSelector(
			String crawlerSettingsHeaderSelector) {
		this.crawlerSettingsHeaderSelector = crawlerSettingsHeaderSelector;
	}

	@Column(name = "crawler_settings_data_selector", nullable = false)
	public String getCrawlerSettingsDataSelector() {
		return this.crawlerSettingsDataSelector;
	}

	public void setCrawlerSettingsDataSelector(
			String crawlerSettingsDataSelector) {
		this.crawlerSettingsDataSelector = crawlerSettingsDataSelector;
	}

}
