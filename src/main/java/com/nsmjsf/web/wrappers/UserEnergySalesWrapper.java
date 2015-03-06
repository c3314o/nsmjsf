package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserEnergySales;

public class UserEnergySalesWrapper {

	private static final Log log = LogFactory
			.getLog(UserEnergySalesWrapper.class);

	UserEnergySales userEnergySales;

	public UserEnergySalesWrapper(UserEnergySales userEnergySales) {
		this.userEnergySales = userEnergySales;
	}

	public UserEnergySalesWrapper() {

	}

	public UserEnergySales getUserEnergySales() {
		return userEnergySales;
	}

	public void setUserEnergySales(UserEnergySales userEnergySales) {
		this.userEnergySales = userEnergySales;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.userEnergySales.getUserEnergySalesId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserEnergySalesWrapper other = (UserEnergySalesWrapper) obj;
		if (!Objects.equals(this.userEnergySales.getUserEnergySalesId(), other
				.getUserEnergySales().getUserEnergySalesId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userEnergySales.toString();

	}

}
