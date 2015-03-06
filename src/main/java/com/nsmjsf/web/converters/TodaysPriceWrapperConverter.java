package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.TodaysPriceAdapter;
import com.nsmjsf.web.datasources.TodaysPriceDataSource;
import com.nsmjsf.web.datamodels.TodaysPrice;
import com.nsmjsf.web.wrappers.TodaysPriceWrapper;

@FacesConverter("todaysPriceWrapperConverter")
public class TodaysPriceWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(TodaysPriceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			TodaysPriceDataSource todaysPriceDataSource = new TodaysPriceDataSource();
			TodaysPrice todaysPrice = todaysPriceDataSource.get(Integer
					.parseInt(value));
			TodaysPriceWrapper todaysPriceWrapper = TodaysPriceAdapter
					.wrap(todaysPrice);
			return todaysPriceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((TodaysPriceWrapper) object)
					.getTodaysPrice().getTodaysPriceId());
		} else {
			return null;
		}
	}

}
