package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostCategory;
import com.nsmjsf.web.wrappers.PostCategoryWrapper;

public class PostCategoryAdapter {
	private static final Log log = LogFactory.getLog(PostCategoryAdapter.class);

	public static List<PostCategoryWrapper> wrapAll(
			List<PostCategory> postCategoryList) {
		List<PostCategoryWrapper> postCategoryWrapperList = new ArrayList<PostCategoryWrapper>();
		for (PostCategory postCategory : postCategoryList) {
			PostCategoryWrapper postCategoryWrapper = new PostCategoryWrapper();
			postCategoryWrapper.setPostCategory(postCategory);
			postCategoryWrapperList.add(postCategoryWrapper);
		}
		return postCategoryWrapperList;

	}

	public static PostCategoryWrapper wrap(PostCategory postCategory) {
		PostCategoryWrapper postCategoryWrapper = new PostCategoryWrapper();
		postCategoryWrapper.setPostCategory(postCategory);
		return postCategoryWrapper;

	}

}
