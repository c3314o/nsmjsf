package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.FiscalYearAdapter;
import com.nsmjsf.web.datasources.FiscalYearDataSource;
import com.nsmjsf.web.datamodels.FiscalYear;
import com.nsmjsf.web.wrappers.FiscalYearWrapper;


@FacesConverter("fiscalYearWrapperConverter")
public class FiscalYearWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(FiscalYearWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			FiscalYearDataSource fiscalYearDataSource = new FiscalYearDataSource();
			FiscalYear fiscalYear = fiscalYearDataSource.get(Integer.parseInt(value));
			FiscalYearWrapper fiscalYearWrapper=FiscalYearAdapter.wrap(fiscalYear);
			return fiscalYearWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((FiscalYearWrapper) object).getFiscalYear().getFiscalYearId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

