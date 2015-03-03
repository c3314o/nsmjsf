
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Agm;

public class AgmWrapper {

private static final Log log = LogFactory
			.getLog(AgmWrapper.class);


	Agm agm;

	public AgmWrapper(Agm agm) {
		this.agm = agm;
	}

	public AgmWrapper() {

	}

	public Agm getAgm() {
		return agm;
	}

	public void setAgm(Agm agm) {
		this.agm = agm;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.agm.getAgmId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final AgmWrapper other = (AgmWrapper) obj;
		if (!Objects.equals(this.agm.getAgmId(), other.getAgm().getAgmId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.agm.toString();

	}

}

