
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.UserEnergy;

public class UserEnergyWrapper {

private static final Log log = LogFactory
			.getLog(UserEnergyWrapper.class);


	UserEnergy userEnergy;

	public UserEnergyWrapper(UserEnergy userEnergy) {
		this.userEnergy = userEnergy;
	}

	public UserEnergyWrapper() {

	}

	public UserEnergy getUserEnergy() {
		return userEnergy;
	}

	public void setUserEnergy(UserEnergy userEnergy) {
		this.userEnergy = userEnergy;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.userEnergy.getUserEnergyId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final UserEnergyWrapper other = (UserEnergyWrapper) obj;
		if (!Objects.equals(this.userEnergy.getUserEnergyId(), other.getUserEnergy().getUserEnergyId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.userEnergy.toString();

	}

}

