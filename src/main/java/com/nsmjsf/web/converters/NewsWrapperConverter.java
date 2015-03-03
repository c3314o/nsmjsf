package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.NewsAdapter;
import com.nsmjsf.web.datasources.NewsDataSource;
import com.nsmjsf.web.datamodels.News;
import com.nsmjsf.web.wrappers.NewsWrapper;


@FacesConverter("newsWrapperConverter")
public class NewsWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(NewsWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			NewsDataSource newsDataSource = new NewsDataSource();
			News news = newsDataSource.get(Integer.parseInt(value));
			NewsWrapper newsWrapper=NewsAdapter.wrap(news);
			return newsWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((NewsWrapper) object).getNews().getNewsId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

