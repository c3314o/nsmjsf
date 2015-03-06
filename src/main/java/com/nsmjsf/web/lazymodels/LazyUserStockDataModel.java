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

import com.nsmjsf.web.datamodels.UserStock;
import com.nsmjsf.web.sorters.UserStockSorter;

public class LazyUserStockDataModel extends LazyDataModel<UserStock> {
	private static final Log log = LogFactory
			.getLog(LazyUserStockDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserStock> userStockList;

	public LazyUserStockDataModel(List<UserStock> userStockList) {
		this.userStockList = userStockList;
	}

	@Override
	public UserStock getRowData(String rowKey) {
		for (UserStock userStock : userStockList) {
			if (userStock.getUserStockId().toString().equalsIgnoreCase(rowKey))
				return userStock;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserStock object) {
		return object.getUserStockId();
	}

	@Override
	public List<UserStock> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserStock> data = new ArrayList<UserStock>();

		// filter
		for (UserStock userStock : userStockList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userStock.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(userStock));
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
				data.add(userStock);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserStockSorter(sortField, sortOrder));
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
