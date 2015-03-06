package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserWatchListStock;

public class UserWatchListStockWrapper {

	private static final Log log = LogFactory
			.getLog(UserWatchListStockWrapper.class);

	UserWatchListStock userWatchListStock;

	public UserWatchListStockWrapper(UserWatchListStock userWatchListStock) {
		this.userWatchListStock = userWatchListStock;
	}

	public UserWatchListStockWrapper() {

	}

	public UserWatchListStock getUserWatchListStock() {
		return userWatchListStock;
	}

	public void setUserWatchListStock(UserWatchListStock userWatchListStock) {
		this.userWatchListStock = userWatchListStock;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83
				* hash
				+ Objects.hashCode(this.userWatchListStock
						.getUserWatchListStockId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserWatchListStockWrapper other = (UserWatchListStockWrapper) obj;
		if (!Objects.equals(this.userWatchListStock.getUserWatchListStockId(),
				other.getUserWatchListStock().getUserWatchListStockId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userWatchListStock.toString();

	}

}
