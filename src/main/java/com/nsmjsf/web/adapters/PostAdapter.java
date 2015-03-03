
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Post;
import com.nsmjsf.web.wrappers.PostWrapper;

public class PostAdapter {
private static final Log log = LogFactory
			.getLog(PostAdapter.class);
	
	public static List<PostWrapper> wrapAll(List<Post> postList)
	{
		List<PostWrapper> postWrapperList=new ArrayList<PostWrapper>();
		for(Post post:postList)
		{
			PostWrapper postWrapper=new PostWrapper();
			postWrapper.setPost(post);
			postWrapperList.add(postWrapper);
		}
		return postWrapperList;
		
	}
	
	public static PostWrapper wrap(Post post)
	{
		PostWrapper postWrapper=new PostWrapper();
		postWrapper.setPost(post);
		return postWrapper;
		
	}

}

