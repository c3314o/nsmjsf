package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.PostLike;

public class PostLikeWrapper {

	private static final Log log = LogFactory.getLog(PostLikeWrapper.class);

	PostLike postLike;

	public PostLikeWrapper(PostLike postLike) {
		this.postLike = postLike;
	}

	public PostLikeWrapper() {

	}

	public PostLike getPostLike() {
		return postLike;
	}

	public void setPostLike(PostLike postLike) {
		this.postLike = postLike;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.postLike.getPostLikeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PostLikeWrapper other = (PostLikeWrapper) obj;
		if (!Objects.equals(this.postLike.getPostLikeId(), other.getPostLike()
				.getPostLikeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.postLike.toString();

	}

}
