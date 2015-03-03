
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.TodaysPrice;

public class TodaysPriceWrapper {

private static final Log log = LogFactory
			.getLog(TodaysPriceWrapper.class);


	TodaysPrice todaysPrice;

	public TodaysPriceWrapper(TodaysPrice todaysPrice) {
		this.todaysPrice = todaysPrice;
	}

	public TodaysPriceWrapper() {

	}

	public TodaysPrice getTodaysPrice() {
		return todaysPrice;
	}

	public void setTodaysPrice(TodaysPrice todaysPrice) {
		this.todaysPrice = todaysPrice;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.todaysPrice.getTodaysPriceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TodaysPriceWrapper other = (TodaysPriceWrapper) obj;
		if (!Objects.equals(this.todaysPrice.getTodaysPriceId(), other.getTodaysPrice().getTodaysPriceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.todaysPrice.toString();

	}

}

