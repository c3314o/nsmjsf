
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FinancialReport;

public class FinancialReportWrapper {

private static final Log log = LogFactory
			.getLog(FinancialReportWrapper.class);


	FinancialReport financialReport;

	public FinancialReportWrapper(FinancialReport financialReport) {
		this.financialReport = financialReport;
	}

	public FinancialReportWrapper() {

	}

	public FinancialReport getFinancialReport() {
		return financialReport;
	}

	public void setFinancialReport(FinancialReport financialReport) {
		this.financialReport = financialReport;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.financialReport.getFinancialReportId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FinancialReportWrapper other = (FinancialReportWrapper) obj;
		if (!Objects.equals(this.financialReport.getFinancialReportId(), other.getFinancialReport().getFinancialReportId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.financialReport.toString();

	}

}

