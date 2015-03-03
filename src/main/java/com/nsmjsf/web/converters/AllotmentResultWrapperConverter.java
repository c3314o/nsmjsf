package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.AllotmentResultAdapter;
import com.nsmjsf.web.datasources.AllotmentResultDataSource;
import com.nsmjsf.web.datamodels.AllotmentResult;
import com.nsmjsf.web.wrappers.AllotmentResultWrapper;


@FacesConverter("allotmentResultWrapperConverter")
public class AllotmentResultWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(AllotmentResultWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AllotmentResultDataSource allotmentResultDataSource = new AllotmentResultDataSource();
			AllotmentResult allotmentResult = allotmentResultDataSource.get(Integer.parseInt(value));
			AllotmentResultWrapper allotmentResultWrapper=AllotmentResultAdapter.wrap(allotmentResult);
			return allotmentResultWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AllotmentResultWrapper) object).getAllotmentResult().getAllotmentResultId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

