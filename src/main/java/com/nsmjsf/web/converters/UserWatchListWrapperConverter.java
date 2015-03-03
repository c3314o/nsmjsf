package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.UserWatchListAdapter;
import com.nsmjsf.web.datasources.UserWatchListDataSource;
import com.nsmjsf.web.datamodels.UserWatchList;
import com.nsmjsf.web.wrappers.UserWatchListWrapper;


@FacesConverter("userWatchListWrapperConverter")
public class UserWatchListWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(UserWatchListWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserWatchListDataSource userWatchListDataSource = new UserWatchListDataSource();
			UserWatchList userWatchList = userWatchListDataSource.get(Integer.parseInt(value));
			UserWatchListWrapper userWatchListWrapper=UserWatchListAdapter.wrap(userWatchList);
			return userWatchListWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserWatchListWrapper) object).getUserWatchList().getUserWatchListId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

