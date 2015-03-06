package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.adapters.InterviewAdapter;
import com.nsmjsf.web.datasources.InterviewDataSource;
import com.nsmjsf.web.datamodels.Interview;
import com.nsmjsf.web.wrappers.InterviewWrapper;

@FacesConverter("interviewWrapperConverter")
public class InterviewWrapperConverter implements Converter {
	private static final Log log = LogFactory
			.getLog(InterviewWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			InterviewDataSource interviewDataSource = new InterviewDataSource();
			Interview interview = interviewDataSource.get(Integer
					.parseInt(value));
			InterviewWrapper interviewWrapper = InterviewAdapter
					.wrap(interview);
			return interviewWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((InterviewWrapper) object).getInterview()
					.getInterviewId());
		} else {
			return null;
		}
	}

}
