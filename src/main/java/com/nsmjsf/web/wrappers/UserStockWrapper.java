
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserStock;

public class UserStockWrapper {

private static final Log log = LogFactory
			.getLog(UserStockWrapper.class);


	UserStock userStock;

	public UserStockWrapper(UserStock userStock) {
		this.userStock = userStock;
	}

	public UserStockWrapper() {

	}

	public UserStock getUserStock() {
		return userStock;
	}

	public void setUserStock(UserStock userStock) {
		this.userStock = userStock;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.userStock.getUserStockId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserStockWrapper other = (UserStockWrapper) obj;
		if (!Objects.equals(this.userStock.getUserStockId(), other.getUserStock().getUserStockId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userStock.toString();

	}

}

