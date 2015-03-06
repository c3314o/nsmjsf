package com.nsmjsf.web.wrappers;

import java.util.Objects;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Proxy;

public class ProxyWrapper {

	private static final Log log = LogFactory.getLog(ProxyWrapper.class);

	Proxy proxy;

	public ProxyWrapper(Proxy proxy) {
		this.proxy = proxy;
	}

	public ProxyWrapper() {

	}

	public Proxy getProxy() {
		return proxy;
	}

	public void setProxy(Proxy proxy) {
		this.proxy = proxy;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 83 * hash + Objects.hashCode(this.proxy.getProxyId());
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final ProxyWrapper other = (ProxyWrapper) obj;
		if (!Objects.equals(this.proxy.getProxyId(), other.getProxy()
				.getProxyId()))
			return false;
		return true;
	}

	public String getLabel() {
		return this.proxy.toString();

	}

}
