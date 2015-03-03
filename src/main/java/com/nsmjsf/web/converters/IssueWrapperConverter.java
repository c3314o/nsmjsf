package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.IssueAdapter;
import com.nsmjsf.web.datasources.IssueDataSource;
import com.nsmjsf.web.datamodels.Issue;
import com.nsmjsf.web.wrappers.IssueWrapper;


@FacesConverter("issueWrapperConverter")
public class IssueWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(IssueWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			IssueDataSource issueDataSource = new IssueDataSource();
			Issue issue = issueDataSource.get(Integer.parseInt(value));
			IssueWrapper issueWrapper=IssueAdapter.wrap(issue);
			return issueWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((IssueWrapper) object).getIssue().getIssueId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

