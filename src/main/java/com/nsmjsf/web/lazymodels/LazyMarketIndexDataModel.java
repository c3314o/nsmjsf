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

import com.nsmjsf.web.datamodels.MarketIndex;
import com.nsmjsf.web.sorters.MarketIndexSorter;

public class LazyMarketIndexDataModel extends LazyDataModel<MarketIndex> {
	private static final Log log = LogFactory
			.getLog(LazyMarketIndexDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<MarketIndex> marketIndexList;

	public LazyMarketIndexDataModel(List<MarketIndex> marketIndexList) {
		this.marketIndexList = marketIndexList;
	}

	@Override
	public MarketIndex getRowData(String rowKey) {
		for (MarketIndex marketIndex : marketIndexList) {
			if (marketIndex.getMarketIndexId().toString()
					.equalsIgnoreCase(rowKey))
				return marketIndex;
		}

		return null;
	}

	@Override
	public Object getRowKey(MarketIndex object) {
		return object.getMarketIndexId();
	}

	@Override
	public List<MarketIndex> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<MarketIndex> data = new ArrayList<MarketIndex>();

		// filter
		for (MarketIndex marketIndex : marketIndexList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = marketIndex.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(marketIndex));
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
				data.add(marketIndex);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MarketIndexSorter(sortField, sortOrder));
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
