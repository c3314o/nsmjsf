

package com.nsmjsf.web.lazymodels;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.nsmjsf.web.datamodels.Proxy;
import com.nsmjsf.web.sorters.ProxySorter;

public class LazyProxyDataModel extends LazyDataModel<Proxy> {
	private static final Log log = LogFactory
			.getLog(LazyProxyDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Proxy> proxyList;

	public LazyProxyDataModel(List<Proxy> proxyList) {
		this.proxyList = proxyList;
	}

	@Override
	public Proxy getRowData(String rowKey) {
		for (Proxy proxy : proxyList) {
			if (proxy.getProxyId().toString().equalsIgnoreCase(rowKey))
				return proxy;
		}

		return null;
	}

	@Override
	public Object getRowKey(Proxy object) {
		return object.getProxyId();
	}

	@Override
	public List<Proxy> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Proxy> data = new ArrayList<Proxy>();

		// filter
		for (Proxy proxy : proxyList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = proxy.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(proxy));
						log.info("filterField:"+filterProperty);
						log.info("filterValue:"+fieldValue);

						if (filterValue == null
								|| fieldValue
										.startsWith(filterValue.toString())) {
							match = true;
						} else {
							match = false;
							break;
						}
					} catch (Exception e) {
						match = false;
					}
				}
			}

			if (match) {
				data.add(proxy);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new ProxySorter(sortField, sortOrder));
		}

		// rowCount
		int dataSize = data.size();
		this.setRowCount(dataSize);

		// paginate
		if (dataSize > pageSize) {
			try {
				return data.subList(first, first + pageSize);
			} catch (IndexOutOfBoundsException e) {
				return data.subList(first, first + (dataSize % pageSize));
			}
		} else {
			return data;
		}
	}

}

