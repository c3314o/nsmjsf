package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.TodaysPriceDumpAdapter;
import com.nsmjsf.web.datasources.TodaysPriceDumpDataSource;
import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.wrappers.TodaysPriceDumpWrapper;

@FacesConverter("todaysPriceDumpWrapperConverter")
public class TodaysPriceDumpWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(TodaysPriceDumpWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			TodaysPriceDumpDataSource todaysPriceDumpDataSource = new TodaysPriceDumpDataSource();
			TodaysPriceDump todaysPriceDump = todaysPriceDumpDataSource
					.get(Integer.parseInt(value));
			TodaysPriceDumpWrapper todaysPriceDumpWrapper = TodaysPriceDumpAdapter
					.wrap(todaysPriceDump);
			return todaysPriceDumpWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((TodaysPriceDumpWrapper) object)
					.getTodaysPriceDump().getTodaysPriceDumpId());
		} else {
			return null;
		}
	}

}
