package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.CurrencyRateAdapter;
import com.nsmjsf.web.datasources.CurrencyRateDataSource;
import com.nsmjsf.web.datamodels.CurrencyRate;
import com.nsmjsf.web.wrappers.CurrencyRateWrapper;

@FacesConverter("currencyRateWrapperConverter")
public class CurrencyRateWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(CurrencyRateWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CurrencyRateDataSource currencyRateDataSource = new CurrencyRateDataSource();
			CurrencyRate currencyRate = currencyRateDataSource.get(Integer
					.parseInt(value));
			CurrencyRateWrapper currencyRateWrapper = CurrencyRateAdapter
					.wrap(currencyRate);
			return currencyRateWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CurrencyRateWrapper) object)
					.getCurrencyRate().getCurrencyRateId());
		} else {
			return null;
		}
	}

}
