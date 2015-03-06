package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostLike;
import com.nsmjsf.web.wrappers.PostLikeWrapper;

public class PostLikeAdapter {
	private static final Log log = LogFactory.getLog(PostLikeAdapter.class);

	public static List<PostLikeWrapper> wrapAll(List<PostLike> postLikeList) {
		List<PostLikeWrapper> postLikeWrapperList = new ArrayList<PostLikeWrapper>();
		for (PostLike postLike : postLikeList) {
			PostLikeWrapper postLikeWrapper = new PostLikeWrapper();
			postLikeWrapper.setPostLike(postLike);
			postLikeWrapperList.add(postLikeWrapper);
		}
		return postLikeWrapperList;

	}

	public static PostLikeWrapper wrap(PostLike postLike) {
		PostLikeWrapper postLikeWrapper = new PostLikeWrapper();
		postLikeWrapper.setPostLike(postLike);
		return postLikeWrapper;

	}

}
