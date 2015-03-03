
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Bod;

public class BodWrapper {

private static final Log log = LogFactory
			.getLog(BodWrapper.class);


	Bod bod;

	public BodWrapper(Bod bod) {
		this.bod = bod;
	}

	public BodWrapper() {

	}

	public Bod getBod() {
		return bod;
	}

	public void setBod(Bod bod) {
		this.bod = bod;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.bod.getBodId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BodWrapper other = (BodWrapper) obj;
		if (!Objects.equals(this.bod.getBodId(), other.getBod().getBodId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.bod.toString();

	}

}

