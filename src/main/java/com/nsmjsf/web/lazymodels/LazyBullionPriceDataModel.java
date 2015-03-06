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

import com.nsmjsf.web.datamodels.BullionPrice;
import com.nsmjsf.web.sorters.BullionPriceSorter;

public class LazyBullionPriceDataModel extends LazyDataModel<BullionPrice> {
	private static final Log log = LogFactory
			.getLog(LazyBullionPriceDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<BullionPrice> bullionPriceList;

	public LazyBullionPriceDataModel(List<BullionPrice> bullionPriceList) {
		this.bullionPriceList = bullionPriceList;
	}

	@Override
	public BullionPrice getRowData(String rowKey) {
		for (BullionPrice bullionPrice : bullionPriceList) {
			if (bullionPrice.getBullionPriceId().toString()
					.equalsIgnoreCase(rowKey))
				return bullionPrice;
		}

		return null;
	}

	@Override
	public Object getRowKey(BullionPrice object) {
		return object.getBullionPriceId();
	}

	@Override
	public List<BullionPrice> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<BullionPrice> data = new ArrayList<BullionPrice>();

		// filter
		for (BullionPrice bullionPrice : bullionPriceList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = bullionPrice.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(bullionPrice));
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
				data.add(bullionPrice);
			}
		}

		// sort
		if (sortField != null) {
			Collections
					.sort(data, new BullionPriceSorter(sortField, sortOrder));
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
