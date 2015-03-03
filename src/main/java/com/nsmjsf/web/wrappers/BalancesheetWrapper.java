
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Balancesheet;

public class BalancesheetWrapper {

private static final Log log = LogFactory
			.getLog(BalancesheetWrapper.class);


	Balancesheet balancesheet;

	public BalancesheetWrapper(Balancesheet balancesheet) {
		this.balancesheet = balancesheet;
	}

	public BalancesheetWrapper() {

	}

	public Balancesheet getBalancesheet() {
		return balancesheet;
	}

	public void setBalancesheet(Balancesheet balancesheet) {
		this.balancesheet = balancesheet;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.balancesheet.getBalancesheetId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BalancesheetWrapper other = (BalancesheetWrapper) obj;
		if (!Objects.equals(this.balancesheet.getBalancesheetId(), other.getBalancesheet().getBalancesheetId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.balancesheet.toString();

	}

}

