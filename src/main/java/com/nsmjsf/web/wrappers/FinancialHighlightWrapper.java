
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FinancialHighlight;

public class FinancialHighlightWrapper {

private static final Log log = LogFactory
			.getLog(FinancialHighlightWrapper.class);


	FinancialHighlight financialHighlight;

	public FinancialHighlightWrapper(FinancialHighlight financialHighlight) {
		this.financialHighlight = financialHighlight;
	}

	public FinancialHighlightWrapper() {

	}

	public FinancialHighlight getFinancialHighlight() {
		return financialHighlight;
	}

	public void setFinancialHighlight(FinancialHighlight financialHighlight) {
		this.financialHighlight = financialHighlight;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.financialHighlight.getFinancialHighlightId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FinancialHighlightWrapper other = (FinancialHighlightWrapper) obj;
		if (!Objects.equals(this.financialHighlight.getFinancialHighlightId(), other.getFinancialHighlight().getFinancialHighlightId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.financialHighlight.toString();

	}

}

