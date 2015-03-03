
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.AllotmentResult;

public class AllotmentResultWrapper {

private static final Log log = LogFactory
			.getLog(AllotmentResultWrapper.class);


	AllotmentResult allotmentResult;

	public AllotmentResultWrapper(AllotmentResult allotmentResult) {
		this.allotmentResult = allotmentResult;
	}

	public AllotmentResultWrapper() {

	}

	public AllotmentResult getAllotmentResult() {
		return allotmentResult;
	}

	public void setAllotmentResult(AllotmentResult allotmentResult) {
		this.allotmentResult = allotmentResult;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.allotmentResult.getAllotmentResultId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AllotmentResultWrapper other = (AllotmentResultWrapper) obj;
		if (!Objects.equals(this.allotmentResult.getAllotmentResultId(), other.getAllotmentResult().getAllotmentResultId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.allotmentResult.toString();

	}

}

