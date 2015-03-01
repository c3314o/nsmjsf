package com.nsmjsf.web.datamodels;

// Generated Feb 28, 2015 11:53:30 PM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 * ProxyId generated by hbm2java
 */
@Embeddable
public class ProxyId implements java.io.Serializable {

	private int proxyId;
	private String proxyIp;
	private int proxySuccess;
	private int proxyFailed;
	private int proxyPort;

	public ProxyId() {
	}

	public ProxyId(int proxyId, String proxyIp, int proxySuccess,
			int proxyFailed, int proxyPort) {
		this.proxyId = proxyId;
		this.proxyIp = proxyIp;
		this.proxySuccess = proxySuccess;
		this.proxyFailed = proxyFailed;
		this.proxyPort = proxyPort;
	}

	@Column(name = "proxy_id", nullable = false)
	public int getProxyId() {
		return this.proxyId;
	}

	public void setProxyId(int proxyId) {
		this.proxyId = proxyId;
	}

	@Column(name = "proxy_ip", nullable = false)
	public String getProxyIp() {
		return this.proxyIp;
	}

	public void setProxyIp(String proxyIp) {
		this.proxyIp = proxyIp;
	}

	@Column(name = "proxy_success", nullable = false)
	public int getProxySuccess() {
		return this.proxySuccess;
	}

	public void setProxySuccess(int proxySuccess) {
		this.proxySuccess = proxySuccess;
	}

	@Column(name = "proxy_failed", nullable = false)
	public int getProxyFailed() {
		return this.proxyFailed;
	}

	public void setProxyFailed(int proxyFailed) {
		this.proxyFailed = proxyFailed;
	}

	@Column(name = "proxy_port", nullable = false)
	public int getProxyPort() {
		return this.proxyPort;
	}

	public void setProxyPort(int proxyPort) {
		this.proxyPort = proxyPort;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof ProxyId))
			return false;
		ProxyId castOther = (ProxyId) other;

		return (this.getProxyId() == castOther.getProxyId())
				&& ((this.getProxyIp() == castOther.getProxyIp()) || (this
						.getProxyIp() != null && castOther.getProxyIp() != null && this
						.getProxyIp().equals(castOther.getProxyIp())))
				&& (this.getProxySuccess() == castOther.getProxySuccess())
				&& (this.getProxyFailed() == castOther.getProxyFailed())
				&& (this.getProxyPort() == castOther.getProxyPort());
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result + this.getProxyId();
		result = 37 * result
				+ (getProxyIp() == null ? 0 : this.getProxyIp().hashCode());
		result = 37 * result + this.getProxySuccess();
		result = 37 * result + this.getProxyFailed();
		result = 37 * result + this.getProxyPort();
		return result;
	}

}
