package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CommentSocial;

public class CommentSocialWrapper {

	private static final Log log = LogFactory
			.getLog(CommentSocialWrapper.class);

	CommentSocial commentSocial;

	public CommentSocialWrapper(CommentSocial commentSocial) {
		this.commentSocial = commentSocial;
	}

	public CommentSocialWrapper() {

	}

	public CommentSocial getCommentSocial() {
		return commentSocial;
	}

	public void setCommentSocial(CommentSocial commentSocial) {
		this.commentSocial = commentSocial;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.commentSocial.getCommentSocialId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CommentSocialWrapper other = (CommentSocialWrapper) obj;
		if (!Objects.equals(this.commentSocial.getCommentSocialId(), other
				.getCommentSocial().getCommentSocialId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.commentSocial.toString();

	}

}
