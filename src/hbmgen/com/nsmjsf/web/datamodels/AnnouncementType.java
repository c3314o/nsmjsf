package com.nsmjsf.web.datamodels;

// Generated Mar 14, 2015 9:52:11 PM by Hibernate Tools 3.4.0.CR1

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AnnouncementType generated by hbm2java
 */
@Entity
@Table(name = "announcement_type", catalog = "admin_nsmjsf")
public class AnnouncementType implements java.io.Serializable {

	private Integer announcementTypeId;
	private String announcementTypeLabel;
	private Set<Announcement> announcements = new HashSet<Announcement>(0);

	public AnnouncementType() {
	}

	public AnnouncementType(String announcementTypeLabel) {
		this.announcementTypeLabel = announcementTypeLabel;
	}

	public AnnouncementType(String announcementTypeLabel,
			Set<Announcement> announcements) {
		this.announcementTypeLabel = announcementTypeLabel;
		this.announcements = announcements;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "announcement_type_id", unique = true, nullable = false)
	public Integer getAnnouncementTypeId() {
		return this.announcementTypeId;
	}

	public void setAnnouncementTypeId(Integer announcementTypeId) {
		this.announcementTypeId = announcementTypeId;
	}

	@Column(name = "announcement_type_label", nullable = false)
	public String getAnnouncementTypeLabel() {
		return this.announcementTypeLabel;
	}

	public void setAnnouncementTypeLabel(String announcementTypeLabel) {
		this.announcementTypeLabel = announcementTypeLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "announcementType")
	public Set<Announcement> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(Set<Announcement> announcements) {
		this.announcements = announcements;
	}

}
