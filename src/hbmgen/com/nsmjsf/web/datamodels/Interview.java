package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Interview generated by hbm2java
 */
@Entity
@Table(name = "interview", catalog = "admin_nsmjsf")
public class Interview implements java.io.Serializable {

	private Integer interviewId;
	private Post post;
	private Company company;
	private String interviewIntervieweeName;
	private Date interviewOrganization;
	private String interviewProfile;
	private Date interviewDate;
	private String interviewSubject;
	private String interviewHighlight;
	private String interviewBody;
	private Date interviewImage;
	private String interviewInterviewer;

	public Interview() {
	}

	public Interview(Post post, Company company,
			String interviewIntervieweeName, Date interviewOrganization,
			String interviewProfile, Date interviewDate,
			String interviewSubject, String interviewHighlight,
			String interviewBody, Date interviewImage,
			String interviewInterviewer) {
		this.post = post;
		this.company = company;
		this.interviewIntervieweeName = interviewIntervieweeName;
		this.interviewOrganization = interviewOrganization;
		this.interviewProfile = interviewProfile;
		this.interviewDate = interviewDate;
		this.interviewSubject = interviewSubject;
		this.interviewHighlight = interviewHighlight;
		this.interviewBody = interviewBody;
		this.interviewImage = interviewImage;
		this.interviewInterviewer = interviewInterviewer;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "interview_id", unique = true, nullable = false)
	public Integer getInterviewId() {
		return this.interviewId;
	}

	public void setInterviewId(Integer interviewId) {
		this.interviewId = interviewId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interview_post_id", nullable = false)
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "interview_company_id", nullable = false)
	public Company getCompany() {
		return this.company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	@Column(name = "interview_interviewee_name", nullable = false)
	public String getInterviewIntervieweeName() {
		return this.interviewIntervieweeName;
	}

	public void setInterviewIntervieweeName(String interviewIntervieweeName) {
		this.interviewIntervieweeName = interviewIntervieweeName;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "interview_organization", nullable = false, length = 0)
	public Date getInterviewOrganization() {
		return this.interviewOrganization;
	}

	public void setInterviewOrganization(Date interviewOrganization) {
		this.interviewOrganization = interviewOrganization;
	}

	@Column(name = "interview_profile", nullable = false, length = 65535)
	public String getInterviewProfile() {
		return this.interviewProfile;
	}

	public void setInterviewProfile(String interviewProfile) {
		this.interviewProfile = interviewProfile;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "interview_date", nullable = false, length = 0)
	public Date getInterviewDate() {
		return this.interviewDate;
	}

	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	@Column(name = "interview_subject", nullable = false, length = 65535)
	public String getInterviewSubject() {
		return this.interviewSubject;
	}

	public void setInterviewSubject(String interviewSubject) {
		this.interviewSubject = interviewSubject;
	}

	@Column(name = "interview_highlight", nullable = false, length = 65535)
	public String getInterviewHighlight() {
		return this.interviewHighlight;
	}

	public void setInterviewHighlight(String interviewHighlight) {
		this.interviewHighlight = interviewHighlight;
	}

	@Column(name = "interview_body", nullable = false, length = 65535)
	public String getInterviewBody() {
		return this.interviewBody;
	}

	public void setInterviewBody(String interviewBody) {
		this.interviewBody = interviewBody;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "interview_image", nullable = false, length = 0)
	public Date getInterviewImage() {
		return this.interviewImage;
	}

	public void setInterviewImage(Date interviewImage) {
		this.interviewImage = interviewImage;
	}

	@Column(name = "interview_interviewer", nullable = false, length = 65535)
	public String getInterviewInterviewer() {
		return this.interviewInterviewer;
	}

	public void setInterviewInterviewer(String interviewInterviewer) {
		this.interviewInterviewer = interviewInterviewer;
	}

}
