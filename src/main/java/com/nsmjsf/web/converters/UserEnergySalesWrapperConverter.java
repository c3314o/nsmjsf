package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.UserEnergySalesAdapter;
import com.nsmjsf.web.datasources.UserEnergySalesDataSource;
import com.nsmjsf.web.datamodels.UserEnergySales;
import com.nsmjsf.web.wrappers.UserEnergySalesWrapper;

@FacesConverter("userEnergySalesWrapperConverter")
public class UserEnergySalesWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(UserEnergySalesWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserEnergySalesDataSource userEnergySalesDataSource = new UserEnergySalesDataSource();
			UserEnergySales userEnergySales = userEnergySalesDataSource
					.get(Integer.parseInt(value));
			UserEnergySalesWrapper userEnergySalesWrapper = UserEnergySalesAdapter
					.wrap(userEnergySales);
			return userEnergySalesWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserEnergySalesWrapper) object)
					.getUserEnergySales().getUserEnergySalesId());
		} else {
			return null;
		}
	}

}
