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

import com.nsmjsf.web.datamodels.Broker;
import com.nsmjsf.web.sorters.BrokerSorter;

public class LazyBrokerDataModel extends LazyDataModel<Broker> {
	private static final Log log = LogFactory.getLog(LazyBrokerDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Broker> brokerList;

	public LazyBrokerDataModel(List<Broker> brokerList) {
		this.brokerList = brokerList;
	}

	@Override
	public Broker getRowData(String rowKey) {
		for (Broker broker : brokerList) {
			if (broker.getBrokerId().toString().equalsIgnoreCase(rowKey))
				return broker;
		}

		return null;
	}

	@Override
	public Object getRowKey(Broker object) {
		return object.getBrokerId();
	}

	@Override
	public List<Broker> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Broker> data = new ArrayList<Broker>();

		// filter
		for (Broker broker : brokerList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = broker.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(broker));
						log.info("filterField:" + filterProperty);
						log.info("filterValue:" + fieldValue);

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
				data.add(broker);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BrokerSorter(sortField, sortOrder));
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
