package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ForumThread;
import com.nsmjsf.web.wrappers.ForumThreadWrapper;

public class ForumThreadAdapter {
	private static final Log log = LogFactory.getLog(ForumThreadAdapter.class);

	public static List<ForumThreadWrapper> wrapAll(
			List<ForumThread> forumThreadList) {
		List<ForumThreadWrapper> forumThreadWrapperList = new ArrayList<ForumThreadWrapper>();
		for (ForumThread forumThread : forumThreadList) {
			ForumThreadWrapper forumThreadWrapper = new ForumThreadWrapper();
			forumThreadWrapper.setForumThread(forumThread);
			forumThreadWrapperList.add(forumThreadWrapper);
		}
		return forumThreadWrapperList;

	}

	public static ForumThreadWrapper wrap(ForumThread forumThread) {
		ForumThreadWrapper forumThreadWrapper = new ForumThreadWrapper();
		forumThreadWrapper.setForumThread(forumThread);
		return forumThreadWrapper;

	}

}
