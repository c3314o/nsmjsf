package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.UserTypeAdapter;
import com.nsmjsf.web.datasources.UserTypeDataSource;
import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.wrappers.UserTypeWrapper;


@FacesConverter("userTypeWrapperConverter")
public class UserTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(UserTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserTypeDataSource userTypeDataSource = new UserTypeDataSource();
			UserType userType = userTypeDataSource.get(Integer.parseInt(value));
			UserTypeWrapper userTypeWrapper=UserTypeAdapter.wrap(userType);
			return userTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserTypeWrapper) object).getUserType().getUserTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

