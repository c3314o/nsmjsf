
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.IssueType;
import com.nsmjsf.web.wrappers.IssueTypeWrapper;

public class IssueTypeAdapter {
private static final Log log = LogFactory
			.getLog(IssueTypeAdapter.class);
	
	public static List<IssueTypeWrapper> wrapAll(List<IssueType> issueTypeList)
	{
		List<IssueTypeWrapper> issueTypeWrapperList=new ArrayList<IssueTypeWrapper>();
		for(IssueType issueType:issueTypeList)
		{
			IssueTypeWrapper issueTypeWrapper=new IssueTypeWrapper();
			issueTypeWrapper.setIssueType(issueType);
			issueTypeWrapperList.add(issueTypeWrapper);
		}
		return issueTypeWrapperList;
		
	}
	
	public static IssueTypeWrapper wrap(IssueType issueType)
	{
		IssueTypeWrapper issueTypeWrapper=new IssueTypeWrapper();
		issueTypeWrapper.setIssueType(issueType);
		return issueTypeWrapper;
		
	}

}

