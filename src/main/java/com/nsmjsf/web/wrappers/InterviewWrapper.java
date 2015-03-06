package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Interview;

public class InterviewWrapper {

	private static final Log log = LogFactory.getLog(InterviewWrapper.class);

	Interview interview;

	public InterviewWrapper(Interview interview) {
		this.interview = interview;
	}

	public InterviewWrapper() {

	}

	public Interview getInterview() {
		return interview;
	}

	public void setInterview(Interview interview) {
		this.interview = interview;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.interview.getInterviewId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final InterviewWrapper other = (InterviewWrapper) obj;
		if (!Objects.equals(this.interview.getInterviewId(), other
				.getInterview().getInterviewId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.interview.toString();

	}

}
