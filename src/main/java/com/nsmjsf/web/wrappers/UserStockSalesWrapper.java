package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserStockSales;

public class UserStockSalesWrapper {

	private static final Log log = LogFactory
			.getLog(UserStockSalesWrapper.class);

	UserStockSales userStockSales;

	public UserStockSalesWrapper(UserStockSales userStockSales) {
		this.userStockSales = userStockSales;
	}

	public UserStockSalesWrapper() {

	}

	public UserStockSales getUserStockSales() {
		return userStockSales;
	}

	public void setUserStockSales(UserStockSales userStockSales) {
		this.userStockSales = userStockSales;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.userStockSales.getUserStockSalesId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserStockSalesWrapper other = (UserStockSalesWrapper) obj;
		if (!Objects.equals(this.userStockSales.getUserStockSalesId(), other
				.getUserStockSales().getUserStockSalesId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userStockSales.toString();

	}

}
