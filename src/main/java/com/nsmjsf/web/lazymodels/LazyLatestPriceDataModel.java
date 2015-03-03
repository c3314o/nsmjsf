

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

import com.nsmjsf.web.datamodels.LatestPrice;
import com.nsmjsf.web.sorters.LatestPriceSorter;

public class LazyLatestPriceDataModel extends LazyDataModel<LatestPrice> {
	private static final Log log = LogFactory
			.getLog(LazyLatestPriceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<LatestPrice> latestPriceList;

	public LazyLatestPriceDataModel(List<LatestPrice> latestPriceList) {
		this.latestPriceList = latestPriceList;
	}

	@Override
	public LatestPrice getRowData(String rowKey) {
		for (LatestPrice latestPrice : latestPriceList) {
			if (latestPrice.getLatestPriceId().toString().equalsIgnoreCase(rowKey))
				return latestPrice;
		}

		return null;
	}

	@Override
	public Object getRowKey(LatestPrice object) {
		return object.getLatestPriceId();
	}

	@Override
	public List<LatestPrice> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<LatestPrice> data = new ArrayList<LatestPrice>();

		// filter
		for (LatestPrice latestPrice : latestPriceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = latestPrice.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(latestPrice));
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
				data.add(latestPrice);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new LatestPriceSorter(sortField, sortOrder));
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

