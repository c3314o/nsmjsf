
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.TodaysPriceDump;

public class TodaysPriceDumpWrapper {

private static final Log log = LogFactory
			.getLog(TodaysPriceDumpWrapper.class);


	TodaysPriceDump todaysPriceDump;

	public TodaysPriceDumpWrapper(TodaysPriceDump todaysPriceDump) {
		this.todaysPriceDump = todaysPriceDump;
	}

	public TodaysPriceDumpWrapper() {

	}

	public TodaysPriceDump getTodaysPriceDump() {
		return todaysPriceDump;
	}

	public void setTodaysPriceDump(TodaysPriceDump todaysPriceDump) {
		this.todaysPriceDump = todaysPriceDump;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.todaysPriceDump.getTodaysPriceDumpId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final TodaysPriceDumpWrapper other = (TodaysPriceDumpWrapper) obj;
		if (!Objects.equals(this.todaysPriceDump.getTodaysPriceDumpId(), other.getTodaysPriceDump().getTodaysPriceDumpId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.todaysPriceDump.toString();

	}

}

