package com.nsmjsf.web.datamodels;

// Generated Mar 14, 2015 9:52:11 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * CommentSocial generated by hbm2java
 */
@Entity
@Table(name = "comment_social", catalog = "admin_nsmjsf")
public class CommentSocial implements java.io.Serializable {

	private Integer commentSocialId;
	private User user;
	private Comment comment;
	private boolean commentSocialLike;
	private boolean commentSocialDislike;

	public CommentSocial() {
	}

	public CommentSocial(User user, Comment comment, boolean commentSocialLike,
			boolean commentSocialDislike) {
		this.user = user;
		this.comment = comment;
		this.commentSocialLike = commentSocialLike;
		this.commentSocialDislike = commentSocialDislike;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_social_id", unique = true, nullable = false)
	public Integer getCommentSocialId() {
		return this.commentSocialId;
	}

	public void setCommentSocialId(Integer commentSocialId) {
		this.commentSocialId = commentSocialId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_social_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_social_comment_id", nullable = false)
	public Comment getComment() {
		return this.comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	@Column(name = "comment_social_like", nullable = false)
	public boolean isCommentSocialLike() {
		return this.commentSocialLike;
	}

	public void setCommentSocialLike(boolean commentSocialLike) {
		this.commentSocialLike = commentSocialLike;
	}

	@Column(name = "comment_social_dislike", nullable = false)
	public boolean isCommentSocialDislike() {
		return this.commentSocialDislike;
	}

	public void setCommentSocialDislike(boolean commentSocialDislike) {
		this.commentSocialDislike = commentSocialDislike;
	}

}
