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
 * MapPostPost generated by hbm2java
 */
@Entity
@Table(name = "map_post_post", catalog = "admin_nsmjsf")
public class MapPostPost implements java.io.Serializable {

	private Integer mapPostPostId;
	private Post postByFromPostId;
	private Post postByToPostId;

	public MapPostPost() {
	}

	public MapPostPost(Post postByFromPostId, Post postByToPostId) {
		this.postByFromPostId = postByFromPostId;
		this.postByToPostId = postByToPostId;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "map_post_post_id", unique = true, nullable = false)
	public Integer getMapPostPostId() {
		return this.mapPostPostId;
	}

	public void setMapPostPostId(Integer mapPostPostId) {
		this.mapPostPostId = mapPostPostId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "from_post_id", nullable = false)
	public Post getPostByFromPostId() {
		return this.postByFromPostId;
	}

	public void setPostByFromPostId(Post postByFromPostId) {
		this.postByFromPostId = postByFromPostId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "to_post_id", nullable = false)
	public Post getPostByToPostId() {
		return this.postByToPostId;
	}

	public void setPostByToPostId(Post postByToPostId) {
		this.postByToPostId = postByToPostId;
	}

}
