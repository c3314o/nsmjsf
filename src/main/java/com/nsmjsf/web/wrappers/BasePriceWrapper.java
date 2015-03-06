package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BasePrice;

public class BasePriceWrapper {

	private static final Log log = LogFactory.getLog(BasePriceWrapper.class);

	BasePrice basePrice;

	public BasePriceWrapper(BasePrice basePrice) {
		this.basePrice = basePrice;
	}

	public BasePriceWrapper() {

	}

	public BasePrice getBasePrice() {
		return basePrice;
	}

	public void setBasePrice(BasePrice basePrice) {
		this.basePrice = basePrice;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.basePrice.getBasePriceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BasePriceWrapper other = (BasePriceWrapper) obj;
		if (!Objects.equals(this.basePrice.getBasePriceId(), other
				.getBasePrice().getBasePriceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.basePrice.toString();

	}

}
