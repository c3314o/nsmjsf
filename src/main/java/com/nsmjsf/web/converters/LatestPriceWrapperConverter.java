package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.LatestPriceAdapter;
import com.nsmjsf.web.datasources.LatestPriceDataSource;
import com.nsmjsf.web.datamodels.LatestPrice;
import com.nsmjsf.web.wrappers.LatestPriceWrapper;

@FacesConverter("latestPriceWrapperConverter")
public class LatestPriceWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(LatestPriceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			LatestPriceDataSource latestPriceDataSource = new LatestPriceDataSource();
			LatestPrice latestPrice = latestPriceDataSource.get(Integer
					.parseInt(value));
			LatestPriceWrapper latestPriceWrapper = LatestPriceAdapter
					.wrap(latestPrice);
			return latestPriceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((LatestPriceWrapper) object)
					.getLatestPrice().getLatestPriceId());
		} else {
			return null;
		}
	}

}
