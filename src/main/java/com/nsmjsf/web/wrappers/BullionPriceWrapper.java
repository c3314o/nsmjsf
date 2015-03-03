
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BullionPrice;

public class BullionPriceWrapper {

private static final Log log = LogFactory
			.getLog(BullionPriceWrapper.class);


	BullionPrice bullionPrice;

	public BullionPriceWrapper(BullionPrice bullionPrice) {
		this.bullionPrice = bullionPrice;
	}

	public BullionPriceWrapper() {

	}

	public BullionPrice getBullionPrice() {
		return bullionPrice;
	}

	public void setBullionPrice(BullionPrice bullionPrice) {
		this.bullionPrice = bullionPrice;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.bullionPrice.getBullionPriceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BullionPriceWrapper other = (BullionPriceWrapper) obj;
		if (!Objects.equals(this.bullionPrice.getBullionPriceId(), other.getBullionPrice().getBullionPriceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bullionPrice.toString();

	}

}

