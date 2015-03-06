package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CrawlerSettings;

public class CrawlerSettingsWrapper {

	private static final Log log = LogFactory
			.getLog(CrawlerSettingsWrapper.class);

	CrawlerSettings crawlerSettings;

	public CrawlerSettingsWrapper(CrawlerSettings crawlerSettings) {
		this.crawlerSettings = crawlerSettings;
	}

	public CrawlerSettingsWrapper() {

	}

	public CrawlerSettings getCrawlerSettings() {
		return crawlerSettings;
	}

	public void setCrawlerSettings(CrawlerSettings crawlerSettings) {
		this.crawlerSettings = crawlerSettings;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.crawlerSettings.getCrawlerSettingsId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CrawlerSettingsWrapper other = (CrawlerSettingsWrapper) obj;
		if (!Objects.equals(this.crawlerSettings.getCrawlerSettingsId(), other
				.getCrawlerSettings().getCrawlerSettingsId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.crawlerSettings.toString();

	}

}
