
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.CurrencyType;

public class CurrencyTypeWrapper {

private static final Log log = LogFactory
			.getLog(CurrencyTypeWrapper.class);


	CurrencyType currencyType;

	public CurrencyTypeWrapper(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	public CurrencyTypeWrapper() {

	}

	public CurrencyType getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(CurrencyType currencyType) {
		this.currencyType = currencyType;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.currencyType.getCurrencyTypeId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final CurrencyTypeWrapper other = (CurrencyTypeWrapper) obj;
		if (!Objects.equals(this.currencyType.getCurrencyTypeId(), other.getCurrencyType().getCurrencyTypeId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.currencyType.toString();

	}

}

