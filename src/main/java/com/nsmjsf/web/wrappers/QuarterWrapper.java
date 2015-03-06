package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Quarter;

public class QuarterWrapper {

	private static final Log log = LogFactory.getLog(QuarterWrapper.class);

	Quarter quarter;

	public QuarterWrapper(Quarter quarter) {
		this.quarter = quarter;
	}

	public QuarterWrapper() {

	}

	public Quarter getQuarter() {
		return quarter;
	}

	public void setQuarter(Quarter quarter) {
		this.quarter = quarter;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.quarter.getQuarterId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final QuarterWrapper other = (QuarterWrapper) obj;
		if (!Objects.equals(this.quarter.getQuarterId(), other.getQuarter()
				.getQuarterId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.quarter.toString();

	}

}
