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

import com.nsmjsf.web.datamodels.User;
import com.nsmjsf.web.sorters.UserSorter;

public class LazyUserDataModel extends LazyDataModel<User> {
	private static final Log log = LogFactory.getLog(LazyUserDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<User> userList;

	public LazyUserDataModel(List<User> userList) {
		this.userList = userList;
	}

	@Override
	public User getRowData(String rowKey) {
		for (User user : userList) {
			if (user.getUserId().toString().equalsIgnoreCase(rowKey))
				return user;
		}

		return null;
	}

	@Override
	public Object getRowKey(User object) {
		return object.getUserId();
	}

	@Override
	public List<User> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<User> data = new ArrayList<User>();

		// filter
		for (User user : userList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = user.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(user));
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
				data.add(user);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserSorter(sortField, sortOrder));
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
