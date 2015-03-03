
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IssueManager;
import com.nsmjsf.web.wrappers.IssueManagerWrapper;

public class IssueManagerAdapter {
private static final Log log = LogFactory
			.getLog(IssueManagerAdapter.class);
	
	public static List<IssueManagerWrapper> wrapAll(List<IssueManager> issueManagerList)
	{
		List<IssueManagerWrapper> issueManagerWrapperList=new ArrayList<IssueManagerWrapper>();
		for(IssueManager issueManager:issueManagerList)
		{
			IssueManagerWrapper issueManagerWrapper=new IssueManagerWrapper();
			issueManagerWrapper.setIssueManager(issueManager);
			issueManagerWrapperList.add(issueManagerWrapper);
		}
		return issueManagerWrapperList;
		
	}
	
	public static IssueManagerWrapper wrap(IssueManager issueManager)
	{
		IssueManagerWrapper issueManagerWrapper=new IssueManagerWrapper();
		issueManagerWrapper.setIssueManager(issueManager);
		return issueManagerWrapper;
		
	}

}

