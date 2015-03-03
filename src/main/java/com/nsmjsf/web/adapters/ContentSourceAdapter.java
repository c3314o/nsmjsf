
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ContentSource;
import com.nsmjsf.web.wrappers.ContentSourceWrapper;

public class ContentSourceAdapter {
private static final Log log = LogFactory
			.getLog(ContentSourceAdapter.class);
	
	public static List<ContentSourceWrapper> wrapAll(List<ContentSource> contentSourceList)
	{
		List<ContentSourceWrapper> contentSourceWrapperList=new ArrayList<ContentSourceWrapper>();
		for(ContentSource contentSource:contentSourceList)
		{
			ContentSourceWrapper contentSourceWrapper=new ContentSourceWrapper();
			contentSourceWrapper.setContentSource(contentSource);
			contentSourceWrapperList.add(contentSourceWrapper);
		}
		return contentSourceWrapperList;
		
	}
	
	public static ContentSourceWrapper wrap(ContentSource contentSource)
	{
		ContentSourceWrapper contentSourceWrapper=new ContentSourceWrapper();
		contentSourceWrapper.setContentSource(contentSource);
		return contentSourceWrapper;
		
	}

}

