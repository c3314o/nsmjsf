package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CurrencyRate;

public class CurrencyRateWrapper {

	private static final Log log = LogFactory.getLog(CurrencyRateWrapper.class);

	CurrencyRate currencyRate;

	public CurrencyRateWrapper(CurrencyRate currencyRate) {
		this.currencyRate = currencyRate;
	}

	public CurrencyRateWrapper() {

	}

	public CurrencyRate getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(CurrencyRate currencyRate) {
		this.currencyRate = currencyRate;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.currencyRate.getCurrencyRateId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CurrencyRateWrapper other = (CurrencyRateWrapper) obj;
		if (!Objects.equals(this.currencyRate.getCurrencyRateId(), other
				.getCurrencyRate().getCurrencyRateId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.currencyRate.toString();

	}

}
