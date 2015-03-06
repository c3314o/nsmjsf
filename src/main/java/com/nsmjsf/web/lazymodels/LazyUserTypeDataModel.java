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

import com.nsmjsf.web.datamodels.UserType;
import com.nsmjsf.web.sorters.UserTypeSorter;

public class LazyUserTypeDataModel extends LazyDataModel<UserType> {
	private static final Log log = LogFactory
			.getLog(LazyUserTypeDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserType> userTypeList;

	public LazyUserTypeDataModel(List<UserType> userTypeList) {
		this.userTypeList = userTypeList;
	}

	@Override
	public UserType getRowData(String rowKey) {
		for (UserType userType : userTypeList) {
			if (userType.getUserTypeId().toString().equalsIgnoreCase(rowKey))
				return userType;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserType object) {
		return object.getUserTypeId();
	}

	@Override
	public List<UserType> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserType> data = new ArrayList<UserType>();

		// filter
		for (UserType userType : userTypeList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userType.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String.valueOf(field.get(userType));
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
				data.add(userType);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserTypeSorter(sortField, sortOrder));
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
