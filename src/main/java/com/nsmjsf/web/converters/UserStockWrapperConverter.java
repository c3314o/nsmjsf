package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserStockAdapter;
import com.nsmjsf.web.datasources.UserStockDataSource;
import com.nsmjsf.web.datamodels.UserStock;
import com.nsmjsf.web.wrappers.UserStockWrapper;

@FacesConverter("userStockWrapperConverter")
public class UserStockWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserStockWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserStockDataSource userStockDataSource = new UserStockDataSource();
			UserStock userStock = userStockDataSource.get(Integer
					.parseInt(value));
			UserStockWrapper userStockWrapper = UserStockAdapter
					.wrap(userStock);
			return userStockWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserStockWrapper) object).getUserStock()
					.getUserStockId());
		} else {
			return null;
		}
	}

}
