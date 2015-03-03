package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.UserPortfolioAdapter;
import com.nsmjsf.web.datasources.UserPortfolioDataSource;
import com.nsmjsf.web.datamodels.UserPortfolio;
import com.nsmjsf.web.wrappers.UserPortfolioWrapper;


@FacesConverter("userPortfolioWrapperConverter")
public class UserPortfolioWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(UserPortfolioWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			UserPortfolioDataSource userPortfolioDataSource = new UserPortfolioDataSource();
			UserPortfolio userPortfolio = userPortfolioDataSource.get(Integer.parseInt(value));
			UserPortfolioWrapper userPortfolioWrapper=UserPortfolioAdapter.wrap(userPortfolio);
			return userPortfolioWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((UserPortfolioWrapper) object).getUserPortfolio().getUserPortfolioId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

