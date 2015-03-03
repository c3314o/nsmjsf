
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MonthlyFinancialHighlight;

public class MonthlyFinancialHighlightWrapper {

private static final Log log = LogFactory
			.getLog(MonthlyFinancialHighlightWrapper.class);


	MonthlyFinancialHighlight monthlyFinancialHighlight;

	public MonthlyFinancialHighlightWrapper(MonthlyFinancialHighlight monthlyFinancialHighlight) {
		this.monthlyFinancialHighlight = monthlyFinancialHighlight;
	}

	public MonthlyFinancialHighlightWrapper() {

	}

	public MonthlyFinancialHighlight getMonthlyFinancialHighlight() {
		return monthlyFinancialHighlight;
	}

	public void setMonthlyFinancialHighlight(MonthlyFinancialHighlight monthlyFinancialHighlight) {
		this.monthlyFinancialHighlight = monthlyFinancialHighlight;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.monthlyFinancialHighlight.getMonthlyFinancialHighlightId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MonthlyFinancialHighlightWrapper other = (MonthlyFinancialHighlightWrapper) obj;
		if (!Objects.equals(this.monthlyFinancialHighlight.getMonthlyFinancialHighlightId(), other.getMonthlyFinancialHighlight().getMonthlyFinancialHighlightId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.monthlyFinancialHighlight.toString();

	}

}

