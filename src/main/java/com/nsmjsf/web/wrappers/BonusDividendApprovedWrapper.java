
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BonusDividendApproved;

public class BonusDividendApprovedWrapper {

private static final Log log = LogFactory
			.getLog(BonusDividendApprovedWrapper.class);


	BonusDividendApproved bonusDividendApproved;

	public BonusDividendApprovedWrapper(BonusDividendApproved bonusDividendApproved) {
		this.bonusDividendApproved = bonusDividendApproved;
	}

	public BonusDividendApprovedWrapper() {

	}

	public BonusDividendApproved getBonusDividendApproved() {
		return bonusDividendApproved;
	}

	public void setBonusDividendApproved(BonusDividendApproved bonusDividendApproved) {
		this.bonusDividendApproved = bonusDividendApproved;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.bonusDividendApproved.getBonusDividendApprovedId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BonusDividendApprovedWrapper other = (BonusDividendApprovedWrapper) obj;
		if (!Objects.equals(this.bonusDividendApproved.getBonusDividendApprovedId(), other.getBonusDividendApproved().getBonusDividendApprovedId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bonusDividendApproved.toString();

	}

}

