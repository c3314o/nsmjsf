package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * News generated by hbm2java
 */
@Entity
@Table(name = "news", catalog = "admin_nsmjsf")
public class News implements java.io.Serializable {

	private int newsId;
	private User user;
	private Post post;
	private String newsAuthor;
	private String newsSource;

	public News() {
	}

	public News(int newsId, User user, Post post) {
		this.newsId = newsId;
		this.user = user;
		this.post = post;
	}

	public News(int newsId, User user, Post post, String newsAuthor,
			String newsSource) {
		this.newsId = newsId;
		this.user = user;
		this.post = post;
		this.newsAuthor = newsAuthor;
		this.newsSource = newsSource;
	}

	@Id
	@Column(name = "news_id", unique = true, nullable = false)
	public int getNewsId() {
		return this.newsId;
	}

	public void setNewsId(int newsId) {
		this.newsId = newsId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "news_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "news_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Column(name = "news_author")
	public String getNewsAuthor() {
		return this.newsAuthor;
	}

	public void setNewsAuthor(String newsAuthor) {
		this.newsAuthor = newsAuthor;
	}

	@Column(name = "news_source")
	public String getNewsSource() {
		return this.newsSource;
	}

	public void setNewsSource(String newsSource) {
		this.newsSource = newsSource;
	}

}
