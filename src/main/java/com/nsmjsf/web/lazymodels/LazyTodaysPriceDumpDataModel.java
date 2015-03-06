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

import com.nsmjsf.web.datamodels.TodaysPriceDump;
import com.nsmjsf.web.sorters.TodaysPriceDumpSorter;

public class LazyTodaysPriceDumpDataModel extends
		LazyDataModel<TodaysPriceDump> {
	private static final Log log = LogFactory
			.getLog(LazyTodaysPriceDumpDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<TodaysPriceDump> todaysPriceDumpList;

	public LazyTodaysPriceDumpDataModel(
			List<TodaysPriceDump> todaysPriceDumpList) {
		this.todaysPriceDumpList = todaysPriceDumpList;
	}

	@Override
	public TodaysPriceDump getRowData(String rowKey) {
		for (TodaysPriceDump todaysPriceDump : todaysPriceDumpList) {
			if (todaysPriceDump.getTodaysPriceDumpId().toString()
					.equalsIgnoreCase(rowKey))
				return todaysPriceDump;
		}

		return null;
	}

	@Override
	public Object getRowKey(TodaysPriceDump object) {
		return object.getTodaysPriceDumpId();
	}

	@Override
	public List<TodaysPriceDump> load(int first, int pageSize,
			String sortField, SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<TodaysPriceDump> data = new ArrayList<TodaysPriceDump>();

		// filter
		for (TodaysPriceDump todaysPriceDump : todaysPriceDumpList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = todaysPriceDump.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(todaysPriceDump));
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
				data.add(todaysPriceDump);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new TodaysPriceDumpSorter(sortField,
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
