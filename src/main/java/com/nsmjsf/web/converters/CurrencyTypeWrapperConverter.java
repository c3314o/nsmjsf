package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.CurrencyTypeAdapter;
import com.nsmjsf.web.datasources.CurrencyTypeDataSource;
import com.nsmjsf.web.datamodels.CurrencyType;
import com.nsmjsf.web.wrappers.CurrencyTypeWrapper;


@FacesConverter("currencyTypeWrapperConverter")
public class CurrencyTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(CurrencyTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CurrencyTypeDataSource currencyTypeDataSource = new CurrencyTypeDataSource();
			CurrencyType currencyType = currencyTypeDataSource.get(Integer.parseInt(value));
			CurrencyTypeWrapper currencyTypeWrapper=CurrencyTypeAdapter.wrap(currencyType);
			return currencyTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CurrencyTypeWrapper) object).getCurrencyType().getCurrencyTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

