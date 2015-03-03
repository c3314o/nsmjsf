

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

import com.nsmjsf.web.datamodels.UserInfo;
import com.nsmjsf.web.sorters.UserInfoSorter;

public class LazyUserInfoDataModel extends LazyDataModel<UserInfo> {
	private static final Log log = LogFactory
			.getLog(LazyUserInfoDataModel.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 8939496625458060791L;
	private List<UserInfo> userInfoList;

	public LazyUserInfoDataModel(List<UserInfo> userInfoList) {
		this.userInfoList = userInfoList;
	}

	@Override
	public UserInfo getRowData(String rowKey) {
		for (UserInfo userInfo : userInfoList) {
			if (userInfo.getUserInfoId().toString().equalsIgnoreCase(rowKey))
				return userInfo;
		}

		return null;
	}

	@Override
	public Object getRowKey(UserInfo object) {
		return object.getUserInfoId();
	}

	@Override
	public List<UserInfo> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {

		log.info("sortfield:" + sortField);
		List<UserInfo> data = new ArrayList<UserInfo>();

		// filter
		for (UserInfo userInfo : userInfoList) {
			boolean match = true;

			if (filters != null) {
				for (Iterator<String> it = filters.keySet().iterator(); it
						.hasNext();) {
					try {
						String filterProperty = it.next();
						Object filterValue = filters.get(filterProperty);
						Field field = userInfo.getClass().getDeclaredField(
								filterProperty);
						field.setAccessible(true);
						String fieldValue = String
								.valueOf(field.get(userInfo));
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
				data.add(userInfo);
			}
		}

		// sort
		if (sortField != null) {
			Collections.sort(data, new UserInfoSorter(sortField, sortOrder));
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

