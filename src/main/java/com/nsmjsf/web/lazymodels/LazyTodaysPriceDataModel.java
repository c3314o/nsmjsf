

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

import com.nsmjsf.web.datamodels.TodaysPrice;
import com.nsmjsf.web.sorters.TodaysPriceSorter;

public class LazyTodaysPriceDataModel extends LazyDataModel<TodaysPrice> {
	private static final Log log = LogFactory
			.getLog(LazyTodaysPriceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<TodaysPrice> todaysPriceList;

	public LazyTodaysPriceDataModel(List<TodaysPrice> todaysPriceList) {
		this.todaysPriceList = todaysPriceList;
	}

	@Override
	public TodaysPrice getRowData(String rowKey) {
		for (TodaysPrice todaysPrice : todaysPriceList) {
			if (todaysPrice.getTodaysPriceId().toString().equalsIgnoreCase(rowKey))
				return todaysPrice;
		}

		return null;
	}

	@Override
	public Object getRowKey(TodaysPrice object) {
		return object.getTodaysPriceId();
	}

	@Override
	public List<TodaysPrice> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<TodaysPrice> data = new ArrayList<TodaysPrice>();

		// filter
		for (TodaysPrice todaysPrice : todaysPriceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = todaysPrice.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(todaysPrice));
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
				data.add(todaysPrice);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new TodaysPriceSorter(sortField, sortOrder));
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

