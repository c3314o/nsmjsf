package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Broker;
import com.nsmjsf.web.wrappers.BrokerWrapper;

public class BrokerAdapter {
	private static final Log log = LogFactory.getLog(BrokerAdapter.class);

	public static List<BrokerWrapper> wrapAll(List<Broker> brokerList) {
		List<BrokerWrapper> brokerWrapperList = new ArrayList<BrokerWrapper>();
		for (Broker broker : brokerList) {
			BrokerWrapper brokerWrapper = new BrokerWrapper();
			brokerWrapper.setBroker(broker);
			brokerWrapperList.add(brokerWrapper);
		}
		return brokerWrapperList;

	}

	public static BrokerWrapper wrap(Broker broker) {
		BrokerWrapper brokerWrapper = new BrokerWrapper();
		brokerWrapper.setBroker(broker);
		return brokerWrapper;

	}

}
