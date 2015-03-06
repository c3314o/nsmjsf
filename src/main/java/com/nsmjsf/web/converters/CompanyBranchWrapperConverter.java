package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.CompanyBranchAdapter;
import com.nsmjsf.web.datasources.CompanyBranchDataSource;
import com.nsmjsf.web.datamodels.CompanyBranch;
import com.nsmjsf.web.wrappers.CompanyBranchWrapper;

@FacesConverter("companyBranchWrapperConverter")
public class CompanyBranchWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(CompanyBranchWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			CompanyBranchDataSource companyBranchDataSource = new CompanyBranchDataSource();
			CompanyBranch companyBranch = companyBranchDataSource.get(Integer
					.parseInt(value));
			CompanyBranchWrapper companyBranchWrapper = CompanyBranchAdapter
					.wrap(companyBranch);
			return companyBranchWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((CompanyBranchWrapper) object)
					.getCompanyBranch().getCompanyBranchId());
		} else {
			return null;
		}
	}

}
