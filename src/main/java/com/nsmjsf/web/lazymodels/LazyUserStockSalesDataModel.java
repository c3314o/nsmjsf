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

import com.nsmjsf.web.datamodels.UserStockSales;
import com.nsmjsf.web.sorters.UserStockSalesSorter;

public class LazyUserStockSalesDataModel extends LazyDataModel<UserStockSales> {
	private static final Log log = LogFactory
			.getLog(LazyUserStockSalesDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserStockSales> userStockSalesList;

	public LazyUserStockSalesDataModel(List<UserStockSales> userStockSalesList) {
		this.userStockSalesList = userStockSalesList;
	}

	@Override
	public UserStockSales getRowData(String rowKey) {
		for (UserStockSales userStockSales : userStockSalesList) {
			if (userStockSales.getUserStockSalesId().toString()
					.equalsIgnoreCase(rowKey))
				return userStockSales;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserStockSales object) {
		return object.getUserStockSalesId();
	}

	@Override
	public List<UserStockSales> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserStockSales> data = new ArrayList<UserStockSales>();

		// filter
		for (UserStockSales userStockSales : userStockSalesList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userStockSales.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(userStockSales));
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
				data.add(userStockSales);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserStockSalesSorter(sortField,
					sortOrder));
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
