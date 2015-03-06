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

import com.nsmjsf.web.datamodels.CurrencyRate;
import com.nsmjsf.web.sorters.CurrencyRateSorter;

public class LazyCurrencyRateDataModel extends LazyDataModel<CurrencyRate> {
	private static final Log log = LogFactory
			.getLog(LazyCurrencyRateDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CurrencyRate> currencyRateList;

	public LazyCurrencyRateDataModel(List<CurrencyRate> currencyRateList) {
		this.currencyRateList = currencyRateList;
	}

	@Override
	public CurrencyRate getRowData(String rowKey) {
		for (CurrencyRate currencyRate : currencyRateList) {
			if (currencyRate.getCurrencyRateId().toString()
					.equalsIgnoreCase(rowKey))
				return currencyRate;
		}

		return null;
	}

	@Override
	public Object getRowKey(CurrencyRate object) {
		return object.getCurrencyRateId();
	}

	@Override
	public List<CurrencyRate> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CurrencyRate> data = new ArrayList<CurrencyRate>();

		// filter
		for (CurrencyRate currencyRate : currencyRateList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = currencyRate.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(currencyRate));
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
				data.add(currencyRate);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new CurrencyRateSorter(sortField, sortOrder));
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
