package com.nsmjsf.web.datamodels;

// Generated Mar 2, 2015 3:36:21 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * CompanyBranch generated by hbm2java
 */
@Entity
@Table(name = "company_branch", catalog = "admin_nsmjsf")
public class CompanyBranch implements java.io.Serializable {

	private Integer companyBranchId;
	private int companyBranchCompanyId;
	private String branchLocation;
	private String companyBranchName;
	private String companyBranchContact;
	private String companyBranchEmail;

	public CompanyBranch() {
	}

	public CompanyBranch(int companyBranchCompanyId, String branchLocation,
			String companyBranchName, String companyBranchContact,
			String companyBranchEmail) {
		this.companyBranchCompanyId = companyBranchCompanyId;
		this.branchLocation = branchLocation;
		this.companyBranchName = companyBranchName;
		this.companyBranchContact = companyBranchContact;
		this.companyBranchEmail = companyBranchEmail;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "company_branch_id", unique = true, nullable = false)
	public Integer getCompanyBranchId() {
		return this.companyBranchId;
	}

	public void setCompanyBranchId(Integer companyBranchId) {
		this.companyBranchId = companyBranchId;
	}

	@Column(name = "company_branch_company_id", nullable = false)
	public int getCompanyBranchCompanyId() {
		return this.companyBranchCompanyId;
	}

	public void setCompanyBranchCompanyId(int companyBranchCompanyId) {
		this.companyBranchCompanyId = companyBranchCompanyId;
	}

	@Column(name = "branch_location", nullable = false)
	public String getBranchLocation() {
		return this.branchLocation;
	}

	public void setBranchLocation(String branchLocation) {
		this.branchLocation = branchLocation;
	}

	@Column(name = "company_branch_name", nullable = false)
	public String getCompanyBranchName() {
		return this.companyBranchName;
	}

	public void setCompanyBranchName(String companyBranchName) {
		this.companyBranchName = companyBranchName;
	}

	@Column(name = "company_branch_contact", nullable = false)
	public String getCompanyBranchContact() {
		return this.companyBranchContact;
	}

	public void setCompanyBranchContact(String companyBranchContact) {
		this.companyBranchContact = companyBranchContact;
	}

	@Column(name = "company_branch_email", nullable = false)
	public String getCompanyBranchEmail() {
		return this.companyBranchEmail;
	}

	public void setCompanyBranchEmail(String companyBranchEmail) {
		this.companyBranchEmail = companyBranchEmail;
	}

}
