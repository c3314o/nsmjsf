
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Issue;
import com.nsmjsf.web.wrappers.IssueWrapper;

public class IssueAdapter {
private static final Log log = LogFactory
			.getLog(IssueAdapter.class);
	
	public static List<IssueWrapper> wrapAll(List<Issue> issueList)
	{
		List<IssueWrapper> issueWrapperList=new ArrayList<IssueWrapper>();
		for(Issue issue:issueList)
		{
			IssueWrapper issueWrapper=new IssueWrapper();
			issueWrapper.setIssue(issue);
			issueWrapperList.add(issueWrapper);
		}
		return issueWrapperList;
		
	}
	
	public static IssueWrapper wrap(Issue issue)
	{
		IssueWrapper issueWrapper=new IssueWrapper();
		issueWrapper.setIssue(issue);
		return issueWrapper;
		
	}

}

