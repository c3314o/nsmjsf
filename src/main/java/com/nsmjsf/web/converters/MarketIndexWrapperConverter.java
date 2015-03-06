package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.MarketIndexAdapter;
import com.nsmjsf.web.datasources.MarketIndexDataSource;
import com.nsmjsf.web.datamodels.MarketIndex;
import com.nsmjsf.web.wrappers.MarketIndexWrapper;

@FacesConverter("marketIndexWrapperConverter")
public class MarketIndexWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(MarketIndexWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			MarketIndexDataSource marketIndexDataSource = new MarketIndexDataSource();
			MarketIndex marketIndex = marketIndexDataSource.get(Integer
					.parseInt(value));
			MarketIndexWrapper marketIndexWrapper = MarketIndexAdapter
					.wrap(marketIndex);
			return marketIndexWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((MarketIndexWrapper) object)
					.getMarketIndex().getMarketIndexId());
		} else {
			return null;
		}
	}

}
