package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.NewHighAdapter;
import com.nsmjsf.web.datasources.NewHighDataSource;
import com.nsmjsf.web.datamodels.NewHigh;
import com.nsmjsf.web.wrappers.NewHighWrapper;


@FacesConverter("newHighWrapperConverter")
public class NewHighWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(NewHighWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			NewHighDataSource newHighDataSource = new NewHighDataSource();
			NewHigh newHigh = newHighDataSource.get(Integer.parseInt(value));
			NewHighWrapper newHighWrapper=NewHighAdapter.wrap(newHigh);
			return newHighWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((NewHighWrapper) object).getNewHigh().getNewHighId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

