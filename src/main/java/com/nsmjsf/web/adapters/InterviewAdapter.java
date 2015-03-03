
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Interview;
import com.nsmjsf.web.wrappers.InterviewWrapper;

public class InterviewAdapter {
private static final Log log = LogFactory
			.getLog(InterviewAdapter.class);
	
	public static List<InterviewWrapper> wrapAll(List<Interview> interviewList)
	{
		List<InterviewWrapper> interviewWrapperList=new ArrayList<InterviewWrapper>();
		for(Interview interview:interviewList)
		{
			InterviewWrapper interviewWrapper=new InterviewWrapper();
			interviewWrapper.setInterview(interview);
			interviewWrapperList.add(interviewWrapper);
		}
		return interviewWrapperList;
		
	}
	
	public static InterviewWrapper wrap(Interview interview)
	{
		InterviewWrapper interviewWrapper=new InterviewWrapper();
		interviewWrapper.setInterview(interview);
		return interviewWrapper;
		
	}

}

