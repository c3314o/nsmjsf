
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.EnergyType;

public class EnergyTypeWrapper {

private static final Log log = LogFactory
			.getLog(EnergyTypeWrapper.class);


	EnergyType energyType;

	public EnergyTypeWrapper(EnergyType energyType) {
		this.energyType = energyType;
	}

	public EnergyTypeWrapper() {

	}

	public EnergyType getEnergyType() {
		return energyType;
	}

	public void setEnergyType(EnergyType energyType) {
		this.energyType = energyType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.energyType.getEnergyTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EnergyTypeWrapper other = (EnergyTypeWrapper) obj;
		if (!Objects.equals(this.energyType.getEnergyTypeId(), other.getEnergyType().getEnergyTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.energyType.toString();

	}

}

