package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.QuarterAdapter;
import com.nsmjsf.web.datasources.QuarterDataSource;
import com.nsmjsf.web.datamodels.Quarter;
import com.nsmjsf.web.wrappers.QuarterWrapper;


@FacesConverter("quarterWrapperConverter")
public class QuarterWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(QuarterWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			QuarterDataSource quarterDataSource = new QuarterDataSource();
			Quarter quarter = quarterDataSource.get(Integer.parseInt(value));
			QuarterWrapper quarterWrapper=QuarterAdapter.wrap(quarter);
			return quarterWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((QuarterWrapper) object).getQuarter().getQuarterId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

