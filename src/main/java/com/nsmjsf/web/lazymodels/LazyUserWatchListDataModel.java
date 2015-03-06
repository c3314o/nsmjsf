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

import com.nsmjsf.web.datamodels.UserWatchList;
import com.nsmjsf.web.sorters.UserWatchListSorter;

public class LazyUserWatchListDataModel extends LazyDataModel<UserWatchList> {
	private static final Log log = LogFactory
			.getLog(LazyUserWatchListDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserWatchList> userWatchListList;

	public LazyUserWatchListDataModel(List<UserWatchList> userWatchListList) {
		this.userWatchListList = userWatchListList;
	}

	@Override
	public UserWatchList getRowData(String rowKey) {
		for (UserWatchList userWatchList : userWatchListList) {
			if (userWatchList.getUserWatchListId().toString()
					.equalsIgnoreCase(rowKey))
				return userWatchList;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserWatchList object) {
		return object.getUserWatchListId();
	}

	@Override
	public List<UserWatchList> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserWatchList> data = new ArrayList<UserWatchList>();

		// filter
		for (UserWatchList userWatchList : userWatchListList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userWatchList.getClass()
								.getDeclaredField(filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(userWatchList));
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
				data.add(userWatchList);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data,
					new UserWatchListSorter(sortField, sortOrder));
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
