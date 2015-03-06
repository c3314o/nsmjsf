package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BullionType;

public class BullionTypeWrapper {

	private static final Log log = LogFactory.getLog(BullionTypeWrapper.class);

	BullionType bullionType;

	public BullionTypeWrapper(BullionType bullionType) {
		this.bullionType = bullionType;
	}

	public BullionTypeWrapper() {

	}

	public BullionType getBullionType() {
		return bullionType;
	}

	public void setBullionType(BullionType bullionType) {
		this.bullionType = bullionType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash
				+ Objects.hashCode(this.bullionType.getBullionTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BullionTypeWrapper other = (BullionTypeWrapper) obj;
		if (!Objects.equals(this.bullionType.getBullionTypeId(), other
				.getBullionType().getBullionTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bullionType.toString();

	}

}
