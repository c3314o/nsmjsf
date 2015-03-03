
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.BodType;

public class BodTypeWrapper {

private static final Log log = LogFactory
			.getLog(BodTypeWrapper.class);


	BodType bodType;

	public BodTypeWrapper(BodType bodType) {
		this.bodType = bodType;
	}

	public BodTypeWrapper() {

	}

	public BodType getBodType() {
		return bodType;
	}

	public void setBodType(BodType bodType) {
		this.bodType = bodType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.bodType.getBodTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BodTypeWrapper other = (BodTypeWrapper) obj;
		if (!Objects.equals(this.bodType.getBodTypeId(), other.getBodType().getBodTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bodType.toString();

	}

}

