

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

import com.nsmjsf.web.datamodels.Month;
import com.nsmjsf.web.sorters.MonthSorter;

public class LazyMonthDataModel extends LazyDataModel<Month> {
	private static final Log log = LogFactory
			.getLog(LazyMonthDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<Month> monthList;

	public LazyMonthDataModel(List<Month> monthList) {
		this.monthList = monthList;
	}

	@Override
	public Month getRowData(String rowKey) {
		for (Month month : monthList) {
			if (String.valueOf(month.getMonthId()).equalsIgnoreCase(rowKey))
				return month;
		}

		return null;
	}

	@Override
	public Object getRowKey(Month object) {
		return object.getMonthId();
	}

	@Override
	public List<Month> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<Month> data = new ArrayList<Month>();

		// filter
		for (Month month : monthList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = month.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(month));
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
				data.add(month);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new MonthSorter(sortField, sortOrder));
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

