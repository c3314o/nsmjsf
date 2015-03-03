
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CommentSocial;
import com.nsmjsf.web.wrappers.CommentSocialWrapper;

public class CommentSocialAdapter {
private static final Log log = LogFactory
			.getLog(CommentSocialAdapter.class);
	
	public static List<CommentSocialWrapper> wrapAll(List<CommentSocial> commentSocialList)
	{
		List<CommentSocialWrapper> commentSocialWrapperList=new ArrayList<CommentSocialWrapper>();
		for(CommentSocial commentSocial:commentSocialList)
		{
			CommentSocialWrapper commentSocialWrapper=new CommentSocialWrapper();
			commentSocialWrapper.setCommentSocial(commentSocial);
			commentSocialWrapperList.add(commentSocialWrapper);
		}
		return commentSocialWrapperList;
		
	}
	
	public static CommentSocialWrapper wrap(CommentSocial commentSocial)
	{
		CommentSocialWrapper commentSocialWrapper=new CommentSocialWrapper();
		commentSocialWrapper.setCommentSocial(commentSocial);
		return commentSocialWrapper;
		
	}

}

