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
 * MapPostCompany generated by hbm2java
 */
@Entity
@Table(name = "map_post_company", catalog = "admin_nsmjsf")
public class MapPostCompany implements java.io.Serializable {

	private Integer postToCompanyConnectionId;
	private Company company;
	private Post post;

	public MapPostCompany() {
	}

	public MapPostCompany(Company company, Post post) {
		this.company = company;
		this.post = post;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "post_to_company_connection_id", unique = true, nullable = false)
	public Integer getPostToCompanyConnectionId() {
		return this.postToCompanyConnectionId;
	}

	public void setPostToCompanyConnectionId(Integer postToCompanyConnectionId) {
		this.postToCompanyConnectionId = postToCompanyConnectionId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
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
