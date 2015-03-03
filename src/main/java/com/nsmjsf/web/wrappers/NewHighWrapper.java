
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.NewHigh;

public class NewHighWrapper {

private static final Log log = LogFactory
			.getLog(NewHighWrapper.class);


	NewHigh newHigh;

	public NewHighWrapper(NewHigh newHigh) {
		this.newHigh = newHigh;
	}

	public NewHighWrapper() {

	}

	public NewHigh getNewHigh() {
		return newHigh;
	}

	public void setNewHigh(NewHigh newHigh) {
		this.newHigh = newHigh;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.newHigh.getNewHighId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final NewHighWrapper other = (NewHighWrapper) obj;
		if (!Objects.equals(this.newHigh.getNewHighId(), other.getNewHigh().getNewHighId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.newHigh.toString();

	}

}

