package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

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
 * BullionType generated by hbm2java
 */
@Entity
@Table(name = "bullion_type", catalog = "admin_nsmjsf")
public class BullionType implements java.io.Serializable {

	private Integer bullionTypeId;
	private String bullionTypeLabel;
	private Set<UserBullion> userBullions = new HashSet<UserBullion>(0);

	public BullionType() {
	}

	public BullionType(String bullionTypeLabel) {
		this.bullionTypeLabel = bullionTypeLabel;
	}

	public BullionType(String bullionTypeLabel, Set<UserBullion> userBullions) {
		this.bullionTypeLabel = bullionTypeLabel;
		this.userBullions = userBullions;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "bullion_type_id", unique = true, nullable = false)
	public Integer getBullionTypeId() {
		return this.bullionTypeId;
	}

	public void setBullionTypeId(Integer bullionTypeId) {
		this.bullionTypeId = bullionTypeId;
	}

	@Column(name = "bullion_type_label", nullable = false)
	public String getBullionTypeLabel() {
		return this.bullionTypeLabel;
	}

	public void setBullionTypeLabel(String bullionTypeLabel) {
		this.bullionTypeLabel = bullionTypeLabel;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "bullionType")
	public Set<UserBullion> getUserBullions() {
		return this.userBullions;
	}

	public void setUserBullions(Set<UserBullion> userBullions) {
		this.userBullions = userBullions;
	}

}
