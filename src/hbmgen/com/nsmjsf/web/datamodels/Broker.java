package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

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
 * Broker generated by hbm2java
 */
@Entity
@Table(name = "broker", catalog = "admin_nsmjsf")
public class Broker implements java.io.Serializable {

	private Integer brokerId;
	private Post post;
	private int brokerCode;
	private String brokerFirmName;
	private String brokerFirmAddress;
	private String brokerFirmPhone;
	private String brokerFirmDirector;
	private String brokerImage;
	private Date brokerFirmOperationDate;

	public Broker() {
	}

	public Broker(int brokerCode, String brokerFirmName,
			String brokerFirmAddress, String brokerFirmPhone) {
		this.brokerCode = brokerCode;
		this.brokerFirmName = brokerFirmName;
		this.brokerFirmAddress = brokerFirmAddress;
		this.brokerFirmPhone = brokerFirmPhone;
	}

	public Broker(Post post, int brokerCode, String brokerFirmName,
			String brokerFirmAddress, String brokerFirmPhone,
			String brokerFirmDirector, String brokerImage,
			Date brokerFirmOperationDate) {
		this.post = post;
		this.brokerCode = brokerCode;
		this.brokerFirmName = brokerFirmName;
		this.brokerFirmAddress = brokerFirmAddress;
		this.brokerFirmPhone = brokerFirmPhone;
		this.brokerFirmDirector = brokerFirmDirector;
		this.brokerImage = brokerImage;
		this.brokerFirmOperationDate = brokerFirmOperationDate;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "broker_id", unique = true, nullable = false)
	public Integer getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "broker_post_id")
	public Post getPost() {
		return this.post;
	}

	public void setPost(Post post) {
		this.post = post;
	}

	@Column(name = "broker_code", nullable = false)
	public int getBrokerCode() {
		return this.brokerCode;
	}

	public void setBrokerCode(int brokerCode) {
		this.brokerCode = brokerCode;
	}

	@Column(name = "broker_firm_name", nullable = false)
	public String getBrokerFirmName() {
		return this.brokerFirmName;
	}

	public void setBrokerFirmName(String brokerFirmName) {
		this.brokerFirmName = brokerFirmName;
	}

	@Column(name = "broker_firm_address", nullable = false, length = 65535)
	public String getBrokerFirmAddress() {
		return this.brokerFirmAddress;
	}

	public void setBrokerFirmAddress(String brokerFirmAddress) {
		this.brokerFirmAddress = brokerFirmAddress;
	}

	@Column(name = "broker_firm_phone", nullable = false)
	public String getBrokerFirmPhone() {
		return this.brokerFirmPhone;
	}

	public void setBrokerFirmPhone(String brokerFirmPhone) {
		this.brokerFirmPhone = brokerFirmPhone;
	}

	@Column(name = "broker_firm_director")
	public String getBrokerFirmDirector() {
		return this.brokerFirmDirector;
	}

	public void setBrokerFirmDirector(String brokerFirmDirector) {
		this.brokerFirmDirector = brokerFirmDirector;
	}

	@Column(name = "broker_image", length = 65535)
	public String getBrokerImage() {
		return this.brokerImage;
	}

	public void setBrokerImage(String brokerImage) {
		this.brokerImage = brokerImage;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "broker_firm_operation_date", length = 0)
	public Date getBrokerFirmOperationDate() {
		return this.brokerFirmOperationDate;
	}

	public void setBrokerFirmOperationDate(Date brokerFirmOperationDate) {
		this.brokerFirmOperationDate = brokerFirmOperationDate;
	}

}
