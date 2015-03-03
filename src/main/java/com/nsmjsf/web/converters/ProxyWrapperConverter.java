package com.nsmjsf.web.converters;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;




import com.nsmjsf.web.adapters.ProxyAdapter;
import com.nsmjsf.web.datasources.ProxyDataSource;
import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.wrappers.ProxyWrapper;


@FacesConverter("proxyWrapperConverter")
public class ProxyWrapperConverter implements Converter{
	private static final Log log = LogFactory.getLog(ProxyWrapperConverter.class);

	@Override
	public Object getAsObject(FacesContext fc, UIComponent arg1, String value) {
		if (value != null && value.trim().length() > 0) {
			ProxyDataSource proxyDataSource = new ProxyDataSource();
			Proxy proxy = proxyDataSource.get(Integer.parseInt(value));
			ProxyWrapper proxyWrapper=ProxyAdapter.wrap(proxy);
			return proxyWrapper;
		} else {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext fc, UIComponent arg1, Object object) {
		if (object != null) {
			return String.valueOf(((ProxyWrapper) object).getProxy().getProxyId());
		} else {
			return null;
		}
	}

	
	
	
	
	
	
	
	
}

