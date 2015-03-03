package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.CompanyDetailAdapter;
import com.nsmjsf.web.datasources.CompanyDetailDataSource;
import com.nsmjsf.web.datamodels.CompanyDetail;
import com.nsmjsf.web.wrappers.CompanyDetailWrapper;


@FacesConverter("companyDetailWrapperConverter")
public class CompanyDetailWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(CompanyDetailWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CompanyDetailDataSource companyDetailDataSource = new CompanyDetailDataSource();
			CompanyDetail companyDetail = companyDetailDataSource.get(Integer.parseInt(value));
			CompanyDetailWrapper companyDetailWrapper=CompanyDetailAdapter.wrap(companyDetail);
			return companyDetailWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CompanyDetailWrapper) object).getCompanyDetail().getCompanyDetailId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

