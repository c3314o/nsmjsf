package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.MonthAdapter;
import com.nsmjsf.web.datasources.MonthDataSource;
import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.wrappers.MonthWrapper;

@FacesConverter("monthWrapperConverter")
public class MonthWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(MonthWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MonthDataSource monthDataSource = new MonthDataSource();
			Month month = monthDataSource.get(Integer.parseInt(value));
			MonthWrapper monthWrapper = MonthAdapter.wrap(month);
			return monthWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MonthWrapper) object).getMonth()
					.getMonthId());
		} else {
			return null;
		}
	}

}
