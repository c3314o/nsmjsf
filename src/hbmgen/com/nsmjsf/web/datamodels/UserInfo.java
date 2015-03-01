package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * UserInfo generated by hbm2java
 */
@Entity
@Table(name = "user_info", catalog = "admin_nsmjsf")
public class UserInfo implements java.io.Serializable {

	private Integer userInfoId;
	private String userInfoFullName;
	private String userInfoAddress;
	private String userInfoWork;
	private Date userInfoDob;
	private String userInfoProfilePicture;
	private Set<User> users = new HashSet<User>(0);

	public UserInfo() {
	}

	public UserInfo(String userInfoFullName, String userInfoProfilePicture) {
		this.userInfoFullName = userInfoFullName;
		this.userInfoProfilePicture = userInfoProfilePicture;
	}

	public UserInfo(String userInfoFullName, String userInfoAddress,
			String userInfoWork, Date userInfoDob,
			String userInfoProfilePicture, Set<User> users) {
		this.userInfoFullName = userInfoFullName;
		this.userInfoAddress = userInfoAddress;
		this.userInfoWork = userInfoWork;
		this.userInfoDob = userInfoDob;
		this.userInfoProfilePicture = userInfoProfilePicture;
		this.users = users;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "user_info_id", unique = true, nullable = false)
	public Integer getUserInfoId() {
		return this.userInfoId;
	}

	public void setUserInfoId(Integer userInfoId) {
		this.userInfoId = userInfoId;
	}

	@Column(name = "user_info_full_name", nullable = false)
	public String getUserInfoFullName() {
		return this.userInfoFullName;
	}

	public void setUserInfoFullName(String userInfoFullName) {
		this.userInfoFullName = userInfoFullName;
	}

	@Column(name = "user_info_address", length = 65535)
	public String getUserInfoAddress() {
		return this.userInfoAddress;
	}

	public void setUserInfoAddress(String userInfoAddress) {
		this.userInfoAddress = userInfoAddress;
	}

	@Column(name = "user_info_work", length = 65535)
	public String getUserInfoWork() {
		return this.userInfoWork;
	}

	public void setUserInfoWork(String userInfoWork) {
		this.userInfoWork = userInfoWork;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "user_info_dob", length = 0)
	public Date getUserInfoDob() {
		return this.userInfoDob;
	}

	public void setUserInfoDob(Date userInfoDob) {
		this.userInfoDob = userInfoDob;
	}

	@Column(name = "user_info_profile_picture", nullable = false, length = 65535)
	public String getUserInfoProfilePicture() {
		return this.userInfoProfilePicture;
	}

	public void setUserInfoProfilePicture(String userInfoProfilePicture) {
		this.userInfoProfilePicture = userInfoProfilePicture;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "userInfo")
	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
