
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.EnergyPrice;

public class EnergyPriceWrapper {

private static final Log log = LogFactory
			.getLog(EnergyPriceWrapper.class);


	EnergyPrice energyPrice;

	public EnergyPriceWrapper(EnergyPrice energyPrice) {
		this.energyPrice = energyPrice;
	}

	public EnergyPriceWrapper() {

	}

	public EnergyPrice getEnergyPrice() {
		return energyPrice;
	}

	public void setEnergyPrice(EnergyPrice energyPrice) {
		this.energyPrice = energyPrice;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.energyPrice.getEnergyPriceId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final EnergyPriceWrapper other = (EnergyPriceWrapper) obj;
		if (!Objects.equals(this.energyPrice.getEnergyPriceId(), other.getEnergyPrice().getEnergyPriceId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.energyPrice.toString();

	}

}

