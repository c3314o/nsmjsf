package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BodAdapter;
import com.nsmjsf.web.datasources.BodDataSource;
import com.nsmjsf.web.datamodels.Bod;
import com.nsmjsf.web.wrappers.BodWrapper;


@FacesConverter("bodWrapperConverter")
public class BodWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BodWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BodDataSource bodDataSource = new BodDataSource();
			Bod bod = bodDataSource.get(Integer.parseInt(value));
			BodWrapper bodWrapper=BodAdapter.wrap(bod);
			return bodWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BodWrapper) object).getBod().getBodId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

