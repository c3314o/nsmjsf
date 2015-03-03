

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

import com.nsmjsf.web.datamodels.CurrencyType;
import com.nsmjsf.web.sorters.CurrencyTypeSorter;

public class LazyCurrencyTypeDataModel extends LazyDataModel<CurrencyType> {
	private static final Log log = LogFactory
			.getLog(LazyCurrencyTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<CurrencyType> currencyTypeList;

	public LazyCurrencyTypeDataModel(List<CurrencyType> currencyTypeList) {
		this.currencyTypeList = currencyTypeList;
	}

	@Override
	public CurrencyType getRowData(String rowKey) {
		for (CurrencyType currencyType : currencyTypeList) {
			if (currencyType.getCurrencyTypeId().toString().equalsIgnoreCase(rowKey))
				return currencyType;
		}

		return null;
	}

	@Override
	public Object getRowKey(CurrencyType object) {
		return object.getCurrencyTypeId();
	}

	@Override
	public List<CurrencyType> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<CurrencyType> data = new ArrayList<CurrencyType>();

		// filter
		for (CurrencyType currencyType : currencyTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = currencyType.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(currencyType));
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
				data.add(currencyType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new CurrencyTypeSorter(sortField, sortOrder));
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

