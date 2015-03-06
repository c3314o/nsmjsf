package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.CrawlerSettingsAdapter;
import com.nsmjsf.web.datasources.CrawlerSettingsDataSource;
import com.nsmjsf.web.datamodels.CrawlerSettings;
import com.nsmjsf.web.wrappers.CrawlerSettingsWrapper;

@FacesConverter("crawlerSettingsWrapperConverter")
public class CrawlerSettingsWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(CrawlerSettingsWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CrawlerSettingsDataSource crawlerSettingsDataSource = new CrawlerSettingsDataSource();
			CrawlerSettings crawlerSettings = crawlerSettingsDataSource
					.get(Integer.parseInt(value));
			CrawlerSettingsWrapper crawlerSettingsWrapper = CrawlerSettingsAdapter
					.wrap(crawlerSettings);
			return crawlerSettingsWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CrawlerSettingsWrapper) object)
					.getCrawlerSettings().getCrawlerSettingsId());
		} else {
			return null;
		}
	}

}
