package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BonusDividend;

public class BonusDividendWrapper {

	private static final Log log = LogFactory
			.getLog(BonusDividendWrapper.class);

	BonusDividend bonusDividend;

	public BonusDividendWrapper(BonusDividend bonusDividend) {
		this.bonusDividend = bonusDividend;
	}

	public BonusDividendWrapper() {

	}

	public BonusDividend getBonusDividend() {
		return bonusDividend;
	}

	public void setBonusDividend(BonusDividend bonusDividend) {
		this.bonusDividend = bonusDividend;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.bonusDividend.getBonusDividendId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BonusDividendWrapper other = (BonusDividendWrapper) obj;
		if (!Objects.equals(this.bonusDividend.getBonusDividendId(), other
				.getBonusDividend().getBonusDividendId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bonusDividend.toString();

	}

}
