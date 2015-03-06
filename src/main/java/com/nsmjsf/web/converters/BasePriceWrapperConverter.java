package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.BasePriceAdapter;
import com.nsmjsf.web.datasources.BasePriceDataSource;
import com.nsmjsf.web.datamodels.BasePrice;
import com.nsmjsf.web.wrappers.BasePriceWrapper;

@FacesConverter("basePriceWrapperConverter")
public class BasePriceWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(BasePriceWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BasePriceDataSource basePriceDataSource = new BasePriceDataSource();
			BasePrice basePrice = basePriceDataSource.get(Integer
					.parseInt(value));
			BasePriceWrapper basePriceWrapper = BasePriceAdapter
					.wrap(basePrice);
			return basePriceWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BasePriceWrapper) object).getBasePrice()
					.getBasePriceId());
		} else {
			return null;
		}
	}

}
