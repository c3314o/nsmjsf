package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.AgmAdapter;
import com.nsmjsf.web.datasources.AgmDataSource;
import com.nsmjsf.web.datamodels.Agm;
import com.nsmjsf.web.wrappers.AgmWrapper;


@FacesConverter("agmWrapperConverter")
public class AgmWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(AgmWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			AgmDataSource agmDataSource = new AgmDataSource();
			Agm agm = agmDataSource.get(Integer.parseInt(value));
			AgmWrapper agmWrapper=AgmAdapter.wrap(agm);
			return agmWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((AgmWrapper) object).getAgm().getAgmId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

