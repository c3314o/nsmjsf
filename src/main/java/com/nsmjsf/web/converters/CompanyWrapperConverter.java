package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.CompanyAdapter;
import com.nsmjsf.web.datasources.CompanyDataSource;
import com.nsmjsf.web.datamodels.Company;
import com.nsmjsf.web.wrappers.CompanyWrapper;


@FacesConverter("companyWrapperConverter")
public class CompanyWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(CompanyWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CompanyDataSource companyDataSource = new CompanyDataSource();
			Company company = companyDataSource.get(Integer.parseInt(value));
			CompanyWrapper companyWrapper=CompanyAdapter.wrap(company);
			return companyWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CompanyWrapper) object).getCompany().getCompanyId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

