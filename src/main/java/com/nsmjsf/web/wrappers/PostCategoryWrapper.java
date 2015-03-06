package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostCategory;

public class PostCategoryWrapper {

	private static final Log log = LogFactory.getLog(PostCategoryWrapper.class);

	PostCategory postCategory;

	public PostCategoryWrapper(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	public PostCategoryWrapper() {

	}

	public PostCategory getPostCategory() {
		return postCategory;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.postCategory.getPostCategoryId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PostCategoryWrapper other = (PostCategoryWrapper) obj;
		if (!Objects.equals(this.postCategory.getPostCategoryId(), other
				.getPostCategory().getPostCategoryId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.postCategory.toString();

	}

}
