package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 3:31:39 PM by Hibernate Tools 3.4.0.CR1

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
 * MapPostCategory generated by hbm2java
 */
@Entity
@Table(name = "map_post_category", catalog = "admin_nsmjsf")
public class MapPostCategory implements java.io.Serializable {

	private Integer mapPostCategoryId;
	private PostCategory postCategory;
	private Post post;

	public MapPostCategory() {
	}

	public MapPostCategory(PostCategory postCategory, Post post) {
		this.postCategory = postCategory;
		this.post = post;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "map_post_category_id", unique = true, nullable = false)
	public Integer getMapPostCategoryId() {
		return this.mapPostCategoryId;
	}

	public void setMapPostCategoryId(Integer mapPostCategoryId) {
		this.mapPostCategoryId = mapPostCategoryId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_category_id", nullable = false)
	public PostCategory getPostCategory() {
		return this.postCategory;
	}

	public void setPostCategory(PostCategory postCategory) {
		this.postCategory = postCategory;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

}
