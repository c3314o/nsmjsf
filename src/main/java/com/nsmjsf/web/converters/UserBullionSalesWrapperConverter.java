package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.UserBullionSalesAdapter;
import com.nsmjsf.web.datasources.UserBullionSalesDataSource;
import com.nsmjsf.web.datamodels.UserBullionSales;
import com.nsmjsf.web.wrappers.UserBullionSalesWrapper;


@FacesConverter("userBullionSalesWrapperConverter")
public class UserBullionSalesWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(UserBullionSalesWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserBullionSalesDataSource userBullionSalesDataSource = new UserBullionSalesDataSource();
			UserBullionSales userBullionSales = userBullionSalesDataSource.get(Integer.parseInt(value));
			UserBullionSalesWrapper userBullionSalesWrapper=UserBullionSalesAdapter.wrap(userBullionSales);
			return userBullionSalesWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserBullionSalesWrapper) object).getUserBullionSales().getUserBullionSalesId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

