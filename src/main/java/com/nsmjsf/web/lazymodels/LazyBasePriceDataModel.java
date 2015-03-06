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

import com.nsmjsf.web.datamodels.BasePrice;
import com.nsmjsf.web.sorters.BasePriceSorter;

public class LazyBasePriceDataModel extends LazyDataModel<BasePrice> {
	private static final Log log = LogFactory
			.getLog(LazyBasePriceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<BasePrice> basePriceList;

	public LazyBasePriceDataModel(List<BasePrice> basePriceList) {
		this.basePriceList = basePriceList;
	}

	@Override
	public BasePrice getRowData(String rowKey) {
		for (BasePrice basePrice : basePriceList) {
			if (basePrice.getBasePriceId().toString().equalsIgnoreCase(rowKey))
				return basePrice;
		}

		return null;
	}

	@Override
	public Object getRowKey(BasePrice object) {
		return object.getBasePriceId();
	}

	@Override
	public List<BasePrice> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<BasePrice> data = new ArrayList<BasePrice>();

		// filter
		for (BasePrice basePrice : basePriceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = basePrice.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(basePrice));
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
				data.add(basePrice);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new BasePriceSorter(sortField, sortOrder));
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
