
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.MarketIndex;

public class MarketIndexWrapper {

private static final Log log = LogFactory
			.getLog(MarketIndexWrapper.class);


	MarketIndex marketIndex;

	public MarketIndexWrapper(MarketIndex marketIndex) {
		this.marketIndex = marketIndex;
	}

	public MarketIndexWrapper() {

	}

	public MarketIndex getMarketIndex() {
		return marketIndex;
	}

	public void setMarketIndex(MarketIndex marketIndex) {
		this.marketIndex = marketIndex;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.marketIndex.getMarketIndexId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MarketIndexWrapper other = (MarketIndexWrapper) obj;
		if (!Objects.equals(this.marketIndex.getMarketIndexId(), other.getMarketIndex().getMarketIndexId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.marketIndex.toString();

	}

}

