

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

import com.nsmjsf.web.datamodels.UserWatchListStock;
import com.nsmjsf.web.sorters.UserWatchListStockSorter;

public class LazyUserWatchListStockDataModel extends LazyDataModel<UserWatchListStock> {
	private static final Log log = LogFactory
			.getLog(LazyUserWatchListStockDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserWatchListStock> userWatchListStockList;

	public LazyUserWatchListStockDataModel(List<UserWatchListStock> userWatchListStockList) {
		this.userWatchListStockList = userWatchListStockList;
	}

	@Override
	public UserWatchListStock getRowData(String rowKey) {
		for (UserWatchListStock userWatchListStock : userWatchListStockList) {
			if (userWatchListStock.getUserWatchListStockId().toString().equalsIgnoreCase(rowKey))
				return userWatchListStock;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserWatchListStock object) {
		return object.getUserWatchListStockId();
	}

	@Override
	public List<UserWatchListStock> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserWatchListStock> data = new ArrayList<UserWatchListStock>();

		// filter
		for (UserWatchListStock userWatchListStock : userWatchListStockList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userWatchListStock.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(userWatchListStock));
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
				data.add(userWatchListStock);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserWatchListStockSorter(sortField, sortOrder));
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

