
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Month;

public class MonthWrapper {

private static final Log log = LogFactory
			.getLog(MonthWrapper.class);


	Month month;

	public MonthWrapper(Month month) {
		this.month = month;
	}

	public MonthWrapper() {

	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.month.getMonthId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final MonthWrapper other = (MonthWrapper) obj;
		if (!Objects.equals(this.month.getMonthId(), other.getMonth().getMonthId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.month.toString();

	}

}

