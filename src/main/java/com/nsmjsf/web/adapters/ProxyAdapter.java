
package com.nsmjsf.web.adapters;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.wrappers.ProxyWrapper;

public class ProxyAdapter {
private static final Log log = LogFactory
			.getLog(ProxyAdapter.class);
	
	public static List<ProxyWrapper> wrapAll(List<Proxy> proxyList)
	{
		List<ProxyWrapper> proxyWrapperList=new ArrayList<ProxyWrapper>();
		for(Proxy proxy:proxyList)
		{
			ProxyWrapper proxyWrapper=new ProxyWrapper();
			proxyWrapper.setProxy(proxy);
			proxyWrapperList.add(proxyWrapper);
		}
		return proxyWrapperList;
		
	}
	
	public static ProxyWrapper wrap(Proxy proxy)
	{
		ProxyWrapper proxyWrapper=new ProxyWrapper();
		proxyWrapper.setProxy(proxy);
		return proxyWrapper;
		
	}

}

