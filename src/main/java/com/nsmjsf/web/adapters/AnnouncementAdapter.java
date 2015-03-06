package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Announcement;
import com.nsmjsf.web.wrappers.AnnouncementWrapper;

public class AnnouncementAdapter {
	private static final Log log = LogFactory.getLog(AnnouncementAdapter.class);

	public static List<AnnouncementWrapper> wrapAll(
			List<Announcement> announcementList) {
		List<AnnouncementWrapper> announcementWrapperList = new ArrayList<AnnouncementWrapper>();
		for (Announcement announcement : announcementList) {
			AnnouncementWrapper announcementWrapper = new AnnouncementWrapper();
			announcementWrapper.setAnnouncement(announcement);
			announcementWrapperList.add(announcementWrapper);
		}
		return announcementWrapperList;

	}

	public static AnnouncementWrapper wrap(Announcement announcement) {
		AnnouncementWrapper announcementWrapper = new AnnouncementWrapper();
		announcementWrapper.setAnnouncement(announcement);
		return announcementWrapper;

	}

}
