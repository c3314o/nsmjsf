
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AnnouncementType;
import com.nsmjsf.web.wrappers.AnnouncementTypeWrapper;

public class AnnouncementTypeAdapter {
private static final Log log = LogFactory
			.getLog(AnnouncementTypeAdapter.class);
	
	public static List<AnnouncementTypeWrapper> wrapAll(List<AnnouncementType> announcementTypeList)
	{
		List<AnnouncementTypeWrapper> announcementTypeWrapperList=new ArrayList<AnnouncementTypeWrapper>();
		for(AnnouncementType announcementType:announcementTypeList)
		{
			AnnouncementTypeWrapper announcementTypeWrapper=new AnnouncementTypeWrapper();
			announcementTypeWrapper.setAnnouncementType(announcementType);
			announcementTypeWrapperList.add(announcementTypeWrapper);
		}
		return announcementTypeWrapperList;
		
	}
	
	public static AnnouncementTypeWrapper wrap(AnnouncementType announcementType)
	{
		AnnouncementTypeWrapper announcementTypeWrapper=new AnnouncementTypeWrapper();
		announcementTypeWrapper.setAnnouncementType(announcementType);
		return announcementTypeWrapper;
		
	}

}

