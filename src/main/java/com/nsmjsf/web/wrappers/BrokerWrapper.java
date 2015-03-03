
package  com.nsmjsf.web.wrappers;


import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Broker;

public class BrokerWrapper {

private static final Log log = LogFactory
			.getLog(BrokerWrapper.class);


	Broker broker;

	public BrokerWrapper(Broker broker) {
		this.broker = broker;
	}

	public BrokerWrapper() {

	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.broker.getBrokerId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final BrokerWrapper other = (BrokerWrapper) obj;
		if (!Objects.equals(this.broker.getBrokerId(), other.getBroker().getBrokerId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.broker.toString();

	}

}

