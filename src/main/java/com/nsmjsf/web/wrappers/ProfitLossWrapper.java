
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.ProfitLoss;

public class ProfitLossWrapper {

private static final Log log = LogFactory
			.getLog(ProfitLossWrapper.class);


	ProfitLoss profitLoss;

	public ProfitLossWrapper(ProfitLoss profitLoss) {
		this.profitLoss = profitLoss;
	}

	public ProfitLossWrapper() {

	}

	public ProfitLoss getProfitLoss() {
		return profitLoss;
	}

	public void setProfitLoss(ProfitLoss profitLoss) {
		this.profitLoss = profitLoss;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.profitLoss.getProfitLossId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProfitLossWrapper other = (ProfitLossWrapper) obj;
		if (!Objects.equals(this.profitLoss.getProfitLossId(), other.getProfitLoss().getProfitLossId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.profitLoss.toString();

	}

}

