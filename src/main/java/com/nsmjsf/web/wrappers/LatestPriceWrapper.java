
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.LatestPrice;

public class LatestPriceWrapper {

private static final Log log = LogFactory
			.getLog(LatestPriceWrapper.class);


	LatestPrice latestPrice;

	public LatestPriceWrapper(LatestPrice latestPrice) {
		this.latestPrice = latestPrice;
	}

	public LatestPriceWrapper() {

	}

	public LatestPrice getLatestPrice() {
		return latestPrice;
	}

	public void setLatestPrice(LatestPrice latestPrice) {
		this.latestPrice = latestPrice;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.latestPrice.getLatestPriceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final LatestPriceWrapper other = (LatestPriceWrapper) obj;
		if (!Objects.equals(this.latestPrice.getLatestPriceId(), other.getLatestPrice().getLatestPriceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.latestPrice.toString();

	}

}

