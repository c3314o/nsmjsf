package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostImage;
import com.nsmjsf.web.wrappers.PostImageWrapper;

public class PostImageAdapter {
	private static final Log log = LogFactory.getLog(PostImageAdapter.class);

	public static List<PostImageWrapper> wrapAll(List<PostImage> postImageList) {
		List<PostImageWrapper> postImageWrapperList = new ArrayList<PostImageWrapper>();
		for (PostImage postImage : postImageList) {
			PostImageWrapper postImageWrapper = new PostImageWrapper();
			postImageWrapper.setPostImage(postImage);
			postImageWrapperList.add(postImageWrapper);
		}
		return postImageWrapperList;

	}

	public static PostImageWrapper wrap(PostImage postImage) {
		PostImageWrapper postImageWrapper = new PostImageWrapper();
		postImageWrapper.setPostImage(postImage);
		return postImageWrapper;

	}

}
