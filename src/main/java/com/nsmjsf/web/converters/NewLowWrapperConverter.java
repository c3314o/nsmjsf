package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.NewLowAdapter;
import com.nsmjsf.web.datasources.NewLowDataSource;
import com.nsmjsf.web.datamodels.NewLow;
import com.nsmjsf.web.wrappers.NewLowWrapper;

@FacesConverter("newLowWrapperConverter")
public class NewLowWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(NewLowWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			NewLowDataSource newLowDataSource = new NewLowDataSource();
			NewLow newLow = newLowDataSource.get(Integer.parseInt(value));
			NewLowWrapper newLowWrapper = NewLowAdapter.wrap(newLow);
			return newLowWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((NewLowWrapper) object).getNewLow()
					.getNewLowId());
		} else {
			return null;
		}
	}

}
