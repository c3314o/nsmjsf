package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IssueManager;

public class IssueManagerWrapper {

	private static final Log log = LogFactory.getLog(IssueManagerWrapper.class);

	IssueManager issueManager;

	public IssueManagerWrapper(IssueManager issueManager) {
		this.issueManager = issueManager;
	}

	public IssueManagerWrapper() {

	}

	public IssueManager getIssueManager() {
		return issueManager;
	}

	public void setIssueManager(IssueManager issueManager) {
		this.issueManager = issueManager;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.issueManager.getIssueManagerId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IssueManagerWrapper other = (IssueManagerWrapper) obj;
		if (!Objects.equals(this.issueManager.getIssueManagerId(), other
				.getIssueManager().getIssueManagerId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.issueManager.toString();

	}

}
