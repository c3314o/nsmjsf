package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.IssueManagerAdapter;
import com.nsmjsf.web.datasources.IssueManagerDataSource;
import com.nsmjsf.web.datamodels.IssueManager;
import com.nsmjsf.web.wrappers.IssueManagerWrapper;


@FacesConverter("issueManagerWrapperConverter")
public class IssueManagerWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(IssueManagerWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			IssueManagerDataSource issueManagerDataSource = new IssueManagerDataSource();
			IssueManager issueManager = issueManagerDataSource.get(Integer.parseInt(value));
			IssueManagerWrapper issueManagerWrapper=IssueManagerAdapter.wrap(issueManager);
			return issueManagerWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((IssueManagerWrapper) object).getIssueManager().getIssueManagerId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

