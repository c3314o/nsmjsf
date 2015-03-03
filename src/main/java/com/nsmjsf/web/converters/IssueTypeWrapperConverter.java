package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.IssueTypeAdapter;
import com.nsmjsf.web.datasources.IssueTypeDataSource;
import com.nsmjsf.web.datamodels.IssueType;
import com.nsmjsf.web.wrappers.IssueTypeWrapper;


@FacesConverter("issueTypeWrapperConverter")
public class IssueTypeWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(IssueTypeWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			IssueTypeDataSource issueTypeDataSource = new IssueTypeDataSource();
			IssueType issueType = issueTypeDataSource.get(Integer.parseInt(value));
			IssueTypeWrapper issueTypeWrapper=IssueTypeAdapter.wrap(issueType);
			return issueTypeWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((IssueTypeWrapper) object).getIssueType().getIssueTypeId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

