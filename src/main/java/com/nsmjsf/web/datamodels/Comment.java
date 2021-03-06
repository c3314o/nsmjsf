package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Comment generated by hbm2java
 */
@Entity
@Table(name = "comment", catalog = "admin_nsmjsf")
public class Comment implements java.io.Serializable {

	private Integer commentId;
	private Post post;
	private User user;
	private String commentText;
	private Date commentDate;
	private Integer commentParent;
	private Set<CommentSocial> commentSocials = new HashSet<CommentSocial>(0);

	public Comment() {
	}

	public Comment(Post post, User user, String commentText, Date commentDate) {
		this.post = post;
		this.user = user;
		this.commentText = commentText;
		this.commentDate = commentDate;
	}

	public Comment(Post post, User user, String commentText, Date commentDate,
			Integer commentParent, Set<CommentSocial> commentSocials) {
		this.post = post;
		this.user = user;
		this.commentText = commentText;
		this.commentDate = commentDate;
		this.commentParent = commentParent;
		this.commentSocials = commentSocials;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "comment_id", unique = true, nullable = false)
	public Integer getCommentId() {
		return this.commentId;
	}

	public void setCommentId(Integer commentId) {
		this.commentId = commentId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "comment_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "comment_text", nullable = false, length = 65535)
	public String getCommentText() {
		return this.commentText;
	}

	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "comment_date", nullable = false, length = 0)
	public Date getCommentDate() {
		return this.commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Column(name = "comment_parent")
	public Integer getCommentParent() {
		return this.commentParent;
	}

	public void setCommentParent(Integer commentParent) {
		this.commentParent = commentParent;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "comment")
	public Set<CommentSocial> getCommentSocials() {
		return this.commentSocials;
	}

	public void setCommentSocials(Set<CommentSocial> commentSocials) {
		this.commentSocials = commentSocials;
	}

}
