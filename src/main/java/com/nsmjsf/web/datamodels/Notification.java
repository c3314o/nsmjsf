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
 * Notification generated by hbm2java
 */
@Entity
@Table(name = "notification", catalog = "admin_nsmjsf")
public class Notification implements java.io.Serializable {

	private Integer notificationId;
	private User user;
	private String notificationLink;
	private String notificationMessage;
	private boolean notificationStatus;

	public Notification() {
	}

	public Notification(User user, String notificationLink,
			String notificationMessage, boolean notificationStatus) {
		this.user = user;
		this.notificationLink = notificationLink;
		this.notificationMessage = notificationMessage;
		this.notificationStatus = notificationStatus;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "notification_id", unique = true, nullable = false)
	public Integer getNotificationId() {
		return this.notificationId;
	}

	public void setNotificationId(Integer notificationId) {
		this.notificationId = notificationId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "notification_user_id", nullable = false)
	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Column(name = "notification_link", nullable = false, length = 65535)
	public String getNotificationLink() {
		return this.notificationLink;
	}

	public void setNotificationLink(String notificationLink) {
		this.notificationLink = notificationLink;
	}

	@Column(name = "notification_message", nullable = false, length = 65535)
	public String getNotificationMessage() {
		return this.notificationMessage;
	}

	public void setNotificationMessage(String notificationMessage) {
		this.notificationMessage = notificationMessage;
	}

	@Column(name = "notification_status", nullable = false)
	public boolean isNotificationStatus() {
		return this.notificationStatus;
	}

	public void setNotificationStatus(boolean notificationStatus) {
		this.notificationStatus = notificationStatus;
	}

}
