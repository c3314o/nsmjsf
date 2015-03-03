package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.UserStockSalesAdapter;
import com.nsmjsf.web.datasources.UserStockSalesDataSource;
import com.nsmjsf.web.datamodels.UserStockSales;
import com.nsmjsf.web.wrappers.UserStockSalesWrapper;


@FacesConverter("userStockSalesWrapperConverter")
public class UserStockSalesWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(UserStockSalesWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserStockSalesDataSource userStockSalesDataSource = new UserStockSalesDataSource();
			UserStockSales userStockSales = userStockSalesDataSource.get(Integer.parseInt(value));
			UserStockSalesWrapper userStockSalesWrapper=UserStockSalesAdapter.wrap(userStockSales);
			return userStockSalesWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserStockSalesWrapper) object).getUserStockSales().getUserStockSalesId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

