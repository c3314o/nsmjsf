package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.FiscalYear;

public class FiscalYearWrapper {

	private static final Log log = LogFactory.getLog(FiscalYearWrapper.class);

	FiscalYear fiscalYear;

	public FiscalYearWrapper(FiscalYear fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public FiscalYearWrapper() {

	}

	public FiscalYear getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(FiscalYear fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.fiscalYear.getFiscalYearId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final FiscalYearWrapper other = (FiscalYearWrapper) obj;
		if (!Objects.equals(this.fiscalYear.getFiscalYearId(), other
				.getFiscalYear().getFiscalYearId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.fiscalYear.toString();

	}

}
