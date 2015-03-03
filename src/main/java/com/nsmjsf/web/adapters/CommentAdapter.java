
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Comment;
import com.nsmjsf.web.wrappers.CommentWrapper;

public class CommentAdapter {
private static final Log log = LogFactory
			.getLog(CommentAdapter.class);
	
	public static List<CommentWrapper> wrapAll(List<Comment> commentList)
	{
		List<CommentWrapper> commentWrapperList=new ArrayList<CommentWrapper>();
		for(Comment comment:commentList)
		{
			CommentWrapper commentWrapper=new CommentWrapper();
			commentWrapper.setComment(comment);
			commentWrapperList.add(commentWrapper);
		}
		return commentWrapperList;
		
	}
	
	public static CommentWrapper wrap(Comment comment)
	{
		CommentWrapper commentWrapper=new CommentWrapper();
		commentWrapper.setComment(comment);
		return commentWrapper;
		
	}

}

