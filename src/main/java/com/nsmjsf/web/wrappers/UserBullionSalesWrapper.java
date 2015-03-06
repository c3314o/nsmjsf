package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserBullionSales;

public class UserBullionSalesWrapper {

	private static final Log log = LogFactory
			.getLog(UserBullionSalesWrapper.class);

	UserBullionSales userBullionSales;

	public UserBullionSalesWrapper(UserBullionSales userBullionSales) {
		this.userBullionSales = userBullionSales;
	}

	public UserBullionSalesWrapper() {

	}

	public UserBullionSales getUserBullionSales() {
		return userBullionSales;
	}

	public void setUserBullionSales(UserBullionSales userBullionSales) {
		this.userBullionSales = userBullionSales;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83
				* hash
				+ Objects.hashCode(this.userBullionSales
						.getUserBullionSalesId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserBullionSalesWrapper other = (UserBullionSalesWrapper) obj;
		if (!Objects.equals(this.userBullionSales.getUserBullionSalesId(),
				other.getUserBullionSales().getUserBullionSalesId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userBullionSales.toString();

	}

}
