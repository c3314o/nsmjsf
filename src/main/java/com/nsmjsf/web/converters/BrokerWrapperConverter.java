package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.BrokerAdapter;
import com.nsmjsf.web.datasources.BrokerDataSource;
import com.nsmjsf.web.datamodels.Broker;
import com.nsmjsf.web.wrappers.BrokerWrapper;


@FacesConverter("brokerWrapperConverter")
public class BrokerWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(BrokerWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			BrokerDataSource brokerDataSource = new BrokerDataSource();
			Broker broker = brokerDataSource.get(Integer.parseInt(value));
			BrokerWrapper brokerWrapper=BrokerAdapter.wrap(broker);
			return brokerWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((BrokerWrapper) object).getBroker().getBrokerId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

