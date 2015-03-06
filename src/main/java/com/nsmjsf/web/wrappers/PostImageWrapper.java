package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostImage;

public class PostImageWrapper {

	private static final Log log = LogFactory.getLog(PostImageWrapper.class);

	PostImage postImage;

	public PostImageWrapper(PostImage postImage) {
		this.postImage = postImage;
	}

	public PostImageWrapper() {

	}

	public PostImage getPostImage() {
		return postImage;
	}

	public void setPostImage(PostImage postImage) {
		this.postImage = postImage;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.postImage.getPostImageId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PostImageWrapper other = (PostImageWrapper) obj;
		if (!Objects.equals(this.postImage.getPostImageId(), other
				.getPostImage().getPostImageId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.postImage.toString();

	}

}
