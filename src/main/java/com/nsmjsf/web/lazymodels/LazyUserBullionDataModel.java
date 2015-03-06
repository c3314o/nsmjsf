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

import com.nsmjsf.web.datamodels.UserBullion;
import com.nsmjsf.web.sorters.UserBullionSorter;

public class LazyUserBullionDataModel extends LazyDataModel<UserBullion> {
	private static final Log log = LogFactory
			.getLog(LazyUserBullionDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserBullion> userBullionList;

	public LazyUserBullionDataModel(List<UserBullion> userBullionList) {
		this.userBullionList = userBullionList;
	}

	@Override
	public UserBullion getRowData(String rowKey) {
		for (UserBullion userBullion : userBullionList) {
			if (userBullion.getUserBullionId().toString()
					.equalsIgnoreCase(rowKey))
				return userBullion;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserBullion object) {
		return object.getUserBullionId();
	}

	@Override
	public List<UserBullion> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserBullion> data = new ArrayList<UserBullion>();

		// filter
		for (UserBullion userBullion : userBullionList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userBullion.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field
								.get(userBullion));
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
				data.add(userBullion);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserBullionSorter(sortField, sortOrder));
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
