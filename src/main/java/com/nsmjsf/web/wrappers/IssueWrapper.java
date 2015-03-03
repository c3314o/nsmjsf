
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Issue;

public class IssueWrapper {

private static final Log log = LogFactory
			.getLog(IssueWrapper.class);


	Issue issue;

	public IssueWrapper(Issue issue) {
		this.issue = issue;
	}

	public IssueWrapper() {

	}

	public Issue getIssue() {
		return issue;
	}

	public void setIssue(Issue issue) {
		this.issue = issue;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.issue.getIssueId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final IssueWrapper other = (IssueWrapper) obj;
		if (!Objects.equals(this.issue.getIssueId(), other.getIssue().getIssueId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.issue.toString();

	}

}

