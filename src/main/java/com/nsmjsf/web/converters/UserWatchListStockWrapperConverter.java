package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserWatchListStockAdapter;
import com.nsmjsf.web.datasources.UserWatchListStockDataSource;
import com.nsmjsf.web.datamodels.UserWatchListStock;
import com.nsmjsf.web.wrappers.UserWatchListStockWrapper;

@FacesConverter("userWatchListStockWrapperConverter")
public class UserWatchListStockWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserWatchListStockWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserWatchListStockDataSource userWatchListStockDataSource = new UserWatchListStockDataSource();
			UserWatchListStock userWatchListStock = userWatchListStockDataSource
					.get(Integer.parseInt(value));
			UserWatchListStockWrapper userWatchListStockWrapper = UserWatchListStockAdapter
					.wrap(userWatchListStock);
			return userWatchListStockWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserWatchListStockWrapper) object)
					.getUserWatchListStock().getUserWatchListStockId());
		} else {
			return null;
		}
	}

}
