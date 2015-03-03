

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

import com.nsmjsf.web.datamodels.UserBullionSales;
import com.nsmjsf.web.sorters.UserBullionSalesSorter;

public class LazyUserBullionSalesDataModel extends LazyDataModel<UserBullionSales> {
	private static final Log log = LogFactory
			.getLog(LazyUserBullionSalesDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserBullionSales> userBullionSalesList;

	public LazyUserBullionSalesDataModel(List<UserBullionSales> userBullionSalesList) {
		this.userBullionSalesList = userBullionSalesList;
	}

	@Override
	public UserBullionSales getRowData(String rowKey) {
		for (UserBullionSales userBullionSales : userBullionSalesList) {
			if (userBullionSales.getUserBullionSalesId().toString().equalsIgnoreCase(rowKey))
				return userBullionSales;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserBullionSales object) {
		return object.getUserBullionSalesId();
	}

	@Override
	public List<UserBullionSales> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserBullionSales> data = new ArrayList<UserBullionSales>();

		// filter
		for (UserBullionSales userBullionSales : userBullionSalesList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userBullionSales.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(userBullionSales));
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
				data.add(userBullionSales);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserBullionSalesSorter(sortField, sortOrder));
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

