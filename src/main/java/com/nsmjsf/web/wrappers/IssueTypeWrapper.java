package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IssueType;

public class IssueTypeWrapper {

	private static final Log log = LogFactory.getLog(IssueTypeWrapper.class);

	IssueType issueType;

	public IssueTypeWrapper(IssueType issueType) {
		this.issueType = issueType;
	}

	public IssueTypeWrapper() {

	}

	public IssueType getIssueType() {
		return issueType;
	}

	public void setIssueType(IssueType issueType) {
		this.issueType = issueType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.issueType.getIssueTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IssueTypeWrapper other = (IssueTypeWrapper) obj;
		if (!Objects.equals(this.issueType.getIssueTypeId(), other
				.getIssueType().getIssueTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.issueType.toString();

	}

}
