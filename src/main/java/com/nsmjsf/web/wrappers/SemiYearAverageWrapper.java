package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.SemiYearAverage;

public class SemiYearAverageWrapper {

	private static final Log log = LogFactory
			.getLog(SemiYearAverageWrapper.class);

	SemiYearAverage semiYearAverage;

	public SemiYearAverageWrapper(SemiYearAverage semiYearAverage) {
		this.semiYearAverage = semiYearAverage;
	}

	public SemiYearAverageWrapper() {

	}

	public SemiYearAverage getSemiYearAverage() {
		return semiYearAverage;
	}

	public void setSemiYearAverage(SemiYearAverage semiYearAverage) {
		this.semiYearAverage = semiYearAverage;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.semiYearAverage.getSemiYearAverageId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final SemiYearAverageWrapper other = (SemiYearAverageWrapper) obj;
		if (!Objects.equals(this.semiYearAverage.getSemiYearAverageId(), other
				.getSemiYearAverage().getSemiYearAverageId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.semiYearAverage.toString();

	}

}
